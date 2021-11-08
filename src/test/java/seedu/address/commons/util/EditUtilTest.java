package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.EditUtil.EditPersonDescriptor;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;



class EditUtilTest {

    @Test
    public void copy_isEqual() {
        EditPersonDescriptor base = new EditPersonDescriptor();
        EditPersonDescriptor test = new EditPersonDescriptor(base);

        assertEquals(base, test);
    }

    @Test
    public void isAnyFieldEdited_true() {
        EditPersonDescriptor editPersonDescriptor;

        editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setAddress(new Address(VALID_ADDRESS_AMY));
        assertTrue(editPersonDescriptor.isAnyFieldEdited());

        editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setBirthday(new Birthday(VALID_BIRTHDAY_AMY));
        assertTrue(editPersonDescriptor.isAnyFieldEdited());

        editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setEmail(new Email(VALID_EMAIL_AMY));
        assertTrue(editPersonDescriptor.isAnyFieldEdited());

        editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setName(new Name(VALID_NAME_AMY));
        assertTrue(editPersonDescriptor.isAnyFieldEdited());

        editPersonDescriptor = new EditPersonDescriptor();
        Set<Tag> testTags = new HashSet<>();
        testTags.add(new Tag(VALID_TAG_FRIEND));
        editPersonDescriptor.setTags(testTags);
        assertTrue(editPersonDescriptor.isAnyFieldEdited());

        editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setPhone(new Phone(VALID_PHONE_AMY));
        assertTrue(editPersonDescriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_false() {
        EditPersonDescriptor test = new EditPersonDescriptor();

        assertFalse(test.isAnyFieldEdited());
    }

    @Test
    public void equals_emptyDescriptor() {
        EditPersonDescriptor left = new EditPersonDescriptor();
        EditPersonDescriptor right = new EditPersonDescriptor();

        assertEquals(left, right);
    }

    @Test
    public void equals_filledDescriptor() {
        EditPersonDescriptor left = new EditPersonDescriptor();
        EditPersonDescriptor right = new EditPersonDescriptor();

        left.setAddress(new Address(VALID_ADDRESS_AMY));
        left.setBirthday(new Birthday(VALID_BIRTHDAY_AMY));
        left.setEmail(new Email(VALID_EMAIL_AMY));
        left.setName(new Name(VALID_NAME_AMY));
        Set<Tag> leftTags = new HashSet<>();
        leftTags.add(new Tag(VALID_TAG_FRIEND));
        left.setTags(leftTags);
        left.setPhone(new Phone(VALID_PHONE_AMY));

        right.setAddress(new Address(VALID_ADDRESS_AMY));
        right.setBirthday(new Birthday(VALID_BIRTHDAY_AMY));
        right.setEmail(new Email(VALID_EMAIL_AMY));
        right.setName(new Name(VALID_NAME_AMY));
        Set<Tag> rightTags = new HashSet<>();
        rightTags.add(new Tag(VALID_TAG_FRIEND));
        right.setTags(rightTags);
        right.setPhone(new Phone(VALID_PHONE_AMY));

        assertEquals(left, right);
    }
}
