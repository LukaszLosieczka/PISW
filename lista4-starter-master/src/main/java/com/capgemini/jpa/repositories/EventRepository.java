package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByAnalysisRequiredAndTimeGreaterThanAndTimeLessThan(boolean toBeAnalyzed, LocalDateTime start, LocalDateTime end, Pageable page);

    @Query("select new com.capgemini.jpa.repositories.ServerStatistic(s, count(e)) from Event e join e.server s group by s")
    List<ServerStatistic> getServersStatistics();

    @Modifying
    @Query("delete from Event e where e.time < :givenDate")
    void deleteInBulkBeforeDate(@Param("givenDate") LocalDateTime givenDate);

    @Modifying
    @Query("update Event e set e.analysisRequired = true where type(e) = :clazz and e.duration > :minDuration")
    void updateInBulkToBeAnalyzedByType(@Param("clazz") Class<? extends Event> clazz, @Param("minDuration") int minDuration);
}
