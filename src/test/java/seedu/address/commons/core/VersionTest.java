package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests functionalities of Versions.
 */
public class VersionTest {

    /**
     * Checks whether the version string is parses correctly.
     */
    @Test
    public void versionParsing_acceptableVersionString_parsedVersionCorrectly() {
        verifyVersionIsParsedCorrectly("V0.0.0ea", 0, 0, 0, true);
        verifyVersionIsParsedCorrectly("V3.10.2", 3, 10, 2, false);
        verifyVersionIsParsedCorrectly("V100.100.100ea", 100, 100, 100, true);
    }

    /**
     * Checks whether the wrong version string will throw an exception.
     */
    @Test
    public void versionParsing_wrongVersionString_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Version.fromString("This is not a version string"));
    }

    /**
     * Checks whether a version contains expected information.
     */
    @Test
    public void versionConstructor_correctParameter_valueAsExpected() {
        Version version = new Version(19, 10, 20, true);

        assertEquals(19, version.getMajor());
        assertEquals(10, version.getMinor());
        assertEquals(20, version.getPatch());
        assertEquals(true, version.isEarlyAccess());
    }

    /**
     * Checks whether the string representation of a version is correct.
     */
    @Test
    public void versionToString_validVersion_correctStringRepresentation() {
        // boundary at 0
        Version version = new Version(0, 0, 0, true);
        assertEquals("V0.0.0ea", version.toString());

        // normal values
        version = new Version(4, 10, 5, false);
        assertEquals("V4.10.5", version.toString());

        // big numbers
        version = new Version(100, 100, 100, true);
        assertEquals("V100.100.100ea", version.toString());
    }

    /**
     * Checks whether compareTo is correct for versions.
     */
    @Test
    public void versionComparable_validVersion_compareToIsCorrect() {
        Version one;
        Version another;

        // Tests equality
        one = new Version(0, 0, 0, true);
        another = new Version(0, 0, 0, true);
        assertTrue(one.compareTo(another) == 0);

        one = new Version(11, 12, 13, false);
        another = new Version(11, 12, 13, false);
        assertTrue(one.compareTo(another) == 0);

        // Tests different patch
        one = new Version(0, 0, 5, false);
        another = new Version(0, 0, 0, false);
        assertTrue(one.compareTo(another) > 0);

        // Tests different minor
        one = new Version(0, 0, 0, false);
        another = new Version(0, 5, 0, false);
        assertTrue(one.compareTo(another) < 0);

        // Tests different major
        one = new Version(10, 0, 0, true);
        another = new Version(0, 0, 0, true);
        assertTrue(one.compareTo(another) > 0);

        // Tests high major vs low minor
        one = new Version(10, 0, 0, true);
        another = new Version(0, 1, 0, true);
        assertTrue(one.compareTo(another) > 0);

        // Tests high patch vs low minor
        one = new Version(0, 0, 10, false);
        another = new Version(0, 1, 0, false);
        assertTrue(one.compareTo(another) < 0);

        // Tests same major minor different patch
        one = new Version(2, 15, 0, false);
        another = new Version(2, 15, 5, false);
        assertTrue(one.compareTo(another) < 0);

        // Tests early access vs not early access on same version number
        one = new Version(2, 15, 0, true);
        another = new Version(2, 15, 0, false);
        assertTrue(one.compareTo(another) < 0);

        // Tests early access lower version vs not early access higher version compare by version number first
        one = new Version(2, 15, 0, true);
        another = new Version(2, 15, 5, false);
        assertTrue(one.compareTo(another) < 0);

        // Tests early access higher version vs not early access lower version compare by version number first
        one = new Version(2, 15, 0, false);
        another = new Version(2, 15, 5, true);
        assertTrue(one.compareTo(another) < 0);
    }

    /**
     * Checks whether hashCode is correct for valid versions.
     */
    @Test
    public void versionComparable_validVersion_hashCodeIsCorrect() {
        Version version = new Version(100, 100, 100, true);
        assertEquals(100100100, version.hashCode());

        version = new Version(10, 10, 10, false);
        assertEquals(1010010010, version.hashCode());
    }

    /**
     * Checks whether equals method is correct for valid versions.
     */
    @Test
    public void versionComparable_validVersion_equalIsCorrect() {
        Version one;
        Version another;

        one = new Version(0, 0, 0, false);
        another = new Version(0, 0, 0, false);
        assertTrue(one.equals(another));

        one = new Version(100, 191, 275, true);
        another = new Version(100, 191, 275, true);
        assertTrue(one.equals(another));
    }

    /**
     * Checks whether verified version is parsed correctly.
     *
     * @param versionString String representation of version.
     * @param major version major.
     * @param minor version minor.
     * @param patch version patch.
     * @param isEarlyAccess whether it is early access.
     */
    @Test
    private void verifyVersionIsParsedCorrectly(String versionString,
                                                int major, int minor, int patch,
                                                boolean isEarlyAccess) {
        assertEquals(new Version(major, minor, patch, isEarlyAccess), Version.fromString(versionString));
    }
}
