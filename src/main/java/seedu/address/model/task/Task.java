package seedu.address.model.task;

/**
 * Basic Task class for v1.2 implementation
 */
public class Task {
    protected String label;
    protected String date;
    private boolean isDone;

    /**
     * Basic constructor, creates a task with the given label.
     *
     * @param label Label the task is created with. Not allowed to be empty.
     */
    public Task(String label, String date) {
        this.label = label;
        this.date = date;
        this.isDone = false;
    }

    /**
     * Method used to mark the task as done.
     *
     * @param isDone indicates whether the task is marked as done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter for boolean isDone.
     *
     * @return the isDone of the Task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getter for label.
     *
     * @return the label of the task.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Setter for label.
     *
     * @param label The new label of the task.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Getter for date. Not applicable for Todo tasks.
     *
     * @return A String representing the date associated with the Task.
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for date. Not applicable for Todo tasks.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Override of toString to provide a user-friendly String representation of Task objects.
     *
     * @return A string representing the Task meant to be printed for the user.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + label + ", due: " + date;
        } else {
            return "[ ] " + label + ", due: " + date;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getLabel().equals(getLabel())
                && otherTask.getDate().equals(getDate());
    }
}
