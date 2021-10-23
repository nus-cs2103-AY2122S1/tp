package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentCommand.EditStudentDescriptor(DESC_AMY);
        assertEquals(DESC_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_AMY, DESC_AMY);

        // null -> returns false
        assertNotEquals(null, DESC_AMY);

        // different types -> returns false
        assertNotEquals(5, DESC_AMY);

        // different values -> returns false
        assertNotEquals(DESC_AMY, DESC_BOB);

        // different name -> returns false
        EditStudentCommand.EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);


        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different username -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withUserName(VALID_USERNAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different reponame -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withRepoName(VALID_REPONAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);


        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(DESC_AMY, editedAmy);
    }
}
