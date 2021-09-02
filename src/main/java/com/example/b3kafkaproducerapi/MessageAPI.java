package com.example.b3kafkaproducerapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/stream")

public class MessageAPI {


    @PostMapping(
            value = "/send",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String postBody(@RequestBody Message message) {
        Message persistedMessage = message;

        Producer simpleProducer = new Producer();
        String topic = "stream";
        ObjectMapper objectMessage = new ObjectMapper();
        try {
            String messageToJson = objectMessage.writeValueAsString(persistedMessage);
            ProducerRecord<Integer, String> data = new ProducerRecord<>(topic, 8, messageToJson);
            simpleProducer.producer.send(data);
            simpleProducer.producer.close();
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return "Message sent";
    }

}
