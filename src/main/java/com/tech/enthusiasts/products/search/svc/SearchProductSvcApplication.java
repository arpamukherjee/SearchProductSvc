package com.tech.enthusiasts.products.search.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableTransactionManagement
@EnableScheduling
public class SearchProductSvcApplication{
	public static void main(String[] args) {
		SpringApplication.run(SearchProductSvcApplication.class, args);
	}
}