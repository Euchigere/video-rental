package com.emmanuelc.videorental.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@DiscriminatorValue("3")
public class NewReleaseVideo extends Video {
    @Column(name = "year_released")
    @NotBlank
    private String yearReleased;
}
