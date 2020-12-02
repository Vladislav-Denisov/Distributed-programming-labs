package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable, Printable {
    private int id;
    private TaskStatus status;
    private Long dateOfCreation;
    private String description;
    private String reporterName;
    private String reporterEmail;
    private String reportFromSupport;

    public Task() {}

    // Создание заявки
    public Task(String description, String reporterName, String reporterEmail, Long dateOfCreation) {
        this.description = description;
        this.reporterName = reporterName;
        this.reporterEmail = reporterEmail;
        this.dateOfCreation = dateOfCreation;
        this.status = TaskStatus.OPEN;
    }

    // Получить описание заявки
    public String getDescription() {
        return description;
    }

    // Установить новое описание заявки
    public void setDescription(String description) {
        this.description = description;
    }

    // Получить имя создателя заявки
    public String getReporterName() {
        return reporterName;
    }

    // Установить имя создателя заявки
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    // Получить электронный адрес создателя заявки
    public String getReporterEmail() {
        return reporterEmail;
    }

    // Установить электронный адрес создателя заявки
    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    // Получить дату создания заявки
    public Long getDateOfCreation() {
        return dateOfCreation;
    }

    // Установить дату создания заявки
    public void setDateOfCreation(Long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    // Получить статус заявки
    public TaskStatus getStatus() {
        return status;
    }

    // Установить новый статус заявки
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    // Получить id заявки
    public int getId() {
        return id;
    }

    // Установить id заявки
    public void setId(int id) {
        this.id = id;
    }

    // Получить ответный комментарий, установленный сотрудником поддержки
    public String getReportFromSupport() {
        return reportFromSupport;
    }

    // Установить ответный комментарий сотрудника поддержки
    public void setReportFromSupport(String reportFromSupport) {
        this.reportFromSupport = reportFromSupport;
    }

    // Для корректного перевода в строку
    @Override
    public String toString() {
        return "Id: " + id + ". " + description + " reporter: " + reporterName + " date of creation: ";
               // + LocalDate.ofEpochDay(dateOfCreation).toString() + " status: " + status.getText();
    }

    @Override
    // Получить информацию о заявке в виде строки
    public String getText() {
        return toString();
    }

    @Override
    // Получить информацию о заявке в виде сокращенной строки
    public String getTrimmedText() {
        return id + ". " + description;
    }

}
