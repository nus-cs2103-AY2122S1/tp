package tutoraid.testutil;

import tutoraid.logic.commands.EditLessonCommand.EditLessonDescriptor;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Timing;

/**
 * A utility class to help with building EditLessonDescriptor objects.
 */
public class EditLessonDescriptorBuilder {

    private final EditLessonDescriptor descriptor;

    public EditLessonDescriptorBuilder() {
        descriptor = new EditLessonDescriptor();
    }

    public EditLessonDescriptorBuilder(EditLessonDescriptor descriptor) {
        this.descriptor = new EditLessonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLessonDescriptor} with fields containing {@code student}'s details
     */
    public EditLessonDescriptorBuilder(Lesson lesson) {
        descriptor = new EditLessonDescriptor();
        descriptor.setLessonName(lesson.getLessonName());
        descriptor.setCapacity(lesson.getCapacity());
        descriptor.setPrice(lesson.getPrice());
        descriptor.setTiming(lesson.getTiming());
    }

    /**
     * Sets the {@code LessonName} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withLessonName(String name) {
        descriptor.setLessonName(new LessonName(name));
        return this;
    }

    /**
     * Sets the student's {@code Capacity} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withCapacity(String capacity) {
        descriptor.setCapacity(new Capacity(capacity));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Sets the parent's {@code Timing} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withTiming(String timing) {
        descriptor.setTiming(new Timing(timing));
        return this;
    }

    public EditLessonDescriptor build() {
        return descriptor;
    }
}
