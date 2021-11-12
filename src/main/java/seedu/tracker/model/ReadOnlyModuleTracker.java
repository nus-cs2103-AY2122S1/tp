package seedu.tracker.model;

import javafx.collections.ObservableList;
import seedu.tracker.model.module.Module;

public interface ReadOnlyModuleTracker {

    /**
    * Returns an unmodifiable view of the modules list.
    * This list will not contain any duplicate modules.
    */
    ObservableList<Module> getModuleList();
}
