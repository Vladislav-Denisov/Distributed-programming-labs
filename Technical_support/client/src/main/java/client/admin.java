package client;

import service.Task;
import service.TaskService;
import service.TaskServiceImplService;
import service.TaskStatus;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class admin {
    public static TaskService taskservice;
    public static void main(String[] args) throws Exception{
        try {
            TaskServiceImplService tsis = new TaskServiceImplService(new URL("http://localhost:8888/TaskService?wsdl"));
            taskservice = tsis.getTaskServiceImplPort();

            // Сканер для считывания данных из консоли
            Scanner scanner = new Scanner(System.in);

            // Основная часть приложения сотрудника поддержки
            while (true) {
                int code = menu(scanner);
                if (code == 0) {
                    System.out.println("Exit...");
                    break;
                }
                switch (code) {
                    case 1:
                    {
                        System.out.println("Display a list of all tasks was selected");
                        printAllTasks();
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Display a list of all tasks except with CLOSED-status was selected");
                        printAllAppropriateTasks();
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Accept the task to work selected");
                        if (acceptTaskToWork(scanner) != 0)
                            System.out.println("Something went wrong in the process of getting the task by id. " +
                                    "You may have specified a non-existent identifier.");
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Confirm completion of the task was selected");
                        if (completeTask(scanner) != 0)
                            System.out.println("Something went wrong in the process of getting the task by id. " +
                                    "You may have specified a non-existent identifier.");
                        break;
                    }
                    case -13211: {
                        System.out.println("Input is not valid. Try again.");
                        break;
                    }
                    default:
                    {
                        System.out.println("Unknown menu item. Try again.");
                        break;
                    }
                } // end Switch
            } // end While (true)
        } catch (javax.xml.ws.WebServiceException ex) {
            System.out.println("WebServiceException!");
        } catch (Exception ex) {
            System.out.println("Unhandled exception!");
            ex.getMessage();
        }
    }

    //-------------------------------------------------------------------------------------------------------
    // Меню пользователя
    public static int menu(Scanner scanner) {
        System.out.println("\n------ Menu ------");
        System.out.println("-- '1' - Display a list of all tasks.");
        System.out.println("-- '2' - Display a list of all tasks except with CLOSED-status");
        System.out.println("-- '3' - Accept the task to work.");
        System.out.println("-- '4' - Confirm completion of the task.");
        System.out.println("-- '0' - Exit.");
        int code = -1;

        try {
            System.out.print("Enter number: ");
            code = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            code = -13211;
        }

        return code;
    }

    //-------------------------------------------------------------------------------------------------------
    // Отобразить все заявки
    public static void printAllTasks() {
        List<Task> list = taskservice.getAllTasks();
        if (list.size() == 0)
            System.out.println("EMPTY");

        for (Task value : list) {
            System.out.println(taskToString(value));
        }
    }

    //-------------------------------------------------------------------------------------------------------
    // Отобразить все заявки со статусами "Открыто", "В работе", "Подтверждено сотрудником".
    public static void printAllAppropriateTasks() {
        List<Task> list = taskservice.getAllAppropriateTasks();
        if (list.size() == 0)
            System.out.println("EMPTY");

        for (Task value : list) {
            System.out.println(taskToString(value));
        }
    }

    //-------------------------------------------------------------------------------------------------------
    // Перевести информаию о заявке в строку
    public static String taskToString(Task task) {
        String report = "";
        if (task.getStatus() == TaskStatus.COMPLETED || task.getStatus() == TaskStatus.CLOSED)
            report = ", report: '" + task.getReportFromSupport() + "'";
        return "id: '" + task.getId() + "' description: '" + task.getDescription() + "', reporter: '" + task.getReporterName()
                + "', date of creation: '" + LocalDate.ofEpochDay(task.getDateOfCreation()).toString() +
                "', status: '" + task.getStatus() + "'" + report;
    }

    //-------------------------------------------------------------------------------------------------------
    // Принять заявку в работу
    public static int acceptTaskToWork(Scanner scanner) {
        int successCode = 0;
        try {
            int id = -1;
            System.out.print("Enter 'id' of task: ");
            id = Integer.parseInt(scanner.nextLine());
            Task task =  taskservice.getTaskById(id);
            if (task != null) {
                TaskStatus status = task.getStatus();
                if (status == TaskStatus.OPEN) {
                    taskservice.setNewStatus(id, TaskStatus.IN_PROGRESS);
                    System.out.println("The status of task with ID '" + id + "' has been changed to " + TaskStatus.IN_PROGRESS);
                } else if (status == TaskStatus.IN_PROGRESS) {
                    System.out.println("Task with ID '" + id + "' is already in work.");
                } else {
                    System.out.println("Task with ID '" + id + "' it is impossible to accept in work.");
                }
            } else {
                successCode = 1;
            }
        } catch (NumberFormatException ex) {
            ex.getMessage();
            successCode = 1;
        }

        return successCode;
    }

    public static int completeTask(Scanner scanner) {
        int successCode = 0;
        try {
            int id = -1;
            System.out.print("Enter 'id' of task: ");
            id = Integer.parseInt(scanner.nextLine());
            Task task =  taskservice.getTaskById(id);
            if (task != null) {
                TaskStatus status = task.getStatus();
                if (status == TaskStatus.IN_PROGRESS) {
                    try {
                        String parseReport = new String();
                        while (parseReport.isEmpty()) {
                            System.out.print("Report: ");
                            parseReport = scanner.nextLine();
                            if (parseReport.isEmpty()) {
                                System.out.println("Report is a required parameter!");
                            }
                        }
                        taskservice.completeTaskSupport(id, parseReport);
                        System.out.println("The status of task with ID '" + id + "' has been changed to " + TaskStatus.COMPLETED +
                                ", report: " + "'" + parseReport + "'");
                    } catch (Exception ex) {
                        ex.getMessage();
                        successCode = 1;
                    }
                } else if (status == TaskStatus.IN_PROGRESS) {
                    System.out.println("Task with ID '" + id + "' is already in work.");
                } else {
                    System.out.println("Task with ID '" + id + "' cannot be completed." +
                            " Make sure that the task has the status" + TaskStatus.IN_PROGRESS);
                }
            } else {
                successCode = 1;
            }
        } catch (NumberFormatException ex) {
            ex.getMessage();
            successCode = 1;
        }

        return successCode;
    }
}
