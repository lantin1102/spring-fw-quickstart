package com.lantin.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created on 2021/07/01/16:51 周四
 *
 * @author Lantin
 */
@Configuration
@MapperScan("com.lantin.spring.mapper")
@Slf4j
public class JdbcConfig {
    @Value("${jdbc.mysql.url}")
    private String url;
    @Value("${jdbc.mysql.username}")
    private String username;
    @Value("${jdbc.mysql.password}")
    private String password;
    @Value("${jdbc.mysql.driver-class-name}")
    private String driverClass;

    @Bean("druidDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager(@Qualifier("druidDataSource") DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //属性配置
        //数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //别名映射
        sqlSessionFactoryBean.setTypeAliasesPackage("com.lantin.spring.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations((resolver.getResources("classpath:mapper/*Mapper.xml")));
        } catch (IOException e) {
            log.error("读取mapper.xml文件异常");
        }

        //映射文件不需要配置了，我们采用的是注解mybatis。默认包扫描
        //驼峰映射
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        //配置分页拦截器
        //两种方法都可以
        //sqlSessionFactoryBean.setPlugins(initPageInterceptor());
        // configuration.addInterceptor(initPageInterceptor());

        sqlSessionFactoryBean.setConfiguration(configuration);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            //因为sqlSessionFactoryBean采用的是spring创建对象的第四种方式 FactoryBean工厂格式。调用getObject创建
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }
}
