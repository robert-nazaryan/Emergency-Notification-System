package org.example.userservice.config;

import lombok.RequiredArgsConstructor;
import org.example.userservice.model.NotificationTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaTemplate<String, NotificationTemplate> kafkaTemplate;

    private static final String TOPIC = "notification-templates";

    public void sendTemplate(NotificationTemplate template) {
        kafkaTemplate.send(TOPIC, template);
    }

}
