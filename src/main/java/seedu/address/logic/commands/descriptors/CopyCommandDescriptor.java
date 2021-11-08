package seedu.address.logic.commands.descriptors;


import static java.util.Objects.requireNonNull;

/**
 * This class stores information about which field to be copied based on the string given to it.
 */
public class CopyCommandDescriptor {

    public enum Field {

        PHONE("phone"), EMAIL("email"), NAME("name"), INVALID("invalid");

        private String field;

        Field(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }

    private Field fieldToCopy;

    /**
     * Constructor of the {@code CopyCommandDescriptor}
     *
     * @param fieldToCopy A string to represent which field to copy
     */
    public CopyCommandDescriptor(String fieldToCopy) {
        requireNonNull(fieldToCopy);
        switch (fieldToCopy) {
        case "phone":
            this.fieldToCopy = Field.PHONE;
            break;
        case "email":
            this.fieldToCopy = Field.EMAIL;
            break;
        case "name":
            this.fieldToCopy = Field.NAME;
            break;
        default:
            this.fieldToCopy = Field.INVALID;
        }
    }

    public boolean hasValidField() {
        return !fieldToCopy.equals(Field.INVALID);
    }

    @Override
    public String toString() {
        return fieldToCopy.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommandDescriptor // instanceof handles nulls
                && fieldToCopy.equals(((CopyCommandDescriptor) other).fieldToCopy)); // state check
    }

    public Field getField() {
        return this.fieldToCopy;
    }

    @Override
    public int hashCode() {
        return fieldToCopy.hashCode();
    }
}
