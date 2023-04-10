package com.capgemini.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "follower")
@NamedEntityGraph(
        name = "Follower.comment",
        attributeNodes = {
                @NamedAttributeNode(value = "comment", subgraph = "Comment.event"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "Comment.event",
                        attributeNodes = {
                                @NamedAttributeNode("event")
                        }
                )
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class Follower {
    @Id
    @SequenceGenerator(name = "FOLLOWER_ID_GENERATOR", sequenceName = "FOLLOWER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOWER_ID_GENERATOR")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @PrePersist
    public void prePersist() {
        subscriptionDate = LocalDateTime.now();
    }
}