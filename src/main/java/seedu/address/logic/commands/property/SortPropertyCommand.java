package seedu.address.logic.commands.property;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.Model;
import seedu.address.model.field.SortType;

import static java.util.Objects.requireNonNull;

public class SortPropertyCommand extends SortCommand {
    public static final String MESSAGE_SUCCESS = "Sorted all properties by %s";

    public SortPropertyCommand(SortType sortType) {
        super(sortType);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        SortType sortType = getSortType();
        switch (sortType) {
        case PRICE:
            model.sortPropertiesPrice();
            break;
        case NAME:
            model.sortPropertiesName();
            break;
        default:
            assert false;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortType));
    }
}
