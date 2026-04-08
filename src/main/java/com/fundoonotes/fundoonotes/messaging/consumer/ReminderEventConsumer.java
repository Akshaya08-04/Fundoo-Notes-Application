package com.fundoonotes.fundoonotes.messaging.consumer;

import com.fundoonotes.fundoonotes.messaging.config.RabbitMqConfig;
import com.fundoonotes.fundoonotes.messaging.event.ReminderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReminderEventConsumer {

    @RabbitListener(queues = RabbitMqConfig.REMINDER_QUEUE)
    public void consumeReminder(ReminderEvent event) {
        System.out.println("Reminder event received for: " + event.getEmail());
        System.out.println("Message: " + event.getMessage());
    }
}
