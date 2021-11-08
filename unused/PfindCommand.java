//@@author Samuel-bit-prog-unused
//not used due to freexing of features. Cannot tweak command format. Naming of command is off, wanted to change
//to mpfind instead of pfind.
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.List;
//
//import seedu.address.commons.util.StringUtil;
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.CommandResult;
//import seedu.address.model.Model;
//import seedu.address.model.module.member.Member;
//import seedu.address.model.module.member.position.Position;
//
///**
// * Finds and lists all members in Ailurus whose name contains any of the argument keywords.
// * Keyword matching is case-insensitive.
// */
//public class PfindCommand extends Command {
//
//    public static final String COMMAND_WORD = "pfind";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all members whose positions contain any of "
//            + "the specified keywords (case-insensitive) and display them as a list with index numbers.\n"
//            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
//            + "Example: " + COMMAND_WORD + " president assistant";
//
//    private final List<String> keywords;
//
//    public PfindCommand(List<String> keywords) {
//        this.keywords = keywords;
//    }
//
//    /**
//     * Returns true if the member contains a position that matches with the keywords.
//     *
//     * @return boolean representing if present
//     */
//    public boolean test(Member m) {
//        boolean isPresent = false;
//        for (Position p : m.getPositions()) {
//            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(p.positionName, keyword))) {
//                isPresent = true;
//            }
//        }
//        return isPresent;
//    }
//
//    @Override
//    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredMemberList(this::test);
//        return new CommandResult(
//                String.format("%1$d members with matching positions listed!", model.getFilteredMemberList().size()));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof PfindCommand // instanceof handles nulls
//                && keywords.equals(((PfindCommand) other).keywords)); // state check
//    }
//}
