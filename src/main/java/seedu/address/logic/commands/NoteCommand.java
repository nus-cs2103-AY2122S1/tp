package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;

public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the note of the student identified by their student name."
            + " Existing note will be overwritten by the input.\n"
            + "Parameters: " + PREFIX_NAME + "STUDENTNAME " + PREFIX_NOTE + "NOTE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Brian " + PREFIX_NOTE + "is bad at UML diagrams.";

    public static final String MESSAGE_SUCCESS = "Success. Student: %1$s, Notes: %2$s";
    public static final String MESSAGE_STUDENT_NONEXISTENT =
            "The student does not exist. Please choose a student that exists.";

    private final Name studentName;
    private final Note note;

    /**
     * Constructor of the NoteCommand with the given student name and note.
     */
    public NoteCommand(Name studentName, Note note) {
        requireNonNull(studentName);
        requireNonNull(note);

        this.studentName = studentName;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Student student = model.getStudentByName(studentName);
        if (student == null) {
            throw new CommandException(MESSAGE_STUDENT_NONEXISTENT);
        }

        Student editedStudent = model.updateStudentNote(student, note);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentName, note), false, false, editedStudent);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        // state check
        NoteCommand n = (NoteCommand) other;
        return studentName.equals(n.studentName)
                && note.equals(n.note);
    }
}
