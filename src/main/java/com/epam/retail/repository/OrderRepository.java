package com.epam.retail.repository;

import com.epam.retail.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findByPrice(Integer price);
}
