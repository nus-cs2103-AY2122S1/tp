package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Score;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;


public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports data from a file. \n"
            + "Parameters: "
            + PREFIX_FILE + "<file_path> "
            + "[" + PREFIX_GROUP + "<number_of_group_columns>] "
            + "[" + PREFIX_ASSESSMENT + "<number_of_assessment_columns>] "
            + "[" + PREFIX_TAG + "<number_of_tag_columns>]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FILE + "data.csv "
            + PREFIX_GROUP + "2 "
            + PREFIX_ASSESSMENT + "10 "
            + PREFIX_TAG + "3";

    public static final String MESSAGE_SUCCESS = "Imported all students";
    public static final String MESSAGE_INVALID_FILE = "Failed to read from the file";
    public static final String MESSAGE_INVALID_NUMBER = "Failed to read the number of columns";
    public static final String MESSAGE_OUT_OF_BOUNDS = "Reached unexpected end of line while reading from file";

    private final int groupCount;
    private final int assessmentCount;
    private final int tagCount;
    private final Path file;

    /**
     * Creates an ImportCommand to import data from the given {@code file}
     */
    public ImportCommand(int groupCount, int assessmentCount, int tagCount, Path file) {
        this.groupCount = groupCount;
        this.assessmentCount = assessmentCount;
        this.tagCount = tagCount;
        this.file = file;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String fileContents;
        try {
            fileContents = FileUtil.readFromFile(file);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_INVALID_FILE);
        }

        AddressBook newAb = new AddressBook();
        List<Assessment> assessments = new ArrayList<>();

        String[] lines = fileContents.split("\n");
        String[] headers = lines[0].split(",", -1);
        for (int i = 0; i < assessmentCount; i++) {
            String assessmentName = readValue(headers, groupCount + 2 + i);
            Assessment assessment = makeAssessment(assessmentName);
            assessments.add(assessment);
            newAb.addAssessment(assessment);
        }

        for (int i = 1; i < lines.length; i++) {
            newAb.addStudent(readStudentFromRow(lines[i], assessments));
        }

        model.setAddressBook(newAb);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private Student readStudentFromRow(String row, List<Assessment> assessments) throws CommandException {
        String[] values = row.split(",", -1);
        Name name = makeName(readValue(values, 0));
        ID id = makeId(readValue(values, 1));
        int readingColumn = 2;

        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < groupCount; i++, readingColumn++) {
            String groupName = readValue(values, readingColumn);
            if (!groupName.isEmpty()) {
                groups.add(makeGroup(groupName));
            }
        }

        Map<Assessment, Score> scores = new HashMap<>();
        for (int i = 0; i < assessmentCount; i++, readingColumn++) {
            String assessmentScore = readValue(values, readingColumn);
            if (!assessmentScore.isEmpty()) {
                Score score = makeScore(assessmentScore);
                scores.put(assessments.get(i), score);
                assessments.get(i).setScores(id, score);
            }
        }

        Set<Tag> tags = new HashSet<>();
        for (int i = 0; i < tagCount; i++, readingColumn++) {
            String tagName = readValue(values, readingColumn);
            if (!tagName.isEmpty()) {
                tags.add(makeTag(tagName));
            }
        }

        return new Student(name, id, groups, scores, tags);
    }

    private String readValue(String[] row, int column) throws CommandException {
        if (column >= row.length) {
            // avoid array out of bounds error
            throw new CommandException(MESSAGE_OUT_OF_BOUNDS);
        }
        return row[column].trim();
    }

    private Name makeName(String name) throws CommandException {
        if (!Name.isValidName(name)) {
            throw new CommandException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private ID makeId(String id) throws CommandException {
        if (!ID.isValidID(id)) {
            throw new CommandException(ID.MESSAGE_CONSTRAINTS);
        }
        return new ID(id);
    }

    private Group makeGroup(String groupName) throws CommandException {
        if (!Group.isValidGroup(groupName)) {
            throw new CommandException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(groupName);
    }

    private Score makeScore(String score) throws CommandException {
        if (!Score.isValidScore(score)) {
            throw new CommandException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Score(score);
    }

    private Assessment makeAssessment(String assessmentName) throws CommandException {
        if (!Assessment.isValidAssessment(assessmentName)) {
            throw new CommandException(Assessment.MESSAGE_CONSTRAINTS);
        }
        return new Assessment(assessmentName);
    }

    private Tag makeTag(String tagName) throws CommandException {
        if (!Tag.isValidTagName(tagName)) {
            throw new CommandException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ImportCommand
                && this.file.equals(((ImportCommand) other).file)
                && this.groupCount == ((ImportCommand) other).groupCount
                && this.assessmentCount == ((ImportCommand) other).assessmentCount
                && this.tagCount == ((ImportCommand) other).tagCount);
    }
}

