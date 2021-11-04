package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS1231S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1231S;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.model.student.TelegramHandle;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.StudentBuilder;

public class ChangeGroupCommandTest {

    @Test
    public void execute_allFieldsSpecified_success() throws Exception {
        Student validStudent = new StudentBuilder().build();
        ModelStubWithStudent modelStub = new ModelStubWithStudent(validStudent);
        //Group builder builds CS2103T and Student builder builds a student in CS2103T
        Group originalGroup = new GroupBuilder().withStudent(validStudent.getName()).build();
        Group newGroup = new GroupBuilder().withGroupName(VALID_GROUP_NAME_CS1231S)
                .withDescription(VALID_GROUP_DESCRIPTION_CS1231S).build();
        modelStub.addGroup(originalGroup);
        modelStub.addGroup(newGroup);

        CommandResult commandResult = new ChangeGroupCommand(validStudent.getName(),
                newGroup.getGroupName()).execute(modelStub);

        assertEquals(String.format(ChangeGroupCommand.MESSAGE_CHANGE_GROUP_SUCCESS, validStudent.getName(),
                newGroup.getGroupName()), commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_studentDoesNotExist_throwsCommandException() {
        ModelStubNoStudent modelStub = new ModelStubNoStudent();
        Student validStudent = new StudentBuilder().build();
        Group validGroup = new GroupBuilder().withGroupName(VALID_GROUP_NAME_CS1231S)
                .withDescription(VALID_GROUP_DESCRIPTION_CS1231S).build();
        ChangeGroupCommand changeGroupCommand = new ChangeGroupCommand(validStudent.getName(),
                validGroup.getGroupName());

        assertThrows(CommandException.class,
                ChangeGroupCommand.MESSAGE_STUDENT_NONEXISTENT, () -> changeGroupCommand.execute(modelStub));
    }

    @Test
    public void execute_groupDoesNotExist_throwsCommandException() {
        ModelStubNoGroup modelStub = new ModelStubNoGroup();
        Student validStudent = new StudentBuilder().build();
        Group validGroup = new GroupBuilder().build();
        ChangeGroupCommand changeGroupCommand = new ChangeGroupCommand(validStudent.getName(),
                validGroup.getGroupName());

        assertThrows(CommandException.class,
                ChangeGroupCommand.MESSAGE_GROUP_NONEXISTENT, () -> changeGroupCommand.execute(modelStub));
    }

    @Test
    public void execute_changeToSameGroup_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        ModelStubWithStudent modelStub = new ModelStubWithStudent(validStudent);
        //Group builder builds CS2103T and Student builder builds a student in CS2103T
        Group originalGroup = new GroupBuilder().withStudent(validStudent.getName()).build();
        modelStub.addGroup(originalGroup);
        ChangeGroupCommand changeGroupCommand = new ChangeGroupCommand(validStudent.getName(),
                originalGroup.getGroupName());

        assertThrows(CommandException.class,
                ChangeGroupCommand.MESSAGE_CHANGE_TO_SAME_GROUP, () -> changeGroupCommand.execute(modelStub));
    }


    private class ModelStubNoStudent extends ModelStub {
        @Override
        public Student getStudentByName(Name studentName) {
            return null;
        }
    }

    private class ModelStubNoGroup extends ModelStub {
        @Override
        public Student getStudentByName(Name studentName) {
            return new StudentBuilder().build();
        }

        @Override
        public Group getGroupByGroupName(GroupName groupName) {
            return null;
        }
    }

    private class ModelStubWithStudent extends ModelStub {
        private Student student;
        private final ArrayList<Group> groupsAdded = new ArrayList<>();

        private ModelStubWithStudent(Student student) {
            this.student = student;
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsAdded.add(group);
        }

        @Override
        public Student getStudentByName(Name studentName) {
            if (this.student.getName().equals(studentName)) {
                return student;
            }
            return null;
        }

        @Override
        public Group getGroupByGroupName(GroupName groupName) {
            for (int i = 0; i < groupsAdded.size(); i++) {
                if (groupsAdded.get(i).getGroupName().equals(groupName)) {
                    return groupsAdded.get(i);
                }
            }
            return null;
        }

        @Override
        public void updateGroupStudent(Group group, Student student) {
            requireAllNonNull(group, student);
            // get students original group
            GroupName oldGroupName = student.getGroupName();
            Group oldGroup = getGroupByGroupName(oldGroupName);

            // remove reference to student in old group
            oldGroup.removeStudentName(student.getName());

            // get new group
            GroupName newGroupName = group.getGroupName();
            Group newGroup = getGroupByGroupName(newGroupName);

            // add reference to student in new group
            newGroup.addStudentName(student.getName());
        }

        @Override
        public void changeStudentGroup(Student student, Group newGroup) {
            requireNonNull(student);
            Student foundStudent = getStudentByName(student.getName());
            Name name = foundStudent.getName();
            TelegramHandle telegramHandle = foundStudent.getTelegramHandle();
            Email email = foundStudent.getEmail();
            Note note = foundStudent.getNote();
            GroupName groupName = newGroup.getGroupName();
            UniqueAssessmentList assessments = foundStudent.getUniqueAssessmentList();
            Student updatedStudent = new Student(name, telegramHandle, email, note, groupName, assessments);
            this.student = updatedStudent;
        }
    }

}
