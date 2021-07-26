package com.yifan.settlement;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
public class SettlementApplication {
	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(SettlementApplication.class, args);
	}

	@Bean
	public SpringLiquibase liquibase(){
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:database/changelog.xml");
		liquibase.setDatabaseChangeLogLockTable("settlement_liquibase_changeloglocktable");
		liquibase.setDatabaseChangeLogTable("settlement_liquibase_changelogtable");
		liquibase.setDataSource(dataSource);
		return liquibase;
	}

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yifan.settlement.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiEndPointInfo());
	}

	private ApiInfo apiEndPointInfo() {
		return new ApiInfoBuilder().title("Client Settlement API")
				.description("APIs to retrieve and create settlement Msg")
				.version("1.0")
				.build();
	}
}
