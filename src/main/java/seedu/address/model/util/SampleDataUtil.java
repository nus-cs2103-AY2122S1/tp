package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.Rating;
import seedu.address.model.contact.Review;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Contact[] getSampleContacts() {
        return new Contact[]{
            new Contact(new CategoryCode("att"), new Name("Marina Bay Sands"), new Phone("66888868"),
                new Email("marinabaysands@example.com"), new Address("10 Bayfront Ave, Singapore 018956"),
                new Review("amazing"), getTagSet("casino", "popular"), new Rating("4")),
            new Contact(new CategoryCode("com"), new Name("VivoCity"), new Phone("63776860"),
                new Email("vivocity@example.com"), new Address("1 HarbourFront Walk, Singapore 098585"),
                new Review("meh"), getTagSet("mall", "south"), new Rating("5")),
            new Contact(new CategoryCode("tpt"), new Name("Singapore DUCKtours"), new Phone("63386877"),
                new Email("sgducktours@example.com"), new Address("3 Temasek Blvd, #01-330 Suntec City,"
                + " Singapore 038983"),
                new Review("not bad"), getTagSet("tour"), new Rating("3")),
            new Contact(new CategoryCode("fnb"), new Name("Bread Street Kitchen by Gordon Ramsay"),
                new Phone("66885665"), new Email("bskbygordon@example.com"),
                new Address("10 Bayfront Ave, L1 - 81, Singapore 018956"),
                new Review("the best"), getTagSet("michelin"), new Rating("2")),
            new Contact(new CategoryCode("oth"), new Name("Esso Telok Blangah"), new Phone("62712705"),
                new Email("essotelokblangah@example.com"), new Address("396 Telok Blangah Road, Singapore 98837."),
                new Review("horrible"),
                getTagSet("kiosk", "break"), new Rating("1")),
            new Contact(new CategoryCode("att"), new Name("Gardens By The Bay"), new Phone("64206848"),
                new Email("gbtb@example.com"), new Address("18 Marina Gardens Dr, Singapore 018953"),
                new Review("- No Review -"),
                getTagSet("park", "outdoor"), new Rating("4")),
            new Contact(new CategoryCode("fnb"), new Name("Green on Earth Vegetarian Cafe"),
                new Phone("66775665"), new Email("goevc@example.com"),
                new Address("386 Upper Bukit Timah Rd, Singapore 678043"),
                new Review("Very good"), getTagSet("vegetarian", "central"), new Rating("4")),
            new Contact(new CategoryCode("fnb"), new Name("Moosehead Kitchen Bar"),
                new Phone("66889900"), new Email("moosehead@example.com"),
                new Address("110 Telok Ayer St, Singapore 068579"),
                new Review("Excellent"), getTagSet("vegetarian", "restaurant"), new Rating("5")),
            new Contact(new CategoryCode("fnb"), new Name("Alter Ego"),
                new Phone("66554433"), new Email("alterego@example.com"),
                new Address("#01-13D, Esplanade Mall, 8 Raffles Avenue, 039802"),
                new Review("Not the best"), getTagSet("vegetarian", "esplanade"), new Rating("2")),
            new Contact(new CategoryCode("fnb"), new Name("HANS IM GLUCK German Burgergrill"),
                new Phone("66112233"), new Email("Hansburgergrill@example.com"),
                new Address("362 Orchard Rd, International Building, Singapore 238887"),
                new Review("Excellent burgers"), getTagSet("burger", "vegetarian"), new Rating("5")),
            new Contact(new CategoryCode("fnb"), new Name("Prive Somerset"),
                new Phone("66334422"), new Email("privesomerset@example.com"),
                new Address("313 Orchard Rd, #01-28, Singapore 238895"),
                new Review("its alright"), getTagSet("vegetarian", "somerset"), new Rating("3")),
            new Contact(new CategoryCode("fnb"), new Name("Joie"),
                new Phone("66110099"), new Email("joie@example.com"),
                new Address("181 Orchard Rd, #12 - 01, Singapore 238896"),
                new Review("Contemporary indoor-outdoor rooftop eatery serving a set menu of adventurous vegetarian "
                        + "cuisine."), getTagSet("vegetarian", "unique", "rooftop"), new Rating("5")),
            new Contact(new CategoryCode("fnb"), new Name("Daehwa Korean"),
                new Phone("66885661"), new Email("daehwa@example.com"),
                new Address("1 Fusionopolis Pl, #01-35 Galaxis, Singapore 138522"),
                new Review("Not bad"), getTagSet("korean", "vegetarian", "restaurant"), new Rating("4")),
            new Contact(new CategoryCode("fnb"), new Name("Udipi Ganesh Vilas Restaurant"),
                new Phone("66886665"), new Email("UGV@example.com"),
                new Address("10 Ceylon Rd, Singapore 429606"),
                new Review("Well, the mushroom dosai & rava masala dosai were really good. But their fried rice was "
                        + "terrible. It’s just so soaked in oil felt like I was drinking the cooking oil. "
                        + "Never order the fried rice. Always Stick to thosai."),
                    getTagSet("vegetarian", "indian"), new Rating("2")),
            new Contact(new CategoryCode("fnb"), new Name("Canton Paradise"), new Phone("67783817"),
                new Email("paradiseGroup@gmail.com"), new Address("2 Bayfront Ave, #01 - 02, Singapore 018972"),
                new Review("Amazing Chinese Food!"),
                getTagSet("chinese", "restaurant"), new Rating("5")),
            new Contact(new CategoryCode("fnb"), new Name("Morton SteakHouse"), new Phone("63393740"),
                new Email("mortons@gmail.com"),
                new Address("5 Raffles Ave, Fourth Storey Mandarin Oriental, Singapore 039797"),
                new Review("Really great steaks and cuts"),
                getTagSet("western", "restaurant"), new Rating("5")),
            new Contact(new CategoryCode("acc"), new Name("Mandarin Oriental Singapore"), new Phone("63380066"),
                new Email("MandarinOriental@gmail.com"), new Address("5 Raffles Ave, Singapore 039797"),
                new Review("Really nice Hotel, plenty of facilities"),
                getTagSet("hotel"), new Rating("5")),
            new Contact(new CategoryCode("att"), new Name("Universal Studios Singapore"), new Phone("65778888"),
                new Email("rws@example.com"), new Address("8 Sentosa Gateway, 098269"),
                new Review("Family friendly place!"), getTagSet("popular", "south"), new Rating("5")),
            new Contact(new CategoryCode("att"), new Name("Singapore Botanic Gardens"), new Phone("90807060"),
                    new Email("sbc@example.com"), new Address("1 Cluny Road, Singapore 259569"),
                    new Review("amazing garden, very calming"),
                    getTagSet("garden", "nature", "botany", "healing"), new Rating("4")),
            new Contact(new CategoryCode("att"), new Name("Singapore Flyer"), new Phone("9189118"),
                    new Email("singflyer@example.com"), new Address("30 Raffles Ave, Singapore 039803"),
                    new Review("good views, eligible for SRV, temporarily closed"),
                    getTagSet("capsule", "views", "river", "exhibition"), new Rating("5")),
            new Contact(new CategoryCode("att"), new Name("Clarke Quay"), new Phone("91919191"),
                    new Email("clarkequay@example.com"), new Address("3 River Valley Rd, Singapore 179024"),
                    new Review("many f&b restaurants around, but no good views"),
                    getTagSet("historical", "bars", "river", "riverside"), new Rating("2")),
            new Contact(new CategoryCode("tpt"), new Name("Big Bus Tours"), new Phone("63386877"),
                    new Email("bigbustours@example.com"),
                    new Address("3 Temasek Blvd, #01-330 Suntec City, Singapore 038983"),
                    new Review("routes are good for a first impression of Singapore, the recorded guide was "
                            + "informative, but that's about it"), getTagSet("tour", "citytour", "guided",
                    "bus"), new Rating("2")),
            new Contact(new CategoryCode("com"), new Name("Bugis Street"), new Phone("63386222"),
                    new Email("bugisstreet@example.com"), new Address("3 New Bugis Street, Singapore 188867"),
                    new Review("cheap and value shopping, better than Chinatown. can buy some souvenirs here"),
                    getTagSet("shopping", "clothes", "souvenir", "street", "local"), new Rating("3")),
            new Contact(new CategoryCode("acc"), new Name("The Pod Boutique Capsule Hotel"), new Phone("62988505"),
                    new Email("thepodbch@example.com"), new Address("289 Beach Rd, Level 3, Singapore 199552"),
                    new Review("snug capsules, complimentary breakfast & Wi-Fi"),
                    getTagSet("hotel", "capsule", "backpacker"), new Rating("3")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
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
