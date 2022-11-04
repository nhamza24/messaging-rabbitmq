package com.nh.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@Scope("singleton")
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(5);
    @RabbitListener(queues = {"keycloak"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
          latch.countDown();
    }

  public CountDownLatch getLatch() {
    return latch;
  }

}