package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Follower;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    //@EntityGraph(value = "Follower.comment", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select new com.capgemini.jpa.repositories.FollowerEvent(f) " +
            "from Follower f " +
            "where f.userId = :userId")
    List<FollowerEvent> findFollowerEventByUserId(@Param("userId") String userId);

    @EntityGraph(value = "Follower.comment", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select f " +
            "from Follower f " +
            "where f.userId = :userId")
    List<Follower> findFollowerByUserId(@Param("userId") String userId);
}
