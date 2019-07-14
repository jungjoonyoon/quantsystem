package com.themachine.quantsystem.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DatabaseConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);
	
	/**
	 * @return
	 * @throws SQLException 
	 */
	@Bean(name="dataSourceTransactionManager")
   	public DataSourceTransactionManager transactionManager(DataSource dataSource) throws SQLException {
   		return new DataSourceTransactionManager(dataSource);
   	}
    
    /**
     * @return
     * @throws Exception
     */
    @Bean(name="sessionFactoryBean")
   	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
    	SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
   		sessionFactoryBean.setDataSource(dataSource);
   		sessionFactoryBean.setTypeAliasesPackage("com.themachine.quantsystem.entity");
   		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("/mappers/*.xml"));
   		return sessionFactoryBean;
   	}
    
	/**
	 * @return
	 */
	@Bean
   	public MapperScannerConfigurer mapperScan(){
   		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
   		mapperScannerConfigurer.setBasePackage("**.mapper");
   		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sessionFactoryBean");
   		
   		return mapperScannerConfigurer;
   	}

}