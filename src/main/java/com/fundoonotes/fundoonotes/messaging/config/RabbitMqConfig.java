package com.fundoonotes.fundoonotes.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String REMINDER_QUEUE = "reminder.queue";
    public static final String NOTES_EXCHANGE = "notes.exchange";
    public static final String REMINDER_ROUTING_KEY = "reminder.created";

    @Bean
    public Queue reminderQueue() {
        return new Queue(REMINDER_QUEUE, true);
    }

    @Bean
    public DirectExchange notesExchange() {
        return new DirectExchange(NOTES_EXCHANGE);
    }

    @Bean
    public Binding reminderBinding(Queue reminderQueue, DirectExchange notesExchange) {
        return BindingBuilder.bind(reminderQueue)
                .to(notesExchange)
                .with(REMINDER_ROUTING_KEY);
    }
}
