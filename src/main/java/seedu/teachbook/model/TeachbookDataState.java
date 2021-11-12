package seedu.teachbook.model;

import java.util.Objects;

/**
 * Class containing all the data in teachbook at during a single state.
 */
public class TeachbookDataState {
    private final TeachbookDisplayState displayState;
    private final TeachBook teachBook;


    /**
     * Constructor method for TeachbookDataState.
     *
     * @param displayState display filters of the current state.
     * @param teachBook data of the current teachbook.
     */
    public TeachbookDataState(TeachbookDisplayState displayState, TeachBook teachBook) {
        this.displayState = displayState;
        this.teachBook = teachBook;
    }

    /**
     * Getter method for displayState in this state.
     *
     * @return displayState of this current datastate.
     */
    public TeachbookDisplayState getDisplayState() {
        return displayState;
    }

    /**
     * Getter method for teachbook in this state.
     *
     * @return TeachBook of this current datastate.
     */
    public TeachBook getTeachBook() {
        return teachBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachbookDataState that = (TeachbookDataState) o;
        return Objects.equals(displayState, that.displayState) && Objects.equals(teachBook, that.teachBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayState.hashCode(), teachBook.hashCode());
    }
}
