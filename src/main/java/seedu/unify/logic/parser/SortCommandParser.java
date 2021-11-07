package seedu.unify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.BiFunction;
import java.util.function.Function;

import seedu.unify.logic.commands.SortCommand;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.task.Task;

/**
 *
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * sfssa
     *
     * @param args sfsf
     * @return sfsfsaf
     * @throws ParseException safest
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
            args, CliSyntax.PREFIX_TYPE, CliSyntax.PREFIX_SORT_ORDER
        );

        BiFunction<Long, Long, Integer> op = (x, y) -> Math.round(Math.signum(x - y));
        if (argumentMultimap.getValue(CliSyntax.PREFIX_SORT_ORDER).isPresent()) {
            String sortOrder = argumentMultimap.getValue(CliSyntax.PREFIX_SORT_ORDER).get();
            if (sortOrder.equalsIgnoreCase("desc")) {
                op = (x, y) -> Math.round(Math.signum(y - x));
            } else if (!sortOrder.equalsIgnoreCase("asc")) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }
        Function<Task, Long> func = Task::getTimeRepresentation;
        if (argumentMultimap.getValue(CliSyntax.PREFIX_TYPE).isPresent()) {
            String sortType = argumentMultimap.getValue(CliSyntax.PREFIX_TYPE).get();
            if (sortType.equalsIgnoreCase("priority")) {
                func = x -> (long) x.getPriority().getObjectPriority().getValue();
            } else if (!sortType.equalsIgnoreCase("time")) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        BiFunction<Function<Task, Long>, BiFunction<Long, Long, Integer>, BiFunction<Task, Task, Integer>> finalFunc = (
            compVar, order) -> (Task x, Task y) ->
            order.apply(compVar.apply(x), compVar.apply((y)));


        return new SortCommand(finalFunc.apply(func, op));
    }

}
