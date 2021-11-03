package seedu.address.model.item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;

/**
 * Stores the partial details of an item. Unlike {@code Item}, fields can be null value.
 * Utilised by logic like {@code AddCommand}, {@code EditCommand} to partially describe items.
 */
public class ItemDescriptor {

    private Name name;
    private Integer id;
    private Set<Tag> tags;
    private Integer count;
    private Double costPrice;
    private Double salesPrice;

    public ItemDescriptor() {
    }

    /**
     * Construct an {@code ItemDescriptor} that corresponds with the given {@code Item}
     */
    public ItemDescriptor(Item toCopy) {
        setName(toCopy.getName());
        setId(toCopy.getId());
        setCount(toCopy.getCount());
        setCostPrice(toCopy.getCostPrice());
        setSalesPrice(toCopy.getSalesPrice());
        setTags(toCopy.getTags());
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public ItemDescriptor(ItemDescriptor toCopy) {
        setName(toCopy.name);
        setId(toCopy.id);
        setCount(toCopy.count);
        setCostPrice(toCopy.costPrice);
        setSalesPrice(toCopy.salesPrice);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, id, count, tags, costPrice, salesPrice);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Optional<Integer> getId() {
        return Optional.ofNullable(id);
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Optional<Integer> getCount() {
        return Optional.ofNullable(count);
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Optional<Double> getCostPrice() {
        return Optional.ofNullable(costPrice);
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Optional<Double> getSalesPrice() {
        return Optional.ofNullable(salesPrice);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    /**
     * Returns true if the given {@code item} has the same name or id as the descriptor.
     */
    public boolean isMatch(Item item) {
        // Return true if name matches
        if (name != null && name.hasSameLower(item.getName())) {
            return true;
        }
        // Return true if id matches
        if (id != null && id.equals(item.getId())) {
            return true;
        }
        // Return false otherwise
        return false;
    }

    /**
     * Returns the corresponding {@code Item}.
     * If count unspecified, defaults to 1.
     * @throws NullPointerException if either name or id is unspecified.
     *
     */
    public Item buildItem() {
        if (name == null || id == null || costPrice == null || salesPrice == null) {
            throw new NullPointerException("Item descriptor requires name, id, cost price, and sales price to build");
        }

        int itemCount = (count == null) ? 1 : count;
        Set<Tag> itemTags = (tags == null) ? new HashSet<>() : tags;
        Double cp = (costPrice == null) ? 1.0 : costPrice;
        Double sp = (salesPrice == null) ? 1.0 : salesPrice;

        return new Item(name, id, itemCount, itemTags, cp, sp);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemDescriptor)) {
            return false;
        }

        // state check
        ItemDescriptor e = (ItemDescriptor) other;

        return getName().equals(e.getName())
                && getId().equals(e.getId())
                && getCount().equals(e.getCount())
                && getTags().equals(e.getTags())
                && getCostPrice().equals(e.getCostPrice())
                && getSalesPrice().equals(e.getSalesPrice());
    }
}
