package seedu.address.logic.parser.modulelesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_MORE_THAN_ONE_LESSON_CODE;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_NO_LESSON_CODE_PROVIDED;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.EditLessonDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCode;

/**
 * Parses input arguments and creates a new EditModuleLessonCommand object
 */
public class EditModuleLessonCommandParser implements Parser<EditModuleLessonCommand> {

    private final Logger logger = LogsCenter.getLogger(EditModuleLessonCommandParser.class);

    /**
     * Parses the given {@code args} in the context of the EditModuleLessonCommand
     * and returns an EditModuleLessonCommand for execution.
     * @throws ParseException if {@code args} does not conform the expected format
     */
    @Override
    public EditModuleLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("----------------[Trying to parse input into EditModuleLessonCommand][" + args + "]");
        ArgumentMultimap validArgumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE,
                        PREFIX_LESSON_DAY, PREFIX_LESSON_TIME, PREFIX_REMARK);

        checkInvalidPrefixPresent(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(validArgumentMultimap.getPreamble());
        } catch (ParseException e) {
            logger.info("[Parsing failed due to invalid index][" + validArgumentMultimap.getPreamble() + "]");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        if (validArgumentMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            ModuleCode moduleCode =
                    ParserUtil.parseModuleCode(validArgumentMultimap.getValue(PREFIX_MODULE_CODE).get());
            if (moduleCode.getLessonCodes().size() == 0) {
                throw new ParseException(MESSAGE_NO_LESSON_CODE_PROVIDED);
            } else if (moduleCode.getLessonCodes().size() > 1) {
                throw new ParseException(MESSAGE_MORE_THAN_ONE_LESSON_CODE);
            }
            editLessonDescriptor.setModuleCode(
                    ParserUtil.parseModuleCode(validArgumentMultimap.getValue(PREFIX_MODULE_CODE).get()));
        }

        if (validArgumentMultimap.getValue(PREFIX_LESSON_DAY).isPresent()) {
            editLessonDescriptor.setLessonDay(
                    ParserUtil.parseLessonDay(validArgumentMultimap.getValue(PREFIX_LESSON_DAY).get()));
        }

        if (validArgumentMultimap.getValue(PREFIX_LESSON_TIME).isPresent()) {
            editLessonDescriptor.setLessonStartTime(
                    ParserUtil.parseLessonTime(validArgumentMultimap.getValue(PREFIX_LESSON_TIME).get()).get(0));
            editLessonDescriptor.setLessonEndTime(
                    ParserUtil.parseLessonTime(validArgumentMultimap.getValue(PREFIX_LESSON_TIME).get()).get(1));
        }

        if (validArgumentMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editLessonDescriptor.setRemark(
                    ParserUtil.parseRemark(validArgumentMultimap.getValue(PREFIX_REMARK).get()));
        }

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            logger.info("[Parsing failed due to no field edited][" + validArgumentMultimap.getPreamble() + "]");
            throw new ParseException(EditModuleLessonCommand.MESSAGE_NO_FIELD_PROVIDED);
        }

        return new EditModuleLessonCommand(index, editLessonDescriptor);
    }

    private void checkInvalidPrefixPresent(String args) throws ParseException {
        ArgumentMultimap invalidArgumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_TELE_HANDLE, PREFIX_NAME);

        if (Stream.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELE_HANDLE)
                .map(invalidArgumentMultimap::getValue).anyMatch(Optional::isPresent)) {
            logger.info("----------------[Parsing failed because invalid prefix is present][" + args + "]");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
