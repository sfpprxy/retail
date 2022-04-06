package com.epam.retail.service;

import com.epam.retail.domain.Member;
import com.epam.retail.domain.Order;
import com.epam.retail.domain.Type;
import com.epam.retail.exception.InvalidFieldException;
import com.epam.retail.repository.MemberRepository;
import com.epam.retail.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Unit Test
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    MemberRepository memberRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submit() {
        assertThrows(
                InvalidFieldException.class,
                () -> orderService.submit(new Order().setBook("Joe").setPrice(100)).setMemberId(null)
        );
        assertThrows(
                InvalidFieldException.class,
                () -> orderService.submit(new Order().setBook("Joe").setPrice(null)).setMemberId(1L)
        );
        assertThrows(
                InvalidFieldException.class,
                () -> orderService.submit(new Order().setBook(null).setPrice(100)).setMemberId(1L)
        );
        Order goodOrder = new Order().setBook("book info").setPrice(100).setMemberId(1L);
        when(memberRepository.findById(any())).thenReturn(
                Optional.of(new Member().setName("name").setType(Type.GOLD).setPoint(0L))
        );
        when(orderRepository.save(goodOrder)).thenReturn(any());
        orderService.submit(goodOrder);
        verify(memberRepository).findById(1L);
        verify(orderRepository).save(goodOrder);
    }

    @Test
    void query() {
        when(orderRepository.findByPrice(any())).thenReturn(any());
        orderService.query(100);
        verify(orderRepository).findByPrice(any());
    }
}
