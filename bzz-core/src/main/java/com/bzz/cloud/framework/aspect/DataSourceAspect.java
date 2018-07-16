package com.bzz.cloud.framework.aspect;

import com.bzz.cloud.framework.annotations.DataBaseSourceTarget;
import com.bzz.cloud.framework.dynamicdatasource.DataSourceContextHolder;
import com.bzz.cloud.framework.transactionmanager.DynamicTransactionManagerConfig;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Order(-999)
@Component
public class DataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	@Before("execution( * com.bzz.cloud.*.service.*(..)) || @annotation(com.bzz.cloud.framework.annotations.DataBaseSourceTarget)")
	public void before(JoinPoint joinPoint) {
		
		
		logger.info("当前数据源为："+DataSourceContextHolder.getDataSource());
		Object target = joinPoint.getTarget();
		String method = joinPoint.getSignature().getName();
		
		Class<?> classz = target.getClass();
		Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
		
		try {
			Method m = classz.getMethod(method, parameterTypes);
			DataBaseSourceTarget dataSource = null;
			
			if (null != m && m.isAnnotationPresent(DataBaseSourceTarget.class)) {
				dataSource = m.getAnnotation(DataBaseSourceTarget.class);
				
			}else if(classz.isAnnotationPresent(DataBaseSourceTarget.class)){
				dataSource = classz.getAnnotation(DataBaseSourceTarget.class);
			}
			if (dataSource != null) {
				DataSourceContextHolder.setDataSource(dataSource.value());
			}else{
				DataSourceContextHolder.setDataSource("dataSourceA");
			}
			logger.info("数据源成功切换为："+ dataSource.value());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Before("execution( * com.bzz.cloud.*.service.*(..)) || @annotation(com.bzz.cloud.framework.annotations.DataBaseSourceTarget)")
	public void after(JoinPoint joinPoint) {
		String dataSource = DataSourceContextHolder.getDataSource();
		if(StringUtils.isNotBlank(dataSource)){
			DataSourceContextHolder.removeDataSource();
		}

	}
	
}
