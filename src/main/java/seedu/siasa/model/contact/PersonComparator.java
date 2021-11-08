package seedu.siasa.model.contact;

import java.util.Comparator;

public class PersonComparator {
    public static final Comparator<Contact> CONTACT_SORT_BY_ALPHA_ASC = (
            a, b) -> String.CASE_INSENSITIVE_ORDER.compare(a.getName().toString(), b.getName().toString());

    public static final Comparator<Contact> CONTACT_SORT_BY_ALPHA_DESC = (
            a, b) -> -String.CASE_INSENSITIVE_ORDER.compare(a.getName().toString(), b.getName().toString());
}
