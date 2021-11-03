package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.scene.layout.Region;
import seedu.address.model.display.Displayable;
import seedu.address.model.display.ItemCard;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

/**
 * Represents an item in the inventory.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item implements Displayable {

    // Identity fields
    private final Name name;
    private final Integer id;
    private final Double costPrice;
    private final Double salesPrice;

    // Data fields
    private Integer count;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Item(Name name, Integer id, Integer count, Set<Tag> tags, Double costPrice, Double salesPrice) {
        requireAllNonNull(name, id, count, tags, costPrice, salesPrice);
        this.count = count;
        this.name = name;
        this.id = id;
        this.tags.addAll(tags);
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
    }

    /**
     * Every field must be present and not null.
     */
    public Item(Item other, Integer count) {
        requireAllNonNull(other, count);
        this.count = count;
        this.name = other.name;
        this.id = other.id;
        this.salesPrice = other.salesPrice;
        this.costPrice = other.costPrice;
        this.tags.addAll(other.tags);
    }

    public Name getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns a new Item with only count updated.
     * @param newCount
     * @return
     */
    public Item updateCount(int newCount) {
        assert(newCount >= 0);

        return new Item(name, id, newCount, tags, costPrice, salesPrice);
    }

    /**
     * Returns true if both items have the same name or id.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && (otherItem.getName().equals(getName()) || (otherItem.getId().equals(getId())));
    }

    /**
     * Returns true if both items have the same count.
     */
    public boolean isSameCount(Item other) {
        if (other == this) {
            return true;
        }

        return other != null && Objects.equals(other.getCount(), getCount());
    }

    /**
     * Returns true if both items have the same count.
     */
    public boolean isSameId(Item other) {
        if (other == this) {
            return true;
        }

        return other != null && Objects.equals(other.getId(), getId());
    }

    /**
     * Returns true if both items have the same count.
     */
    public boolean isSameName(Item other) {
        if (other == this) {
            return true;
        }

        return other != null && Objects.equals(other.getName(), getName());
    }

    /**
     * Returns true if both items have the same identity and data fields.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getName().equals(getName())
                && otherItem.getId().equals(getId())
                && otherItem.getCount().equals(getCount())
                && otherItem.getSalesPrice().equals(getSalesPrice())
                && otherItem.getCostPrice().equals(getCostPrice())
                && otherItem.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(String.format("; count: %06d", getId()))
                .append("; count: ")
                .append(getCount())
                .append(String.format("; costPrice: $%.2f", getCostPrice()))
                .append(String.format("; salesPrice: $%.2f", getSalesPrice()));

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public UiPart<Region> asDisplayCard(int index) {
        return new ItemCard(this, index);
    }
}
