package seedu.address.testutil;

import seedu.address.logic.commands.AddScoreCommand.ScoreDescriptor;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Score;

/**
 * A utility class to help with building ScoreDescriptor objects.
 */
public class ScoreDescriptorBuilder {
    private ScoreDescriptor descriptor;

    public ScoreDescriptorBuilder() {
        descriptor = new ScoreDescriptor();
    }

    public ScoreDescriptorBuilder(ScoreDescriptor descriptor) {
        this.descriptor = new ScoreDescriptor(descriptor);
    }

    /**
     * Sets the {@code Assessment} of the {@code ScoreDescriptorBuilder} that we are building.
     */
    public ScoreDescriptorBuilder withAssessment(String name) {
        descriptor.setAssessment(new Assessment(name));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code ScoreDescriptorBuilder} that we are building.
     */
    public ScoreDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code id} of the {@code ScoreDescriptorBuilder} that we are building.
     */
    public ScoreDescriptorBuilder withId(String id) {
        descriptor.setId(new ID(id));
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Score} that we are building.
     */
    public ScoreDescriptorBuilder withScore(String score) {
        descriptor.setScore(new Score(score));
        return this;
    }

    public ScoreDescriptor build() {
        return descriptor;
    }
}

