package com.fundoonotes.fundoonotes.messaging.producer;

import com.fundoonotes.fundoonotes.messaging.config.RabbitMqConfig;
import com.fundoonotes.fundoonotes.messaging.event.ReminderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReminderEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReminderEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishReminderEvent(ReminderEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.NOTES_EXCHANGE,
                RabbitMqConfig.REMINDER_ROUTING_KEY,
                event
        );
    }
}
