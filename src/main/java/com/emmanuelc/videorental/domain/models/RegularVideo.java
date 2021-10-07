package com.emmanuelc.videorental.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@DiscriminatorValue("1")
public class RegularVideo extends Video {
}
