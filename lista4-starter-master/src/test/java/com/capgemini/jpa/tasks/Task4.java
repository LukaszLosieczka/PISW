package com.capgemini.jpa.tasks;

import com.capgemini.jpa.repositories.ServerStatistic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class Task4 {

    @Test
    void shouldCountEventsByServer() throws Exception {
        // given ensured by script
        int expectedServer_1 = 15;
        int expectedServer_2 = 14;
        int expectedServer_3 = 11;

        // when
        List<ServerStatistic> result = new ArrayList<>();// replace by repository method call

        // then

        assertThat(result, hasSize(3));

        // convert to map
        Map<Long, Long> map = result.stream()
                .collect(Collectors.toMap(s -> s.getServer().getId(), ServerStatistic::getCount));
        assertThat(map.get(1L), is(expectedServer_1));
        assertThat(map.get(2L), is(expectedServer_2));
        assertThat(map.get(3L), is(expectedServer_3));
    }

}
