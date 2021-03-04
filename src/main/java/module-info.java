module edu.miu.cs401.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dataaccess;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;

    opens edu.miu.cs401.todo to javafx.fxml;
    exports edu.miu.cs401.todo;
}