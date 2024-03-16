package com.military.cloud.common.database.interceptor;


import com.military.cloud.common.database.annotations.DistributedId;
import com.military.cloud.common.database.model.BaseModelField;
import com.military.cloud.common.core.exception.BizException;
import com.military.cloud.common.core.response.ResponseEnum;
import com.military.cloud.common.core.response.ServerResponseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 分布式id生成
 * 1. 分布式id是通过美团的leaf生成的，是需要与hxnmall-leaf数据库 当中 leaf_alloc表中 biz_tag字段相关联的key
 * 2. 为了注入分布式id更加方便，规定为DistributedId为注解的字段加入该字段
 *
 * @author FrozenWatermelon
 * @date 2020/9/9
 * @see DistributedId
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class GeneratedKeyInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(GeneratedKeyInterceptor.class);
    /**
     * 单个插入名称
     */
    private static final String INSERT = "insert";

    /**
     * 单个插入名称
     */
    private static final String SAVE = "save";

    /**
     * 批量插入名称
     */
    private static final String BATCH_INSERT = "insertBatch";

    /**
     * 批量插入名称
     */
    private static final String BATCH_SAVE = "saveBatch";

   /* @Autowired
    private SegmentFeignClient segmentFeignClient;*/

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 不是 insert 类型的跳过
        if (SqlCommandType.INSERT != sqlCommandType) {
            return invocation.proceed();
        }

        int one = 1;

        // 获取参数
        Object parameter = invocation.getArgs()[one];

        // 找数据库中的对象
        Object dbObject = findDbObject(parameter);

        if (dbObject == null) {
            return invocation.proceed();
        }

        // 插入
        if (mappedStatement.getId().contains(INSERT) || mappedStatement.getId().contains(SAVE)) {
            generatedKey(dbObject);
        } else if (mappedStatement.getId().contains(BATCH_INSERT) || mappedStatement.getId().contains(BATCH_SAVE)) {
            // 获取批量查询的参数并生成主键
            if (parameter instanceof HashMap) {
                Object list = ((Map) parameter).get("list");
                if (list instanceof ArrayList) {
                    for (Object o : (ArrayList) list) {
                        generatedKey(dbObject);
                    }
                }
            }
        }

        return invocation.proceed();
    }

    protected BaseModelField findDbObject(Object parameterObj) {
        if (parameterObj instanceof BaseModelField) {
            return (BaseModelField) parameterObj;
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof BaseModelField) {
                    return (BaseModelField) val;
                }
            }
        }
        return null;
    }

    /**
     * 获取私有成员变量 ,并设置主键
     *
     * @param parameter 参数
     */
    private void generatedKey(Object parameter) throws Throwable {

        Field[] fieldList = parameter.getClass().getDeclaredFields();

        for (Field field : fieldList) {

            if (!field.getType().isAssignableFrom(Long.class)) {
                continue;
            }

            DistributedId annotation = field.getAnnotation(DistributedId.class);
            if (annotation == null) {
                continue;
            }

            field.setAccessible(true);
            if (field.get(parameter) != null) {
                break;
            }
            ServerResponseEntity<Long> segmentIdResponseEntity = null;//segmentFeignClient.getSegmentId(annotation.value());
            if (segmentIdResponseEntity.isSuccess()) {
                // 这里设置分布式id
                field.set(parameter, segmentIdResponseEntity.getData());
            } else {
                logger.error("can't get distributed id !!!! ");
                throw new BizException(ResponseEnum.EXCEPTION);
            }
        }
    }

    /**
     * Plugin.wrap生成拦截代理对象
     */
    @Override
    public Object plugin(Object o) {
        if (o instanceof Executor) {
            return Plugin.wrap(o, this);
        } else {
            return o;
        }
    }

}

