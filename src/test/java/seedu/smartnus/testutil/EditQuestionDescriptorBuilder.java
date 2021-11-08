package seedu.smartnus.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * A utility class to help with building EditQuestionDescriptor objects.
 */
public class EditQuestionDescriptorBuilder {

    private EditQuestionDescriptor descriptor;

    public EditQuestionDescriptorBuilder() {
        descriptor = new EditQuestionDescriptor();
    }

    public EditQuestionDescriptorBuilder(EditCommand.EditQuestionDescriptor descriptor) {
        this.descriptor = new EditQuestionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditQuestionDescriptor} with fields containing {@code question}'s details
     */
    public EditQuestionDescriptorBuilder(Question question) {
        descriptor = new EditCommand.EditQuestionDescriptor();
        descriptor.setName(question.getName());
        descriptor.setImportance(question.getImportance());
        descriptor.setTags(question.getTags());
        descriptor.setWrongChoices(question.getWrongChoices());
        descriptor.setAnswer(question.getCorrectChoice());
        descriptor.setTfqChoices(question.getChoices());
        descriptor.setSaqChoices(question.getChoices());
    }

    /**
     * Sets the {@code Name} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Importance} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withImportance(String importance) {
        descriptor.setImportance(new Importance(importance));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditQuestionDescriptor}
     * that we are building.
     */
    public EditQuestionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code wrongChoices} into a {@code Set<Choice>} and set it to the {@code EditQuestionDescriptor}
     * that we are building.
     */
    public EditQuestionDescriptorBuilder withWrongChoices(String... wrongChoices) {
        Set<Choice> choiceSet = Stream.of(wrongChoices).map(choice -> new Choice(choice, false))
                .collect(Collectors.toSet());
        descriptor.setWrongChoices(choiceSet);
        return this;
    }

    /**
     * Sets the {@code answer} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Choice(answer, true));
        return this;
    }

    /**
     * Sets the {@code parsedTfqChoices} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withTfqChoices(Set<Choice> choices) {
        descriptor.setTfqChoices(choices);
        return this;
    }

    /**
     * Sets the {@code parsedSaqChoices} of the {@code EditQuestionDescriptor} that we are building.
     */
    public EditQuestionDescriptorBuilder withSaqChoices(Set<Choice> choices) {
        descriptor.setSaqChoices(choices);
        return this;
    }

    public EditCommand.EditQuestionDescriptor build() {
        return descriptor;
    }
}
