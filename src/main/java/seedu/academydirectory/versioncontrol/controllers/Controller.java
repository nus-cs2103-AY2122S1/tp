package seedu.academydirectory.versioncontrol.controllers;

import seedu.academydirectory.versioncontrol.objects.VcObject;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public abstract class Controller<T extends VcObject> {
    protected final HashGenerator generator;

    /**
     * Creates a Controller to build
     * @param generator
     */
    protected Controller(HashGenerator generator) {
        this.generator = generator;
    }
}
