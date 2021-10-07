package com.emmanuelc.videorental.controller;

import com.emmanuelc.videorental.domain.dto.ApiResponse;
import com.emmanuelc.videorental.domain.models.Video;
import com.emmanuelc.videorental.service.VideoService;
import com.emmanuelc.videorental.utils.ControllerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos")
    public ResponseEntity<ApiResponse<Page<Video>>> fetchVideos(@PageableDefault(size = 5) Pageable pageable) {
        Page<Video> videos = videoService.getVideos(pageable);
        return ControllerUtil.buildResponseEntity(videos);
    }
}
