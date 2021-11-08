package manageme.logic.commands.module;

import static manageme.logic.parser.CliSyntax.PREFIX_NAME;

import manageme.testutil.EditModuleDescriptorBuilder;

public class ModuleCommandTestUtil {
    public static final String VALID_MODNAME_A = "CS2100";
    public static final String VALID_MODNAME_B = "CS2103";

    public static final String MODNAME_DESC_A = " " + PREFIX_NAME + VALID_MODNAME_A;
    public static final String MODNAME_DESC_B = " " + PREFIX_NAME + VALID_MODNAME_B;

    public static final EditModuleCommand.EditModuleDescriptor DESC_MODULE_A;
    public static final EditModuleCommand.EditModuleDescriptor DESC_MODULE_B;

    static {
        DESC_MODULE_A = new EditModuleDescriptorBuilder().withName(VALID_MODNAME_A).build();
        DESC_MODULE_B = new EditModuleDescriptorBuilder().withName(VALID_MODNAME_B).build();
    }
}
