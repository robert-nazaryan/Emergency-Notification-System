package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userservice.model.NotificationTemplate;
import org.example.userservice.repository.NotificationMessageRepository;
import org.example.userservice.repository.NotificationTemplateRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationMessageRepository notificationMessageRepository;
    private final KafkaTemplate<String, NotificationTemplate> kafkaTemplate;

    private static final String TOPIC = "notification-template-topic";

    public NotificationTemplate createTemplate(NotificationTemplate template) {
        notificationMessageRepository.save(template.getNotificationMessage());
        NotificationTemplate savedTemplate = notificationTemplateRepository.save(template);
        kafkaTemplate.send(TOPIC, savedTemplate);  // Отправляем шаблон в Kafka
        return savedTemplate;
    }

    public List<NotificationTemplate> getTemplates() {
        return notificationTemplateRepository.findAll();
    }

}
