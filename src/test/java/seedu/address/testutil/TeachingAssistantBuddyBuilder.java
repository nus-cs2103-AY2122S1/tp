package seedu.address.testutil;

import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building TeachingAssistantBuddy objects.
 * Example usage: <br>
 *     {@code TeachingAssistantBuddy tab = new TeachingAssistantBuilder()
 *     .withModule(new Module(new ModuleName("CS2103").build();}
 */
public class TeachingAssistantBuddyBuilder {

    private TeachingAssistantBuddy teachingAssistantBuddy;

    public TeachingAssistantBuddyBuilder() {
        teachingAssistantBuddy = new TeachingAssistantBuddy();
    }

    public TeachingAssistantBuddyBuilder(TeachingAssistantBuddy teachingAssistantBuddy) {
        this.teachingAssistantBuddy = teachingAssistantBuddy;
    }

    /**
     * Adds a new {@code Module} to the {@code TeachingAssistantBuddy} that we are building.
     */
    public TeachingAssistantBuddyBuilder withModule(Module module) {
        teachingAssistantBuddy.addModule(module);
        return this;
    }

    public TeachingAssistantBuddy build() {
        return teachingAssistantBuddy;
    }
}
