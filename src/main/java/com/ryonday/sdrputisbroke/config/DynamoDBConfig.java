package com.ryonday.sdrputisbroke.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.ConversionSchemas;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.google.common.collect.ImmutableSet;
import com.ryonday.sdrputisbroke.domain.Thingie;
import com.ryonday.sdrputisbroke.repository.ThingieRepository;
import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.mapping.DynamoDBMappingContext;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(
    basePackageClasses = {ThingieRepository.class},
    dynamoDBMapperConfigRef = "mapperConfig",
    amazonDynamoDBRef = "dynamoDB")
@Slf4j
public class DynamoDBConfig {


    @Bean
    public DynamoDBMapperConfig mapperConfig() {
        return DynamoDBMapperConfig.builder()
            .withConversionSchema(ConversionSchemas.V2)
            .build();
    }

    @Bean
    public AmazonDynamoDB dynamoDB() {
        return DynamoDBEmbedded.create().amazonDynamoDB();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB dynamoDB) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        log.info("Creating table...");
        mapper.newTableMapper(Thingie.class).createTableIfNotExists(new ProvisionedThroughput(1000L, 1000L));

        return mapper;
    }

    /**
     * Because:
     * <p>
     * https://github.com/sparknetworks/DynamoDBExample/blob/master/src/main/java/de/affinitas/data/dynamo/DynamoDBAutoConfiguration.java
     * https://github.com/derjust/spring-data-dynamodb/issues/15https://github.com/derjust/spring-data-dynamodb/issues/15
     */
    @Bean
    @Primary
//    @ConditionalOnMissingBean
    public DynamoDBMappingContext mappingContext() {
        DynamoDBMappingContext context = new DynamoDBMappingContext();
        context.setInitialEntitySet(ImmutableSet.<Class<?>>builder()
            .add(Thingie.class)
            .build());
        context.getPersistentEntity(Thingie.class);

        return context;
    }
}
