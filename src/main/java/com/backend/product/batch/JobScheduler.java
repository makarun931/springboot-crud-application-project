package com.backend.product.batch;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job simpleJob;

    @Scheduled(cron = "0 0/30 * * * ?") // Execute every 30 minutes
    public void scheduleSimpleJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
        jobLauncher.run(simpleJob, jobParameters);
    }
}
