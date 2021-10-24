package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;

public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_ARGUMENTS = "Student: %1$s, Notes: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the note of the student identified by their student name"
            + " existing note will be overwritten by the input.\n"
            + "Parameters: " + PREFIX_NAME + "STUDENTNAME " + PREFIX_NOTE + "NOTE\n"
            + "Example: " + COMMAND_WORD + " Brian " + PREFIX_NOTE + " is bad at UML diagrams.";

    private final Name studentName;
    private final String note;

    /**
     * Constructor of the NoteCommand with the given student name and note.
     */
    public NoteCommand(Name studentName, String note) {
        this.studentName = studentName;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        return new CommandResult(MESSAGE_ARGUMENTS); // todo brian must change this later pls thank u brian.
    }
}
