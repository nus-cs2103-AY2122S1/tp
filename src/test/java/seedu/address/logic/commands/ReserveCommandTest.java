package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Phone;


class ReserveCommandTest {
    private static final int DUMMY_NUMBER_OF_PEOPLE = 2;
    private static final Phone DUMMY_PHONE = new Phone("98765432");
    private static final LocalDateTime DUMMY_DATE_TIME =
            LocalDateTime.parse("11/11/2021 2000", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        assertCommandFailure(
                new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME),
                model,
                String.format(
                        "Reserve: phone=%s, numberOfPeople=%d, time=%s",
                        DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME));
    }
}
