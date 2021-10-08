package seedu.tracker.testutil;


import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.module.Module;

/**
 * A utility class to help with building ModuleTracker objects.
 * Example usage: <br>
 *     {@code ModuleTracker mt = new ModuleTracker().withModule(CS2103T).withModule(CS2100).build();}
 */
public class ModuleTrackerBuilder {

    private ModuleTracker moduleTracker;

    public ModuleTrackerBuilder() {
        moduleTracker = new ModuleTracker();
    }

    public ModuleTrackerBuilder(ModuleTracker moduleTracker) {
        this.moduleTracker = moduleTracker;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleTracker} that we are building.
     */
    public ModuleTrackerBuilder withModule(Module module) {
        moduleTracker.addModule(module);
        return this;
    }

    public ModuleTracker build() {
        return moduleTracker;
    }
}
