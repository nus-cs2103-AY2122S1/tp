package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents the types of sorting methods we can apply to employees in RHRH.
 */
public class SortByEmployee {

    private static final String NAME = "n";
    private static final String PHONE = "p";
    private static final String ADDRESS = "a";
    private static final String EMAIL = "e";
    private static final String SALARY = "sal";
    private static final String LEAVES = "l";
    private static final String JOB_TITLE = "jt";

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String SALARY_DESC = "salary";
    private static final String LEAVES_DESC = "leaves";
    private static final String JOB_TITLE_DESC = "job title";

    public static final String MESSAGE_CONSTRAINTS = "Sort by can only be 1 of the employee fields: "
            + NAME_DESC + "/" + PHONE_DESC + "/" + ADDRESS_DESC + "/" + EMAIL_DESC + "/" + SALARY_DESC + "/"
            + LEAVES + "/" + JOB_TITLE_DESC + " and it should not be blank";

    private final String sortBy;

    /**
     * Constructs an {@code Address}.
     *
     * @param sortBy A valid sorting type.
     */
    public SortByEmployee(String sortBy) {
        requireNonNull(sortBy);
        checkArgument(isValidSortBy(sortBy), MESSAGE_CONSTRAINTS);
        this.sortBy = sortBy;
    }

    /**
     * Returns true if a given string is a valid sorting type.
     */
    public static boolean isValidSortBy(String test) {
        switch (test) {
        case NAME:
        case ADDRESS:
        case EMAIL:
        case PHONE:
        case SALARY:
        case LEAVES:
        case JOB_TITLE:
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns a comparator to sort the employee list based on the sorting type and order given.
     * @param isAscending A boolean representing if sorting order is ascending.
     * @return A comparator to sort the employee list.
     */
    public Comparator<Employee> selectComparator(boolean isAscending) throws ParseException {
        switch (sortBy) {
        case NAME:
            return EmployeeComparator.getNameComparator(isAscending);
        case ADDRESS:
            return EmployeeComparator.getAddressComparator(isAscending);
        case EMAIL:
            return EmployeeComparator.getEmailComparator(isAscending);
        case PHONE:
            return EmployeeComparator.getPhoneComparator(isAscending);
        case SALARY:
            return EmployeeComparator.getSalaryComparator(isAscending);
        case LEAVES:
            return EmployeeComparator.getLeavesComparator(isAscending);
        case JOB_TITLE:
            return EmployeeComparator.getJobTitleComparator(isAscending);
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public String toString() {
        switch (sortBy) {
        case ADDRESS:
            return ADDRESS_DESC;
        case EMAIL:
            return EMAIL_DESC;
        case PHONE:
            return PHONE_DESC;
        case SALARY:
            return SALARY_DESC;
        case LEAVES:
            return LEAVES_DESC;
        case JOB_TITLE:
            return JOB_TITLE_DESC;
        default:
            return NAME_DESC;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByEmployee // instanceof handles nulls
                && sortBy.equals(((SortByEmployee) other).sortBy)); // state check
    }

    @Override
    public int hashCode() {
        return sortBy.hashCode();
    }
}
