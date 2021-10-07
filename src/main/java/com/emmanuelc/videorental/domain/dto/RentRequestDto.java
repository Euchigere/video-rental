package com.emmanuelc.videorental.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class RentRequestDto {
    // using video id instead of title to ensure uniqueness of identity
    @NotNull
    private Long videoId;

    @NotBlank
    private String username;

    @Min(1)
    private int noOfDays;
}
