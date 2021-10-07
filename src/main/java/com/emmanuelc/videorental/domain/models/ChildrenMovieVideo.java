package com.emmanuelc.videorental.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@DiscriminatorValue("2")
public class ChildrenMovieVideo extends Video {
    @Column(name = "max_age")
    @NotNull private Integer maximumAge;
}
