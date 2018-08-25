/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.web.util;

import java.util.Map;
import java.util.HashMap;

/**
 * Utility class for HTML escaping and unescaping.
 * Based on code from com.purpletech.util.Utils, written by Alex Chaffee.
 * <p>
 * <p>Reference:
 * <a href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">
 * http://hotwired.lycos.com/webmonkey/reference/special_characters/
 * </a>
 *
 * @author Juergen Hoeller
 * @since 01.03.2003
 */
public abstract class HtmlUtils {

    private static Object[][] entities = {
            {"#39", 39}, // ' - apostrophe
            {"quot", 34}, // " - double-quote
            {"amp", 38}, // & - ampersand
            {"lt", 60}, // < - less-than
            {"gt", 62}, // > - greater-than
            {"nbsp", 160}, // non-breaking space
            {"copy", 169}, // ?- copyright
            {"reg", 174}, // ?- registered trademark
            {"Agrave", 192}, // ?- uppercase A, grave accent
            {"Aacute", 193}, // ?- uppercase A, acute accent
            {"Acirc", 194}, // ?- uppercase A, circumflex accent
            {"Atilde", 195}, // ?- uppercase A, tilde
            {"Auml", 196}, // ?- uppercase A, umlaut
            {"Aring", 197}, // ?- uppercase A, ring
            {"AElig", 198}, // ?- uppercase AE
            {"Ccedil", 199}, // ?- uppercase C, cedilla
            {"Egrave", 200}, // ?- uppercase E, grave accent
            {"Eacute", 201}, // ?- uppercase E, acute accent
            {"Ecirc", 202}, // ?- uppercase E, circumflex accent
            {"Euml", 203}, // ?- uppercase E, umlaut
            {"Igrave", 204}, // ?- uppercase I, grave accent
            {"Iacute", 205}, // ?- uppercase I, acute accent
            {"Icirc", 206}, // ?- uppercase I, circumflex accent
            {"Iuml", 207}, // ?- uppercase I, umlaut
            {"ETH", 208}, // ?- uppercase Eth, Icelandic
            {"Ntilde", 209}, // ?- uppercase N, tilde
            {"Ograve", 210}, // ?- uppercase O, grave accent
            {"Oacute", 211}, // ?- uppercase O, acute accent
            {"Ocirc", 212}, // ?- uppercase O, circumflex accent
            {"Otilde", 213}, // ?- uppercase O, tilde
            {"Ouml", 214}, // ?- uppercase O, umlaut
            {"Oslash", 216}, // ?- uppercase O, slash
            {"Ugrave", 217}, // ?- uppercase U, grave accent
            {"Uacute", 218}, // ?- uppercase U, acute accent
            {"Ucirc", 219}, // ?- uppercase U, circumflex accent
            {"Uuml", 220}, // ?- uppercase U, umlaut
            {"Yacute", 221}, // ?- uppercase Y, acute accent
            {"THORN", 222}, // ?- uppercase THORN, Icelandic
            {"szlig", 223}, // ?- lowercase sharps, German
            {"agrave", 224}, // ?- lowercase a, grave accent
            {"aacute", 225}, // ?- lowercase a, acute accent
            {"acirc", 226}, // ?- lowercase a, circumflex accent
            {"atilde", 227}, // ?- lowercase a, tilde
            {"auml", 228}, // ?- lowercase a, umlaut
            {"aring", 229}, // ?- lowercase a, ring
            {"aelig", 230}, // ?- lowercase ae
            {"ccedil", 231}, // ?- lowercase c, cedilla
            {"egrave", 232}, // ?- lowercase e, grave accent
            {"eacute", 233}, // ?- lowercase e, acute accent
            {"ecirc", 234}, // ?- lowercase e, circumflex accent
            {"euml", 235}, // ?- lowercase e, umlaut
            {"igrave", 236}, // ?- lowercase i, grave accent
            {"iacute", 237}, // ?- lowercase i, acute accent
            {"icirc", 238}, // ?- lowercase i, circumflex accent
            {"iuml", 239}, // ?- lowercase i, umlaut
            {"eth", 240}, // ?- lowercase eth, Icelandic
            {"ntilde", 241}, // ?- lowercase n, tilde
            {"ograve", 242}, // ?- lowercase o, grave accent
            {"oacute", 243}, // ?- lowercase o, acute accent
            {"ocirc", 244}, // ?- lowercase o, circumflex accent
            {"otilde", 245}, // ?- lowercase o, tilde
            {"ouml", 246}, // ?- lowercase o, umlaut
            {"oslash", 248}, // ?- lowercase o, slash
            {"ugrave", 249}, // ?- lowercase u, grave accent
            {"uacute", 250}, // ?- lowercase u, acute accent
            {"ucirc", 251}, // ?- lowercase u, circumflex accent
            {"uuml", 252}, // ?- lowercase u, umlaut
            {"yacute", 253}, // ?- lowercase y, acute accent
            {"thorn", 254}, // ?- lowercase thorn, Icelandic
            {"yuml", 255}, //  - lowercase y, umlaut
            {"euro", 8364}, // Euro symbol
    };

    private static Map<Object, Object> e2i = new HashMap<>();
    private static Map<Object, Object> i2e = new HashMap<>();

    static {
        for (Object[] entity : entities) {
            e2i.put(entity[0], entity[1]);
            i2e.put(entity[1], entity[0]);
        }
    }

    /**
     * Turns funky characters into HTML entity equivalents.<p>
     * E.g. <tt>"bread" & "butter"</tt> => <tt>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</tt>
     * <p>Update: supports nearly all HTML entities, including funky accents. See the source code for more detail.
     **/
    public static String htmlEscape(String source) {
        StringBuilder buf = new StringBuilder();
        if (source != null) {
            for (int i = 0; i < source.length(); ++i) {
                char ch = source.charAt(i);
                String entity = (String) i2e.get((int) ch);
                if (entity == null) {
                    if (((int) ch) > 128)
                        buf.append("&#").append((int) ch).append(";");
                    else
                        buf.append(ch);
                } else {
                    buf.append("&").append(entity).append(";");
                }
            }
        }
        return buf.toString();
    }

    /**
     * Reverses htmlEscape.
     **/
    public static String htmlUnescape(String source) {
        StringBuilder buf = new StringBuilder();
        if (source != null) {
            for (int i = 0; i < source.length(); ++i) {
                char ch = source.charAt(i);
                if (ch == '&') {
                    int semi = source.indexOf(';', i + 1);
                    if (semi == -1) {
                        buf.append(ch);
                        continue;
                    }
                    String entity = source.substring(i + 1, semi); //semi-1);
                    Integer iso;
                    if (entity.charAt(0) == '#')
                        iso = new Integer(entity.substring(1));
                    else
                        iso = (Integer) e2i.get(entity);
                    if (iso == null) {
                        buf.append("&").append(entity).append(";");
                    } else {
                        buf.append((char) (iso.intValue()));
                        i = semi;
                    }
                } else {
                    buf.append(ch);
                }
            }
        }
        return buf.toString();
    }
}
