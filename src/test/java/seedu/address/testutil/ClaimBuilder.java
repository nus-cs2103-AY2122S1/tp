package seedu.address.testutil;

import seedu.address.model.claim.Claim;
import seedu.address.model.claim.Description;
import seedu.address.model.claim.Status;
import seedu.address.model.claim.Title;

public class ClaimBuilder {
    public static final String DEFAULT_TITLE = "Heart Surgery";
    public static final String DEFAULT_DESCRIPTION = "by Dr. Chan Keng Song at TTSH";
    public static final String DEFAULT_STATUS = "Pending";

    private Title title;
    private Description description;
    private Status status;

    public ClaimBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        status = new Status(DEFAULT_STATUS);
    }

    public ClaimBuilder(String title, String description, String status) {
        this.title = new Title(title);
        this.description = new Description(description);
        this.status = new Status(status);
    }

    public ClaimBuilder(Claim claim) {
        this.title = claim.getTitle();
        this.description = claim.getDescription();
        this.status = claim.getStatus();

    }

    public ClaimBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    public ClaimBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public ClaimBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    public Claim build() {
        return new Claim(title, description, status);
    }
}
