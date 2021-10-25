package seedu.address.ui;

import seedu.address.model.person.Name;
import seedu.address.model.task.Task;

/**
 * Tuple data structure to hold important information for the TreeView nodes.
 */
public class Tuple {
    private Name name;

    /** This node is a name header */
    private boolean nameHeader;

    private Task task;
    private int index;

    /** Constructor for Tuple class */
    public Tuple(Name name, boolean nameHeader, Task task, int index) {
        this.name = name;
        this.nameHeader = nameHeader;
        this.task = task;
        this.index = index;
    }

    public Name getName() {
        return name;
    }

    public Task getTask() {
        return task;
    }

    public boolean isNameHeader() {
        return nameHeader;
    }

    public int getIndex() {
        return index;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setNameHeader(boolean isNameHeader) {
        this.nameHeader = isNameHeader;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
