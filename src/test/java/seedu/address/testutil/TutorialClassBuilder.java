package seedu.address.testutil;


import seedu.address.model.student.ClassCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

public class TutorialClassBuilder {

    public static final String DEFAULT_CLASSCODE = "G01";
    public static final String DEFAULT_SCHEDULE = "Mon 10am to 12pm";

    private ClassCode classCode;
    private Schedule schedule;
    private Set<Tag> tags;

    /**
     * Creates a {@code TutorialClassBuilder} with the default details.
     */
    public TutorialClassBuilder() {
        classCode = new ClassCode(DEFAULT_CLASSCODE);
        schedule = new Schedule(DEFAULT_SCHEDULE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TutorialClassBuilder with the data of {@code tutorialCLassToCopy}.
     */
    public TutorialClassBuilder(TutorialClass tutorialClassToCopy) {
        classCode = tutorialClassToCopy.getClassCode();
        schedule = tutorialClassToCopy.getSchedule();
        tags = tutorialClassToCopy.getTags();
    }

    /**
     * Sets the {@code ClassCode} of the {@code TutorialClass} that we are building.
     */
    public TutorialClassBuilder withClassCode(String classCode) {
        this.classCode = new ClassCode(classCode);
        return this;
    }

    /**
     * Sets the {@code ClassCode} of the {@code TutorialClass} that we are building.
     */
    public TutorialClassBuilder withSchedule(String schedule) {
        this.schedule = new Schedule(schedule);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code TutorialClass} that we are building.
     */
    public TutorialClassBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public TutorialClass build() {
        return new TutorialClass(classCode, schedule, tags);
    }
}
