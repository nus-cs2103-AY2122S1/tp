package seedu.tracker.model.module;

import java.util.function.Predicate;

import seedu.tracker.model.calendar.AcademicCalendar;

/**
 * Tests that whether a {@code Module} is in specific semester.
 */
public class ModuleInSpecificSemesterPredicate implements Predicate<Module> {
    private final AcademicCalendar academicCalendar;

    public ModuleInSpecificSemesterPredicate(AcademicCalendar academicCalendar) {
        this.academicCalendar = academicCalendar;
    }

    @Override
    public boolean test(Module module) {
        return module.hasAcademicCalendar() && this.academicCalendar.equals(module.getAcademicCalendar());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleInSpecificSemesterPredicate // instanceof handles nulls
                && academicCalendar
                .equals(((ModuleInSpecificSemesterPredicate) other).academicCalendar)); // state check
    }
}
