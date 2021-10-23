package seedu.address.logic.commands;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Score;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports data from to a file. \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Exported to file: %1$s";
    public static final String MESSAGE_FAILURE = "Failed to export to file";

    public final String BASE_PATH = "sourceControl.csv";
    private int tries = 0;
    private int groupColumns;
    private int assessmentColumns;
    private int tagColumns;
    private Path file;

    /**
     * Creates an ExportCommand to export data to a file. Ensures that a new file is created every time.
     */
    public ExportCommand() {
        this.file = Path.of(BASE_PATH);
        while (FileUtil.isFileExists(file)) {
            tries++;
            this.file = Path.of(BASE_PATH + "(" + tries + ")");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            FileUtil.createFile(file);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        StringBuilder writeContent = new StringBuilder();
        writeContent.append("Name,Id,");

        List<Group> groups = model.getAddressBook().getGroupList();
        appendGroupHeaders(writeContent, groups);

        List<Assessment> assessments = model.getAddressBook().getAssessmentList();
        appendAssessmentHeaders(writeContent, assessments);

        List<Student> students = model.getAddressBook().getStudentList();
        appendTagHeaders(writeContent, students);

        replaceLastCharacterWithNewLine(writeContent);

        for (Student student : students) {
            appendStudentRow(writeContent, student, assessments);
        }

        try {
            FileUtil.writeToFile(file, writeContent.toString());
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, file.toString()));
    }

    private void appendGroupHeaders(StringBuilder writeContent, List<Group> groups) {
        groupColumns = groups.size();

        for (int i = 1; i <= groupColumns; i++) {
            writeContent.append("Group ").append(i).append(",");
        }
    }

    private void appendAssessmentHeaders(StringBuilder writeContent, List<Assessment> assessments) {
        assessmentColumns = assessments.size();

        for (Assessment assessment : assessments) {
            writeContent.append(assessment.value).append(",");
        }
    }

    private void appendTagHeaders(StringBuilder writeContent, List<Student> students) {
        tagColumns = 0;
        for (Student student : students) {
            tagColumns = Math.max(tagColumns, student.getTags().size());
        }

        for (int i = 1; i <= tagColumns; i++) {
            writeContent.append("Tag ").append(i).append(",");
        }
    }

    private void appendStudentRow(StringBuilder writeContent, Student student, List<Assessment> assessments) {
        writeContent.append(student.getName().fullName);
        writeContent.append(student.getId().getValue());

        List<Group> groups = student.getGroups();
        for (Group group : groups) {
            writeContent.append(group.getValue()).append(",");
        }
        for (int i = groups.size(); i < groupColumns; i++) {
            writeContent.append(",");
        }

        for (Assessment assessment : assessments) {
            Map<ID, Score> scores = assessment.getScores();
            if (scores.containsKey(student.getId())) {
                writeContent.append(scores.get(student.getId()).getValue());
            }
            writeContent.append(",");
        }

        List<Tag> tags = List.copyOf(student.getTags());
        for (Tag tag : tags) {
            writeContent.append(tag.tagName).append(",");
        }
        for (int i = tags.size(); i < tagColumns; i++) {
            writeContent.append(",");
        }

        replaceLastCharacterWithNewLine(writeContent);
    }

    private void replaceLastCharacterWithNewLine(StringBuilder writeContent) {
        int length = writeContent.length();
        writeContent.replace(length, length - 1, "\n");
    }
}
