package com.amqpexp.demo;

import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setExchange(exchange().getName());
        return rabbitTemplate;
    }

    @Bean
    public Queue queueBangalore(){
        return new Queue("bangaloreQ");
    }
    @Bean
    public Queue queueMumbai(){
        return new Queue("mumbaiQ");
    }
    @Bean
    public TopicExchange exchange(){
        return  new TopicExchange("GoodsDelivery");
    }

    public Binding bindingBangalore(TopicExchange  topicExchange,Queue queue){
          return BindingBuilder.bind(queue).to(exchange()).with("food.bangalore");
    }


    public Binding bindingMumbai(TopicExchange  topicExchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange()).with("*.mumbai");
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
