package com.epam.retail.controller;

import com.epam.retail.domain.Member;
import com.epam.retail.domain.Order;
import com.epam.retail.domain.Type;
import com.epam.retail.repository.MemberRepository;
import com.epam.retail.repository.OrderRepository;
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
public class OrderControllerTest {

    @Autowired
    WebTestClient webClient;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void submit() {
        Member member = new Member().setId(1L).setName("name1").setType(Type.GOLD).setPoint(0L);
        memberRepository.save(member);

        Order order = new Order(null, null, "book info", 100, 1L);
        webClient.post().uri("/order/submit")
                .body(Mono.just(order), Order.class)
                .exchange()
                .expectStatus().isOk();

        Order actualOrder = orderRepository.findAll().iterator().next();
        assertEquals(order, actualOrder.setId(null).setCreateTime(null));

        Member actualMember = memberRepository.findByName("name1");
        assertEquals(300, actualMember.getPoint());
    }

    @Test
    void query() {
        orderRepository.save(new Order(null, null, "book info 1", 50, 1L));
        orderRepository.save(new Order(null, null, "book info 2", 50, 1L));
        orderRepository.save(new Order(null, null, "book info 3", 200, 1L));

        webClient.get()
                .uri(u -> u
                        .path("/order/query")
                        .queryParam("price", 50)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .hasSize(2);
    }

}
