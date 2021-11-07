package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Set;

import seedu.address.model.lessoncode.LessonCode;

/**
 * Represents a module code that a Person is taking in contHACKS.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should consists of a two- or three-letter prefix followed by a 4-digit number"
                    + " and optionally a one-letter suffix";

    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,3}[\\d]{4}[a-zA-Z]?";
    public final String value;
    public final Set<LessonCode> lessonCodes;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode, Set<LessonCode> lessonCodes) {
        requireAllNonNull(moduleCode, lessonCodes);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        lessonCodes.forEach(lessonCode -> checkArgument(LessonCode.isValidLessonCode(lessonCode.lessonCode)));
        value = moduleCode;
        this.lessonCodes = lessonCodes;
    }

    /**
     * Returns true if a given string is a valid module code.
     *
     * @param test The given string.
     * @return True if the given string is a valid module code, false otherwise.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getModuleCodeName() {
        return value;
    }

    public Set<LessonCode> getLessonCodes() {
        return Collections.unmodifiableSet(lessonCodes);
    }

    /**
     * Returns formatted String for use in UI.
     */
    public String formattedToString() {
        if (lessonCodes.isEmpty()) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (LessonCode lessonCode : lessonCodes) {
            sb.append(value);
            sb.append(" ");
            sb.append(lessonCode);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(value);
        for (LessonCode lessonCode : lessonCodes) {
            sb.append(" ");
            sb.append(lessonCode);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleCode
                && value.equals(((ModuleCode) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
