package client;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import service.TaskService;
import service.TaskServiceImplService;
import service.TaskStatus;
import service.Task;


public class user {
    public static TaskService taskservice;

    public static void main(String[] args) throws Exception {
        try {
            // Подключение к веб-сервису
            TaskServiceImplService tsis = new TaskServiceImplService(new URL("http://localhost:8888/TaskService?wsdl"));
            taskservice = tsis.getTaskServiceImplPort();

            // Сканер для считывания данных из консоли
            Scanner scanner = new Scanner(System.in);

            // Основная часть пользовательского приложения
            while (true) {
                int code = menu(scanner);
                if (code == 0) {
                    System.out.println("Exit...");
                    break;
                }
                switch (code) {
                    case 1:
                    {
                        System.out.println("Add a new task was selected");
                        if (addNewTask(scanner) != 0)
                            System.out.println("Something went wrong while adding the task. Try again.");
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Check the task status was selected");
                        if (checkTaskStatus(scanner) != 0)
                            System.out.println("Something went wrong in the process of getting the task by id. " +
                                    "You may have specified a non-existent identifier.");
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Confirm completion of the task was selected");
                        int retCode = completeTask(scanner);
                        if (retCode != 0)
                            switch (retCode) {
                                case 1: {
                                    System.out.println("Something went wrong in the process of getting the task by id. " +
                                            "You may have specified a non-existent identifier.");
                                    break;
                                }
                                case 2: {
                                    System.out.println("This task cannot be confirmed as completed.");
                                    break;
                                }
                            }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Return the task to work was selected");
                        int retCode = reopenTask(scanner);
                        if (retCode != 0)
                            switch (retCode) {
                                case 1: {
                                    System.out.println("Something went wrong in the process of getting the task by id. " +
                                            "You may have specified a non-existent identifier.");
                                    break;
                                }
                                case 2: {
                                    System.out.println("This task cannot be reopened.");
                                    break;
                                }
                            }
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
        System.out.println("-- '1' - Add a new task.");
        System.out.println("-- '2' - Check the task status.");
        System.out.println("-- '3' - Confirm completion of the task.");
        System.out.println("-- '4' - Return the task to work.");
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
    // Добавление новой заявки
    public static int addNewTask(Scanner scanner) {
        int successCode = 0;
        Task task = new Task();
        System.out.println("Let's specify the task parameters.");

        // Установка имени пользователя
        try {
            String parseName = new String();
            while (parseName.isEmpty()) {
                System.out.print("Your name: ");
                parseName = scanner.nextLine();
                if (parseName.isEmpty()) {
                    System.out.println("Your name is a required parameter!");
                }
            }
            task.setReporterName(parseName);
        } catch (Exception ex) {
            ex.getMessage();
            successCode = 1;
            return successCode;
        }

        // Установка e-mail пользователя
        try {
            String parseMail = new String();
            while (parseMail.isEmpty()) {
                System.out.print("Your e-mail: ");
                parseMail = scanner.nextLine();
                if (parseMail.isEmpty()) {
                    System.out.println("Your e-mail is a required parameter!");
                }
            }
            task.setReporterEmail(parseMail);
        } catch (Exception ex) {
            ex.getMessage();
            successCode = 1;
            return successCode;
        }

        // Установка описания задачи
        try {
            String parseDescription = new String();
            while (parseDescription.isEmpty()) {
                System.out.print("Description: ");
                parseDescription = scanner.nextLine();
                if (parseDescription.isEmpty()) {
                    System.out.println("Description is a required parameter!");
                }
            }
            task.setDescription(parseDescription);
        } catch (Exception ex) {
            ex.getMessage();
            successCode = 1;
            return successCode;
        }

        task.setDateOfCreation(LocalDate.now().toEpochDay());
        task.setStatus(TaskStatus.OPEN);
        int number = taskservice.addTask(task);
        System.out.println("Task successfully added with a number '" + number + "'");

        return successCode;
    }

    //-------------------------------------------------------------------------------------------------------
    // Проверка статуса заявки
    public static int checkTaskStatus(Scanner scanner) {
        int successCode = 0;
        try {
            int id = -1;
            System.out.print("Enter 'id' of task: ");
            id = Integer.parseInt(scanner.nextLine());

            Task tsk = taskservice.getTaskById(id);
            if (tsk == null) {
                successCode = 1;
            } else {
                System.out.println("Current status of the task: " + tsk.getStatus());
            }
        } catch (NumberFormatException ex) {
            ex.getMessage();
            successCode = 1;
        }
        return successCode;
    }

    //-------------------------------------------------------------------------------------------------------
    // Подтвердить выполнение заявки
    public static int completeTask(Scanner scanner) {
        int successCode = 0;
        try {
            int id = -1;
            System.out.print("Enter 'id' of task: ");
            id = Integer.parseInt(scanner.nextLine());

            Task tsk = taskservice.getTaskById(id);
            if (tsk != null) {
                if (tsk.getStatus() == TaskStatus.COMPLETED) {
                    taskservice.confirmCompleteTask(id);
                    System.out.println("Task with ID '" + id + "' successfully closed.");
                } else {
                    successCode = 2;
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

    //-------------------------------------------------------------------------------------------------------
    // Вернуть заявку в работу
    public static int reopenTask(Scanner scanner) {
        int successCode = 0;
        try {
            int id = -1;
            System.out.print("Enter 'id' of task: ");
            id = Integer.parseInt(scanner.nextLine());

            Task tsk = taskservice.getTaskById(id);
            if (tsk != null) {
                if (tsk.getStatus() == TaskStatus.COMPLETED) {
                    try {
                        String parseDescription = new String();
                        while (parseDescription.isEmpty()) {
                            System.out.print("Reason for reopen task: ");
                            parseDescription = scanner.nextLine();
                            if (parseDescription.isEmpty()) {
                                System.out.println("Reason for reopen task is a required parameter!");
                            }
                        }
                        taskservice.reopenTask(id, parseDescription);
                        System.out.println("Task with ID '" + id + "' successfully reopened.");
                    } catch (Exception ex) {
                        ex.getMessage();
                        successCode = 1;
                    }
                } else {
                    successCode = 2; // заявка не может быть переоткрыта
                }
            } else {
                successCode = 1; // заявка не неайдена
            }
        } catch (NumberFormatException ex) {
            ex.getMessage();
            successCode = 1;
        }
        return successCode;
    }
}
