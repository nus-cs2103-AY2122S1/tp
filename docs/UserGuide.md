### General

#### Switch Tabs: [contacts] or [tasks] or [help]

Switches to another specified tab.

Format: contacts or tasks or help

#### Exiting the program: [exit]

Exits the program.

Format: exit

### Contacts

#### Adding contact details of a person: [add]

Adds a contact to the address book. Only name is compulsory during creation.

Format:  add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...

#### Editing contact details: [edit]

Replaces the contact details of the contact at the chosen index with the new details.

Format: edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL a/ADDRESS] [t/TAG]...

* Edits the person at the specified INDEX. The index refers to the index number shown in the displayed contact list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing t/ without specifying any tags after it.

#### Deleting a contact: [delete]

Delete the contact at the chosen index.

Format: delete INDEX
* Deletes the person at the specified INDEX.
* The index refers to the index number shown in the contact list.
* The index must be a positive integer i.e. 1, 2, 3, …

#### Finding contact through searching name: [find]

Finds all contacts whose name matches the search term.

Format: find NAME

* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the name is searched.
* Persons matching all keywords will be returned (i.e. AND search). e.g. Hans Bo will return only Hans Bo and Hans Bo the Second. It will not return Hans Gruber, Bo Yang

#### Finding contact through searching a specific field: [find]

Find all contacts whose parameter (number, email, etc) matches the search term.

Format: find [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...
* The search is case-insensitive. e.g hans@gmail.com will match Hans@gmail.com
* The order of the keywords does not matter. e.g. a/tampines 123 will match Blk 123 Tampines.
* Contacts matching all keywords will be returned (i.e. AND search). e.g. “find p/86235343 t/CS2101” will return only contacts who both have the given phone number AND the tag CS2101. It will not return contacts with different phone numbers, even if they contain the tag CS2101.

#### Clearing all contacts: [clear]

Deletes all contacts.

Format: clear
