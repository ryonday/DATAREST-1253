package com.ryonday.sdrputisbroke.conversion;

import com.google.common.base.Splitter;
import com.ryonday.sdrputisbroke.domain.ThingieId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToThingieIdConverter implements Converter<String, ThingieId> {

    private final static Splitter s = Splitter.on('-')
        .omitEmptyStrings()
        .limit(2)
        .trimResults();

    @Override
    public ThingieId convert(String source) {

        List<String> idParts = s.splitToList(source);
        if (idParts.size() != 2) {
            throw new IllegalArgumentException("Need two elements only");
        }

        return new ThingieId(idParts.get(0), idParts.get(1));
    }
}
