package pl.java.scalatech.config.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConnectionNotificationListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        log.info("!!!!!  {} ,  {}", notification, handback);
    }
}