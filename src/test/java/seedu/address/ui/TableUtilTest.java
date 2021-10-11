package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.ui.util.TableUtil;

public class TableUtilTest {
    @Test
    public void installCopyPasteHandler_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TableUtil.installCopyPasteHandler(null));
    }

    @Test
    public void handle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TableUtil.TableKeyEventHandler().handle(null));
    }

    @Test
    public void copySelectionToClipboard_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TableUtil.getSelectionString(null));
    }
}

