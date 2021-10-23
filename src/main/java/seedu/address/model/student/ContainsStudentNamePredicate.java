package seedu.address.model.student;

import java.util.function.Predicate;

public class ContainsStudentNamePredicate implements Predicate<Student> {
    private final Name name;

    public ContainsStudentNamePredicate(Name name) {
        this.name = name;
    }

    @Override
    public boolean test(Student student) {
        return this.name.equals(student.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ContainsStudentNamePredicate) // instanceof handles nulls
            && name.equals(((ContainsStudentNamePredicate) other).name);
    }
}
