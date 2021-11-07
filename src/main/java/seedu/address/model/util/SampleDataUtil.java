package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Conthacks;
import seedu.address.model.ReadOnlyConthacks;
import seedu.address.model.lessoncode.LessonCode;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TeleHandle;

/**
 * Contains utility methods for populating {@code Conthacks} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@u.nus.edu"), getModuleCodeSet("CS1231 T12"),
                    new Phone("87438807"), new TeleHandle("@alexyeoh"), new Remark("overseas")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@u.nus.edu"), getModuleCodeSet("CS2030S T10"),
                    new Phone("99272758"), new TeleHandle("@berniceyu"), EMPTY_REMARK),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@u.nus.edu"),
                    getModuleCodeSet("CS1231 T12"), new Phone("93210283"), new TeleHandle("@charlotteO"), EMPTY_REMARK),
            new Person(new Name("David Li"), new Email("lidavid@u.nus.edu"), getModuleCodeSet("CS2030S T10",
                    "CS2040 T05"), new Phone("91031282"), new TeleHandle("@davidli"), new Remark("late")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), getModuleCodeSet("CS2040 T05"),
                    new Phone("92492021"), new TeleHandle("@irfan"), EMPTY_REMARK),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@u.nus.edu"), getModuleCodeSet("CS2103T T09"),
                    new Phone("92624417"), new TeleHandle("@royBala"), new Remark("lab5 done"))
        };
    }

    public static ModuleLesson[] getSampleModuleLessons() {
        return new ModuleLesson[] {
            new ModuleLesson(parseModuleCode("CS1231 T12"), new LessonDay("2"),
                    new LessonTime("10:00"), new LessonTime("11:00"), new Remark("COM1-113")),
            new ModuleLesson(parseModuleCode("CS2030S T10"), new LessonDay("2"),
                    new LessonTime("15:00"), new LessonTime("16:00"), new Remark("COM2-0223")),
            new ModuleLesson(parseModuleCode("CS2103 T09"), new LessonDay("1"),
                    new LessonTime("14:00"), new LessonTime("15:00"), new Remark("I3 Audi")),
            new ModuleLesson(parseModuleCode("CS2040 T05"), new LessonDay("1"),
                    new LessonTime("17:00"), new LessonTime("18:00"), new Remark("COM1-120")),
        };
    }

    public static ReadOnlyConthacks getSampleConthacks() {
        Conthacks sampleAb = new Conthacks();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (ModuleLesson sampleModuleLesson : getSampleModuleLessons()) {
            sampleAb.addLesson(sampleModuleLesson);
        }
        return sampleAb;
    }

    /**
     * Returns a module code set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(SampleDataUtil::parseModuleCode)
                .collect(Collectors.toSet());
    }

    /**
     * Helper method for {@code getModuleCodeSet} to parse {@code String moduleCode} into a {@code ModuleCode}.
     */
    public static ModuleCode parseModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        String[] moduleCodeArr = trimmedModuleCode.split("\\s+");
        assert moduleCodeArr.length >= 1 : "Array should not be empty\n";
        Set<LessonCode> lessonCodes = Arrays.stream(moduleCodeArr)
                .skip(1)
                .map(LessonCode::new)
                .collect(Collectors.toSet());
        return new ModuleCode(moduleCodeArr[0], lessonCodes);
    }

}
