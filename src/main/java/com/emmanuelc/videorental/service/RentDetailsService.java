package com.emmanuelc.videorental.service;

import com.emmanuelc.videorental.domain.dto.RentRequestDto;
import com.emmanuelc.videorental.domain.models.RentDetails;

public interface RentDetailsService {
    RentDetails processRentRequest(RentRequestDto rentRequest);
}
