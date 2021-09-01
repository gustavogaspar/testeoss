package com.example.b3kafkaproducerapi;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/response")

public class MessageAPI {

        @PostMapping("/postbody")
        public String postBody(@RequestBody String fullName) {
            return "Hello " + fullName;
        }

    @PostMapping(
            value = "/postbody1",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String postBody(@RequestBody Message message) {
        Message persistedMessage = message;

        Producer simpleProducer = new Producer();
        String topic = "topic";
        String msg = "message";
        ProducerRecord<Integer, Message> data = new ProducerRecord<>(topic, 8, persistedMessage);
        simpleProducer.producer.send(data);
        simpleProducer.producer.close();
        return "Message sent";
    }

}
