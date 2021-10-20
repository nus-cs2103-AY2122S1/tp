package socialite.testutil;

import java.util.HashSet;
import java.util.Set;

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
import socialite.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_FACEBOOK = "amy.bee";
    public static final String DEFAULT_INSTAGRAM = "amy.bee";
    public static final String DEFAULT_REMARK = "";
    public static final String DEFAULT_TELEGRAM = "amy_bee";
    public static final String DEFAULT_TWITTER = "amy_bee";
    public static final String DEFAULT_TIKTOK = "amy.bee";

    private Name name;
    private Phone phone;
    private Remark remark;
    private Set<Tag> tags;
    private Facebook facebook;
    private Instagram instagram;
    private Telegram telegram;
    private TikTok tiktok;
    private Twitter twitter;
    private Dates dates;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        facebook = new Facebook(DEFAULT_FACEBOOK);
        instagram = new Instagram(DEFAULT_INSTAGRAM);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        tiktok = new TikTok(DEFAULT_TIKTOK);
        twitter = new Twitter(DEFAULT_TWITTER);
        dates = new Dates();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
        facebook = personToCopy.getFacebook();
        instagram = personToCopy.getInstagram();
        telegram = personToCopy.getTelegram();
        tiktok = personToCopy.getTiktok();
        twitter = personToCopy.getTwitter();
        dates = personToCopy.getDates();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Instagram} of the {@code Person} that we are building.
     */
    public PersonBuilder withInstagram(String instagram) {
        this.instagram = new Instagram(instagram);
        return this;
    }

    /**
     * Sets the {@code Facebook} of the {@code Person} that we are building.
     */
    public PersonBuilder withFacebook(String facebook) {
        this.facebook = new Facebook(facebook);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Twitter} of the {@code Person} that we are building.
     */
    public PersonBuilder withTwitter(String twitter) {
        this.twitter = new Twitter(twitter);
        return this;
    }

    /**
     * Sets the {@code TikTok} of the {@code Person} that we are building
     */
    public PersonBuilder withTikTok(String tiktok) {
        this.tiktok = new TikTok(tiktok);
        return this;
    }

    /**
     * Sets the {@code Dates} of the {@code Person} that we are building
     */
    public PersonBuilder withDates(Dates dates) {
        this.dates = new Dates(dates);
        return this;
    }

    public Person build() {
        return new Person(name, phone, remark, tags, facebook, instagram, telegram, tiktok, twitter, dates);
    }

}
