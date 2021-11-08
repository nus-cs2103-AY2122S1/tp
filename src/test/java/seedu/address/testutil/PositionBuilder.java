package seedu.address.testutil;

import seedu.address.model.position.Position;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.Title;

/**
 * A utility class to help with building Person objects.
 */
public class PositionBuilder {
    public static final String DEFAULT_TITLE = "Sales Representative";

    private Title title;

    private PositionStatus status;

    /**
     * Creates a {@code PositionBuilder} with the default details.
     */
    public PositionBuilder() {
        title = new Title(DEFAULT_TITLE);
        status = PositionStatus.OPEN;
    }

    /**
     * Initializes the PositionBuilder with the data of {@code positionToCopy}.
     */
    public PositionBuilder(Position positionToCopy) {
        title = positionToCopy.getTitle();
        status = positionToCopy.getStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Position} that we are building.
     */
    public PositionBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Position} that we are building.
     */
    public PositionBuilder withStatus(PositionStatus status) {
        this.status = status;
        return this;
    }

    public Position build() {
        return new Position(title, status);
    }

}
