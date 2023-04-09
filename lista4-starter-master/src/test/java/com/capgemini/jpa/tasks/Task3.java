package com.capgemini.jpa.tasks;

import com.capgemini.jpa.entities.Event;
import com.capgemini.jpa.entities.RequestEvent;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@DataJpaTest
class Task3 {

    @Autowired
    EntityManager entityManager;

    @Test
    void shouldDeleteInBulkEventsOlderThan() throws Exception {
        // given
        LocalDateTime givenDate = LocalDateTime.of(2017, 12, 31, 0, 0);

        // when
        //repository.deleteInBulkBeforDate(givenDate); // replace with repository method call

        // then
        assertThat(new SimpleJpaRepository<Event, Long>(Event.class, entityManager).findAll(), hasSize(32));
    }

    @Test
    void shouldUpdateEventsLongerThanInBulk() throws Exception {
        // given
        int threshold = 1000;
        Class<RequestEvent> clazz = RequestEvent.class;

        // when
//		repository.updateInBulkToBeAnalyzedByType(clazz, threshold);

        // then
        assertThat(new SimpleJpaRepository<Event, Long>(Event.class, entityManager).findAll().stream()//
                .filter(e -> e.getDuration() > threshold)//
                .filter(Event::isAnalysisRequired)//
                .count(), CoreMatchers.is(3));
    }

}
