package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    //@EntityGraph(value = "follower-entity-graph")
    @Query("select new com.capgemini.jpa.repositories.FollowerEvent" +
            "(f.comment.event.time, f.comment.event.duration, f.comment.event.analysisRequired, f.comment.content, f.subscriptionDate) " +
            "from Follower f join f.comment join f.comment.event " +
            "where f.userId = :userId")
    List<FollowerEvent> findFollowerEventByUserId(@Param("userId") String userId);
}
