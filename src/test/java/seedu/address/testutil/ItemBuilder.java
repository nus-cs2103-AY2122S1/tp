package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.newmodel.item.Item;
import seedu.address.newmodel.item.Name;
import seedu.address.newmodel.tag.Tag;
import seedu.address.newmodel.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Banana Muffin";
    public static final String DEFAULT_ID = "#111111";

    private Name name;
    private String id;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new Name(DEFAULT_NAME);
        id = DEFAULT_ID;
        tags = new HashSet<>();
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        id = itemToCopy.getId();
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Item} that we are building.
     */
    public ItemBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Item build() {
        return new Item(name, id, tags);
    }

}
