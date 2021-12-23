package com.example.dl_maksbackend.web;

import com.example.dl_maksbackend.entities.OrgEntity;
import com.example.dl_maksbackend.entities.TweetEntity;
import com.example.dl_maksbackend.repositories.OrgRepository;
import com.example.dl_maksbackend.repositories.TweetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TweetController {
    private TweetRepository tweetRepository;
    private OrgRepository orgRepository;

    public TweetController(TweetRepository tweetRepository, OrgRepository orgRepository) {
        this.tweetRepository = tweetRepository;
        this.orgRepository = orgRepository;
    }

    @PostMapping("org")
    public ResponseEntity<OrgEntity> createOrg(@RequestBody OrgEntity orgEntity){
        OrgEntity savedOrgEntity = orgRepository.save(orgEntity);
        return ResponseEntity.status(HttpStatus.OK).body(savedOrgEntity);

    }



    @PostMapping("{orgId}")
    public void postTweetByKeyword(@PathVariable String orgId) {
        Optional<OrgEntity> org = orgRepository.findById(orgId);
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(org.get().getKeyword());
        try {
            QueryResult result = twitter.search(query);
            for(Status tweet :result.getTweets()){
                TweetEntity tweetEntity = new TweetEntity();
                tweetEntity.setAuthorName(tweet.getUser().getName());
                tweetEntity.setAuthorUsername(tweet.getUser().getScreenName());
                tweetEntity.setContent(tweet.getText());
                tweetEntity.setId(tweet.getId());
                tweetEntity.setOrgEntity(org.get());
                //tweetEntity.setSentiment(1);
                tweetRepository.save(tweetEntity);
            }
        }catch(TwitterException e){
            System.out.println(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<TweetEntity>> getAllTweets(){
        return ResponseEntity.status(HttpStatus.OK).body(tweetRepository.findAll());
    }
}
