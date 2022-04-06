package com.epam.retail.service;

import com.epam.retail.domain.Member;
import com.epam.retail.domain.Order;
import com.epam.retail.exception.InvalidFieldException;
import com.epam.retail.exception.RetailException;
import com.epam.retail.repository.MemberRepository;
import com.epam.retail.repository.OrderRepository;
import com.epam.retail.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    OrderRepository orderRepository;

    MemberRepository memberRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Order submit(Order order) {
        if (U.hasEmpty(order.getBook(), order.getPrice(), order.getMemberId())) {
            throw new InvalidFieldException("book, price, memberId");
        }
        Member member = memberRepository.findById(order.getMemberId())
                .orElseThrow(() -> new RetailException("member not found"));
        Order savedOrder = orderRepository.save(order);
        // calculate the member's point, assume that price can be 0 (coupon)
        long point = member.getPoint();
        switch (member.getType()) {
            case GOLD:
                point += order.getPrice() * 3L;
                break;
            case SILVER:
                point += order.getPrice() * 2L;
                break;
            case COPPER:
                point += order.getPrice();
                break;
        }
        memberRepository.save(member.setPoint(point));
        return savedOrder;
    }

    @Transactional
    public Iterable<Order> query(Integer price) {
        return orderRepository.findByPrice(price);
    }
}
