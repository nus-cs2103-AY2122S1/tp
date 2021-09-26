package seedu.address.model.person;

import seedu.address.model.person.exceptions.InvalidTimeException;
import seedu.address.model.person.exceptions.OverlapTaskException;

/**
 * Represents the schedule for the staff, which contains all the task for the staff.
 * Built as a interval tree.
 */
public class Schedule {
    private Task taskNode = null;
    private Schedule leftNode = null;
    private Schedule rightNode = null;

    //argument value for interval tree
    private Time maxTime;

    /**
     * Constructor of Schedule. Let the task become the taskNode of this Schedule object.
     * @param task The given task.
     */
    public Schedule(Task task) {
        this.taskNode = task;
        maxTime = task.getEndTime();
    }

    /**
     * Update the maxTime for the current node.
     */
    public void updateMax() {
        Time tempMax;
        if (leftNode != null && rightNode != null) {
            tempMax = leftNode.maxTime.compareTo(rightNode.maxTime) >= 0 ? leftNode.maxTime : rightNode.maxTime;
        } else if (leftNode != null) {
            tempMax = leftNode.maxTime;
        } else if (rightNode != null) {
            tempMax = rightNode.maxTime;
        } else {
            tempMax = this.maxTime;
        }
        this.maxTime = this.maxTime.compareTo(tempMax) >= 0 ? this.maxTime : tempMax;
    }

    /**
     * Constructor that builds an empty Schedule node.
     */
    public Schedule() {
        try {
            this.maxTime = new Time(0, 0);
        } catch (InvalidTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new task to the staff's schedule.
     * @param newTask The new task.
     * @throws OverlapTaskException Throws when the task is overlapped with other tasks of the user.
     */
    public void add(Task newTask) throws OverlapTaskException {
        if (taskNode == null) {
            taskNode = newTask;
        } else {
            if (newTask.getEndTime().compareTo(taskNode.getStartTime()) <= 0) {
                if (leftNode == null) {
                    leftNode = new Schedule();
                }
                leftNode.add(newTask);
            } else if (newTask.getStartTime().compareTo(taskNode.getEndTime()) >= 0) {
                if (rightNode == null) {
                    rightNode = new Schedule();
                }
                rightNode.add(newTask);
            } else {
                throw new OverlapTaskException();
            }
        }
        updateMax();
    }

    /**
     * Tests whether the staff is working at a moment.
     *
     * @param time The time want to find.
     * @return Whether the staff is working at the given moment.
     */
    public boolean isWorking(Time time) {
        return search(time) != null;
    }

    /**
     * Finds the interval that containing t.
     *
     * @param time The moment want to search.
     * @return The interval containing time. Null if this interval does not exist.
     */
    public Schedule search(Time time) {
        Schedule cur = this;
        while (cur != null && taskNode.inInterval(time)) {
            if (cur.leftNode == null) {
                cur = cur.rightNode;
            } else if (time.compareTo(cur.leftNode.maxTime) > 0) {
                cur = cur.rightNode;
            } else {
                cur = cur.leftNode;
            }
        }
        return cur;
    }

}
