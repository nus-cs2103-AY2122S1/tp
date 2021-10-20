package socialite.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import socialite.model.AddressBook;
import socialite.model.ReadOnlyAddressBook;
import socialite.model.handle.Facebook;
import socialite.model.handle.Instagram;
import socialite.model.handle.Telegram;
import socialite.model.handle.TikTok;
import socialite.model.handle.Twitter;
import socialite.model.person.Name;
import socialite.model.person.Person;
import socialite.model.person.Phone;
import socialite.model.person.Remark;
import socialite.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        // TODO(@bnjmnt4n)
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), EMPTY_REMARK,
                getTagSet("friends"), new Facebook("alex.yeoh"), new Instagram("alex.yeoh"),
                new Telegram("alyeoh"), new TikTok("alex.yeoh"), new Twitter("alexy"),
                null),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), EMPTY_REMARK,
                getTagSet("colleagues", "friends"), new Facebook("bern.yu"),
                new Instagram("bern.yu"), new Telegram("bern_yu"),
                new TikTok("bernice.yu"), new Twitter("berniceyu"), null),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), EMPTY_REMARK,
                getTagSet("neighbours"), new Facebook("charlotte.oli"),
                new Instagram("charlotte.oli"), new Telegram("olichar"),
                new TikTok("charlotte.olive"), new Twitter("charolive"), null),
            new Person(new Name("David Li"), new Phone("91031282"), EMPTY_REMARK,
                getTagSet("family"), new Facebook("davey"),
                new Instagram("davey"), new Telegram("david_li"), new TikTok("david"),
                new Twitter("david"), null),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), EMPTY_REMARK,
                getTagSet("classmates"), new Facebook("irfan.him"),
                new Instagram("irfan.him"), new Telegram("irfan_ibrahim"),
                new TikTok("irfan"), new Twitter("irfanibrahim"), null),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), EMPTY_REMARK,
                getTagSet("colleagues"), new Facebook("balaroy"),
                new Instagram("balaroy"), new Telegram("roybala"),
                new TikTok("roy.balakrishnan"), new Twitter("royB"), null)
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
