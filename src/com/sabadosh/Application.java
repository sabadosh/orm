package com.sabadosh;

import java.sql.SQLException;
import java.util.List;
import static com.sabadosh.Console.*;

public class Application {
    public TaskDAO taskDAO;

    public Application() throws SQLException{
        taskDAO = new TaskDAO();
    }

    public static void main(String[] args) throws SQLException {
        Application app = new Application();
        boolean exit = true;
        while (exit) {
            String operation = getCommandFromConsole();
            if (operation.equals("create")) {
                app.createTask();
                app.showTasksList();
            } else if (operation.equals("list")) {
                app.showTasksList();
            } else if (operation.equals("delete")) {
                app.deleteTask();
                app.showTasksList();
            } else if (operation.equals("exit")) {
                exit = false;
            }
        }
        app.closeConnection();
    }

    private void createTask() throws SQLException {
        Task newTask = getTaskFromConsole();
        taskDAO.createTask(newTask);
    }

    private void deleteTask() throws SQLException {
        System.out.println("Enter task id to remove:");
        String taskId = getInput();
        try {
            int id = Integer.parseInt(taskId);
            taskDAO.deleteTaskById(id);
        } catch (Exception  e) {
            System.out.println("Enter right ID!");
        }
    }

    private void showTasksList() throws SQLException {
        List<Task> taskList = taskDAO.getAllTasks();
        showList(taskList);
    }

    private void  closeConnection() throws  SQLException{
        taskDAO.closeConnection();
    }

}
