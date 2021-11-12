package seedu.teachbook.model.classobject;

import static java.util.Objects.requireNonNull;

/**
 * A {@code ClassNameDescriptor} describes a {@code ClassName}, although the class with the name may not exist
 * in the TeachBook currently. It contains a {@code String} without format constraint as the name of a class.
 * A {@code ClassNameDescriptor} can equal to a {@code ClassName} when they contain the same {@code String}.
 */
public class ClassNameDescriptor {

    public final String nameOfClass;

    /**
     * Constructs a {@code ClassNameDescriptor}.
     *
     * @param className a class name as a string.
     */
    public ClassNameDescriptor(String className) {
        requireNonNull(className);
        nameOfClass = className;
    }

    @Override
    public String toString() {
        return nameOfClass;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClassNameDescriptor) {
            return nameOfClass.equals(((ClassNameDescriptor) other).nameOfClass);
        } else if (other instanceof ClassName) {
            return nameOfClass.equals(((ClassName) other).nameOfClass);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return nameOfClass.hashCode();
    }

}
