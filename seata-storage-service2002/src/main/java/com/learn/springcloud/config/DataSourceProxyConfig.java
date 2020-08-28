package com.learn.springcloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 使用seata对数据源进行代理
 * @ClassName: DataSourceProxyConfig
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 16:30
 * History:
 * @<version> 1.0
 */
//@Configuration
public class DataSourceProxyConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    /**
     *  从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
     *  原生datasource前缀取"spring.datasource"
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }


    /**
     * 构造datasource代理对象，替换原来的datasource
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource){
        return  new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean bean =new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy);
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(patternResolver.getResources(mapperLocations));
        bean.setTransactionFactory(new SpringManagedTransactionFactory());
        return  bean.getObject();
    }
}
