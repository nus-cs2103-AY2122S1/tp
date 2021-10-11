package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class used to test serialization and deserialization
 */
public class SerializableTestClass {
    public static final String JSON_STRING_REPRESENTATION = String.format("{%n"
            + "  \"name\" : \"This is a test class\",%n"
            + "  \"listOfLocalDateTimes\" : "
            + "[ \"-999999999-01-01T00:00:00\", \"+999999999-12-31T23:59:59.999999999\", "
            + "\"0001-01-01T01:01:00\" ],%n"
            + "  \"mapOfIntegerToString\" : {%n"
            + "    \"1\" : \"One\",%n"
            + "    \"2\" : \"Two\",%n"
            + "    \"3\" : \"Three\"%n"
            + "  }%n"
            + "}");

    public static final String ENCRYPTED_JSON_STRING_REPRESENTATION =
            "]\n"
            + "<TA<���\u001BM�����1�.\n"
            + "�|s�q�&�*�X���-\u0001!�\u0018\u0001��\u0002p�aȶ�8<\u001C�|�\u0013������zp�QbZ�6�r��TY��U5��@\u0010�Z��t�\n"
            + "��^\r"
            + "�\u001F�0]��\u000F�e�\u000F�>9��{,v i~q��LF���e�\u0015��ϴ�$��\\�$\u0018䲟�\u0013]�|��\u0015�\u0003\u0010��B\t6H�e��Sm��OȎV\u0017��Djv�0�jN@z�[�4y����D��\u000Ed*�\u0018�_����}�+S\u0018�<\u0015�oʹn/�\u0015��\u0005\u0003�+��8?�\r"
            + "�3,��Î��'��UG�\\�?�}Mr�\u0001�\u0001n�\u0001OV1\u0014$4�#�1���\u000B��0\u0000��:�53�d��\u0013���pf�(\u0016\u0004?/���W>B�\u001F^r��\u0002e���ξ��\u0014�Q�#���\u000F�\u000B+58�Dv|���\u0019�\r"
            + ")��xv\u000B�{9I3T\u0006R�5�μ�ym/\u0001�����T�~�\b�'�@U\u000F`��\\\u0013�qK8d\u0018{F)6�\u001E���6��[���B�(�k�g̯7H{�|┪I� ї�ۏ���y���\f\u000Fl��������;kCr]�<�Y��_�\u0000w�E\u0003�Y����s�\u0006�";

    private static final String NAME_TEST_VALUE = "This is a test class";

    private String name;

    private List<LocalDateTime> listOfLocalDateTimes;
    private HashMap<Integer, String> mapOfIntegerToString;

    public static String getNameTestValue() {
        return NAME_TEST_VALUE;
    }

    public static List<LocalDateTime> getListTestValues() {
        List<LocalDateTime> listOfLocalDateTimes = new ArrayList<>();

        listOfLocalDateTimes.add(LocalDateTime.MIN);
        listOfLocalDateTimes.add(LocalDateTime.MAX);
        listOfLocalDateTimes.add(LocalDateTime.of(1, 1, 1, 1, 1));

        return listOfLocalDateTimes;
    }

    public static HashMap<Integer, String> getHashMapTestValues() {
        HashMap<Integer, String> mapOfIntegerToString = new HashMap<>();

        mapOfIntegerToString.put(1, "One");
        mapOfIntegerToString.put(2, "Two");
        mapOfIntegerToString.put(3, "Three");

        return mapOfIntegerToString;
    }

    public void setTestValues() {
        name = getNameTestValue();
        listOfLocalDateTimes = getListTestValues();
        mapOfIntegerToString = getHashMapTestValues();
    }

    public String getName() {
        return name;
    }

    public List<LocalDateTime> getListOfLocalDateTimes() {
        return listOfLocalDateTimes;
    }

    public HashMap<Integer, String> getMapOfIntegerToString() {
        return mapOfIntegerToString;
    }
}
