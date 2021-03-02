module edu.miu.cs401.todo {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.miu.cs401.todo to javafx.fxml;
    exports edu.miu.cs401.todo;
}