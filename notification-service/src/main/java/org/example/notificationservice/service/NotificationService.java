package org.example.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationservice.model.NotificationMessage;
import org.example.notificationservice.model.NotificationTemplate;
import org.example.notificationservice.repository.NotificationMessageRepository;
import org.example.notificationservice.repository.NotificationTemplateRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationMessageRepository notificationMessageRepository;
    private final EmailService emailService;


    @KafkaListener(topics = "notification-template-topic", groupId = "notification-group")
    public void listen(NotificationTemplate template) {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setTitle(template.getNotificationMessage().getTitle());
        notificationMessage.setContent(template.getNotificationMessage().getContent());

        NotificationTemplate notificationTemplate = new NotificationTemplate();
        notificationTemplate.setNotificationMessage(notificationMessage);
        notificationTemplate.setRecipients(template.getRecipients());

        notificationMessageRepository.save(template.getNotificationMessage());
        notificationTemplateRepository.save(template);
        log.info("Received template from Kafka: {}", template);
    }

    public void sendNotification(Long templateId) {
        NotificationTemplate template = notificationTemplateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        List<String> recipients = template.getRecipients();

        for (String recipient : recipients) {
            try {
                emailService.sendEmail(recipient,
                        template.getNotificationMessage().getTitle(),
                        template.getNotificationMessage().getContent());
                log.info("Notification sent to {}", recipient);
            } catch (Exception e) {
                log.error("Failed to send notification to {}", recipient, e);
            }
        }
    }

}
