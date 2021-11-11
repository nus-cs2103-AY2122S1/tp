package seedu.intern.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.intern.model.InternWatcher;
import seedu.intern.model.ReadOnlyInternWatcher;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Job;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;

/**
 * Contains utility methods for populating {@code InternWatcher} with sample data.
 */
public class SampleDataUtil {

    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Grade("4.50"), new Institution("NTU"), new Course("Computer Science"),
                    new GraduationYearMonth("06/2020"), new Job("Software Engineer"), getSkillSet("HTML")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Grade("4.60"), new Institution("SUTD"), new Course("Computer Science"),
                    new GraduationYearMonth("06/2020"), new Job("Hardware Engineer"),
                    getSkillSet("HTML", "CSS")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Grade("4.70"), new Institution("YALE"), new Course("Computer Science"),
                    new GraduationYearMonth("06/2020"), new Job("Front Desk"),
                    getSkillSet("Java")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Grade("4.80"), new Institution("NTU"), new Course("Computer Science"),
                    new GraduationYearMonth("06/2020"), new Job("Finance Manger"),
                    getSkillSet("Python")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Grade("4.90"), new Institution("SUSS"), new Course("Computer Science"),
                    new GraduationYearMonth("06/2020"), new Job("Engineer Trainee"),
                    getSkillSet("CSS")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Grade("5.00"), new Institution("SMU"), new Course("Computer Science"),
                    new GraduationYearMonth("06/2020"), new Job("Account"), getSkillSet("Office"))
        };
    }

    public static ReadOnlyInternWatcher getSampleInternWatcher() {
        InternWatcher sampleAb = new InternWatcher();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleAb.addApplicant(sampleApplicant);
        }
        return sampleAb;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

}
