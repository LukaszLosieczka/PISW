package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Long> {

    Optional<Server> findByName(String name);
}
