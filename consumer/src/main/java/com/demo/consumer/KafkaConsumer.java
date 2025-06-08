package com.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    //For demo purposes, we have multiple consumers in a single file. But ideally, in a production-grade application,
    //or in a real world scenario, consumers run in different applications on different servers listening & consuming
    //messages.

    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
    public void listen1(String message){
        System.out.println("Received message 1: " + message);
    }

    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
    public void listen2(String message){
        System.out.println("Received message 2: " + message);
    }

    @KafkaListener(topics = "my-topic-new", groupId = "my-new-group-rider")
    public void listenRiderLocation(RiderLocation location){
        System.out.println("Received Location: " + location.getRiderId() + " : "
                                                + location.getLatitude() + " : "
                                                + location.getLongitude());
    }
}
