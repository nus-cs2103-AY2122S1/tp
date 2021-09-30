package seedu.address.model.group;

public class GroupName {
    public final String name;

    public GroupName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof GroupName // instanceof handles nulls
            && name.equals(((GroupName) other).name)); // state check
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
