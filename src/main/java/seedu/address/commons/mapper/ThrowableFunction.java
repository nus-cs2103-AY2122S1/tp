package seedu.address.commons.mapper;

import seedu.address.logic.parser.exceptions.ParseException;

@FunctionalInterface
public interface ThrowableFunction<T, R, E extends ParseException> {
    R apply(T t) throws E;
}
