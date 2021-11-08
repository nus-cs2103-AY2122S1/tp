package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alex_yeoh@gmail.com"),
                new Role("Software Engineer"),
                new EmploymentType("Full time"),
                new ExpectedSalary("4500"),
                new LevelOfEducation("PhD"),
                new Experience("0"),
                getTagSet("Excellent"),
                Optional.of(new Interview("2021-10-20, 9:30")),
                Optional.of(new Notes("He is a perfect candidate for this job!"))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu99@yahoo.com.sg"),
                new Role("Software Tester"),
                new EmploymentType("Part time"),
                new ExpectedSalary("3300"),
                new LevelOfEducation("Masters"),
                new Experience("1"),
                getTagSet("Remarkable", "Passionate"),
                Optional.empty(),
                Optional.of(new Notes("She is very passionate about this field."))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("ch4rl0tt3@hotmail.com"),
                new Role("Receptionist"),
                new EmploymentType("Temporary"),
                new ExpectedSalary("1200"),
                new LevelOfEducation("Elementary"),
                new Experience("2"),
                getTagSet("Average"),
                Optional.of(new Interview("2021-10-20, 11:00")),
                Optional.of(new Notes("Nothing outstanding about this applicant."))),
            new Person(new Name("David Li"), new Phone("91031282"),
                new Email("li.david@u.nus.edu"),
                new Role("Lab Technician"),
                new EmploymentType("Internship"),
                new ExpectedSalary("0"),
                new LevelOfEducation("Bachelors"),
                new Experience("3"),
                getTagSet("Outstanding"),
                Optional.of(new Interview("2022-10-20, 15:25")),
                Optional.of(new Notes("He would not fit the job based off his resume."))),
            new Person(new Name("Eobard Thawne"), new Phone("82647211"),
                new Email("reverse.flash@zoom.sg"),
                new Role("Quantum Physicist"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("6000"),
                new LevelOfEducation("PhD"),
                new Experience("10"),
                getTagSet("Fast", "Efficient"),
                Optional.empty(),
                Optional.of(new Notes("He is very established in his field."))),
            new Person(new Name("Finn DeHuemen"), new Phone("91085282"),
                new Email("finnventure_time@yahoo.com.sg"),
                new Role("Human Resource Manager"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("3450"),
                new LevelOfEducation("Bachelors"),
                new Experience("3"),
                getTagSet("Courageous"),
                Optional.of(new Interview("2021-08-12, 12:00")),
                Optional.of(new Notes("He was raised in a multiracial and multicultural family."))),
            new Person(new Name("Gary Squarepants"), new Phone("91324862"),
                new Email("gary-meow-meow@snailmail.com"),
                new Role("Veterinary Technician"),
                new EmploymentType("Internship"),
                new ExpectedSalary("600"),
                new LevelOfEducation("Masters"),
                new Experience("0"),
                getTagSet("Slow", "Lazy"),
                Optional.of(new Interview("2021-12-25, 18:30")),
                Optional.of(new Notes("He could be a good fit if he puts in the effort."))),
            new Person(new Name("Harry Petter"), new Phone("88888888"),
                new Email("HP-99@hogwarts.sg"),
                new Role("Magician"),
                new EmploymentType("Temporary"),
                new ExpectedSalary("1700"),
                new LevelOfEducation("High School"),
                new Experience("5"),
                getTagSet(),
                Optional.of(new Interview("2022-01-01, 07:00")),
                Optional.empty()),
            new Person(new Name("Indiana Jonas"), new Phone("99356382"),
                new Email("1nd14n4_j0n4s@gmail.com"),
                new Role("Waiter"),
                new EmploymentType("Part Time"),
                new ExpectedSalary("900"),
                new LevelOfEducation("Elementary"),
                new Experience("0"),
                getTagSet(),
                Optional.empty(),
                Optional.of(new Notes("Not much experience is needed for the role he applied."))),
            new Person(new Name("Jake Dedorg"), new Phone("81938134"),
                new Email("jakes_and_bacon@hotmail.com"),
                new Role("Therapist"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("4700"),
                new LevelOfEducation("University"),
                new Experience("8"),
                getTagSet("Experienced"),
                Optional.of(new Interview("2022-04-01, 09:40")),
                Optional.of(new Notes("He does not have amazing qualifications, "
                        + "but has been in the industry for a long time."))),
            new Person(new Name("Kingsley Lee Wei Qiang"), new Phone("96234235"),
                new Email("QS_Sakura@protonmail.com"),
                new Role("Social Media Marketer"),
                new EmploymentType("Temporary"),
                new ExpectedSalary("2900"),
                new LevelOfEducation("High School"),
                new Experience("4"),
                getTagSet(),
                Optional.of(new Interview("2022-06-28, 15:10")),
                Optional.of(new Notes("He seems overconfident in his own abilities."))),
            new Person(new Name("Liz Gillies"), new Phone("92427849"),
                new Email("EliZabEth_G@pear.com"),
                new Role("Actress"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("5450"),
                new LevelOfEducation("Bachelors"),
                new Experience("6"),
                getTagSet("Intense"),
                Optional.of(new Interview("2021-11-05, 12:50")),
                Optional.empty()),
            new Person(new Name("Monica Bing"), new Phone("82412342"),
                new Email("monica-gellar-bing@protonmail.ch"),
                new Role("Cleaner"),
                new EmploymentType("Part Time"),
                new ExpectedSalary("850"),
                new LevelOfEducation("Middle School"),
                new Experience("4"),
                getTagSet("Calculative"),
                Optional.empty(),
                Optional.of(new Notes("She has been recently diagnosed with Obsessive Compulsive Disorder."))),
            new Person(new Name("Ola Otisya"), new Phone("91742532"),
                new Email("0la0tisyA@outlook.com"),
                new Role("Teaching Assistant"),
                new EmploymentType("Internship"),
                new ExpectedSalary("2300"),
                new LevelOfEducation("University"),
                new Experience("2"),
                getTagSet(),
                Optional.of(new Interview("2021-02-28, 19:35")),
                Optional.empty()),
            new Person(new Name("Pam Halpert"), new Phone("82354282"),
                new Email("pam_beeeeeeesly@zoho.com"),
                new Role("Receptionist"),
                new EmploymentType("Temporary"),
                new ExpectedSalary("3100"),
                new LevelOfEducation("Bachelors"),
                new Experience("12"),
                getTagSet("Pregnant"),
                Optional.of(new Interview("2022-11-26, 08:00")),
                Optional.of(new Notes("She might need to take maternity leave upon being hired."))),
            new Person(new Name("Quincy Kru"), new Phone("91854643"),
                new Email("Quincy_CCNC@aol.com"),
                new Role("Dietitian"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("6600"),
                new LevelOfEducation("PhD"),
                new Experience("2"),
                getTagSet(),
                Optional.of(new Interview("2021-07-30, 17:20")),
                Optional.of(new Notes("She is extremely qualified but just lacks the experience."
                        + "She could be an asset to the company under the right guidance."))),
            new Person(new Name("Raze Balakrishnan"), new Phone("82452617"),
                new Email("raze_b00m_b00m@botmail.com"),
                new Role("Demolition Expert"),
                new EmploymentType("Temporary"),
                new ExpectedSalary("4200"),
                new LevelOfEducation("High School"),
                new Experience("5"),
                getTagSet("Temperamental", "Rash"),
                Optional.empty(),
                Optional.empty()),
            new Person(new Name("Shishyal"), new Phone("83423523"),
                new Email("sheeeeeesh_yall@gmail.com.sg"),
                new Role("Lawyer"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("7300"),
                new LevelOfEducation("Bachelors"),
                new Experience("4"),
                getTagSet("Persistent", "Clever"),
                Optional.of(new Interview("2022-06-06, 14:50")),
                Optional.of(new Notes("He would be a good fit in the company."))),
            new Person(new Name("Twasa Drim"), new Phone("91062412"),
                new Email("twas-a-dream@aim.com"),
                new Role("Insurance Agent"),
                new EmploymentType("Part Time"),
                new ExpectedSalary("2650"),
                new LevelOfEducation("Bachelors"),
                new Experience("2"),
                getTagSet(),
                Optional.empty(),
                Optional.of(new Notes("He would not fit the job based off his resume."))),
            new Person(new Name("Tony Stark"), new Phone("96162884"),
                new Email("tony@starkindustries.com"),
                new Role("Engineer"),
                new EmploymentType("Full Time"),
                new ExpectedSalary("10000"),
                new LevelOfEducation("Masters"),
                new Experience("20"),
                getTagSet(),
                Optional.empty(),
                Optional.of(new Notes("He has extensive knowledge of robotics and AI."))),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
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
