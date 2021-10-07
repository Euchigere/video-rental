package com.emmanuelc.videorental.utils;

import com.emmanuelc.videorental.domain.models.enumerations.Genre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Converter
public class GenreListConverter implements AttributeConverter<List<Genre>, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(List<Genre> attribute) {
        if (attribute == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        ListIterator<Genre> attributeIterator = attribute.listIterator();
        while (attributeIterator.hasNext()) {
            sb.append(attributeIterator.next().name());
            if (attributeIterator.hasNext()) {
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }

    @Override
    public List<Genre> convertToEntityAttribute(String dbData) {
        if (StringUtil.isNullOrBlank(dbData)) {
            return null;
        }
        String[] arr = dbData.split(SEPARATOR);
        final List<Genre> list = new ArrayList<>();
        for (String val: arr) {
            Genre genre = Genre.getEnumValue(val);
            list.add(genre);
        }
        return list;
    }
}
