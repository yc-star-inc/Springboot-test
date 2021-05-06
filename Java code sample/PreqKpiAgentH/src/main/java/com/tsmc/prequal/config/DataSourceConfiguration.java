package com.tsmc.prequal.config;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.ClassUtils;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConfigurationProperties("datasource.local")
@EnableJpaRepositories(
     basePackages = "com.tsmc.prequal",
     transactionManagerRef = "localTransactionManager",
     entityManagerFactoryRef = "localEntityManagerFactory"
 )
public class DataSourceConfiguration {
	@Autowired
	private Environment env;
	
	@Bean
	JdbcTemplate jdbcTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
	    // extract this 4 parameters using your own logic
	    final String driverClassName = "oracle.jdbc.OracleDriver";
	    final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
	    final String username = "ppmsdm";
	    final String password = "ppmsdm";
	    // Build dataSource manually:
	    final Class<?> driverClass = ClassUtils.resolveClassName(driverClassName, this.getClass().getClassLoader());
	    final Driver driver = (Driver) ClassUtils.getConstructorIfAvailable(driverClass).newInstance();
	    final DataSource dataSource = new SimpleDriverDataSource(driver, jdbcUrl, username, password);
	    // or using DataSourceBuilder:
	    //final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).username(username).password(password).build();
	    // and make the jdbcTemplate
	    return new JdbcTemplate(dataSource);
	}
	// https://www.baeldung.com/spring-jdbc-jdbctemplate
	// https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/309798/
	
//	@Bean
//	NamedParameterJdbcTemplate namedJdbcTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
//	    // extract this 4 parameters using your own logic
//	    final String driverClassName = "oracle.jdbc.OracleDriver";
//	    final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
//	    final String username = "ppmsdm";
//	    final String password = "ppmsdm";
//	    // Build dataSource manually:
//	    final Class<?> driverClass = ClassUtils.resolveClassName(driverClassName, this.getClass().getClassLoader());
//	    final Driver driver = (Driver) ClassUtils.getConstructorIfAvailable(driverClass).newInstance();
//	    final DataSource dataSource = new SimpleDriverDataSource(driver, jdbcUrl, username, password);
//	    // or using DataSourceBuilder:
//	    //final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).username(username).password(password).build();
//	    // and make the jdbcTemplate
//	    return new NamedParameterJdbcTemplate(dataSource);
//	}
}
