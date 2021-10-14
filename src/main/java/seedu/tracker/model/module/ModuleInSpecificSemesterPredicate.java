package seedu.tracker.model.module;

import seedu.tracker.model.calendar.AcademicCalendar;

import java.util.function.Predicate;

public class ModuleInSpecificSemesterPredicate implements Predicate<Module>{
    private final AcademicCalendar academicCalendar;

    public ModuleInSpecificSemesterPredicate(AcademicCalendar academicCalendar) {
        this.academicCalendar = academicCalendar;
    }

    @Override
    public boolean test(Module module) {
        return this.academicCalendar.equals(module.getAcademicCalendar());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && academicCalendar.equals(((ModuleInSpecificSemesterPredicate) other).academicCalendar)); // state check
    }
}
