package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Server;
import lombok.Data;

@Data
public class ServerStatistic {
    Server server;
    long count;

}
