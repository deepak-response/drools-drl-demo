package com.javatechie.spring.drools.api;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MegaOfferService {
    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KieSession kieSession;

    public Order getDiscount(Order orderRequest) {
        Order orderDiscount = new Order();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderDiscount;
    }
}
