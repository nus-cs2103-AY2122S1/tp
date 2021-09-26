package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;

public interface ReadOnlyModTracker {

    /**
    * Returns an unmodifiable view of the modules list.
    * This list will not contain any duplicate modules.
    */
    ObservableList<Module> getModuleList();
}
