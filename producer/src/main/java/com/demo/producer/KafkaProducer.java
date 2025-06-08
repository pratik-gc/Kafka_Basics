package com.demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KafkaProducer {

    //Before starting Spring Boot application, make sure to run Docker Desktop and start zookeeper and kafka images.
    //Once these 2 containers are running, use following commands in cmd window.
    //>> docker exec -it kafka bash
    //>> kafka-console-consumer --topic my-topic --bootstrap-server localhost:9092 --group my-group --from-beginning
    //Assuming that we have already created producer and consumer topics inside kafka image beforehand.

    //Even if the mentioned topic name in our code doesn't exist in kafka image, it will create a new topic for you.
    //But this approach is generally not recommended in a production mode.
    //(Refer to KafkaConsumer.java file for topic names to listen.)

    private final KafkaTemplate<String, RiderLocation> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, RiderLocation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message){
        RiderLocation location = new RiderLocation("rider123", 16.34, 54.28 );
        kafkaTemplate.send("my-topic-new", location);
        return "Message sent: " + location.getRiderId();
    }
}
