package com.amqpexp.demo;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Profile("producer")
@RestController
@RequestMapping("/service")
public class ProducerService {
    private Logger logger=Logger.getLogger("ProducerService");

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private TopicExchange topicExchange;
    @PostMapping("/sendbangaloremessage")
    public void sendToBangalore(@RequestBody String message){
        logger.info("sending message :"+ message);
        rabbitTemplate.convertAndSend(topicExchange.getName(),"food.bangalore",message);
    }


    @PostMapping("/sendmumbaimessage")
    public void sendToMumbai(@RequestBody String message){
        logger.info("sending message :"+ message);
        rabbitTemplate.convertAndSend(topicExchange.getName(),"food.mumbai",message);
    }

    @GetMapping("/receivemessage")
    public String getMessage(){
        logger.info("sending message :");
        return "hello";
    }
}
