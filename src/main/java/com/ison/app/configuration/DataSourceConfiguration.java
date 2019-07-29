
package com.ison.app.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ison.app.constants.AppicationConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

	@Autowired
	private Environment environment;

	@Bean(destroyMethod = "close", name = AppicationConstants.FIRST_DATA_SOURCE_BEAN_NAME)
	public DataSource firstDataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(environment.getRequiredProperty("db1.datasource.driver-class-name"));
		dataSourceConfig.setJdbcUrl(environment.getRequiredProperty("db1.datasource.url"));
		dataSourceConfig.setUsername(environment.getRequiredProperty("db1.datasource.username"));
		dataSourceConfig.setPassword(environment.getRequiredProperty("db1.datasource.password"));

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	public SessionFactory getSessionFactory(DataSource firstDataSource) {

		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(firstDataSource);

		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.ison.app.model");

		return builder.buildSessionFactory();

	}

	private Properties getHibernateProperties() {

		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	/*
	 * public JpaVendorAdapter firstJpaVendorAdapter() { return new
	 * HibernateJpaVendorAdapter(); }
	 * 
	 * private Properties commonJpaProperties() { Properties properties = new
	 * Properties(); properties.put(AppicationConstants.HIBERNATE_DIALECT,
	 * environment.getRequiredProperty("db1.jpa.properties.hibernate.dialect"));
	 * properties.put(AppicationConstants.HIBERNATE_SHOW_SQL,
	 * environment.getRequiredProperty("db1.jpa.show-sql"));
	 * properties.put(AppicationConstants.HIBERNATE_FORMAT_SQL,
	 * environment.getRequiredProperty(AppicationConstants.HIBERNATE_FORMAT_SQL));
	 * properties.put("hibernate.ddl-auto",
	 * environment.getRequiredProperty("db1.jpa.hibernate.ddl-auto"));
	 * 
	 * return properties; }
	 * 
	 * @Bean(name = AppicationConstants.FIRST_ENTITY_MANAGER) public
	 * LocalContainerEntityManagerFactoryBean firstEntityManagerFactory() { try {
	 * LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
	 * = new LocalContainerEntityManagerFactoryBean();
	 * localContainerEntityManagerFactoryBean.setDataSource(firstDataSource());
	 * localContainerEntityManagerFactoryBean.setPackagesToScan(AppicationConstants.
	 * FIRST_MODAL_PACKAGE); localContainerEntityManagerFactoryBean
	 * .setPersistenceUnitName(AppicationConstants.FIRST_PERSISTENCE_UNIT_NAME);
	 * localContainerEntityManagerFactoryBean.setJpaVendorAdapter(
	 * firstJpaVendorAdapter());
	 * localContainerEntityManagerFactoryBean.setJpaProperties(commonJpaProperties()
	 * );
	 * 
	 * return localContainerEntityManagerFactoryBean;
	 * 
	 * } catch (Exception e) {
	 * logger.error("DSConfiguration.LocalContainerEntityManagerFactoryBean(): " +
	 * e.getMessage()); } return new LocalContainerEntityManagerFactoryBean(); }
	 * 
	 * @Bean(name = AppicationConstants.FIRST_TRANSACTION_MANAGER) public
	 * PlatformTransactionManager firstTransactionManager(
	 * javax.persistence.EntityManagerFactory firstEntityManager) { try { return new
	 * JpaTransactionManager(firstEntityManager); } catch (Exception e) {
	 * logger.error("DSConfiguration.firstTransactionManager(): " + e.getMessage());
	 * } return new JpaTransactionManager(); }
	 * 
	 * @Bean(name = AppicationConstants.FIRST_JDBC_TEMPLATE) public JdbcTemplate
	 * firstJdbcTemplate(DataSource firstDataSource) { JdbcTemplate jdbcTemplate =
	 * new JdbcTemplate(firstDataSource);
	 * jdbcTemplate.setResultsMapCaseInsensitive(true); return jdbcTemplate; }
	 */
}
