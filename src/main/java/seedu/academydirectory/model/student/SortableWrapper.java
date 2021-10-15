package seedu.academydirectory.model.student;

import static java.util.Objects.requireNonNull;

public abstract class SortableWrapper implements Information {
    public static boolean isValidSortableWrapper(String test) {
        requireNonNull(test);
        return true;
    }

}
