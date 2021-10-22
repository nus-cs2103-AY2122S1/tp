package seedu.address.logic.parser.modulelesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_LESSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
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
import seedu.address.logic.parser.Prefix;
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
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_DAY, PREFIX_TIME, PREFIX_REMARK);

        checkInvalidPrefixPresent(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(validArgumentMultimap.getPreamble());
        } catch (ParseException e) {
            logger.info("----------------[Parsing failed due to invalid index][" + validArgumentMultimap.getPreamble() + "]");
            throw new ParseException(MESSAGE_INVALID_MODULE_LESSON_DISPLAYED_INDEX);
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        parseModuleCodesForEdit(validArgumentMultimap.getAllValues(PREFIX_MODULE_CODE))
                .ifPresent(editLessonDescriptor::setModuleCode);

        if (validArgumentMultimap.getValue(PREFIX_DAY).isPresent()) {
            editLessonDescriptor.setLessonDay(ParserUtil.parseDay(validArgumentMultimap.getValue(PREFIX_DAY).get()));
        }

        if (validArgumentMultimap.getValue(PREFIX_TIME).isPresent()) {
            editLessonDescriptor.setLessonTime(ParserUtil.parseTime(validArgumentMultimap.getValue(PREFIX_TIME).get()));
        }

        if (validArgumentMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editLessonDescriptor.setRemark(ParserUtil.parseRemark(validArgumentMultimap.getValue(PREFIX_REMARK).get()));
        }

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            logger.info("----------------[Parsing failed due to no field edited][" + validArgumentMultimap.getPreamble() + "]");
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditModuleLessonCommand(index, editLessonDescriptor);
    }

    private Optional<Set<ModuleCode>> parseModuleCodesForEdit(Collection<String> moduleCodes) throws ParseException {
        assert moduleCodes != null;

        if (moduleCodes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseModuleCodes(moduleCodes));
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
