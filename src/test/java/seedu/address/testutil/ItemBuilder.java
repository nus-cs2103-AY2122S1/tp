package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Cream Puff";
    public static final String DEFAULT_ID = "#654321";
    public static final String DEFAULT_COUNT = "5";

    private Name name;
    private String id;
    private String count;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new Name(DEFAULT_NAME);
        id = DEFAULT_ID;
        count = DEFAULT_COUNT;
        tags = new HashSet<>();
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        id = itemToCopy.getId();
        count = Integer.toString(itemToCopy.getCount());
        tags = new HashSet<>(itemToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Item} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Item} that we are building.
     */
    public ItemBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code count} of the {@code Item} that we are building.
     */
    public ItemBuilder withCount(String count) {
        this.count = count;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Item} that we are building.
     */
    public ItemBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Item build() {
        return new Item(name, id, Integer.parseInt(count), tags);
    }

}
