package com.rbsn.schedule.order.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid monitor
 * @author Cruz
 * @Date 2017-6-6
 * @version 01-00
 * 
 */
@Configuration
public class DruidStatConfig {
 
	
	@Bean
	public ServletRegistrationBean servletDruidStat(){
	   ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//白名单：
       servletRegistrationBean.addInitParameter("allow","127.0.0.1");
       servletRegistrationBean.addInitParameter("allow","192.168.1.87");
       //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
       servletRegistrationBean.addInitParameter("deny","");
       //登录查看信息的账号密码.
       //servletRegistrationBean.addInitParameter("loginUsername","admin");

       //servletRegistrationBean.addInitParameter("loginPassword","admin");
       //是否能够重置数据.
       servletRegistrationBean.addInitParameter("resetEnable","true");

	   return servletRegistrationBean;
	   
	}
	
	@Bean
    public FilterRegistrationBean druidStatFilter(){
       FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
       //添加过滤规则.
       filterRegistrationBean.addUrlPatterns("/*");
       filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
       return filterRegistrationBean;

    }
}
