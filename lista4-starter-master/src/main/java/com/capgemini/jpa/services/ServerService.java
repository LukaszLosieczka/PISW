package com.capgemini.jpa.services;

import com.capgemini.jpa.entities.Server;
import com.capgemini.jpa.repositories.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;

    public Optional<Server> findByName(String name) {
        return serverRepository.findByName(name);
    }

}
