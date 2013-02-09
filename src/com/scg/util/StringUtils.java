package com.scg.util;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/20/13
 * Time: 8:41 AM
 */
public class StringUtils {

    public static String pad(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * Center the contents of the string. If the supplied string is longer than the desired width, it is truncated to the
     * specified length. If the supplied string is shorter than the desired width, padding characters are added to the beginning
     * and end of the string such that the length is that specified; one additional padding character is prepended if required.
     * All leading and trailing whitespace is removed before centering.
     *
     * @param str the string to be left justified; if null, an empty string is used
     * @param width the desired width of the string; must be positive
     * @param padWithChar the character to use for padding, if needed
     * @return the left justified string
     */
    public static String justifyCenter( String str,
                                        final int width,
                                        char padWithChar ) {
        // Trim the leading and trailing whitespace ...
        str = str != null ? str.trim() : "";

        int addChars = width - str.length();
        if (addChars < 0) {
            // truncate
            return str.subSequence(0, width).toString();
        }
        // Write the content ...
        int prependNumber = addChars / 2;
        int appendNumber = addChars - prependNumber;

        final StringBuilder sb = new StringBuilder();

        // Prepend the pad character(s) ...
        while (prependNumber > 0) {
            sb.append(padWithChar);
            --prependNumber;
        }

        // Add the actual content
        sb.append(str);

        // Append the pad character(s) ...
        while (appendNumber > 0) {
            sb.append(padWithChar);
            --appendNumber;
        }

        return sb.toString();
    }

}
