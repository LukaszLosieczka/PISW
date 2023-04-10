package com.capgemini.jpa.repositories;

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
}
