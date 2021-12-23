package com.example.dl_maksbackend.repositories;

import com.example.dl_maksbackend.entities.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity,Long> {
}
