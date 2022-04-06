package com.epam.retail.controller;

import com.epam.retail.domain.Order;
import com.epam.retail.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/order")
public class OrderController {

	OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	// System can submit order
	@PostMapping("/submit")
	public Order submit(@RequestBody Order order) {
		return orderService.submit(order);
	}

	// System can query orders (by order price for example)
	@GetMapping("/query")
	public Iterable<Order> query(@RequestParam("price") Integer price) {
		return orderService.query(price);
	}

}
