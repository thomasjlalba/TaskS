package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        printData(tasksData);
        System.out.println();
        System.out.println("Printing deadlines");
        printDeadlinesUsingStream(tasksData);

        ArrayList<Task> FilteredList = filterTaskListUsingStreams(tasksData, "11");
        System.out.println("\n Filtered list of tasks");
        
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    
    public static void printDeadlineUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter(t-> instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);
    }
    
    public static ArrayList<Task> filterTaskListUsingStreams(ArrayList<task> tasks, String filterString) {
        ArrayList<task> filteredList = (ArrayList<task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
    }
}
