package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUITIONS;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Remark;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.ui.RemarkEditor;

public class RemarkClassCommand extends Command {

    public static final String COMMAND_WORD = "remarkclass";
    public static final String MESSAGE_UPDATE_REMARK_SUCCESS = "Remark updated for Tuition Class: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Tuition Class: %1$s";
    public static final String REMARK_EDITOR_ERROR_MESSAGE = "Something went wrong with the remark editor!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the tuition class identified "
            + "by the index number used in the last tuition classes listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "physics homework: read chapter 3 pg 49-53.";

    private final Index index;

    /**
     * @param index of the tuition class in the filtered tuition class list to edit the remark
     */
    public RemarkClassCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<TuitionClass> lastShownList = model.getFilteredTuitionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        TuitionClass classToEdit = lastShownList.get(index.getZeroBased());

        String name = classToEdit.getName().name;
        String remarkToEdit = classToEdit.getRemark().value;
        Remark newRemark = showRemarkEditor(name, remarkToEdit);

        TuitionClass editedClass = new TuitionClass(classToEdit.getName(), classToEdit.getLimit(),
                classToEdit.getTimeslot(), classToEdit.getStudentList(), newRemark);

        model.setTuition(classToEdit, editedClass);
        model.updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);

        return new CommandResult(generateSuccessMessage(editedClass));
    }

    private String generateSuccessMessage(TuitionClass classToEdit) {
        String remark = classToEdit.getRemark().value;
        String message = !remark.isEmpty() ? MESSAGE_UPDATE_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, classToEdit);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkClassCommand)) {
            return false;
        }

        // state check
        RemarkClassCommand e = (RemarkClassCommand) other;
        return index.equals(e.index);
    }

    public Remark showRemarkEditor(String name, String remarkToEdit) throws CommandException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/RemarkEditor.fxml"));
            DialogPane remarkEditor = fxmlLoader.load();

            RemarkEditor remarkController = fxmlLoader.getController();
            remarkController.setRemark(name, remarkToEdit);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(remarkEditor);
            dialog.setTitle("Remark Editor");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                return remarkController.getNewRemark();
            }
        } catch (IOException e) {
            throw new CommandException(REMARK_EDITOR_ERROR_MESSAGE, e);
        }
        return new Remark(remarkToEdit);
    }
}
