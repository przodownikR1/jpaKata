package pl.java.scalatech.config.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "Spring-beans:name=SampleJmx")
public class HelloJMX {
    String message = null;

    @ManagedAttribute
    public String getMessage() {
        return this.message;
    }

    @ManagedAttribute
    public void setMessage(String Message) {
        this.message = Message;
    }
}