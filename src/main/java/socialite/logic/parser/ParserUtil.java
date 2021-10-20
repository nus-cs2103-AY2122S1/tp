package socialite.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import socialite.commons.core.index.Index;
import socialite.commons.util.StringUtil;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.handle.Facebook;
import socialite.model.handle.Instagram;
import socialite.model.handle.Telegram;
import socialite.model.handle.TikTok;
import socialite.model.handle.Twitter;
import socialite.model.person.Dates;
import socialite.model.person.Name;
import socialite.model.person.Phone;
import socialite.model.person.Remark;
import socialite.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String handle} into a {@code Facebook}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid for Facebook.
     */
    public static Facebook parseFacebook(String handle) throws ParseException {
        if (handle == null || handle.equals("")) {
            return new Facebook(null);
        }
        String trimmedHandle = handle.trim();
        if (!Facebook.isValidHandle(trimmedHandle)) {
            throw new ParseException(Facebook.MESSAGE_CONSTRAINTS);
        }
        return new Facebook(trimmedHandle);
    }

    /**
     * Parses a {@code String handle} into a {@code Instagram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid for Instagram.
     */
    public static Instagram parseInstagram(String handle) throws ParseException {
        if (handle == null || handle.equals("")) {
            return new Instagram(null);
        }
        String trimmedHandle = handle.trim();
        if (!Instagram.isValidHandle(trimmedHandle)) {
            throw new ParseException(Instagram.MESSAGE_CONSTRAINTS);
        }
        return new Instagram(trimmedHandle);
    }

    /**
     * Parses a {@code String handle} into a {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid for Telegram.
     */
    public static Telegram parseTelegram(String handle) throws ParseException {
        if (handle == null || handle.equals("")) {
            return new Telegram(null);
        }
        String trimmedHandle = handle.trim();
        if (!Telegram.isValidHandle(trimmedHandle)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedHandle);
    }

    /**
     * Parses a {@code String twitter} into an {@code Twitter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param twitter twtter handle to add
     * @return the created Twitter object
     * @throws ParseException if the given {@code twitter} is invalid
     */
    public static Twitter parseTwitter(String twitter) throws ParseException {
        if (twitter == null || twitter.equals("")) {
            return new Twitter(null);
        }
        String trimmedTwitter = twitter.trim();
        if (!Twitter.isValidHandle(trimmedTwitter)) {
            throw new ParseException(Twitter.MESSAGE_CONSTRAINTS);
        }
        return new Twitter(trimmedTwitter);
    }

    /**
     * Parses a {@code String tiktok} into an {@code TikTok}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tiktok tiktok handle to add
     * @return the created TikTok object
     * @throws ParseException if the given {@code tiktok} is invalid
     */
    public static TikTok parseTikTok(String tiktok) throws ParseException {
        if (tiktok == null || tiktok.equals("")) {
            return new TikTok(null);
        }
        String trimmedTikTok = tiktok.trim();
        if (!TikTok.isValidHandle(trimmedTikTok)) {
            throw new ParseException(TikTok.MESSAGE_CONSTRAINTS);
        }
        return new TikTok(trimmedTikTok);
    }

    /**
     * Parses a {@code String dates} into a {@code Dates} object.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param dates the dates to add
     * @return the created Dates object
     * @throws ParseException if the given {@code dates} is invalid
     */
    public static Dates parseDates(String dates) throws ParseException {
        requireNonNull(dates);
        String trimmedDates = dates.trim();
        if (!Dates.isValidDatesSequence(trimmedDates)) {
            throw new ParseException(Dates.MESSAGE_CONSTRAINTS);
        }
        return new Dates(trimmedDates);
    }
}
