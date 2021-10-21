package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.LastVisit;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_LANGUAGE, PREFIX_ADDRESS, PREFIX_HEALTH_CONDITION, PREFIX_LAST_VISIT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_LANGUAGE).isPresent()) {
            editPersonDescriptor.setLanguage(ParserUtil.parseLanguage(argMultimap.getValue(PREFIX_LANGUAGE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_LAST_VISIT).isPresent()) {
            LastVisit lastVisit = ParserUtil.parseLastVisit(argMultimap.getValue(PREFIX_LAST_VISIT).get()).get();
            editPersonDescriptor.setLastVisit(lastVisit);
        }

        parseHealthConditionsForEdit(argMultimap.getAllValues(PREFIX_HEALTH_CONDITION))
                .ifPresent(editPersonDescriptor::setHealthConditions);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> healthConditions} into a {@code Set<HealthCondition>} if
     * {@code healthConditions} is non-empty. If {@code healthConditions} contain only one element which is an
     * empty string, it will be parsed into a {@code Set<HealthCondition>} containing zero healthConditions.
     */
    private Optional<Set<HealthCondition>> parseHealthConditionsForEdit(Collection<String> healthConditions)
            throws ParseException {
        assert healthConditions != null;

        if (healthConditions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> healthConditionSet = healthConditions.size() == 1 && healthConditions.contains("")
                ? Collections.emptySet() : healthConditions;
        return Optional.of(ParserUtil.parseHealthConditions(healthConditionSet));
    }

}
