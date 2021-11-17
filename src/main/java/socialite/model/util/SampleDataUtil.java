package socialite.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import socialite.model.CommandHistory;
import socialite.model.ContactList;
import socialite.model.ReadOnlyCommandHistory;
import socialite.model.ReadOnlyContactList;
import socialite.model.handle.Facebook;
import socialite.model.handle.Instagram;
import socialite.model.handle.Telegram;
import socialite.model.handle.TikTok;
import socialite.model.handle.Twitter;
import socialite.model.person.Dates;
import socialite.model.person.Name;
import socialite.model.person.Person;
import socialite.model.person.Phone;
import socialite.model.person.Remark;
import socialite.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ContactList} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark(null);
    public static final Facebook EMPTY_FACEBOOK = new Facebook(null);
    public static final Instagram EMPTY_INSTAGRAM = new Instagram(null);
    public static final Telegram EMPTY_TELEGRAM = new Telegram(null);
    public static final TikTok EMPTY_TIKTOK = new TikTok(null);
    public static final Twitter EMPTY_TWITTER = new Twitter(null);
    public static final Dates EMPTY_DATES = new Dates();

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), EMPTY_REMARK,
                getTagSet("friends"), new Facebook("alex.yeoh"), new Instagram("alex.yeoh"),
                new Telegram("alyeoh"), new TikTok("alex.yeoh"), EMPTY_TWITTER,
                EMPTY_DATES),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), EMPTY_REMARK,
                getTagSet("colleagues", "friends"), new Facebook("bern.yu"),
                EMPTY_INSTAGRAM, new Telegram("bern_yu"),
                new TikTok("bernice.yu"), new Twitter("berniceyu"), EMPTY_DATES),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Remark("Her birthday coming!"),
                getTagSet("neighbours"), EMPTY_FACEBOOK,
                new Instagram("charlotte.oli"), new Telegram("olichar"),
                EMPTY_TIKTOK, new Twitter("charolive"), EMPTY_DATES),
            new Person(new Name("David Li"), new Phone("91031282"), EMPTY_REMARK,
                getTagSet("family"), new Facebook("davey"),
                new Instagram("davey"), new Telegram("david_li"), new TikTok("david"), new Twitter("david"),
                EMPTY_DATES),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Remark("Outing on Saturday"),
                getTagSet("classmates"), new Facebook("irfan.him"),
                new Instagram("irfan.him"), new Telegram("irfan_ibrahim"),
                new TikTok("irfan"), new Twitter("irfanibrahim"), EMPTY_DATES),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), EMPTY_REMARK,
                getTagSet("colleagues"), new Facebook("balaroy"),
                new Instagram("balaroy"), EMPTY_TELEGRAM,
                EMPTY_TIKTOK, new Twitter("royB"), EMPTY_DATES)
        };
    }

    public static String[] getSampleCommands() {
        return new String[] {
            "list",
            "help",
            "help add",
        };
    }


    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleAb = new ContactList();
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

    public static ReadOnlyCommandHistory getSampleCommandHistory() {
        CommandHistory sampleCh = new CommandHistory();
        for (String sampleCommand : getSampleCommands()) {
            sampleCh.addCommand(sampleCommand);
        }
        return sampleCh;
    }
}
