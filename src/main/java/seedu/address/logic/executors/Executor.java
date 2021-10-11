package seedu.address.logic.executors;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.executors.exceptions.ExecuteException;
import seedu.address.model.Model;

public abstract class Executor {
    protected static Model model;

    public static void setup(Model model) {
        requireNonNull(model);
        Executor.model = model;
    }

    public abstract CommandResult execute() throws ExecuteException;
}
