package seedu.times.model.tuitionclass.exceptions;

import seedu.times.model.person.Name;

public class DuplicateStudentInClassException extends RuntimeException {
    public DuplicateStudentInClassException(Name duplicatedName) {
        super(duplicatedName.toString());
    }
}
