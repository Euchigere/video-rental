package com.emmanuelc.videorental.service.impl;

import com.emmanuelc.videorental.domain.dto.RentRequestDto;
import com.emmanuelc.videorental.domain.models.*;
import com.emmanuelc.videorental.exceptions.ResourceNotFoundException;
import com.emmanuelc.videorental.repository.RentDetailsRepo;
import com.emmanuelc.videorental.repository.VideoRepo;
import com.emmanuelc.videorental.service.RentDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

@Service
public class RentDetailsServiceImpl implements RentDetailsService {
    static final int SCALE = 2;

    static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    // rates are configurable from the properties file
    @Value("#{${app.rent.rates}}") Map<String, Integer> ratesMap;

    private final RentDetailsRepo rentDetailsRepo;

    private final VideoRepo videoRepo;

    public RentDetailsServiceImpl(RentDetailsRepo rentDetailsRepo, VideoRepo videoRepo) {
        this.rentDetailsRepo = rentDetailsRepo;
        this.videoRepo = videoRepo;
    }

    @Override
    public RentDetails processRentRequest(final RentRequestDto rentRequest) {
        final Video video = videoRepo.findById(rentRequest.getVideoId()).orElseThrow(
                () -> new ResourceNotFoundException("Video not found")
        );

        final BigDecimal price = calculatePrice(video, rentRequest.getNoOfDays());
        final RentDetails rentDetails = new RentDetails();

        rentDetails.setUsername(rentRequest.getUsername());
        rentDetails.setNoOfDays(rentRequest.getNoOfDays());
        rentDetails.setVideoTitle(video.getTitle());
        rentDetails.setPrice(price);
        rentDetails.setVideo(video);

        return rentDetailsRepo.save(rentDetails);
    }

    BigDecimal calculatePrice(Video video, int noOfDays) {
        if (video instanceof RegularVideo) {
            return calculatePrice(noOfDays);
        } else if (video instanceof ChildrenMovieVideo) {
            return calculatePrice((ChildrenMovieVideo) video, noOfDays);
        } else {
            return calculatePrice((NewReleaseVideo) video, noOfDays);
        }
    }

    private BigDecimal calculatePrice(int noOfDays) {
        final int rate = ratesMap.get("regular");
        final double value = rate * noOfDays;
        return BigDecimal.valueOf(value)
                .setScale(SCALE, ROUNDING_MODE);
    }

    private BigDecimal calculatePrice(ChildrenMovieVideo video, int noOfDays) {
        final int rate = ratesMap.get("children");
        final double value = (rate * noOfDays) + (video.getMaximumAge() / 2.0);
        return BigDecimal.valueOf(value)
                .setScale(SCALE, ROUNDING_MODE);
    }

    private BigDecimal calculatePrice(NewReleaseVideo video, int noOfDays) {
        final int rate = ratesMap.get("newRelease");
        final LocalDate releaseDate = LocalDate.of(Integer.parseInt(video.getYearReleased()), 1, 1);
        final int yearsSinceRelease = LocalDate.now().getYear() - releaseDate.getYear();
        final double value = (rate * noOfDays) - yearsSinceRelease;
        return BigDecimal.valueOf(value)
                .setScale(SCALE, ROUNDING_MODE);
    }
}
