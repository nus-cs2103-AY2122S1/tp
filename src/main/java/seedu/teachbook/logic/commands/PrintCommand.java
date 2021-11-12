package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_COLUMN;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.teachbook.commons.util.ExcelUtil;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;

/**
 * Print an Excel sheet according to current class.
 *
 * @author Lim Yu Long
 */
public class PrintCommand extends Command {

    public static final String COMMAND_WORD = "print";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Prints the currently displayed student list as an Excel file.\n"
            + "Parameters: "
            + "[" + PREFIX_COLUMN + "class] "
            + "[" + PREFIX_COLUMN + "phone] "
            + "[" + PREFIX_COLUMN + "email] "
            + "[" + PREFIX_COLUMN + "address] "
            + "[" + PREFIX_COLUMN + "tags] "
            + "[" + PREFIX_COLUMN + "remark] "
            + "[" + PREFIX_COLUMN + "attendance] "
            + "[" + PREFIX_COLUMN + "grade] "
            + "[" + PREFIX_COLUMN + "COLUMN_TITLE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COLUMN + "class "
            + PREFIX_COLUMN + "phone "
            + PREFIX_COLUMN + "address "
            + PREFIX_COLUMN + "attendance "
            + PREFIX_COLUMN + "grade "
            + PREFIX_COLUMN + "Signature";

    public static final String MESSAGE_SUCCESS = "Excel file generated under directory %1$s";
    public static final String MESSAGE_RESTART = "try restarting the app and executing \"print\" command again.\n";
    public static final String MESSAGE_CONTACT_SUPPORT = "If the above does not work or you have any queries, "
            + "please post an issue at https://github.com/AY2122S1-CS2103T-W10-2/tp/issues";
    public static final String MESSAGE_FILE_NOT_FOUND = "Print failed! "
            + "This can be caused by an Excel file with a conflicting name is opened. "
            + "Try closing the file before using \"print\" command.\n"
            + "Else, this can also be caused by your system configuration. "
            + "For \"print\" command to execute, "
            + "the app needs to access and write to your system's downloads folder. "
            + "Try giving the app suitable permissions.\n"
            + "If these does not solve the issue, "
            + MESSAGE_RESTART + MESSAGE_CONTACT_SUPPORT;
    public static final String MESSAGE_FAIL = "Print failed! "
            + "An error occurs when printing the student list due to unknown reasons. You can "
            + MESSAGE_RESTART + MESSAGE_CONTACT_SUPPORT;

    private final List<String> columnList;

    /**
     * Creates a PrintCommand to print the specified {@code ColumnList}
     */
    public PrintCommand(List<String> columnList) {
        this.columnList = columnList;
    }


    /**
     * Utility code to generate column base on student list and column required
     *
     * @param columnName name of column to be generated
     * @param studentList list of student to generate the column for
     * @return list of string with the student information base on {@code columnName}.
     */
    public static List<String> generateColumn(String columnName, List<Student> studentList) {
        List<String> result = new ArrayList<>();

        switch (columnName.toLowerCase()) {
        case "class":
            result.add("Class");
            for (Student student : studentList) {
                result.add(student.getStudentClass().getClassName().nameOfClass);
            }
            break;

        case "phone":
            result.add("Phone Number");
            for (Student student : studentList) {
                result.add(student.getPhone().value);
            }
            break;

        case "email":
            result.add("Email");
            for (Student student : studentList) {
                result.add(student.getEmail().value);
            }
            break;

        case "address":
            result.add("Address");
            for (Student student : studentList) {
                result.add(student.getAddress().value);
            }
            break;

        case "tags":
            result.add("Tags");
            for (Student student : studentList) {
                result.add(student.getTags().stream().map(Tag::toString).collect(Collectors.joining(" ")));
            }
            break;

        case "remark":
            result.add("Remark");
            for (Student student : studentList) {
                result.add(student.getRemark().value);
            }
            break;

        case "attendance":
            result.add("Attendance");
            for (Student student : studentList) {
                result.add(student.getAttendanceString());
            }
            break;

        case "grade":
            result.add("Grade");
            for (Student student : studentList) {
                result.add(student.getGrade().value);
            }
            break;

        default:
            result.add(columnName);
            for (int i = 0; i < studentList.size(); i++) {
                result.add("");
            }
            break;
        }

        return result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getFilteredStudentList();
        List<List<String>> toPrint = new ArrayList<>();
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Name");
        for (Student s : studentList) {
            studentNames.add(s.getName().fullName);
        }

        toPrint.add(studentNames);
        for (String columnName : columnList) {
            toPrint.add(generateColumn(columnName, studentList));
        }

        String filePath;
        try {
            filePath = ExcelUtil.toExcel(toPrint);
        } catch (FileNotFoundException | SecurityException ex) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception ex) {
            throw new CommandException(MESSAGE_FAIL);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath), false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrintCommand // instanceof handles nulls
                && columnList.equals(((PrintCommand) other).columnList));
    }

}
