package com.emmanuelc.videorental.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rental_info")
public class RentDetails extends BaseModel {
    @Column(nullable = false)
    @NotNull private String username;

    @Column(name = "video_title", nullable = false)
    @NotNull private String videoTitle;

    @Column(name = "no_of_days", nullable = false)
    @NotNull private int noOfDays;

    @Column(nullable = false)
    @NotNull private BigDecimal price;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;
}
