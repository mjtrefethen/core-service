package com.egoshard.service.core.locale;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableTransactionManagement
@EnableJpaRepositories("com.egoshard.service.core.locale")
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
    return new ApiInfo(
        "Core Locale API",
        "Common core API functionality for interacting with Locale region information.",
        "",
        "",
        new Contact("", "", ""),
        "",
        ""
    );
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://core-locale-mariadb:3306/core_db");
    dataSource.setUsername("core_db_user");
    dataSource.setPassword("password");

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    entityManagerFactoryBean.setPackagesToScan("com.egoshard.service.core.locale");

    entityManagerFactoryBean.setJpaProperties(hibProperties());

    return entityManagerFactoryBean;
  }

  private Properties hibProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    properties.put("hibernate.show_sql", true);
    return properties;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

}
