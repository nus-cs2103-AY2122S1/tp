package seedu.unify.testutil;

import seedu.unify.model.UniFy;
import seedu.unify.model.task.Task;

/**
 * A utility class to help with building UniFy objects.
 * Example usage: <br>
 *     {@code UniFy ab = new UniFyBuilder().withTask("John", "Doe").build();}
 */
public class UniFyBuilder {

    private UniFy uniFy;

    public UniFyBuilder() {
        uniFy = new UniFy();
    }

    public UniFyBuilder(UniFy uniFy) {
        this.uniFy = uniFy;
    }

    /**
     * Adds a new {@code Task} to the {@code UniFy} that we are building.
     */
    public UniFyBuilder withTask(Task task) {
        uniFy.addTask(task);
        return this;
    }

    public UniFy build() {
        return uniFy;
    }
}
