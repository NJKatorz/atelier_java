import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks;

    public TodoList() {
        tasks = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        if (task == null)
            return false;
        if (containsTask(task))
            return false;
        return tasks.add(task);
    }
    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }


    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public Task findTask(Task task) {
        return tasks.stream().anyMatch(t -> t.equals(task)) ? task : null;
    }

    public boolean updateTask(Task currentTask, Task updatedTask) {
        if (currentTask == null || updatedTask == null)
            return false;
        Task taskFound = findTask(currentTask);
        if (taskFound == null)
            return false;
        taskFound.setTitle(updatedTask.getTitle());
        taskFound.setDescription(updatedTask.getDescription());
        return true;
    }
}
