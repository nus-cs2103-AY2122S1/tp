package seedu.address.model.claim;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.ClaimBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.*;

public class ClaimTest {
    private final Claim DEFAULT_CLAIM = new ClaimBuilder(VALID_CLAIM_TITLE_AMY,
            VALID_CLAIM_DESCRIPTION_AMY, VALID_CLAIM_STATUS_AMY).build();

    @Test
    public void getTitle() {
        assertTrue(DEFAULT_CLAIM.getTitle().toString().equals(VALID_CLAIM_TITLE_AMY));
    }

    @Test
    public void getDescription() {
        assertTrue(DEFAULT_CLAIM.getDescription().toString().equals(VALID_CLAIM_DESCRIPTION_AMY));
    }

    @Test
    public void getStatus() {
        assertTrue(DEFAULT_CLAIM.getStatus().toString().equals(VALID_CLAIM_STATUS_AMY));
    }

    @Test
    public void toStringTest() {
        assertTrue(DEFAULT_CLAIM.toString().equals(String.format("Claim: %s; Description: %s; Status: %s",
                VALID_CLAIM_TITLE_AMY, VALID_CLAIM_DESCRIPTION_AMY, VALID_CLAIM_STATUS_AMY)));
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(DEFAULT_CLAIM.equals(DEFAULT_CLAIM));
        // different type -> return false
        assertFalse(DEFAULT_CLAIM.equals(5));

        // different status, same title -> return true
        assertTrue(DEFAULT_CLAIM.equals(new ClaimBuilder(DEFAULT_CLAIM)
                .withStatus(VALID_CLAIM_STATUS_BOB).build()));
        // different description, same title 0
        assertTrue(DEFAULT_CLAIM.equals(new ClaimBuilder(DEFAULT_CLAIM)
                .withDescription(VALID_CLAIM_DESCRIPTION_BOB).build()));
        // Different title
        assertFalse(DEFAULT_CLAIM.equals(new ClaimBuilder(DEFAULT_CLAIM)
                .withTitle(VALID_CLAIM_TITLE_BOB).build()));
    }
}
