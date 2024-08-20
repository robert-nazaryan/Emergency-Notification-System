package org.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.userservice.model.NotificationTemplate;
import org.example.userservice.service.NotificationTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class NotificationTemplateController {

    private final NotificationTemplateService notificationTemplateService;

    @PostMapping("/add")
    public NotificationTemplate addTemplate(@RequestBody NotificationTemplate template) {
        return notificationTemplateService.createTemplate(template);
    }

    @GetMapping
    public List<NotificationTemplate> getTemplates() {
        return notificationTemplateService.getTemplates();
    }

}
