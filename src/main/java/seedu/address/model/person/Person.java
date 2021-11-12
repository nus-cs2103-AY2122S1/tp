package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Person in contHACKS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Email email;
    private final Set<ModuleCode> moduleCodes = new HashSet<>();
    private final TeleHandle teleHandle;
    private final Phone phone;
    private final Remark remark;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Set<ModuleCode> moduleCodes,
                  Phone phone, TeleHandle teleHandle, Remark remark) {
        requireAllNonNull(name, email, moduleCodes, phone, teleHandle, remark);
        this.name = name;
        this.email = email;
        this.remark = remark;
        this.moduleCodes.addAll(moduleCodes);
        this.phone = phone;
        this.teleHandle = teleHandle;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable module codes set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(moduleCodes);
    }

    public ModuleCode get(ModuleCode moduleCode) {
        List<ModuleCode> listOfModuleCode = new ArrayList<>(moduleCodes);
        return listOfModuleCode.get(listOfModuleCode.indexOf(moduleCode));
    }

    public Phone getPhone() {
        return phone;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both persons have the same email, phone number or telegram handle.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        }

        // same email
        if (otherPerson.getEmail().equals(getEmail())) {
            return true;
        }

        // same phone number
        if (!otherPerson.getPhone().value.equals("") && otherPerson.getPhone().equals(getPhone())) {
            return true;
        }

        // same telegram handle
        if (!otherPerson.getTeleHandle().value.equals("") && otherPerson.getTeleHandle().equals(getTeleHandle())) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getModuleCodes().equals(getModuleCodes())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getTeleHandle().equals(getTeleHandle())
                && otherPerson.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, moduleCodes, phone, teleHandle, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Module : ");

        Set<ModuleCode> moduleCodes = getModuleCodes();
        builder.append(getModuleCodesInString(moduleCodes));

        if (!getPhone().value.isEmpty()) {
            builder.append("; Phone: ");
            builder.append(getPhone());
        }

        if (!getTeleHandle().value.isEmpty()) {
            builder.append("; Telegram: ");
            builder.append(getTeleHandle());
        }

        if (!remark.toString().trim().isEmpty()) {
            builder.append("; Remark: ");
            builder.append(getRemark());
        }

        return builder.toString();
    }

    private String getModuleCodesInString(Set<ModuleCode> moduleCodes) {
        ModuleCode[] array = moduleCodes.toArray(ModuleCode[]::new);
        StringBuilder sb = new StringBuilder(array[0].toString());
        for (int i = 1; i < array.length; i++) {
            sb.append(", ");
            sb.append(array[i]);
        }
        return sb.toString();
    }
}
