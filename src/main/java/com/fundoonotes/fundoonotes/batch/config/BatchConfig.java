package com.fundoonotes.fundoonotes.batch.config;

import com.fundoonotes.fundoonotes.batch.dto.NoteImportRow;
import com.fundoonotes.fundoonotes.batch.processor.NoteItemProcessor;
import com.fundoonotes.fundoonotes.batch.writer.NoteItemWriter;
import com.fundoonotes.fundoonotes.entity.Note;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public ListItemReader<NoteImportRow> noteReader() {
        return new ListItemReader<>(Arrays.asList(
                new NoteImportRow("Imported Note 1", "Imported description 1"),
                new NoteImportRow("Imported Note 2", "Imported description 2")
        ));
    }

    @Bean
    public Step noteImportStep(JobRepository jobRepository,
                               PlatformTransactionManager transactionManager,
                               ListItemReader<NoteImportRow> noteReader,
                               NoteItemProcessor processor,
                               NoteItemWriter writer) {
        return new StepBuilder("noteImportStep", jobRepository)
                .<NoteImportRow, Note>chunk(2, transactionManager)
                .reader(noteReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importNotesJob(JobRepository jobRepository, Step noteImportStep) {
        return new JobBuilder("importNotesJob", jobRepository)
                .start(noteImportStep)
                .build();
    }
}
