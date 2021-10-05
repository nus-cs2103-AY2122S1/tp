package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class EnrollCommand extends Command{

    public static final String COMMAND_WORD = "enroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrolls a specified student from a given TuitiONE lesson\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_GRADE + "GRADE "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "START_TIME"
            + "Example: " + "enroll 1 s/Science g/P5 d/Wed t/1230";

    private Lesson lesson;

    public EnrollCommand(Lesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        throw new CommandException("Not implemented EnrollCommand yet");
//        if (model.hasPerson(toAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
//        }
//
//        model.addPerson(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

//        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), remark));
    }
}
