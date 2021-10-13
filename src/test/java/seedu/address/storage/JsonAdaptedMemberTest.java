package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMember.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.BENSON;
import static seedu.address.testutil.TypicalMembers.JERRY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.data.Name;
import seedu.address.model.data.member.Address;
import seedu.address.model.data.member.Email;
import seedu.address.model.data.member.Member;
import seedu.address.model.data.member.Phone;
import seedu.address.model.task.TaskList;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.TypicalTasks;

public class JsonAdaptedMemberTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_POSITION = "#friend";
    private static final String INVALID_TASK_NAME = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().get().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().get().toString();
    private static final List<JsonAdaptedPosition> VALID_POSITIONS = BENSON.getPositions().stream()
            .map(JsonAdaptedPosition::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TASK_LIST = TypicalTasks.getTypicalTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMemberDetails_returnsMember() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(BENSON);
        assertEquals(BENSON, member.toModelType());
    }

    @Test
    public void toModelType_validMemberDetails_returnsMember2() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(JERRY);
        assertEquals(JERRY, member.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_POSITIONS, VALID_TASK_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(null, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_POSITIONS, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_POSITIONS, VALID_TASK_LIST);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_POSITIONS, VALID_TASK_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_POSITIONS, VALID_TASK_LIST);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_POSITIONS, VALID_TASK_LIST);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        try {
            member.toModelType();
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPositions_throwsIllegalValueException() {
        List<JsonAdaptedPosition> invalidPositions = new ArrayList<>(VALID_POSITIONS);
        invalidPositions.add(new JsonAdaptedPosition(INVALID_POSITION));
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, invalidPositions, VALID_TASK_LIST);
        assertThrows(IllegalValueException.class, member::toModelType);
    }

    @Test
    public void toModelType_invalidTaskList_throwsIllegalValueException() {
        List<JsonAdaptedTask> invalidTaskList = new ArrayList<>(VALID_TASK_LIST);
        invalidTaskList.add(new JsonAdaptedTask(INVALID_TASK_NAME, false));
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_POSITIONS, invalidTaskList);
        assertThrows(IllegalValueException.class, member::toModelType);
    }

    @Test
    public void constructor_test() {
        JsonAdaptedMember jsonAdaptedMember = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_POSITIONS, VALID_TASK_LIST);
        try {
            Member member = jsonAdaptedMember.toModelType();
            Member expectedMember = new MemberBuilder(BENSON).build();
            TaskList taskList = new TaskList();
            taskList.setTasks((TypicalTasks.getTypicalTasks()));
            assertEquals(expectedMember, member);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

}
