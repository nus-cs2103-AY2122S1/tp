package socialite.logic.commands;

import static java.util.Objects.requireNonNull;
import static socialite.logic.parser.CliSyntax.PREFIX_FACEBOOK;
import static socialite.logic.parser.CliSyntax.PREFIX_INSTAGRAM;
import static socialite.logic.parser.CliSyntax.PREFIX_NAME;
import static socialite.logic.parser.CliSyntax.PREFIX_PHONE;
import static socialite.logic.parser.CliSyntax.PREFIX_REMARK;
import static socialite.logic.parser.CliSyntax.PREFIX_TAG;
import static socialite.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static socialite.logic.parser.CliSyntax.PREFIX_TIKTOK;
import static socialite.logic.parser.CliSyntax.PREFIX_TWITTER;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import socialite.commons.core.Messages;
import socialite.commons.core.index.Index;
import socialite.commons.util.CollectionUtil;
import socialite.logic.commands.exceptions.CommandException;
import socialite.model.Model;
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
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_FACEBOOK + "FACEBOOK] "
            + "[" + PREFIX_INSTAGRAM + "INSTAGRAM] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_TIKTOK + "TIKTOK] "
            + "[" + PREFIX_TWITTER + "TWITTER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_HELP_GUIDE = "Enter 'help edit' for in-app guidance.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Facebook updatedFacebook = editPersonDescriptor.getFacebook().orElse(personToEdit.getFacebook());
        Instagram updatedInstagram = editPersonDescriptor.getInstagram().orElse(personToEdit.getInstagram());
        Telegram updatedTelegram = editPersonDescriptor.getTelegram().orElse(personToEdit.getTelegram());
        Twitter updatedTwitter = editPersonDescriptor.getTwitter().orElse(personToEdit.getTwitter());
        TikTok updatedTikTok = editPersonDescriptor.getTikTok().orElse(personToEdit.getTiktok());
        Dates updatedDates = editPersonDescriptor.getDates().orElse(personToEdit.getDates());

        return new Person(updatedName, updatedPhone, updatedRemark, updatedTags, updatedFacebook, updatedInstagram,
                updatedTelegram, updatedTikTok, updatedTwitter, updatedDates);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
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

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
            setFacebook(toCopy.facebook);
            setInstagram(toCopy.instagram);
            setTelegram(toCopy.telegram);
            setTikTok(toCopy.tiktok);
            setTwitter(toCopy.twitter);
            setDates(toCopy.dates);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, remark, tags, facebook, instagram,
                    telegram, tiktok, twitter, dates);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }


        public void setInstagram(Instagram instagram) {
            this.instagram = instagram;
        }

        public Optional<Instagram> getInstagram() {
            return Optional.ofNullable(instagram);
        }

        public void setFacebook(Facebook facebook) {
            this.facebook = facebook;
        }

        public Optional<Facebook> getFacebook() {
            return Optional.ofNullable(facebook);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setTikTok(TikTok tiktok) {
            this.tiktok = tiktok;
        }

        public Optional<TikTok> getTikTok() {
            return Optional.ofNullable(this.tiktok);
        }

        public void setTwitter(Twitter twitter) {
            this.twitter = twitter;
        }

        public Optional<Twitter> getTwitter() {
            return Optional.ofNullable(this.twitter);
        }

        public void setDates(Dates dates) {
            this.dates = dates;
        }

        public Optional<Dates> getDates() {
            return Optional.ofNullable(this.dates);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getRemark().equals(e.getRemark())
                    && getTags().equals(e.getTags())
                    && getFacebook().equals(e.getFacebook())
                    && getInstagram().equals(e.getInstagram())
                    && getTelegram().equals(e.getTelegram())
                    && getTikTok().equals(e.getTikTok())
                    && getTwitter().equals(e.getTwitter())
                    && getDates().equals(e.getDates());
        }
    }
}
