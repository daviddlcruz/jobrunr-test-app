package com.example.leader;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.configuration.JobRunrConfiguration;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.common.SqlStorageProviderFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import shared.MyJobRequest;

import javax.sql.DataSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static java.time.LocalTime.now;

@SpringBootApplication
public class LeaderApplication {

	private final ApplicationContext applicationContext;

	private final DataSource dataSource;


	public LeaderApplication(ApplicationContext applicationContext, DataSource dataSource) {
		this.applicationContext = applicationContext;
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(LeaderApplication.class, args);
	}

	@Bean
	public JobScheduler jobScheduler() {
		return new JobScheduler(storageProvider());
	}

	@Bean
	public JobRequestScheduler jobRequestScheduler() {
		return new JobRequestScheduler(storageProvider());
	}

	@Bean
	ApplicationRunner leader(JobRequestScheduler jobRequestScheduler) {
		return args -> {
			jobRequestScheduler.schedule(UUID.randomUUID(), Instant.now().plus(5, ChronoUnit.MINUTES), new MyJobRequest("David task"));
		};
	}

	@Bean
	public StorageProvider storageProvider() {
		return SqlStorageProviderFactory.using(dataSource);
	}

	@Bean
	public JobRunrConfiguration.JobRunrConfigurationResult jobRunr() {
		return JobRunr.configure()
				.useStorageProvider(storageProvider())
				.useDashboard()
				.initialize();
	}
}
