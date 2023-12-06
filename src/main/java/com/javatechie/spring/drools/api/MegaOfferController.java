package com.javatechie.spring.drools.api;

import org.kie.api.runtime.KieContainer;
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
	private KieContainer kieContainer;
	@Autowired
	private KieSession session;

	@PostMapping("/order")
	public Order orderNow(@RequestBody Order order) {
		Order orderDiscount = new Order();
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.setGlobal("orderDiscount", orderDiscount);
		session.insert(order);
		session.fireAllRules();
		//session.dispose();
		return order;
	}


	@PostMapping("/get-discount")
	public ResponseEntity<Order> getDiscount(@RequestBody Order orderRequest) {
		Order discount = megaOfferService.getDiscount(orderRequest);
		return new ResponseEntity<>(orderRequest, HttpStatus.OK);
	}

}
