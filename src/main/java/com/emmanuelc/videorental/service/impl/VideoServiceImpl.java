package com.emmanuelc.videorental.service.impl;

import com.emmanuelc.videorental.domain.models.Video;
import com.emmanuelc.videorental.repository.VideoRepo;
import com.emmanuelc.videorental.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepo videoRepo;

    public VideoServiceImpl(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }

    @Override
    public Page<Video> getVideos(final Pageable pageable) {
        return videoRepo.findAll(pageable);
    }
}
