package com.example.dl_maksbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class TweetEntity {
    @Id
    private long id;
    private String authorUsername;
    private String authorName;
    private String content;
    private int sentiment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "org_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OrgEntity orgEntity;
}
