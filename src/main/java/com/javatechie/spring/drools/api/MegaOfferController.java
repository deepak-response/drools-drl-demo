package com.javatechie.spring.drools.api;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MegaOfferController {
	@Autowired
	private MegaOfferService megaOfferService;

	@Autowired
	private KieSession session;

	@PostMapping("/order")
	public Order orderNow(@RequestBody Order order) {
		session.insert(order);
		session.fireAllRules();
		return order;
	}

	@PostMapping("/get-discount")
	public ResponseEntity<Order> getDiscount(@RequestBody Order orderRequest) {
		Order discount = megaOfferService.getDiscount(orderRequest);
		return new ResponseEntity<>(discount, HttpStatus.OK);
	}

}
