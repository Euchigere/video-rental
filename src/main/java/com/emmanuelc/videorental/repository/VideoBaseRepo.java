package com.emmanuelc.videorental.repository;

import com.emmanuelc.videorental.domain.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface VideoBaseRepo<T extends Video> extends JpaRepository<T, Long> {
}
