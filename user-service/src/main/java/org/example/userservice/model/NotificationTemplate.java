package org.example.userservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@ToString
@Table(name = "notification_template")
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NotificationMessage notificationMessage;

    @ElementCollection
    // Эта аннотация указывает, что поле представляет собой коллекцию простых значений (например, строки),
    // которые будут храниться в отдельной таблице базы данных.
    // В данном случае, она используется для хранения списка email-адресов, связанных с шаблоном сообщения.
    private List<String> recipients;

}
