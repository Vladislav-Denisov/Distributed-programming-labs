
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SetNewStatus_QNAME = new QName("http://service/", "setNewStatus");
    private final static QName _GetAllTasks_QNAME = new QName("http://service/", "getAllTasks");
    private final static QName _CompleteTaskSupport_QNAME = new QName("http://service/", "completeTaskSupport");
    private final static QName _ReopenTaskResponse_QNAME = new QName("http://service/", "reopenTaskResponse");
    private final static QName _AddTask_QNAME = new QName("http://service/", "addTask");
    private final static QName _GetAllTasksResponse_QNAME = new QName("http://service/", "getAllTasksResponse");
    private final static QName _CompleteTaskSupportResponse_QNAME = new QName("http://service/", "completeTaskSupportResponse");
    private final static QName _AddTaskResponse_QNAME = new QName("http://service/", "addTaskResponse");
    private final static QName _ConfirmCompleteTaskResponse_QNAME = new QName("http://service/", "confirmCompleteTaskResponse");
    private final static QName _GetAllAppropriateTasks_QNAME = new QName("http://service/", "getAllAppropriateTasks");
    private final static QName _GetTaskById_QNAME = new QName("http://service/", "getTaskById");
    private final static QName _GetTaskByIdResponse_QNAME = new QName("http://service/", "getTaskByIdResponse");
    private final static QName _ReopenTask_QNAME = new QName("http://service/", "reopenTask");
    private final static QName _SetNewStatusResponse_QNAME = new QName("http://service/", "setNewStatusResponse");
    private final static QName _ConfirmCompleteTask_QNAME = new QName("http://service/", "confirmCompleteTask");
    private final static QName _GetAllAppropriateTasksResponse_QNAME = new QName("http://service/", "getAllAppropriateTasksResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReopenTaskResponse }
     * 
     */
    public ReopenTaskResponse createReopenTaskResponse() {
        return new ReopenTaskResponse();
    }

    /**
     * Create an instance of {@link CompleteTaskSupport }
     * 
     */
    public CompleteTaskSupport createCompleteTaskSupport() {
        return new CompleteTaskSupport();
    }

    /**
     * Create an instance of {@link GetAllTasks }
     * 
     */
    public GetAllTasks createGetAllTasks() {
        return new GetAllTasks();
    }

    /**
     * Create an instance of {@link SetNewStatus }
     * 
     */
    public SetNewStatus createSetNewStatus() {
        return new SetNewStatus();
    }

    /**
     * Create an instance of {@link CompleteTaskSupportResponse }
     * 
     */
    public CompleteTaskSupportResponse createCompleteTaskSupportResponse() {
        return new CompleteTaskSupportResponse();
    }

    /**
     * Create an instance of {@link AddTask }
     * 
     */
    public AddTask createAddTask() {
        return new AddTask();
    }

    /**
     * Create an instance of {@link GetAllTasksResponse }
     * 
     */
    public GetAllTasksResponse createGetAllTasksResponse() {
        return new GetAllTasksResponse();
    }

    /**
     * Create an instance of {@link GetTaskById }
     * 
     */
    public GetTaskById createGetTaskById() {
        return new GetTaskById();
    }

    /**
     * Create an instance of {@link GetTaskByIdResponse }
     * 
     */
    public GetTaskByIdResponse createGetTaskByIdResponse() {
        return new GetTaskByIdResponse();
    }

    /**
     * Create an instance of {@link GetAllAppropriateTasks }
     * 
     */
    public GetAllAppropriateTasks createGetAllAppropriateTasks() {
        return new GetAllAppropriateTasks();
    }

    /**
     * Create an instance of {@link ConfirmCompleteTaskResponse }
     * 
     */
    public ConfirmCompleteTaskResponse createConfirmCompleteTaskResponse() {
        return new ConfirmCompleteTaskResponse();
    }

    /**
     * Create an instance of {@link AddTaskResponse }
     * 
     */
    public AddTaskResponse createAddTaskResponse() {
        return new AddTaskResponse();
    }

    /**
     * Create an instance of {@link ConfirmCompleteTask }
     * 
     */
    public ConfirmCompleteTask createConfirmCompleteTask() {
        return new ConfirmCompleteTask();
    }

    /**
     * Create an instance of {@link GetAllAppropriateTasksResponse }
     * 
     */
    public GetAllAppropriateTasksResponse createGetAllAppropriateTasksResponse() {
        return new GetAllAppropriateTasksResponse();
    }

    /**
     * Create an instance of {@link SetNewStatusResponse }
     * 
     */
    public SetNewStatusResponse createSetNewStatusResponse() {
        return new SetNewStatusResponse();
    }

    /**
     * Create an instance of {@link ReopenTask }
     * 
     */
    public ReopenTask createReopenTask() {
        return new ReopenTask();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetNewStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "setNewStatus")
    public JAXBElement<SetNewStatus> createSetNewStatus(SetNewStatus value) {
        return new JAXBElement<SetNewStatus>(_SetNewStatus_QNAME, SetNewStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllTasks")
    public JAXBElement<GetAllTasks> createGetAllTasks(GetAllTasks value) {
        return new JAXBElement<GetAllTasks>(_GetAllTasks_QNAME, GetAllTasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteTaskSupport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "completeTaskSupport")
    public JAXBElement<CompleteTaskSupport> createCompleteTaskSupport(CompleteTaskSupport value) {
        return new JAXBElement<CompleteTaskSupport>(_CompleteTaskSupport_QNAME, CompleteTaskSupport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReopenTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "reopenTaskResponse")
    public JAXBElement<ReopenTaskResponse> createReopenTaskResponse(ReopenTaskResponse value) {
        return new JAXBElement<ReopenTaskResponse>(_ReopenTaskResponse_QNAME, ReopenTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addTask")
    public JAXBElement<AddTask> createAddTask(AddTask value) {
        return new JAXBElement<AddTask>(_AddTask_QNAME, AddTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllTasksResponse")
    public JAXBElement<GetAllTasksResponse> createGetAllTasksResponse(GetAllTasksResponse value) {
        return new JAXBElement<GetAllTasksResponse>(_GetAllTasksResponse_QNAME, GetAllTasksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteTaskSupportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "completeTaskSupportResponse")
    public JAXBElement<CompleteTaskSupportResponse> createCompleteTaskSupportResponse(CompleteTaskSupportResponse value) {
        return new JAXBElement<CompleteTaskSupportResponse>(_CompleteTaskSupportResponse_QNAME, CompleteTaskSupportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "addTaskResponse")
    public JAXBElement<AddTaskResponse> createAddTaskResponse(AddTaskResponse value) {
        return new JAXBElement<AddTaskResponse>(_AddTaskResponse_QNAME, AddTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmCompleteTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "confirmCompleteTaskResponse")
    public JAXBElement<ConfirmCompleteTaskResponse> createConfirmCompleteTaskResponse(ConfirmCompleteTaskResponse value) {
        return new JAXBElement<ConfirmCompleteTaskResponse>(_ConfirmCompleteTaskResponse_QNAME, ConfirmCompleteTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAppropriateTasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllAppropriateTasks")
    public JAXBElement<GetAllAppropriateTasks> createGetAllAppropriateTasks(GetAllAppropriateTasks value) {
        return new JAXBElement<GetAllAppropriateTasks>(_GetAllAppropriateTasks_QNAME, GetAllAppropriateTasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getTaskById")
    public JAXBElement<GetTaskById> createGetTaskById(GetTaskById value) {
        return new JAXBElement<GetTaskById>(_GetTaskById_QNAME, GetTaskById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getTaskByIdResponse")
    public JAXBElement<GetTaskByIdResponse> createGetTaskByIdResponse(GetTaskByIdResponse value) {
        return new JAXBElement<GetTaskByIdResponse>(_GetTaskByIdResponse_QNAME, GetTaskByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReopenTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "reopenTask")
    public JAXBElement<ReopenTask> createReopenTask(ReopenTask value) {
        return new JAXBElement<ReopenTask>(_ReopenTask_QNAME, ReopenTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetNewStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "setNewStatusResponse")
    public JAXBElement<SetNewStatusResponse> createSetNewStatusResponse(SetNewStatusResponse value) {
        return new JAXBElement<SetNewStatusResponse>(_SetNewStatusResponse_QNAME, SetNewStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmCompleteTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "confirmCompleteTask")
    public JAXBElement<ConfirmCompleteTask> createConfirmCompleteTask(ConfirmCompleteTask value) {
        return new JAXBElement<ConfirmCompleteTask>(_ConfirmCompleteTask_QNAME, ConfirmCompleteTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAppropriateTasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllAppropriateTasksResponse")
    public JAXBElement<GetAllAppropriateTasksResponse> createGetAllAppropriateTasksResponse(GetAllAppropriateTasksResponse value) {
        return new JAXBElement<GetAllAppropriateTasksResponse>(_GetAllAppropriateTasksResponse_QNAME, GetAllAppropriateTasksResponse.class, null, value);
    }

}
