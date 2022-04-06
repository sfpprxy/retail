package com.epam.retail.controller;

import com.epam.retail.domain.Member;
import com.epam.retail.domain.Type;
import com.epam.retail.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Integration Test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class MemberControllerTest {

    @Autowired
    WebTestClient webClient;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void create() {
        Member m1 = new Member(null, "m1", Type.SILVER, null);
        Member m2 = new Member(null, "m2", Type.COPPER, 10L);
        webClient.post().uri("/member/create")
                .body(Mono.just(m1), Member.class)
                .exchange()
                .expectStatus().isOk();
        webClient.post().uri("/member/create")
                .body(Mono.just(m2), Member.class)
                .exchange()
                .expectStatus().isOk();
        assertEquals(m1.setId(null).setPoint(0L), memberRepository.findByName("m1").setId(null));
        assertEquals(m2.setId(null), memberRepository.findByName("m2").setId(null));
    }
}
