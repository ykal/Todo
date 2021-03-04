module edu.miu.cs401.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dataaccess;

    opens edu.miu.cs401.todo to javafx.fxml;
    exports edu.miu.cs401.todo;
}