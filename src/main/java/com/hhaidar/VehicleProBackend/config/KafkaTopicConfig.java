package com.hhaidar.VehicleProBackend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic bookingSlotsTopic(){
        return TopicBuilder.name("bookings").build();
    }

}
