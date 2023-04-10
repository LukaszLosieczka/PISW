package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Follower;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FollowerEvent {

    LocalDateTime eventTime;
    int eventDuration;
    Boolean eventAnalysisRequired;
    String commentContent;
    LocalDateTime followerSubscriptionDate;

    public FollowerEvent(Follower follower){
        eventTime = follower.getComment().getEvent().getTime();
        eventDuration = follower.getComment().getEvent().getDuration();
        eventAnalysisRequired = follower.getComment().getEvent().isAnalysisRequired();
        commentContent = follower.getComment().getContent();
        followerSubscriptionDate = follower.getSubscriptionDate();
    }
}
