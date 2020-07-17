package com.kingc.assessstandardtool.tool.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class UtilsEnvironment {
	/**
	 * 获取Environment中数据源为Properties的所有属性
	 * @param environment
	 * @return
	 */
	public static Properties getProperties(Environment environment){
		//检验environment是否为StandardEnvironment实例
		if(environment == null || !(environment instanceof StandardEnvironment)){
			return null ; 
		}
		
		Properties properties = new Properties() ;
		StandardEnvironment servletEnvironment = (StandardEnvironment)environment ;
		MutablePropertySources mutablePropertySources = servletEnvironment.getPropertySources() ;
		//迭代各个不同的数据源数据
		Iterator<PropertySource<?>> propertyIterator = mutablePropertySources.iterator() ;
		//获取所有的源数据标签
		while (propertyIterator.hasNext()) {
			PropertySource<?> propertySource = propertyIterator.next() ;
			//只获取Properties类型的数据
			if(propertySource!=null  && propertySource.getSource() !=null ){
				if(propertySource.getSource() instanceof Properties){
					properties.putAll((Properties)propertySource.getSource());
				}else if(propertySource instanceof CompositePropertySource){
					//dispatcherservlet获取的properties文件的内容为CompositePropertySource
					CompositePropertySource compositePropertySource = (CompositePropertySource)propertySource ;
					for (String key : compositePropertySource.getPropertyNames()) {
						properties.put(key , compositePropertySource.getProperty(key)) ;
					}
				}
			}
		}

		//重新由environment获取key对应的值，解决加载多个配置文件带有相同key值的问题
		for (Object itemKey : properties.keySet()) {
			properties.put(itemKey , environment.getProperty(itemKey.toString())) ;
		}
		
		return properties ;
	}
	/**
	 * 获取environment中以paramPrefix为前缀的属性
	 * @param environment
	 * @param paramPrefix
	 * @return
	 */
	public static Map<String, String> getPropertiesByPrefix(Environment environment , String paramPrefix){
		if(StringUtils.isBlank(paramPrefix) || environment ==null){
			return null ;
		}

		//后缀以“.”开头
		if(!paramPrefix.endsWith(".")){
			paramPrefix += "." ;
		}
		
		Properties properties = UtilsEnvironment.getProperties(environment) ;
		
		Map<String, String> param = null ;
		for (Map.Entry<Object, Object> property : properties.entrySet()) {
			String key = property.getKey().toString() ;
			if(!StringUtils.startsWith( key, paramPrefix )){
				continue ;
			}
			
			if(param==null){
				param = new HashMap<String,String>() ;
			}
			//将paramPrefix后的字符作为key存储
			param.put(key.substring(paramPrefix.length()) , property.getValue().toString()) ;
		}
		return param ;
	}
}
