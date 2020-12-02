package entity;

public enum TaskStatus {
    OPEN("Open"),                   // Заявка открыта
    IN_PROGRESS("In progress"),     // Заявка в работе
    COMPLETED("Completed"),         // Заявка выполнена сотрудниками, но выполнение не подтверждено пользователем
    CLOSED("Closed");               // Заявка закрыта пользователем

    private String text;

    TaskStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
