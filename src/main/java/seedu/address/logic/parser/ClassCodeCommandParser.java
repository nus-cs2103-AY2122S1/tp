package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ClassCodeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.ClassCode;

public class ClassCodeCommandParser implements Parser<ClassCodeCommand> {

    @Override
    public ClassCodeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASSCODE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassCodeCommand.MESSAGE_USAGE), ive);
        }

        String classCode = argMultimap.getValue(PREFIX_CLASSCODE).orElse("");

        return new ClassCodeCommand(index, new ClassCode(classCode));
    }

}
