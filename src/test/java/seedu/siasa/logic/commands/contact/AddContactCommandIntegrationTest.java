package seedu.siasa.logic.commands.contact;

import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.siasa.model.Model;
import seedu.siasa.model.ModelManager;
import seedu.siasa.model.UserPrefs;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddContactCommand}.
 */
public class AddContactCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSiasa(), new UserPrefs());
    }

    @Test
    public void execute_newContact_success() {
        Contact validContact = new ContactBuilder().build();

        Model expectedModel = new ModelManager(model.getSiasa(), new UserPrefs());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddContactCommand(validContact), model,
                String.format(AddContactCommand.MESSAGE_SUCCESS, validContact), expectedModel);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact contactInList = model.getSiasa().getContactList().get(0);
        assertCommandFailure(new AddContactCommand(contactInList), model, AddContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

}
