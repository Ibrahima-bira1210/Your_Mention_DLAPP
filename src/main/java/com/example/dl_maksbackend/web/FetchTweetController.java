package com.example.dl_maksbackend.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FetchTweetController {
    @GetMapping("/{keyWord}")
    public List<Status> getTweetByKeyWord(@PathVariable String keyWord) {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(keyWord);

        try {
            QueryResult result = twitter.search(query);
            return result.getTweets();
        }catch (TwitterException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
