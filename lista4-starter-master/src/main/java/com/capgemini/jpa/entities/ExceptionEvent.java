package com.capgemini.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ExceptionEvent extends Event {

    private String exceptionName;

    private String occuranceClass;

    private String occuranceMethod;

    private Integer occuranceLine;

}
