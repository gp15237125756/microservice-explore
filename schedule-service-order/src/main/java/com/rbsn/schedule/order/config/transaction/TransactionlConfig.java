package com.rbsn.schedule.order.config.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableAutoConfiguration
@Configuration
public class TransactionlConfig {

	@Autowired
	@Qualifier("druidDataSource")
	private DataSource druidDataSource;
	
 	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(druidDataSource);
    }
}
