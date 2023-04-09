package com.capgemini.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SqlEvent extends Event {

    @Column(length = 4000, nullable = false)
    private String sqlQuery;

}
