package com.example.worker;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.configuration.JobRunrConfiguration;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.common.SqlStorageProviderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import shared.LongRunnigJobRequestHandler;
import shared.MyJobRequestHandler;

import javax.sql.DataSource;

@SpringBootApplication
public class WorkerApplication {

	private final ApplicationContext applicationContext;

	private final DataSource dataSource;

	public WorkerApplication(ApplicationContext applicationContext, DataSource dataSource) {
		this.applicationContext = applicationContext;
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
	}

	@Bean
	public StorageProvider storageProvider() {
		return SqlStorageProviderFactory.using(dataSource);
	}

	@Bean
	MyJobRequestHandler jobRequestHandler() {
		return new MyJobRequestHandler();
	}

	@Bean
	LongRunnigJobRequestHandler longRunnigJobRequestHandler() {
		return new LongRunnigJobRequestHandler();
	}

	@Bean
	public JobRunrConfiguration.JobRunrConfigurationResult jobRunr() {
		return JobRunr.configure()
				.useStorageProvider(storageProvider())
				.useBackgroundJobServer()
				.initialize();
	}
}
