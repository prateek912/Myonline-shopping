package com.spring.backend.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"com.spring.backend"})
@EnableTransactionManagement
public class HibernateConfig {

	// For loggin
	private static final Logger logger = LoggerFactory.getLogger(HibernateConfig.class);
	
	private final static String dbUrl="jdbc:h2:tcp://localhost/~/Myonlineshopping";
	private final static String dbDialect="org.hibernate.dialect.H2Dialect";
	private final static String dbDriver="org.h2.Driver";
	private final static String username="prtk";
	private final static String password="1234";
	
	
	// DataSource bean for providing connection information
	@Bean("dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return dataSource;
	}
	
	// For getting SessionFactory Object of Hibernate
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		// Providing Hibernate Properties
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.spring.backend.dto");
		
		return builder.buildSessionFactory();
	}

	// Hibernate Properties
	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect",dbDialect);
		props.put("hibernate.hbm2ddl.auto","update");
		if(logger.isDebugEnabled()) {
			props.put("hibernate.show_sql","true");
			props.put("hibernate.format_sql","true");
		}
		
		return props;
	}
	
	// Transaction Management Bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory factory) {
		HibernateTransactionManager tm = new HibernateTransactionManager(factory);
		return tm;
	}
}
