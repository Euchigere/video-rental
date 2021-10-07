package com.emmanuelc.videorental.controller;

import com.emmanuelc.videorental.domain.dto.ApiResponse;
import com.emmanuelc.videorental.domain.dto.RentRequestDto;
import com.emmanuelc.videorental.domain.models.RentDetails;
import com.emmanuelc.videorental.service.RentDetailsService;
import com.emmanuelc.videorental.utils.ControllerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RentDetailsController {
    private final RentDetailsService rentDetailsService;

    public RentDetailsController(RentDetailsService rentDetailsService) {
        this.rentDetailsService = rentDetailsService;
    }

    @PostMapping("/rents")
    public ResponseEntity<ApiResponse<RentDetails>> rentVideo(@Valid @RequestBody RentRequestDto rentRequestDto) {
        final RentDetails rentDetails = rentDetailsService.processRentRequest(rentRequestDto);
        return ControllerUtil.buildResponseEntity(rentDetails);
    }
}
