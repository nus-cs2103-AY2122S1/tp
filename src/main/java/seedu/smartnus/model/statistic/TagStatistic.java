package seedu.smartnus.model.statistic;

import seedu.smartnus.model.tag.Tag;

public class TagStatistic extends Statistic {
    private final Tag tag;

    /**
     * Constructor for a tag-statistic.
     * @param tag The tag.
     * @param statistic The statistic.
     */
    public TagStatistic(Tag tag, Statistic statistic) {
        super(statistic.getAttemptCount(), statistic.getCorrectCount());
        this.tag = tag;
    }

    /**
     * Returns the title of the tag.
     * @return The tag name.
     */
    public String getTitle() {
        return tag.getTagName();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagStatistic
                && tag.equals(((TagStatistic) other).tag));
    }
}
