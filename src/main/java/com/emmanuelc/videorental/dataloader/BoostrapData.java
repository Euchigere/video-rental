package com.emmanuelc.videorental.dataloader;

import com.emmanuelc.videorental.domain.models.ChildrenMovieVideo;
import com.emmanuelc.videorental.domain.models.NewReleaseVideo;
import com.emmanuelc.videorental.domain.models.RegularVideo;
import com.emmanuelc.videorental.domain.models.enumerations.Genre;
import com.emmanuelc.videorental.domain.models.enumerations.VideoType;
import com.emmanuelc.videorental.repository.VideoRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoostrapData implements ApplicationListener<ApplicationReadyEvent> {
    private final VideoRepo videoRepo;

    public BoostrapData(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        RegularVideo video1 = (RegularVideo) new RegularVideo()
                .setTitle("Chucky")
                .setType(VideoType.REGULAR)
                .setGenre(Genre.ROMANCE);

        RegularVideo video2 = (RegularVideo) new RegularVideo()
                        .setTitle("Walking dead")
                        .setType(VideoType.REGULAR)
                        .setGenre(Genre.HORROR);

        RegularVideo video3 = (RegularVideo) new RegularVideo()
                        .setTitle("Equalizer")
                        .setType(VideoType.REGULAR)
                        .setGenre(Genre.COMEDY);

        ChildrenMovieVideo video4 = (ChildrenMovieVideo) new ChildrenMovieVideo()
                .setMaximumAge(10)
                .setTitle("Moana")
                .setType(VideoType.CHILDREN_MOVIE)
                .setGenre(Genre.DRAMA);

        ChildrenMovieVideo video5 = (ChildrenMovieVideo) new ChildrenMovieVideo()
                .setMaximumAge(12)
                .setTitle("Mulan")
                .setType(VideoType.CHILDREN_MOVIE)
                .setGenre(Genre.ACTION);

        ChildrenMovieVideo video6 = (ChildrenMovieVideo) new ChildrenMovieVideo()
                .setMaximumAge(8)
                .setTitle("Luca")
                .setType(VideoType.CHILDREN_MOVIE)
                .setGenre(Genre.DRAMA);

        NewReleaseVideo video7 = (NewReleaseVideo) new NewReleaseVideo()
                .setYearReleased("2020")
                .setTitle("Spectre")
                .setType(VideoType.NEW_RELEASE)
                .setGenre(Genre.ACTION);

        NewReleaseVideo video8 = (NewReleaseVideo) new NewReleaseVideo()
                .setYearReleased("2021")
                .setTitle("Fast and Furious")
                .setType(VideoType.NEW_RELEASE)
                .setGenre(Genre.ACTION);

        NewReleaseVideo video9 = (NewReleaseVideo) new NewReleaseVideo()
                .setYearReleased("2019")
                .setTitle("Skylines")
                .setType(VideoType.NEW_RELEASE)
                .setGenre(Genre.DRAMA);

        videoRepo.saveAll(List.of(video1, video2, video3, video4, video5, video6, video7, video8, video9));
    }
}
