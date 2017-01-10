package com.egoshard.service.core.locale;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 */
@EnableSwagger2
@SpringBootApplication()
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/locales/**"))
        .build();
  }

  /**
   * API Info as it appears on the swagger-ui page
   */
  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
        "Core Locale API",
        "Common core API functionality for interacting with Locale region information.",
        "",
        "",
        new Contact("", "", ""),
        "",
        ""
    );
    return apiInfo;
  }

  /*
     * Configure ContentNegotiatingViewResolver
     */
//  @Bean
//  public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
//    ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
//    ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
//    contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
//    contentNegotiationManager.setDefaultContentType(MediaType.APPLICATION_JSON);
//    contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
//    contentViewResolver.setDefaultViews(Collections.singletonList(new MappingJackson2JsonView()));
//    return contentViewResolver;
//  }

//  @Bean
//  @ConditionalOnBean(ViewResolver.class)
//  @ConditionalOnMissingBean(name = "viewResolver", value = ContentNegotiatingViewResolver.class)
//  public ContentNegotiatingViewResolver viewResolver(BeanFactory beanFactory) {
//    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//    resolver.setContentNegotiationManager(
//        beanFactory.getBean(ContentNegotiationManager.class));
//    // ContentNegotiatingViewResolver uses all the other view resolvers to locate
//    // a view so it should have a high precedence
//    resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    return resolver;
//  }

}
