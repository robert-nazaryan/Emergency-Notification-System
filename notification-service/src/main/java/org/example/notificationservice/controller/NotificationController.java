package org.example.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public void sendNotification(@RequestParam Long templateId) {
        log.info("Received request to send notification with templateId: {}", templateId);
        try {
            notificationService.sendNotification(templateId);
            log.info("Notification request processed successfully.");
        } catch (Exception e) {
            log.error("Error processing notification request.", e);
        }
    }

}
