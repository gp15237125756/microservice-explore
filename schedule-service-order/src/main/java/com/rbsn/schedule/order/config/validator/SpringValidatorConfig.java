package com.rbsn.schedule.order.config.validator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@EnableAutoConfiguration
public class SpringValidatorConfig {

	public MethodValidationPostProcessor MethodValidationPostProcessor(){
		return new MethodValidationPostProcessor();
	}
	
}
