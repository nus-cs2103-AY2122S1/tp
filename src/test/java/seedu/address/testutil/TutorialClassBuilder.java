package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.student.ClassCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.util.SampleDataUtil;


public class TutorialClassBuilder {

    public static final String DEFAULT_CLASSCODE = "G06";
    public static final String DEFAULT_SCHEDULE = "Mon 10:00am to 12:00pm, Fri 10:00am to 12:00pm";

    private ClassCode classCode;
    private Schedule schedule;
    private List<TutorialGroup> tutorialGroups;
    private Set<Tag> tags;

    /**
     * Creates a {@code TutorialClassBuilder} with the default details.
     */
    public TutorialClassBuilder() {
        classCode = new ClassCode(DEFAULT_CLASSCODE);
        schedule = new Schedule(DEFAULT_SCHEDULE);
        tutorialGroups = new ArrayList<>();
        tutorialGroups.add(new TutorialGroupBuilder().build());
        tags = new HashSet<>();
    }

    /**
     * Initializes the TutorialClassBuilder with the data of {@code tutorialCLassToCopy}.
     */
    public TutorialClassBuilder(TutorialClass tutorialClassToCopy) {
        classCode = tutorialClassToCopy.getClassCode();
        schedule = tutorialClassToCopy.getSchedule();
        tutorialGroups = tutorialClassToCopy.getTutorialGroupsAsList();
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
     * Sets the {@code TutorialGroups} of the {@code TutorialClass} that we are building.
     */
    public TutorialClassBuilder withTutorialGroups(TutorialGroup tutorialGroups) {
        this.tutorialGroups.add(tutorialGroups);
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
        return new TutorialClass(classCode, schedule, tutorialGroups, tags);
    }
}
