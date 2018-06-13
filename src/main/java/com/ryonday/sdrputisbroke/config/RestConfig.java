package com.ryonday.sdrputisbroke.config;

import com.ryonday.sdrputisbroke.domain.Thingie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.List;

@Configuration
@Slf4j
public class RestConfig extends RepositoryRestConfigurerAdapter {

    @Autowired
    private List<Converter<?, ?>> converters;

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        super.configureConversionService(conversionService);

        converters.forEach(cnv -> {
            log.info("Adding converter: {}", cnv);
            conversionService.addConverter(cnv);
        });
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ALL)
            .exposeIdsFor(Thingie.class);
    }
}
