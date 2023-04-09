package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Server;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerStatistic {
    Server server;
    long count;

}
