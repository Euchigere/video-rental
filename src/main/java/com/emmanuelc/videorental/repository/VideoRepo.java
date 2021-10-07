package com.emmanuelc.videorental.repository;

import com.emmanuelc.videorental.domain.models.Video;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends VideoBaseRepo<Video> {
}
