package com.csv.csvproject.inventory.fileconfig;

import com.csv.csvproject.inventory.model.Products;
import com.csv.csvproject.inventory.model.Status;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import javax.sql.DataSource;
import java.net.MalformedURLException;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/inventory_db");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("root");
        return dataSourceBuilder.build();
    }
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Products> fileReader() {
        FlatFileItemReader<Products> reader = new FlatFileItemReader<>();
        try {
            reader.setResource(new UrlResource("file:///C:\\Users\\Lenovo\\Desktop\\csvProject\\src\\main\\resources\\products.csv\n"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    private LineMapper<Products> getLineMapper() {
        DefaultLineMapper<Products> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"Name", "Code", "Quantity", "Price"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2, 3});

        BeanWrapperFieldSetMapper<Products> fieldSetMapper = new BeanWrapperFieldSetMapper<Products>();
        fieldSetMapper.setTargetType(Products.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public ProductProcessor processor() {
        return new ProductProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Products> writer() {
        JdbcBatchItemWriter<Products> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Products>());
        writer.setSql("insert into products(name, code, quantity, price) values(:name, :code, :quantity, :price)");
        writer.setDataSource(this.dataSource());
        return writer;
    }

    @Bean
    public Job importProductJob() {
        return this.jobBuilderFactory.get("PRODUCT_IMPORT_JOB")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                // .start(step1())
                .end()
                .build();

    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .<Products, Products>chunk(10)
                .reader(fileReader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
