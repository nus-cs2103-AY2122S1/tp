package seedu.fast.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;

public class CommandUtilTest {

    @Test
    public void checkIndexExceedLimit() {
        Index indexSafe = Index.fromZeroBased(4);
        Index indexEqual = Index.fromZeroBased(5);
        Index indexExceedByOne = Index.fromZeroBased(6);

        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        // out of bound index
        assertTrue(CommandUtil.checkIndexExceedLimit(indexEqual, list));
        assertTrue(CommandUtil.checkIndexExceedLimit(indexExceedByOne, list));

        // within size of list
        assertFalse(CommandUtil.checkIndexExceedLimit(indexSafe, list));
    }
}
