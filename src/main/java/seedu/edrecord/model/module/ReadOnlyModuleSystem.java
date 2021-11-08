package seedu.edrecord.model.module;

import java.util.List;

/**
 * Unmodifiable view of a module system
 */
public interface ReadOnlyModuleSystem {

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    List<Module> getModuleList();

}
