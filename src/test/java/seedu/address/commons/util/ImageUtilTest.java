package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ImageUtilTest {

    @Test
    public void getSocialIcon_fileExists() {
        assertTrue(ImageUtil.getSocialIcon("ig") != null);
        assertTrue(ImageUtil.getSocialIcon("tg") != null);
        assertTrue(ImageUtil.getSocialIcon("in") != null);
        assertTrue(ImageUtil.getSocialIcon("fb") != null);
        assertTrue(ImageUtil.getSocialIcon("sc") != null);
        assertTrue(ImageUtil.getSocialIcon("tw") != null);
        assertTrue(ImageUtil.getSocialIcon("gh") != null);
        assertTrue(ImageUtil.getSocialIcon("dc") != null);
        assertTrue(ImageUtil.getSocialIcon("") == null);
    }

    @Test
    public void getGenderIcon_fileExists() {
        assertTrue(ImageUtil.getGenderIcon("M") != null);
        assertTrue(ImageUtil.getGenderIcon("F") != null);
        assertTrue(ImageUtil.getGenderIcon("O") != null);
        assertTrue(ImageUtil.getGenderIcon("") == null);
    }

}
