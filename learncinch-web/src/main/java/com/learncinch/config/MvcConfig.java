/**
 * 
 */
package com.learncinch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The Class MvcConfig: Web relaated configuration
 *
 * @author Mandeep
 */
@Configuration
// @EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    // private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
    // "classpath:/META-INF/resources/", "classpath:/resources/",
    // "classpath:/static/", "classpath:/public/" };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	// registry.addViewController("/home").setViewName("home");
	// registry.addViewController("/").setViewName("login");
	// registry.addRedirectViewController("/", "/user/timeEntry");
	registry.addRedirectViewController("/", "/login");
	// registry.addViewController("/hello").setViewName("hello");
	registry.addViewController("/login").setViewName("login");
	// registry.addViewController("/user/timeEntry").setViewName("time-entry/list");

    }

    /*
     * @Override public void addResourceHandlers(ResourceHandlerRegistry
     * registry) { registry.addResourceHandler("/**").addResourceLocations(
     * CLASSPATH_RESOURCE_LOCATIONS); }
     */

//    @Bean
//    public LocaleResolver localeResolver() {
//	SessionLocaleResolver slr = new SessionLocaleResolver();
//	slr.setDefaultLocale(Locale.US);
//	return slr;
//    }

//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//	LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//	lci.setParamName("lang");
//	return lci;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//	registry.addInterceptor(localeChangeInterceptor());
//    }

//    @Bean
//    public DynamicsDialect dynamicsDialect() {
//	return new DynamicsDialect();
//    }

    // @Bean
    // public SpringTemplateEngine templateEngine() {
    // SpringTemplateEngine engine = new SpringTemplateEngine();
    // final Set<IDialect> dialects = new HashSet<IDialect>();
    // dialects.add(dynamicsDialect());
    // engine.setDialects(dialects);
    // return engine;
    // }

//    @Bean
//    public JasperReportsViewResolver getJasperReportsViewResolver() {
//
//	JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//	resolver.setPrefix("classpath:jasper/");
//	resolver.setSuffix(".jrxml");
//	// resolver.setReportDataKey("datasource");
//	resolver.setViewNames("*rpt_*");
//	// resolver.setJdbcDataSource(datasource);
//	resolver.setViewClass(JasperReportsMultiFormatView.class);
//	resolver.setOrder(0);
//	return resolver;
//    }
}
