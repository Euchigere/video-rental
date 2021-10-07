package com.emmanuelc.videorental.domain.models;

import com.emmanuelc.videorental.domain.models.enumerations.Genre;
import com.emmanuelc.videorental.domain.models.enumerations.VideoType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "video")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "TINYINT(1)")
public abstract class Video extends BaseModel {
    @Column(nullable = false)
    @NotNull private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull protected VideoType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull private Genre genre;
}
