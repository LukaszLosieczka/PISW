package com.capgemini.jpa.tasks;

import com.capgemini.jpa.entities.Comment;
import com.capgemini.jpa.entities.Event;
import com.capgemini.jpa.entities.Follower;
import com.capgemini.jpa.repositories.CommentRepository;
import com.capgemini.jpa.repositories.EventRepository;
import com.capgemini.jpa.repositories.FollowerEvent;
import com.capgemini.jpa.repositories.FollowerRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


@DataJpaTest
class Task6 {

    @Autowired
    private FollowerRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void shouldReturnOneFollowerEvent() throws Exception {
        // given
        Event event = eventRepository.getById(2L);
        Comment comment = new Comment(null, "abcd", event);
        String userId = "123";
        Follower follower = new Follower(null, userId, comment, null);
        commentRepository.saveAndFlush(comment);
        repository.saveAndFlush(follower);
        // when
        List<FollowerEvent> followers = repository.findFollowerEventByUserId(userId);
        // then
        assertThat(followers.size(), Matchers.is(1));
        FollowerEvent followerEvent = followers.get(0);
        assertThat(followerEvent.getEventDuration(), Matchers.is(event.getDuration()));
        assertThat(followerEvent.getEventTime(), Matchers.is(event.getTime()));
        assertThat(followerEvent.getCommentContent(), Matchers.is(comment.getContent()));
        assertThat(followerEvent.getFollowerSubscriptionDate().getDayOfMonth(), Matchers.is(follower.getSubscriptionDate().getDayOfMonth()));
    }

}
