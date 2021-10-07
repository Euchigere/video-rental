package com.emmanuelc.videorental.domain.models.enumerations;

public enum Genre {
    ACTION, DRAMA, ROMANCE, COMEDY, HORROR;

    public static Genre getEnumValue(String value) {
        for (Genre genre: values()) {
            if (genre.name().equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Unknown value '" + value + "' for Genre enums");
    }
}
