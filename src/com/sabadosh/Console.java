package com.sabadosh;

import java.util.List;
import java.util.Scanner;

public class Console {

    private static String[] commands = {"create", "list", "delete", "exit"};

    public static void showList(List<Task> taskList) {
        String space = "             ";
        System.out.println("Task id" + space + "Task priority" + space + "Task name");
        for (Task task  : taskList) {
            System.out.print(task.getId());
            System.out.print(space);
            System.out.print(task.getPriority());
            System.out.print(space);
            System.out.println(task.getName());
            System.out.println("- - - - - - - - - - - ");
        }
    }

    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    public static String getCommandFromConsole() {
        boolean valid = false;
        String command = "";
        while (!valid) {
            System.out.println("Enter command");
            command = getInput();
            for (String operation:commands) {
                if (command.equals(operation)) {
                    valid = true;
                    break;
                }
            }
        }
        return command;
    }


    public static Task getTaskFromConsole() {
        System.out.println("Enter task name:");
        String name = getInput();
        System.out.println("Enter task priority:");
        String priority = getInput();
        return new Task(name, priority);
    }



}
