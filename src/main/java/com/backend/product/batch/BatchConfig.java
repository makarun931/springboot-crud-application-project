package com.backend.product.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SimpleTasklet simpleTasklet;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory
            .get("simpleJob")
            .start(simpleStep())
            .build();
    }

    @Bean
    public Step simpleStep() {
        return stepBuilderFactory
            .get("simpleStep")
            .tasklet(simpleTasklet)
            .build();
    }
}

