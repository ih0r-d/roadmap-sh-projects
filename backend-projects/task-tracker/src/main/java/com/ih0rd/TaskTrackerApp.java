package com.ih0rd;

import com.ih0rd.cli.TaskManager;

import java.util.Arrays;

/**
 * Task Tracker Application
 */
public class TaskTrackerApp {
    public static void main(String[] args) {

            /*
# Adding a new task
    task-cli add "Buy groceries"
    # Output: Task added successfully (ID: 1)

    # Updating and deleting tasks
    task-cli update 1 "Buy groceries and cook dinner"
    task-cli delete 1

    # Marking a task as in progress or done
    task-cli mark-in-progress 1
    task-cli mark-done 1

    # Listing all tasks
    task-cli list

    # Listing tasks by status
    task-cli list done
    task-cli list todo
    task-cli list in-progress

* */
        var vars = "add \"Buy groceries\"";


//        TaskManager.run(vars.split(" "));
        System.out.println("args: " + Arrays.toString(args));
        TaskManager.run(args);
    }
}
