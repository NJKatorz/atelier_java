import java.util.Objects;

public class Task {

    private String title;
    private String description;
    private boolean finished;

    public Task(String title, String description) {
        if (title == null)
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (description == null)
            throw new IllegalArgumentException("Description cannot be null");
        if (title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title;
        this.description = description;
        this.finished = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean finishTask() {
         if (finished)
            return false;
        finished = true;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    public boolean setTitle(String title) {
        if (title == null || title.isBlank())
            return false;
        if (finished)
           return false;
        this.title = title;
        return true;
    }

    public boolean setDescription(String description) {
        if (description == null)
            return false;

        if (finished)
            return false;
        this.description = description;
        return true;
    }
}
