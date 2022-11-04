package com.nh.messagingrabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MessagingRabbitmqApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    @Autowired
    private static RabbitTemplate rabbitTemplate = new RabbitTemplate();

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx = SpringApplication.run(MessagingRabbitmqApplication.class, args);

        Receiver receiver = ctx.getBean(Receiver.class);
        while (receiver.getLatch().getCount() > 0) {
            final long count = receiver.getLatch().getCount();
            LOGGER.info("count  " + count);
            LOGGER.info("Sending message...");
            //rabbitTemplate.convertAndSend("routing.key.rmq", "   Message number :" + count);
            Thread.sleep(500L);
        }

        System.exit(0);
    }

}
