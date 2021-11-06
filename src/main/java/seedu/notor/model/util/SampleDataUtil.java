package seedu.notor.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.notor.commons.core.LogsCenter;
import seedu.notor.commons.util.DateUtil;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Notor} with sample data.
 */
public class SampleDataUtil {
    public static final Note EMPTY_NOTE = Note.of("", "");
    public static final String DATE_TIME_NOW = DateUtil.getCurrentDateTime();

    private static final Logger logger = LogsCenter.getLogger(SampleDataUtil.class);

    // @formatter:off
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    Note.of("Agenda \n Review Resume", DATE_TIME_NOW), getTagSet("SWE")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    EMPTY_NOTE, getTagSet()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("92218221"), new Email("olicharlotte@example.com"),
                    EMPTY_NOTE, getTagSet("AI")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    Note.of("Consultation tommorrow.", DATE_TIME_NOW), getTagSet("Database")),
            new Person(new Name("Irfan Ibrahim"), new Phone("91003210"), new Email("irfan@example.com"),
                    EMPTY_NOTE, getTagSet("Y4", "SWE")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    EMPTY_NOTE, getTagSet("Y4", "SWE"))
        };
    }

    public static ReadOnlyNotor getSampleNotor() {
        try {
            Notor sampleAb = new Notor();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }
            SuperGroup orbital = new SuperGroup(new Name("Orbital"));
            SuperGroup finalYearProject = new SuperGroup(new Name("FYP"));
            SubGroup artemis = new SubGroup(new Name("Artemis"), null, "Orbital");
            SubGroup apollo = new SubGroup(new Name("Apollo"), null, "Orbital");
            orbital.addSubGroup(artemis);
            orbital.addSubGroup(apollo);
            sampleAb.addSuperGroup(orbital);
            sampleAb.addSuperGroup(finalYearProject);
            sampleAb.addPersonToSuperGroup(orbital, "Alex Yeoh", "Bernice Yu", "Charlotte Oliveiro");
            sampleAb.addPersonToSuperGroup(finalYearProject, "Irfan Ibrahim", "Roy Balakrishnan");
            sampleAb.addPersonToSubGroup(apollo, "Alex Yeoh", "Bernice Yu");
            sampleAb.addPersonToSubGroup(artemis, "Charlotte Oliveiro");
            return sampleAb;
        } catch (ExecuteException e) {
            e.printStackTrace();
            logger.info("An unexpected error occurred when creating sample data."
                    + "Will be starting with an empty Notor.");
            return new Notor();
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
