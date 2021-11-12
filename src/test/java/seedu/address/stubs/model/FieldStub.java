package seedu.address.stubs.model;


import seedu.address.model.person.Field;

/**
 * Stub class of a field to test equality.
 */
public class FieldStub implements Field {

    private final int val;


    /**
     * Constructor of the stub
     * @param val The value that the stub is given.
     */
    public FieldStub(int val) {
        this.val = val;
    }


    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj instanceof FieldStub
                && ((FieldStub) obj).val == val;
    }
}
