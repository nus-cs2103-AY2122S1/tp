package seedu.address.model.claim;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLAIM_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLAIM_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLAIM_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLAIM_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLAIM_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLAIM_TITLE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClaimBuilder;



public class ClaimTest {
    private final Claim defaultClaim = new ClaimBuilder(VALID_CLAIM_TITLE_AMY,
            VALID_CLAIM_DESCRIPTION_AMY, VALID_CLAIM_STATUS_AMY).build();

    @Test
    public void getTitle() {
        assertTrue(defaultClaim.getTitle().toString().equals(VALID_CLAIM_TITLE_AMY));
    }

    @Test
    public void getDescription() {
        assertTrue(defaultClaim.getDescription().toString().equals(VALID_CLAIM_DESCRIPTION_AMY));
    }

    @Test
    public void getStatus() {
        assertTrue(defaultClaim.getStatus().toString().equals(VALID_CLAIM_STATUS_AMY.toUpperCase()));
    }

    @Test
    public void toStringTest() {
        assertTrue(defaultClaim.toString().equals(String.format("Claim: %s; Description: %s; Status: %s",
                VALID_CLAIM_TITLE_AMY, VALID_CLAIM_DESCRIPTION_AMY, VALID_CLAIM_STATUS_AMY.toUpperCase())));
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(defaultClaim.equals(defaultClaim));
        // different type -> return false
        assertFalse(defaultClaim.equals(5));

        // different status, same title -> return true
        assertTrue(defaultClaim.equals(new ClaimBuilder(defaultClaim)
                .withStatus(VALID_CLAIM_STATUS_BOB).build()));
        // different description, same title 0
        assertTrue(defaultClaim.equals(new ClaimBuilder(defaultClaim)
                .withDescription(VALID_CLAIM_DESCRIPTION_BOB).build()));
        // Different title
        assertFalse(defaultClaim.equals(new ClaimBuilder(defaultClaim)
                .withTitle(VALID_CLAIM_TITLE_BOB).build()));
    }
}
