package socialite.model.person;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import socialite.commons.util.CollectionUtil;
import socialite.logic.commands.ShareCommand;
import socialite.model.handle.Facebook;
import socialite.model.handle.Instagram;
import socialite.model.handle.Telegram;
import socialite.model.handle.TikTok;
import socialite.model.handle.Twitter;
import socialite.model.tag.Tag;

/**
 * Represents a Person on the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private ProfilePicture profilePicture = ProfilePicture.DEFAULT_PICTURE;
    private boolean isPinned = false;

    // Social media handle fields
    private final Facebook facebook;
    private final Instagram instagram;
    private final Telegram telegram;
    private final TikTok tiktok;
    private final Twitter twitter;
    private final Dates dates;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Remark remark, Set<Tag> tags,
                  Facebook facebook, Instagram instagram, Telegram telegram, TikTok tiktok, Twitter twitter,
                  Dates dates) {
        CollectionUtil.requireAllNonNull(name, phone, tags);
        this.name = name;
        this.phone = phone;
        this.remark = remark;
        this.tags.addAll(tags);
        this.facebook = facebook;
        this.instagram = instagram;
        this.telegram = telegram;
        this.tiktok = tiktok;
        this.twitter = twitter;
        this.dates = dates;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Remark getRemark() {
        return remark;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public TikTok getTiktok() {
        return tiktok;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean getPinnedStatus() {
        return isPinned;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public Instagram getInstagram() {
        return instagram;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Dates getDates() {
        return dates;
    }

    public void setPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public void setProfilePicture(Path value) {
        this.profilePicture = new ProfilePicture(value);
    }
    /**
     * Returns true if both persons have the same name and phone number.
     * Rationale: Two unique persons can share the same first and last name.
     * For example, a user might be friends with two different individuals who are named "Alice Jones".
     * However, each Alice Jones will have a different phone number, similar to a unique National ID.
     * Hence, the phone number shall act as the tiebreaker to differentiate the two individuals instead.
     * Overall, this method defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getPhone().equals(getPhone());
    }


    /**
     * Pinning-related methods
     */
    public boolean isPinned() {
        return isPinned;
    }

    public void pin() {
        isPinned = true;
    }

    public void unpin() {
        isPinned = false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getProfilePicture().equals(getProfilePicture())
                && otherPerson.getPinnedStatus() == getPinnedStatus()
                && otherPerson.getFacebook().equals(getFacebook())
                && otherPerson.getInstagram().equals(getInstagram())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getTiktok().equals(getTiktok())
                && otherPerson.getTwitter().equals(getTwitter())
                && otherPerson.getDates().equals(getDates());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, remark, tags, facebook, instagram, telegram, tiktok, twitter, dates,
                profilePicture, isPinned);
    }

    /**
     * Returns a formatted string when user wants to forward the contact using `share` command.
     */

    public String toSharingString() {
        final StringBuilder info = new StringBuilder();
        info.append("\tPhone: ").append(getPhone());

        if (getFacebook().get() != null) {
            info.append("\n\tFacebook: ").append(getFacebook().getUrl());
        }

        if (getInstagram().get() != null) {
            info.append("\n\tInstagram: ").append(getInstagram().getUrl());
        }

        if (getTelegram().get() != null) {
            info.append("\n\tTelegram: ").append(getTelegram().getUrl());
        }

        if (getTiktok().get() != null) {
            info.append("\n\tTikTok: ").append(getTiktok().getUrl());
        }

        if (getTwitter().get() != null) {
            info.append("\n\tTwitter: ").append(getTwitter().getUrl());
        }

        return String.format(ShareCommand.MESSAGE_SHARE_PERSON_TEMPLATE, getName(), info);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());


        if (getFacebook().get() != null) {
            builder.append("; Facebook: ").append(getFacebook().get());
        }

        if (getInstagram().get() != null) {
            builder.append("; Instagram: ").append(getInstagram().get());
        }

        if (getTelegram().get() != null) {
            builder.append("; Telegram: ").append(getTelegram().get());
        }

        if (getTiktok().get() != null) {
            builder.append("; TikTok: ").append(getTiktok().get());
        }

        if (getTwitter().get() != null) {
            builder.append("; Twitter: ").append(getTwitter().get());
        }

        if (getRemark().get() != null) {
            builder.append("; Remark: ").append(getRemark().get());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Collection<Date> dates = getDates().get().values();
        if (!dates.isEmpty()) {
            builder.append("; Dates: ");
            dates.forEach(date -> builder.append("[").append(date).append("]"));
        }

        return builder.toString();
    }

    /**
     * creates a copy of this person, with the same fields
     * @return copy of the person
     */
    public Person copy() {
        Person copy = new Person(
                this.name, this.phone, this.remark, this.tags, this.facebook, this.instagram, this.telegram,
                this.tiktok, this.twitter, this.dates);
        copy.setProfilePicture(this.profilePicture.value);
        copy.setPinned(this.isPinned);
        return copy;
    }
}
