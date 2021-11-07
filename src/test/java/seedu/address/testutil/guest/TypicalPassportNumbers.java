package seedu.address.testutil.guest;

import seedu.address.model.guest.PassportNumber;

public class TypicalPassportNumbers {
    public static final PassportNumber PASSPORT_NUMBER_FIRST_PERSON = new PassportNumber("E0123122G");
    public static final PassportNumber PASSPORT_NUMBER_SECOND_PERSON = new PassportNumber("T12312311A");
    public static final PassportNumber PASSPORT_NUMBER_THIRD_PERSON = new PassportNumber("M123123124D");
    public static final PassportNumber PASSPORT_NUMBER_FOURTH_PERSON_NOT_ADDED = new PassportNumber("M999123124D");
    public static final PassportNumber PASSPORT_NUMBER_UNUSED = new PassportNumber("L21312314H");
    // Default passport number used in builders
    public static final PassportNumber PASSPORT_NUMBER_DEFAULT = new PassportNumber("A163812686D");
}
