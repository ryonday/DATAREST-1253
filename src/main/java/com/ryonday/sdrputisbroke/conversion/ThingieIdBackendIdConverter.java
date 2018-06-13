package com.ryonday.sdrputisbroke.conversion;

import com.google.common.base.Joiner;
import com.ryonday.sdrputisbroke.domain.Thingie;
import com.ryonday.sdrputisbroke.domain.ThingieId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Component
@Slf4j
public class ThingieIdBackendIdConverter implements BackendIdConverter {

    private static final Joiner j = Joiner.on('-').skipNulls();

    @Override
    public Serializable fromRequestId(String id, Class<?> entityType) {
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String toRequestId(Serializable id, Class<?> entityType) {
        checkNotNull(id, "Cannot serialize a null id.");
        checkArgument(supports(entityType), "Cannot convert non-CompositeId-implementing id '%s'.", entityType.getName());

        ThingieId fooId = (ThingieId) id;

        Object hashKey = fooId.getFoo();
        Object rangeKey = fooId.getBar();

        return j.join(hashKey, rangeKey);
    }

    @Override
    public boolean supports(Class<?> delimiter) {
        log.info("Checking for support for {}", delimiter);
        return Thingie.class
            .isAssignableFrom(checkNotNull(delimiter, "Cannot check whether null class type is supported."));
    }
}
