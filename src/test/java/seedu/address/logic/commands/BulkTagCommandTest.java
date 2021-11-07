package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUS_NETWORK_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUS_NETWORK_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class BulkTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_bulkTagPersonList_success() {
        HashSet<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("friends"));
        BulkTagCommand bulkTagCommand = new BulkTagCommand(tagSet);

        StringBuilder tagsSb = new StringBuilder();
        for (Tag tag : tagSet) {
            tagsSb.append(tag.toString() + " ");
        }

        String stringTags = tagsSb.toString().trim().substring(0, tagsSb.toString().length() - 1);

        String expectedMessage = String.format(BulkTagCommand.MESSAGE_SUCCESS, stringTags);
        model.setAddressBook(new AddressBook());

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());
        System.out.println(model.equals(expectedModel));

        model.addPerson(new PersonBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withGitHubId(VALID_GITHUB_ID_AMY)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_AMY)
                .withType(VALID_TYPE_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY)
                .withTutorialId(VALID_TUTORIAL_ID_AMY)
                .build());

        model.addPerson(new PersonBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withGitHubId(VALID_GITHUB_ID_BOB)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_BOB)
                .withType(VALID_TYPE_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withTutorialId(VALID_TUTORIAL_ID_BOB)
                .build());

        expectedModel.addPerson(new PersonBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND)
                .withGitHubId(VALID_GITHUB_ID_AMY)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_AMY)
                .withType(VALID_TYPE_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY)
                .withTutorialId(VALID_TUTORIAL_ID_AMY)
                .build());

        expectedModel.addPerson(new PersonBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withGitHubId(VALID_GITHUB_ID_BOB)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_BOB)
                .withType(VALID_TYPE_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withTutorialId(VALID_TUTORIAL_ID_BOB)
                .build());

        assertCommandSuccess(bulkTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        HashSet<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("friends"));
        BulkTagCommand bulkTagCommand1 = new BulkTagCommand(tagSet);
        BulkTagCommand bulkTagCommand2 = new BulkTagCommand(tagSet);

        tagSet = new HashSet<>();
        tagSet.add(new Tag("owesMoney"));
        BulkTagCommand bulkTagCommand3 = new BulkTagCommand(tagSet);

        tagSet = new HashSet<>();
        tagSet.add(new Tag("friends"));
        tagSet.add(new Tag("owesMoney"));
        BulkTagCommand bulkTagCommand4 = new BulkTagCommand(tagSet);
        BulkTagCommand bulkTagCommand5 = new BulkTagCommand(tagSet);

        // same object -> returns true
        assertTrue(bulkTagCommand1.equals(bulkTagCommand1));

        // same values -> returns true
        assertTrue(bulkTagCommand1.equals(bulkTagCommand2));
        assertTrue(bulkTagCommand4.equals(bulkTagCommand5));

        // different tags -> returns false
        assertFalse(bulkTagCommand1.equals(bulkTagCommand3));

        // different number of tags -> returns false
        assertFalse(bulkTagCommand1.equals(bulkTagCommand4));

        // null -> returns false
        assertFalse(bulkTagCommand1.equals(null));
    }
}
