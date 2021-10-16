package seedu.notor.logic.commands.person;

/**
 * Deprecated for now.
 * TODO: move this functionality into PersonAddGroupCommand since we do not have add subgroup command anymore
 */
@Deprecated
public class PersonAddSubGroupCommand {
//    public static final String COMMAND_WORD = "person_add_subgroup";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to a subgroup"
//            + "Parameters: "
//            + PREFIX_NAME + "NAME "
//            + PREFIX_GROUP + "GROUP"
//            + PREFIX_SUBGROUP + "SUBGROUP\n"
//            + "Example: " + COMMAND_WORD + " "
//            + PREFIX_NAME + "John Doe "
//            + PREFIX_GROUP + "Orbital "
//            + PREFIX_SUBGROUP + "Artemis";
//
//    public static final String MESSAGE_SUCCESS = "Added person to the %s";
//    public static final String MESSAGE_DUPLICATE_GROUP = "This person is already in the subgroup";
//
//    protected String personName;
//
//    protected String groupName;
//
//    protected String subGroupName;
//
//    /**
//     * Creates an PersonAddSubGroupCommand to add the specified {@code SubGroup}
//     *
//     * @param personName the name of the person to be added into the subgroup.
//     * @param groupName the name of the group where the subgroup belongs to.
//     * @param subGroupName the name of the subGroup.
//     */
//    public PersonAddSubGroupCommand(String personName, String groupName, String subGroupName) {
//        this.personName = personName;
//        this.groupName = groupName;
//        this.subGroupName = subGroupName;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        try {
//            Person personToEdit = model.findPerson(personName);
//            SuperGroup superGroup = model.findGroup(groupName);
//            superGroup.addSubGroup(subGroupName);
//            SubGroup subGroup = superGroup.findSubGroup(subGroupName);
//            subGroup.addPerson(personToEdit);
//            personToEdit.addSubGroup(subGroup);
//            superGroup.findSubGroup(subGroupName).addPerson(personToEdit);
//            model.setPerson(personToEdit, personToEdit);
//            return new CommandResult(String.format(MESSAGE_SUCCESS, groupName + "_" + subGroupName));
//        } catch (DuplicateItemException e) {
//            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
//        }
//    }
}
