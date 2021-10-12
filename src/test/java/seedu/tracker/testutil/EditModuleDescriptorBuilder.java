package seedu.tracker.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.tracker.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s detail
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setCode(module.getCode());
        descriptor.setTitle(module.getTitle());
        descriptor.setDescription(module.getDescription());
        descriptor.setMc(module.getMc());
        descriptor.setTags(module.getTags());
    }

    /**
     * Sets the {@code Code} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withCode(String code) {
        descriptor.setCode(new Code(code));
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Mc} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withMc(int mc) {
        descriptor.setMc(new Mc(mc));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
