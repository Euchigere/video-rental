package com.emmanuelc.videorental.service.impl;

import com.emmanuelc.videorental.domain.models.ChildrenMovieVideo;
import com.emmanuelc.videorental.domain.models.NewReleaseVideo;
import com.emmanuelc.videorental.domain.models.RegularVideo;
import com.emmanuelc.videorental.domain.models.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RentDetailsServiceImplTest {
    @Autowired
    RentDetailsServiceImpl rentDetailsService;

    void checkPriceCalculation(Video video, int noOfDays, BigDecimal expectedPrice) {
        final BigDecimal actualPrice = rentDetailsService.calculatePrice(video, noOfDays);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void shouldCalculatePriceAccuratelyForRegularVideo() {
        RegularVideo video = new RegularVideo();
        checkPriceCalculation(video, 10, valueOf(100.00));
    }

    @Test
    void shouldCalculatePriceAccuratelyForChildrenVideo() {
        ChildrenMovieVideo video1 = new ChildrenMovieVideo().setMaximumAge(8);
        ChildrenMovieVideo video2 = new ChildrenMovieVideo().setMaximumAge(12);

        checkPriceCalculation(video1, 5, valueOf(44.00));
        checkPriceCalculation(video2, 8, valueOf(70.00));
    }

    @Test
    void shouldCalculatePriceAccuratelyForNewReleaseVideo() {
        NewReleaseVideo video1 = new NewReleaseVideo().setYearReleased(String.valueOf(LocalDate.now().getYear()));
        NewReleaseVideo video2 = new NewReleaseVideo().setYearReleased(String.valueOf(LocalDate.now().minusYears(1).getYear()));
        NewReleaseVideo video3 = new NewReleaseVideo().setYearReleased(String.valueOf(LocalDate.now().minusYears(2).getYear()));

        checkPriceCalculation(video1, 2, valueOf(30.00));
        checkPriceCalculation(video2, 3, valueOf(44.00));
        checkPriceCalculation(video3, 5, valueOf(73.00));
    }

    private BigDecimal valueOf(Double number) {
        return BigDecimal.valueOf(number)
                .setScale(
                        RentDetailsServiceImpl.SCALE,
                        RentDetailsServiceImpl.ROUNDING_MODE
                );
    }
}