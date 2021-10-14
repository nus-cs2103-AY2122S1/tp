package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class ItemDescriptorBuilder {

    private ItemDescriptor descriptor;

    public ItemDescriptorBuilder() {
        descriptor = new ItemDescriptor();
    }

    public ItemDescriptorBuilder(ItemDescriptor descriptor) {
        this.descriptor = new ItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code item}'s details
     */
    public ItemDescriptorBuilder(Item item) {
        descriptor = new ItemDescriptor();
        descriptor.setName(item.getName());
        descriptor.setId(item.getId());
        descriptor.setTags(item.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withCount(String count) {
        descriptor.setCount(Integer.parseInt(count));
        return this;
    }


    /**
     * Sets the id of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withId(String id) {
        descriptor.setId(id);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditItemDescriptor}
     * that we are building.
     */
    public EditItemDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditItemDescriptor build() {
        return descriptor;
    }
}
