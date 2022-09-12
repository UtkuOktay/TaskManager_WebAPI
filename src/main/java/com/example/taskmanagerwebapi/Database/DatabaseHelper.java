package com.example.taskmanagerwebapi.Database;

import com.example.taskmanagerwebapi.Task.TaskItem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Repository
public class DatabaseHelper {
    private final Properties properties = new Properties();
    private final String username = "postgres";
    private final String password = "1";
    private final String url = "jdbc:postgresql://localhost/TasksDB?user=" + username + "&password=" + password;

    public DatabaseHelper() {
        properties.setProperty("user", username);
        properties.setProperty("password", password);
    }

    //Returns all the tasks in the database.
    public List<TaskItem> getTasks() {
        List<TaskItem> tasks = new LinkedList<TaskItem>();

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Tasks");

            while (resultSet.next())
                tasks.add(new TaskItem(resultSet.getString("ID"), resultSet.getString("name"), resultSet.getBoolean("isCompleted")));

            return tasks;
        }
        catch (SQLException e) {
            return null;
        }
    }

    //Returns the queried task if it exists. If not, returns null.
    public TaskItem getTask(String id) {
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tasks WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new TaskItem(resultSet.getString("ID"), resultSet.getString("name"), resultSet.getBoolean("isCompleted"));
        }
        catch (SQLException e) {
            System.out.println("An error occured during the operation.");
        }
        return null;
    }

    //Creates and inserts a task into the database.
    public String insertTask(String name, boolean isCompleted) {
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            //COALESCE is a function that returns the first parameter which is not null. In the use case here, if max(id) returns null, that is the table is empty, the function returns 0.
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Tasks (id, name, iscompleted) VALUES (COALESCE((SELECT CAST(CAST(max(id) AS INT) + 1 AS VARCHAR) FROM Tasks), '0'), ?, ?);");
            statement.setString(1, name);
            statement.setBoolean(2, isCompleted);
            statement.executeUpdate();
            return null;
        }
        catch (SQLException e) {
            return "An error occured during the operation.";
        }
    }

    //Updates the provided task.
    public String updateTask(TaskItem task) {
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("UPDATE Tasks SET name = ?, iscompleted = ? WHERE id = ?");
            statement.setString(1, task.getName());
            statement.setBoolean(2, task.isCompleted());
            statement.setString(3, task.getId());
            statement.executeUpdate();
            return null;
        }
        catch (SQLException e) {
            return "An error occured during the operation.";
        }
    }

    //Deletes the task with the provided id.
    public String deleteTask(String id) {
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Tasks WHERE id = ?");
            statement.setString(1, id);
            statement.executeUpdate();
            return null;
        }
        catch (SQLException e) {
            return "An error occured during the operation.";
        }
    }
}
