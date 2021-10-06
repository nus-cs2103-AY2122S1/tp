package seedu.address.model.person;


import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthdays should come in the form of yyyy-MM-dd";
    public static final String VALIDATION_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public final LocalDate birthday;

    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.birthday = LocalDate.parse(birthday);
    }

    public static boolean isValidBirthday(String birthday) {
        return birthday.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.birthday.toString();
    }

}
