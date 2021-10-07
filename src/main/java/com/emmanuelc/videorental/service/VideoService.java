package com.emmanuelc.videorental.service;

import com.emmanuelc.videorental.domain.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoService {
    Page<Video> getVideos(Pageable pageable);
}
