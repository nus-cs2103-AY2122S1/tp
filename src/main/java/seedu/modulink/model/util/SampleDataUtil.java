package seedu.modulink.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.AddressBook;
import seedu.modulink.model.ReadOnlyAddressBook;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Your name"), new StudentId("A0000000A"), new Phone("81234567"),
                new Email("youremail@email.com"), new GitHubUsername("your-github-user"),
                new TelegramHandle("yourtelehandle"), false,
                new HashSet<>(), true),
            new Person(new Name("Alex Yeoh"), new StudentId("A1234567R"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new GitHubUsername("alex-yeoh-y"),
                new TelegramHandle("@alexyeoh"), false,
                getTagSet("CS2103T", "CS2106", "CS2101 need member"), false),
            new Person(new Name("Zachary Lau"), new StudentId("A1234567H"), new Phone("96523487"),
                new Email("zachlau@example.com"), new GitHubUsername("zachattach"),
                new TelegramHandle("zacharrryyyy"), false,
                getTagSet("CS2100", "CS2106", "CS2101 need group"), false),
            new Person(new Name("Ng Jia Yuan"), new StudentId("A1234567N"), new Phone("98432567"),
                new Email("ngjy@example.com"), new GitHubUsername("yuan"),
                new TelegramHandle("yuan"), false,
                getTagSet("CS5210 need group", "CS2106", "CS2100 need member"), false),
            new Person(new Name("Charlton Tan"), new StudentId("A1234567L"), new Phone("97324615"),
                new Email("charles@example.com"), new GitHubUsername("C-dollarsign"),
                new TelegramHandle("followmyspotify"), false,
                getTagSet("CS2101 need group", "IS1101"), false),
            new Person(new Name("Ahmad bin Ali"), new StudentId("A1234567B"), new Phone("95238541"),
                new Email("aba@example.com"), new GitHubUsername("alibinahmad"),
                new TelegramHandle("diversity"), false,
                getTagSet("CS2100", "CS2106", "CS2103T need group"), false),
            new Person(new Name("Bernice Yu"), new StudentId("A1234568X"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new GitHubUsername(null),
                new TelegramHandle("berniceYuuu"), false,
                getTagSet("CS2100", "CS3230 need group", "CS4234 need member"), false),
            new Person(new Name("Charlotte Oliveiro"), new StudentId("A1234569Y"), new Phone("93210283"),
                new Email("charlotte@example.com"), new GitHubUsername("charlotteoliveiro"),
                new TelegramHandle("charlotte24"), false,
                getTagSet("CS1101S", "CS3230 need member"), false),
            new Person(new Name("David Li"), new StudentId("A1234570Z"), new Phone("91031282"),
                new Email("lidavid@example.com"), new GitHubUsername("Davidli"),
                new TelegramHandle(null), false,
                getTagSet("CS2101"), false),
            new Person(new Name("Irfan Ibrahim"), new StudentId("A1234571R"), new Phone("92492021"),
                new Email("irfan@example.com"), new GitHubUsername("Irfanib"),
                new TelegramHandle("irfanibrahim"), false,
                getTagSet("CS2101 need member", "ES2660 need group", "CS2103T"), false),
            new Person(new Name("Roy Balakrishnan"), new StudentId("A1234572X"), new Phone("92624417"),
                new Email("royb@example.com"), new GitHubUsername("Roybalakrishnan"),
                new TelegramHandle("heyitsroy"), false,
                getTagSet("CS2100", "CS2103T", "CS2101 need group"), false),
            new Person(new Name("Nicole Wong"), new StudentId("A1234567Q"), new Phone("96523546"),
                new Email("niolewong@example.com"), new GitHubUsername("NicoleW"),
                new TelegramHandle("yellowNicole"), false,
                getTagSet("CS2101 need member", "MA2001"), false),
            new Person(new Name("Jeremy Tan"), new StudentId("A1234567W"), new Phone("96875439"),
                new Email("jTan2000@example.com"), new GitHubUsername("jTanremy"),
                new TelegramHandle("jeret"), false,
                getTagSet("CS2101", "CS1101S need member", "CS2103T"), false),
            new Person(new Name("Aniq Nathaniel"), new StudentId("A1234567E"), new Phone("92584685"),
                new Email("aniqNathan@example.com"), new GitHubUsername("NathAniq"),
                new TelegramHandle("Aniqle4urThoughts"), false,
                getTagSet("CS2101 need group", "CS4234", "CS2103T need group"), false),
            new Person(new Name("Timmy Turner"), new StudentId("A1234569E"), new Phone("95483265"),
                new Email("timmyturns@example.com"), new GitHubUsername("TimTam"),
                new TelegramHandle(null), false,
                getTagSet("ST2334 need group", "GEA1000 need member"), false),
            new Person(new Name("Alice Lin"), new StudentId("A1310684L"), new Phone("88702899"),
                new Email("alicelin@outlook.com"), new GitHubUsername(null),
                new TelegramHandle(null), false,
                getTagSet("CS2030S"), false),
            new Person(new Name("Alex Ong"), new StudentId("A1158684X"), new Phone("83149485"),
                new Email("alexong@hotmail.com"), new GitHubUsername("alexong4"),
                new TelegramHandle(null), false,
                getTagSet("CS2101"), false),
            new Person(new Name("Aaron Heng"), new StudentId("A1132128A"), new Phone("81762929"),
                new Email("aaronheng@gmail.com"), new GitHubUsername("aaronheng21"),
                new TelegramHandle(null), false,
                getTagSet("CS3243 need member"), false),
            new Person(new Name("Ahmad Ong"), new StudentId("A1191832Z"), new Phone("89946306"),
                new Email("ahmadong@email.com"), new GitHubUsername("ahmadong4"),
                new TelegramHandle("ahmadong42"), false,
                getTagSet("CS2030S need group"), false),
            new Person(new Name("Bob Johnson"), new StudentId("A1872556X"), new Phone("92296295"),
                new Email("bobjohnson@outlook.com"), new GitHubUsername(null),
                new TelegramHandle("bobjohnson71"), false,
                getTagSet("CS2030S need group"), false),
            new Person(new Name("Brandon Jones"), new StudentId("A1096456L"), new Phone("90676077"),
                new Email("brandonjones@hotmail.com"), new GitHubUsername("brandonjones17"),
                new TelegramHandle("brandonjones64"), false,
                getTagSet("CS2101 need group"), false),
            new Person(new Name("Bernard William"), new StudentId("A1664306Y"), new Phone("94379764"),
                new Email("bernardwilliam@outlook.com"), new GitHubUsername("bernardwilliam39"),
                new TelegramHandle("bernardwilliam15"), false,
                getTagSet("CS3243 need group"), false),
            new Person(new Name("Charlie Davis"), new StudentId("A1359885Y"), new Phone("85889043"),
                new Email("charliedavis@hotmail.com"), new GitHubUsername(null),
                new TelegramHandle("charliedavis57"), false,
                getTagSet("CS2103T need member"), false),
            new Person(new Name("Cassandra Ong"), new StudentId("A1984289X"), new Phone("84809611"),
                new Email("cassandraong@u.nus.edu"), new GitHubUsername("cassandraong60"),
                new TelegramHandle(null), false,
                getTagSet("CS3243"), false),
            new Person(new Name("Charles William"), new StudentId("A1900975L"), new Phone("98935555"),
                new Email("charleswilliam@hotmail.com"), new GitHubUsername("charleswilliam3"),
                new TelegramHandle(null), false,
                getTagSet("CS2106 need member"), false),
            new Person(new Name("Damien Lim"), new StudentId("A1963998Z"), new Phone("98454108"),
                new Email("damienlim@gmail.com"), new GitHubUsername(null),
                new TelegramHandle(null), false,
                getTagSet("CS2102 need group"), false),
            new Person(new Name("Dan Heng"), new StudentId("A1481608Y"), new Phone("86924167"),
                new Email("danheng@outlook.com"), new GitHubUsername("danheng47"),
                new TelegramHandle("danheng45"), false,
                getTagSet("CS2101"), false),
            new Person(new Name("Denise Gray"), new StudentId("A1617407Z"), new Phone("98499986"),
                new Email("denisegray@hotmail.com"), new GitHubUsername(null),
                new TelegramHandle("denisegray39"), false,
                getTagSet("CS2040S need member"), false),
            new Person(new Name("Eliza Davis"), new StudentId("A1473884R"), new Phone("83974364"),
                new Email("elizadavis@yahoo.com"), new GitHubUsername("elizadavis50"),
                new TelegramHandle(null), false,
                getTagSet("CS3243"), false),
            new Person(new Name("Felicia Lin"), new StudentId("A1830220Z"), new Phone("93013799"),
                new Email("felicialin@yahoo.com"), new GitHubUsername("felicialin40"),
                new TelegramHandle(null), false,
                getTagSet("CS3230"), false),
            new Person(new Name("Fundy Smith"), new StudentId("A1711717X"), new Phone("97454364"),
                new Email("fundysmith@hotmail.com"), new GitHubUsername("fundysmith32"),
                new TelegramHandle("fundysmith45"), false,
                getTagSet("CS2101 need member"), false),
            new Person(new Name("Gordon Gray"), new StudentId("A1149650L"), new Phone("87929336"),
                new Email("gordongray@email.com"), new GitHubUsername("gordongray82"),
                new TelegramHandle("gordongray45"), false,
                getTagSet("CS3243"), false),
            new Person(new Name("James Ong"), new StudentId("A1899147R"), new Phone("94102049"),
                new Email("jamesong@yahoo.com"), new GitHubUsername(null),
                new TelegramHandle(null), false,
                getTagSet("CS2102"), false),
            new Person(new Name("Jane Gray"), new StudentId("A1371778Y"), new Phone("98642544"),
                new Email("janegray@u.nus.edu"), new GitHubUsername("janegray14"),
                new TelegramHandle("janegray5"), false,
                getTagSet("CS1231S"), false),
            new Person(new Name("Janice Johnson"), new StudentId("A1779972A"), new Phone("95017499"),
                new Email("janicejohnson@outlook.com"), new GitHubUsername(null),
                new TelegramHandle("janicejohnson94"), false,
                getTagSet("CS1231S need member"), false),
            new Person(new Name("Justin Jones"), new StudentId("A1380349Z"), new Phone("96762415"),
                new Email("justinjones@hotmail.com"), new GitHubUsername(null),
                new TelegramHandle("justinjones31"), false,
                getTagSet("CS1101S"), false),
            new Person(new Name("Harold Lin"), new StudentId("A1838586Y"), new Phone("82578104"),
                new Email("haroldlin@email.com"), new GitHubUsername("haroldlin58"),
                new TelegramHandle("haroldlin9"), false,
                getTagSet("CS2103T need group"), false),
            new Person(new Name("Henderson William"), new StudentId("A1196405R"), new Phone("89277387"),
                new Email("hendersonwilliam@yahoo.com"), new GitHubUsername("hendersonwilliam70"),
                new TelegramHandle("hendersonwilliam27"), false,
                getTagSet("CS3243"), false),
            new Person(new Name("Ishan Heng"), new StudentId("A1246783X"), new Phone("89588652"),
                new Email("ishanheng@hotmail.com"), new GitHubUsername("ishanheng76"),
                new TelegramHandle("ishanheng95"), false,
                getTagSet("CS2102"), false),
            new Person(new Name("Destin Smith"), new StudentId("A1412064Z"), new Phone("81394689"),
                new Email("destinsmith@email.com"), new GitHubUsername("destinsmith44"),
                new TelegramHandle("destinsmith62"), false,
                getTagSet("CS2040S need group"), false),
            new Person(new Name("Logan Jones"), new StudentId("A1112170R"), new Phone("91333890"),
                new Email("loganjones@hotmail.com"), new GitHubUsername("loganjones79"),
                new TelegramHandle("loganjones55"), false,
                getTagSet("CS2106 need group"), false),
            new Person(new Name("Paul Gray"), new StudentId("A1601243Y"), new Phone("89977560"),
                new Email("paulgray@email.com"), new GitHubUsername("paulgray2"),
                new TelegramHandle("paulgray58"), false,
                getTagSet("CS2100 need group"), false),
            new Person(new Name("Harry Chin"), new StudentId("A1334871X"), new Phone("95501504"),
                new Email("harrychin@outlook.com"), new GitHubUsername("harrychin92"),
                new TelegramHandle("harrychin16"), false,
                getTagSet("CS1101S need group"), false),
            new Person(new Name("Reginald Heng"), new StudentId("A1951638Z"), new Phone("94225881"),
                new Email("reginaldheng@outlook.com"), new GitHubUsername("reginaldheng92"),
                new TelegramHandle("reginaldheng55"), false,
                getTagSet("CS2106"), false),
            new Person(new Name("Kevin Lin"), new StudentId("A1768364R"), new Phone("90027713"),
                new Email("kevinlin@email.com"), new GitHubUsername(null),
                new TelegramHandle(null), false,
                getTagSet("CS2106 need group"), false),
            new Person(new Name("Kirsten Davis"), new StudentId("A1930742L"), new Phone("89859195"),
                new Email("kirstendavis@hotmail.com"), new GitHubUsername("kirstendavis78"),
                new TelegramHandle("kirstendavis58"), false,
                getTagSet("CS3243 need member"), false),
            new Person(new Name("Bernice Smith"), new StudentId("A1757485Z"), new Phone("82955392"),
                new Email("bernicesmith@yahoo.com"), new GitHubUsername(null),
                new TelegramHandle("bernicesmith1"), false,
                getTagSet("CS2105"), false),
            new Person(new Name("Charmaine Heng"), new StudentId("A1134115A"), new Phone("98190393"),
                new Email("charmaineheng@outlook.com"), new GitHubUsername("charmaineheng55"),
                new TelegramHandle("charmaineheng78"), false,
                getTagSet("CS2102"), false),
            new Person(new Name("Jonathan Lau"), new StudentId("A1707247L"), new Phone("95430738"),
                new Email("jonathanlau@u.nus.edu"), new GitHubUsername(null),
                new TelegramHandle("@jonathanlau41"), false,
                getTagSet("CS1101S need group"), false),
            new Person(new Name("Melvin Johnson"), new StudentId("A1341762Z"), new Phone("87270892"),
                new Email("melvinjohnson@email.com"), new GitHubUsername("melvinjohnson1"),
                new TelegramHandle(null), false,
                getTagSet("CS1231S need group"), false),
            new Person(new Name("Mary William"), new StudentId("A1383490L"), new Phone("87715884"),
                new Email("marywilliam@u.nus.edu"), new GitHubUsername("marywilliam92"),
                new TelegramHandle("marywilliam92"), false,
                getTagSet("CS3244 need group"), false),
            new Person(new Name("Nelson Heng"), new StudentId("A1620514A"), new Phone("83104259"),
                new Email("nelsonheng@outlook.com"), new GitHubUsername("nelsonheng58"),
                new TelegramHandle("nelsonheng95"), false,
                getTagSet("CS3230 need member"), false),
            new Person(new Name("Nicole Gray"), new StudentId("A1443745Z"), new Phone("95841730"),
                new Email("nicolegray@u.nus.edu"), new GitHubUsername("nicolegray0"),
                new TelegramHandle("nicolegray97"), false,
                getTagSet("CS2102 need group"), false),
            new Person(new Name("Nico Ong"), new StudentId("A1428496Y"), new Phone("88133575"),
                new Email("nicoong@outlook.com"), new GitHubUsername("nicoong92"),
                new TelegramHandle(null), false,
                getTagSet("CS2103T"), false)
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
    public static Set<Mod> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(modString -> {
                    try {
                        return new Mod(modString);
                    } catch (ParseException e) {
                        return null;
                    }
                })
                .collect(Collectors.toSet());
    }

}
