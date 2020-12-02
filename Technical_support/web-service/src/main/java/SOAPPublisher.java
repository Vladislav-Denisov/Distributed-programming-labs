import javax.xml.ws.Endpoint;

import service.TaskServiceImpl;

public class SOAPPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/TaskService", new TaskServiceImpl());
        System.out.println("TaskService is started");
    }
}
