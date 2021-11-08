package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NOT_IN_NOTE_PANEL;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.ui.panel.NoteListPanel;

public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "note";
    public static final String MESSAGE_SUCCESS = "New note added: %s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in SmartNUS";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to SmartNUS. "
            + "Parameters: "
            + PREFIX_NOTE + "NOTE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE + "CS2103T has deadlines every week ";

    private final Note toAdd;

    /**
     * Temporary constructor
     */
    public AddNoteCommand(Note note) {
        requireNonNull(note);
        this.toAdd = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getPanel().equals(NoteListPanel.NOTE_PANEL)) {
            throw new CommandException(MESSAGE_NOT_IN_NOTE_PANEL);
        }

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteCommand) other).toAdd)); // check if the note to add is the same
    }
}
