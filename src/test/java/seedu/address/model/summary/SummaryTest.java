package seedu.address.model.summary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;


public class SummaryTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_getNumberOfContacts_success() {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        int size = addressBook.getPersonList().size();

        Summary summary = new Summary(getTypicalAddressBook());
        int summarySize = summary.getNumberOfContacts();

        assertEquals(size, summarySize);
    }

}
