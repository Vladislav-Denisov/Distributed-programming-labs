package service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import entity.*;

@WebService(endpointInterface = "service.TaskService")
public class TaskServiceImpl implements TaskService {
    private static Map<Integer, Task> taskMap = new ConcurrentHashMap<>();  // Словарь заявок (id : заявка)
    private static int count = 0;  // Количество заявок

    // Добавить новую заявку
    @Override
    public int addTask(Task task) {
        task.setId(count);
        taskMap.put(count, task);
        count++;
        return count - 1;
    }

    // Получить массив всех заявок
    @Override
    public Task[] getAllTasks() {
        System.out.println("getAllTasks():");
        Task[] result = new Task[taskMap.size()];
        int i = 0;
        for (Task value : taskMap.values()) {
            System.out.println(value);
            result[i++] = value;
        }
        System.out.println();
        return result;
    }

    // Получить массив заявок со статусами "Открыто", "В работе", "Подтверждено сотрудником".
    @Override
    public Task[] getAllAppropriateTasks() {
        System.out.println("getAllAppropriateTasks():");
        List<Task> tasks = new ArrayList<>();
        for (Task value : taskMap.values()) {
            if (value.getStatus() != TaskStatus.CLOSED) {
                System.out.println(value);
                tasks.add(value);
            }
        }
        System.out.println();
        return tasks.toArray(new Task[0]);
    }

    // Получить заявку по id
    @Override
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    // Установить статус заявки
    @Override
    public void setNewStatus(int id, TaskStatus status) {
        getTaskById(id).setStatus(status);
    }

    // Вернуть заявку в работу
    @Override
    public void reopenTask(int id, String newDescription) {
        Task task = getTaskById(id);
        task.setDescription(newDescription);
        task.setStatus(TaskStatus.OPEN);
    }

    // Клиентское подтверждение выполнения заявки
    @Override
    public void confirmCompleteTask(int id) {
        Task task = getTaskById(id);
        task.setStatus(TaskStatus.CLOSED);
    }

    // Подтвердить выполнение заявки сотрудником поддеркжи
    @Override
    public void completeTaskSupport(int id, String report) {
        Task task = getTaskById(id);
        task.setReportFromSupport(report);
        task.setStatus(TaskStatus.COMPLETED);
    }

}
