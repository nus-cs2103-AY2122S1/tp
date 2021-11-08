package seedu.siasa.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.siasa.logic.commands.policy.EditPolicyCommand.EditPolicyDescriptor;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;

/**
 * A utility class to help with building EditContactDescriptor objects.
 */
public class EditPolicyDescriptorBuilder {

    private EditPolicyDescriptor descriptor;

    public EditPolicyDescriptorBuilder() {
        descriptor = new EditPolicyDescriptor();
    }

    public EditPolicyDescriptorBuilder(EditPolicyDescriptor descriptor) {
        this.descriptor = new EditPolicyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPolicyDescriptor} with fields containing {@code policy}'s details
     */
    public EditPolicyDescriptorBuilder(Policy policy) {
        descriptor = new EditPolicyDescriptor();
        descriptor.setTitle((policy.getTitle()));
        descriptor.setTags(policy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditPolicyDescriptor} that we are building.
     */
    public EditPolicyDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPolicyDescriptor}
     * that we are building.
     */
    public EditPolicyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPolicyDescriptor build() {
        return descriptor;
    }
}
