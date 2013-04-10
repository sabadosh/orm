package com.sabadosh;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class TaskDAO  {
    private final static String DATABASE_URL = "jdbc:sqlite:tasks.db";
    public Dao<Task, Integer> taskDao;
    private ConnectionSource connectionSource;

    public TaskDAO() throws SQLException{
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
        taskDao = DaoManager.createDao(connectionSource, Task.class);
        TableUtils.createTableIfNotExists(connectionSource, Task.class);
    }

    public TaskDAO(String url) throws SQLException{
        connectionSource = new JdbcConnectionSource(url);
        taskDao = DaoManager.createDao(connectionSource, Task.class);
        TableUtils.createTableIfNotExists(connectionSource, Task.class);

    }
    public void createTask(Task task) throws SQLException {
        taskDao.create(task);
    }

    public void deleteTask(Task task) throws SQLException {
        taskDao.delete(task);
    }

    public void deleteTaskById(int id) throws SQLException {
        taskDao.deleteById(id);
    }

    public List<Task> getAllTasks() throws SQLException {
        return taskDao.queryForAll();
    }

    public List<Task> getTaskByName(String name) throws SQLException {
        QueryBuilder<Task, Integer> statementBuilder = taskDao.queryBuilder();
        statementBuilder.where().eq(Task.NAME_FIELD_NAME, name);
        return taskDao.query(statementBuilder.prepare());
    }

    public Task getTaskById(int id) throws SQLException {
        return taskDao.queryForId(id);
    }

    public void closeConnection() throws SQLException {
        connectionSource.close();
    }
}
