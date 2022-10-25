package com.amqpexp.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.logging.Logger;

@Profile("consumer")
@Configuration
public class ConsumerService {

    private Logger logger=Logger.getLogger("ConsumerService");
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "bangaloreQ")
    public void processBangaloreMessage(String message){
        logger.info(" Received Message from Bangalore Q  ::::" +message);
    }

    @RabbitListener(queues = "mumbaiQ")
    public void processMumbaiMessage(String message){
        logger.info(" Received Message from Mumbai Q::::" +message);
    }
}
