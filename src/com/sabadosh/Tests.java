package com.sabadosh;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.List;


public class Tests {
    public static TaskDAO taskDAO;

    public static void closeConnection() throws  SQLException{
        taskDAO.closeConnection();
    }
    @BeforeClass
    public static void setup() throws SQLException{
        taskDAO = new TaskDAO("jdbc:sqlite:tests.db");
    }

    @AfterClass
    public static void destroy() throws SQLException{
        closeConnection();
    }

    @Test
    public void testTaskCreate() throws SQLException {
        Task newTask = new Task("testTask", "P1");
        taskDAO.createTask(newTask);
        List<Task> tasks = taskDAO.getTaskByName(newTask.getName());
        int taskIdAfterSave = 1;
        for (Task task : tasks) {
            assert newTask.getName().equals(task.getName());
            assert newTask.getPriority().equals(task.getPriority());
            taskIdAfterSave = task.getId();
        }
        taskDAO.deleteTaskById(taskIdAfterSave);
        List<Task> tasksAfterDelete = taskDAO.getAllTasks();
        for (Task task : tasksAfterDelete) {
            assert !(newTask.getName().equals(task.getName()));
            assert !(newTask.getPriority().equals(task.getPriority()));
        }
    }
}
