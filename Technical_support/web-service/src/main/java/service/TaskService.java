package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import entity.*;

@WebService
public interface TaskService {
    // Добавить новую заявку
    @WebMethod
    public int addTask(Task task);

    // Получить массив всех заявок
    @WebMethod
    public Task[] getAllTasks();

    // Получить массив заявок со статусами "Открыто", "В работе", "Подтверждено сотрудником".
    @WebMethod
    public Task[] getAllAppropriateTasks();

    // Получить заявку по id
    @WebMethod
    public Task getTaskById(int id);

    // Изменить текущий статус задачи
    @WebMethod
    public void setNewStatus(int id, TaskStatus status);

    // Вернуть заявку в работу
    @WebMethod
    public void reopenTask(int id, String newDescription);

    // Клиентское подтверждение выполнения заявки
    @WebMethod
    public void confirmCompleteTask(int id);

    // Подтвердить выполненение заявки сотрудником поддержки
    @WebMethod
    public void completeTaskSupport(int id, String report);

}
