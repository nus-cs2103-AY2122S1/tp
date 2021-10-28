package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.mapper.PrefixMapper.compareFunction;
import static seedu.address.commons.mapper.PrefixMapper.getName;

import java.util.Comparator;
import java.util.function.BiFunction;

import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Compare two {@code Client} based on {@code Prefix} and {@code SortDirection}
 * to determine their ordering
 */
public class SortByMeetingDateTime implements Comparator<Client> {

    private final SortDirection direction;

    /**
     * @param direction to sort by. Either asc or dsc.
     */
    public SortByMeetingDateTime(SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
    }

    @Override
    public int compare(Client a, Client b) {
        try {
            return a.compareMeeting(b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
