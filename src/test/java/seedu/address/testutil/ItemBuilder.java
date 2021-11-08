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
    public static final String DEFAULT_ID = "654321";
    public static final String DEFAULT_COUNT = "5";
    public static final String DEFAULT_COSTPRICE = "5.0";
    public static final String DEFAULT_SALESPRICE = "7.0";

    private Name name;
    private String id;
    private String count;
    private Set<Tag> tags;
    private String costPrice;
    private String salesPrice;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new Name(DEFAULT_NAME);
        id = DEFAULT_ID;
        count = DEFAULT_COUNT;
        costPrice = DEFAULT_COSTPRICE;
        salesPrice = DEFAULT_SALESPRICE;
        tags = new HashSet<>();
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        id = Integer.toString(itemToCopy.getId());
        count = Integer.toString(itemToCopy.getCount());
        costPrice = Double.toString(itemToCopy.getCostPrice());
        salesPrice = Double.toString(itemToCopy.getSalesPrice());
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

    /**
     * Sets the {@code costPrice} of the {@code Item} that we are building.
     */
    public ItemBuilder withCostPrice(String costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    /**
     * Sets the {@code salesPrice} of the {@code Item} that we are building.
     */
    public ItemBuilder withSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
        return this;
    }

    /**
     * Build the {@code Item}
     */
    public Item build() {
        return new Item(name, Integer.parseInt(id), Integer.parseInt(count), tags, Double.parseDouble(costPrice),
                Double.parseDouble(salesPrice));
    }

}
