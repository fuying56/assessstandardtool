package com.kingc.assessstandardtool.mybatis.config;


import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.dialect.helper.MySqlDialect;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import com.kingc.assessstandardtool.mybatis.base.dao.MybatisBaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;


import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis配置文件
 *
 * @author Administrator
 */
@Configuration
public class MybatisConfig {

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, Environment environment) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //获取mybatis映射mapper文件路径,例：classpath:com/framework/sample/mapper/*Mapper.xml;classpath:com/framework/sample/mapper2/*Mapper.xml
        Map<String, Resource> resourceMap = Maps.newHashMap();

        List<String> locations = Splitter.on(';').trimResults().omitEmptyStrings().splitToList(environment.getRequiredProperty("mybatis.mapper.locations"));
        if (null != locations) {
            for (String location : locations) {
                Resource[] tmpResources = resolver.getResources(location);
                for (Resource tmpResource : tmpResources) {
                    if (tmpResource.exists()) {
                        resourceMap.put(tmpResource.getURI().toString(), tmpResource);
                    }
                }
            }
        }
        //这是mapper文件扫描路径
        sessionFactory.setMapperLocations(resourceMap.values().toArray(new Resource[]{}));
        //设置插件
        sessionFactory.setPlugins(new PageInterceptor[]{initPagePlugin()});
        //包别名
        sessionFactory.setTypeAliasesPackage(environment.getProperty("mybatis.alias.package", "com.framework"));
        //开启后将在启动时检查设定的parameterMap,resultMap是否存在，是否合法
        sessionFactory.setFailFast(true);
        return sessionFactory.getObject();
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(environment.getProperty("mybatis.mapper.package"));
        mapperScannerConfigurer.setMarkerInterface(MybatisBaseMapper.class);
        return mapperScannerConfigurer;
    }

    /**
     * 初始化分页插件
     */
    protected PageInterceptor initPagePlugin() {
        //初始化mybatis的分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        //设置sql语言
        properties.put("dialect", new MySqlDialect());
        //不进行合理化查询优化
        properties.put("reasonable", "false");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
