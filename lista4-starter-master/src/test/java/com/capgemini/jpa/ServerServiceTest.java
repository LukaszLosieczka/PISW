package com.capgemini.jpa;


import com.capgemini.jpa.entities.Server;
import com.capgemini.jpa.repositories.ServerRepository;
import com.capgemini.jpa.services.ServerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
class ServerServiceTest {

    @Autowired
    private ServerService serverService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldReturnServerByName() throws Exception {
        // given
        String serverName = "myServerName";
        String ip = "127.0.0.1";
        Server server = new Server(serverName, ip);
        testEntityManager.persistAndFlush(server);

        // when
        Optional<Server> result = serverService.findByName(serverName);

        // then
        assertThat(result.isPresent(), Matchers.is(true));
        assertThat(result.get().getName(), Matchers.is(serverName));
        assertThat(result.get().getIp(), Matchers.is(ip));
        assertThat(result.get().getId(), Matchers.is(server.getId()));
    }


    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ServerService serverService(ServerRepository serverRepository) {
            return new ServerService(serverRepository);
        }
    }
}
