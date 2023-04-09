package com.capgemini.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class RequestEvent extends Event {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HttpMethod method;

}
