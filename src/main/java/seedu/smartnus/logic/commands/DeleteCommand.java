package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NOT_IN_NOTE_PANEL;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NOT_IN_QUESTION_PANEL;
import static seedu.smartnus.logic.commands.CommandUtil.NOTE_KEYWORD;
import static seedu.smartnus.logic.commands.CommandUtil.QUESTION_KEYWORD;

import java.util.List;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.ui.panel.NoteListPanel;
import seedu.smartnus.ui.panel.QuestionListPanel;

/**
 * Deletes a question identified using it's displayed index from SmartNus.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the respective displayed list.\n"
            + "Parameters: list / note \n"
            + "INDEX (must be a positive integer between 1 and 2147483647 both inclusive)\n"
            + "Example: " + COMMAND_WORD + " " + QUESTION_KEYWORD + " 1";

    public static final String MESSAGE_DELETE_QUESTION_SUCCESS = "Deleted Question: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Note: %1$s";

    private final Index targetIndex;
    private final String deleteFromList;

    /**
     * Instantiates a new DeleteCommand object.
     * @param deleteItem question or note to be deleted.
     * @param targetIndex index of the item to be deleted.
     */
    public DeleteCommand(String deleteItem, Index targetIndex) {
        deleteFromList = deleteItem;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (deleteFromList) {
        case QUESTION_KEYWORD:
            if (!model.getPanel().equals(QuestionListPanel.QUESTION_PANEL)) {
                throw new CommandException(MESSAGE_NOT_IN_QUESTION_PANEL);
            }

            List<Question> lastShownQuestionList = model.getFilteredQuestionList();

            if (targetIndex.getZeroBased() >= lastShownQuestionList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
            }

            Question questionToDelete = lastShownQuestionList.get(targetIndex.getZeroBased());
            model.deleteQuestion(questionToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete));
        case NOTE_KEYWORD:
            if (!model.getPanel().equals(NoteListPanel.NOTE_PANEL)) {
                throw new CommandException(MESSAGE_NOT_IN_NOTE_PANEL);
            }

            List<Note> lastShownNoteList = model.getFilteredNoteList();

            if (targetIndex.getZeroBased() >= lastShownNoteList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
            }

            Note noteToDelete = lastShownNoteList.get(targetIndex.getZeroBased());
            model.deleteNote(noteToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete));
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && deleteFromList.equals(((DeleteCommand) other).deleteFromList) // state check
                && targetIndex.equals(((DeleteCommand) other).targetIndex));
    }
}
