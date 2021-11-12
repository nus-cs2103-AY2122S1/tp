package seedu.teachbook.model;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.student.Student;

/**
 * Class storing the current display filters applied on the model
 */
public class TeachbookDisplayState {
    private final GeneralIndex index;
    private final Predicate<Student> predicate;

    /**
     * Constructor method for DisplayState
     * @param index of class to be displayed in this state.
     * @param predicate to be applied on the list of students of displayed list in this state.
     */
    public TeachbookDisplayState(GeneralIndex index, Predicate<Student> predicate) {
        this.index = index;
        this.predicate = predicate;
    }

    /**
     * Getter method for predicate.
     *
     * @return Predicate to be applied on model in this state.
     */
    public Predicate<Student> getPredicate() {
        return predicate;
    }

    /**
     * Getter method for index.
     *
     * @return GeneralIndex of the class selected in this state.
     */
    public GeneralIndex getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachbookDisplayState that = (TeachbookDisplayState) o;
        return Objects.equals(index, that.index) && Objects.equals(predicate, that.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, predicate);
    }
}
