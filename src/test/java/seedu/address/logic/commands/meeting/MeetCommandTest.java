package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MeetCommand}.
 */

public class MeetCommandTest {
    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        MeetCommandTest.ModelStubAcceptingMeetingAdded modelStub = new MeetCommandTest.ModelStubAcceptingMeetingAdded();
        Meeting validMeeting = new MeetingBuilder().build();

        CommandResult commandResult = new MeetCommand(validMeeting).execute(modelStub);

        assertEquals(String.format(MeetCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_conflictingMeeting_throwsCommandException() {
        Meeting validMeeting = new MeetingBuilder().build();
        MeetCommand meetCommand = new MeetCommand(validMeeting);
        MeetCommandTest.ModelStub modelStub = new MeetCommandTest.ModelStubWithMeeting(validMeeting);

        assertThrows(CommandException.class,
                MeetCommand.MESSAGE_MEETING_CONFLICT, () -> meetCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Meeting presentation = new MeetingBuilder().withTitle("Presentation").build();
        Meeting meeting = new MeetingBuilder().withTitle("Meeting").build();
        MeetCommand addPresentationCommand = new MeetCommand(presentation);
        MeetCommand addMeetingCommand = new MeetCommand(meeting);

        // same object -> returns true
        assertTrue(addPresentationCommand.equals(addPresentationCommand));

        // same values -> returns true
        MeetCommand addPresentationCommandCopy = new MeetCommand(presentation);
        assertTrue(addPresentationCommand.equals(addPresentationCommandCopy));

        // different types -> returns false
        assertFalse(addPresentationCommand.equals(1));

        // null -> returns false
        assertFalse(addPresentationCommand.equals(null));

        // different meeting -> returns false
        assertFalse(addPresentationCommand.equals(addMeetingCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void massDeleteStudent(List<Student> target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEqualHistory(Model other) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTeacher(Teacher teacher) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTeacher(Teacher teacher) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeacher(Teacher target, Teacher editedTeacher) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTeacher(Teacher target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void massDeleteTeacher(List<Teacher> target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Teacher> getFilteredTeacherList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTeacherList(Predicate<Teacher> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeetingConflict(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearMeetings() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single meeting.
     */
    private class ModelStubWithMeeting extends MeetCommandTest.ModelStub {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeetingConflict(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.hasConflictWith(meeting);
        }
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends MeetCommandTest.ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeetingConflict(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::hasConflictWith);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}

