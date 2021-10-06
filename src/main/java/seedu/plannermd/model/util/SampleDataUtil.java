package seedu.plannermd.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PlannerMd} with sample data.
 */
public class SampleDataUtil {

    private static final Remark EMPTY_REMARK = new Remark("");

    public static Patient[] getSamplePatients() {
        return new Patient[] {
                new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), new BirthDate("27/10/1967"),
                        new Remark("Prefer Dr. Lau"), getTagSet("friends"), new Risk("LOW")),
                new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new BirthDate("22/08/1967"),
                        new Remark("Monthly insulin prescription"), getTagSet("colleagues", "friends"),
                        new Risk("MEDIUM")),
                new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new BirthDate("20/03/1976"),
                        new Remark("Only understands Mandarin"), getTagSet("neighbours"), new Risk("HIGH")),
                new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new BirthDate("20/07/1964"),
                        EMPTY_REMARK, getTagSet("family"), new Risk("LOW")),
                new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), new BirthDate("25/12/2000"), EMPTY_REMARK,
                        getTagSet("classmates"), new Risk("LOW")),
                new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), new BirthDate("09/08/1965"), EMPTY_REMARK,
                        getTagSet("colleagues"), new Risk("MEDIUM")) };
    }

    public static ReadOnlyPlannerMd getSamplePlannerMd() {
        PlannerMd samplePm = new PlannerMd();
        for (Patient samplePatient : getSamplePatients()) {
            samplePm.addPatient(samplePatient);
        }
        return samplePm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
