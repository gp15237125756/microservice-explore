package com.rbsn.schedule.order.config.apiDoc;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class RestSecurityConfig  extends WebSecurityConfigurerAdapter {
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	  }
}
