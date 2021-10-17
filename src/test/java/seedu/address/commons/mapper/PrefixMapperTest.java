package seedu.address.commons.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.mapper.PrefixMapper.PREFIX_EDIT_GET_MAP;
import static seedu.address.commons.mapper.PrefixMapper.PREFIX_EDIT_SET_MAP;
import static seedu.address.commons.mapper.PrefixMapper.PREFIX_GET_MAP;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class PrefixMapperTest {
    @Test
    public void ensureTypeMatch() {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        for (Prefix prefix: ALL_PREFIXES) {
            Function<Person, ?> personFunction = PREFIX_GET_MAP.get(prefix);
            @SuppressWarnings("unchecked")
            BiConsumer<EditPersonDescriptor, Object> editSetFunction =
                    (BiConsumer<EditPersonDescriptor, Object>) PREFIX_EDIT_SET_MAP.get(prefix);
            Function<EditPersonDescriptor, ? extends Optional<?>> editGetFunction = PREFIX_EDIT_GET_MAP.get(prefix);
            if (editSetFunction == null || editGetFunction == null) {
                continue;
            }

            Object attribute = personFunction.apply(person);
            editSetFunction.accept(editPersonDescriptor, attribute);
            Object editAttribute = editGetFunction.apply(editPersonDescriptor).get();
            assertEquals(attribute.getClass(), editAttribute.getClass());
        }
    }
}
