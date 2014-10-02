package pl.java.scalatech.config.jmx;

import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

@Component
@ManagedResource("spitter:name=ConnectionCounterNotifier")
@ManagedNotification(notificationTypes = "ConnectionMetter.alert", name = "TODO")
public class ConnectionNotifierImpl implements NotificationPublisherAware, ConnectionNotifier {

    private NotificationPublisher notificationPublisher;

    @Override
    public void toManyConnectionAsTheSameTime() {
        notificationPublisher.sendNotification(new Notification("connectionCounter", this, 0));
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;

    }
}