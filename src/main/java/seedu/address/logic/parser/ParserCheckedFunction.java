package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

@FunctionalInterface
public interface ParserCheckedFunction<T, R> {
    public R apply(T t) throws ParseException;
}
