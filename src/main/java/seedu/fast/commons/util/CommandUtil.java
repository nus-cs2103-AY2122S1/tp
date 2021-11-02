package seedu.fast.commons.util;

import seedu.fast.commons.core.index.Index;

import java.util.List;

/**
 * Container class for commonly used Command specific function.
 */
public class CommandUtil {
    /**
     * Checks if a given {@code Index} is within the size of a {@code List}.
     *
     * @param index The {@code Index} to be checked.
     * @param list The {@code List} to be checked against.
     * @return A boolean indicating if the {@code Index} is within the size of the {@code List}.
     */
    public static boolean checkIndexExceedLimit(Index index, List list) {
        return index.getZeroBased() >= list.size();
    }
}
