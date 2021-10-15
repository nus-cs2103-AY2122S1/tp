package seedu.notor.logic.executors;

import static java.util.Objects.requireNonNull;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.Model;

public abstract class Executor {
    protected static Model model;

    public static void setup(Model model) {
        requireNonNull(model);
        Executor.model = model;
    }

    public abstract CommandResult execute() throws ExecuteException;
}
