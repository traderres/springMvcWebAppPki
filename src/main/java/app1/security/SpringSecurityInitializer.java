package app1.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


/*****************************************************************************************
 * Created by adam on 11/12/2015.
 *
 * SpringSecurityInitializer class:  This is used to initialize Spring Security
 *
 * NOTE:  The above code is the *SAME* as defining this in the web.xml
 *    <filter>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 *    </filter>
 *
 *    <filter-mapping>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <url-pattern>/*</url-pattern>
 *      <dispatcher>ERROR</dispatcher>
 *      <dispatcher>REQUEST</dispatcher>
 *    </filter-mapping>
 *****************************************************************************************/
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer
{

}