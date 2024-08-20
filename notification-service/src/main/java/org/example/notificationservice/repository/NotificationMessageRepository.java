package org.example.notificationservice.repository;


import org.example.notificationservice.model.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationMessageRepository extends JpaRepository<NotificationMessage, Long> {
}
