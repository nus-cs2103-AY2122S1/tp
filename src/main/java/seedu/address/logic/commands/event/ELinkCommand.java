package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;

public class ELinkCommand extends Command {
    public static final String COMMAND_WORD = "elink";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links an event to a contact. "
        + "Parameters: "
        + PREFIX_LINK + "EVENT_INDEX "
        + PREFIX_CONTACT + "CONTACT_INDEX ";
    public static final String MESSAGE_SUCCESS = "Successfully linked the event %s to the contact%s %s";

    private final Index eventIndex;
    private final Set<Index> contactIndexes;

    /**
     * Creates an ELinkCommand to link the specified {@code Event} to a {@code Contact}
     */
    public ELinkCommand(Index eventIndex, Set<Index> contactIndexes) {
        assert !contactIndexes.isEmpty();
        this.eventIndex = eventIndex;
        this.contactIndexes = contactIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Event> lastShownEventList = model.getFilteredEventList();
        List<Contact> lastShownContactList = model.getFilteredContactList();
        checkCommandValidity(lastShownEventList, lastShownContactList);

        String commandResult = "";
        Event eventToLink = lastShownEventList.get(eventIndex.getZeroBased());
        int count = 0;
        for (Index contactIndex : contactIndexes) {
            Contact contactToLink = lastShownContactList.get(contactIndex.getZeroBased());
            model.linkEventAndContact(eventToLink, contactToLink);
            if (count == 0) {
                commandResult += String.format(MESSAGE_SUCCESS, eventToLink.getName(),
                        contactIndexes.size() > 1 ? "s" : "", contactToLink.getName());
            } else {
                commandResult += contactToLink.getName();
            }
            if (count != contactIndexes.size() - 1) {
                commandResult += ", ";
            } else {
                commandResult += ".";
            }
            count++;
        }

        model.updateFilteredContactList(PREDICATE_HIDE_ALL_CONTACTS); // Hide first to update the contact cards.
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.updateFilteredEventList(PREDICATE_HIDE_ALL_EVENTS); // Hide first to update the event cards.
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        return new CommandResult(commandResult);
    }

    private void checkCommandValidity(List<Event> eventList, List<Contact> contactList) throws CommandException {
        if (eventIndex.getZeroBased() >= eventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        for (Index contactIndex : contactIndexes) {
            if (contactIndex.getZeroBased() >= contactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }
        }
    }
}
