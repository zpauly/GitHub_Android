package com.zpauly.githubapp.utils;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.TypefaceSpan;

import org.markdown4j.Markdown4jProcessor;
import org.xml.sax.XMLReader;

import java.io.IOException;

/**
 * Created by zpauly on 2016/10/20.
 */

public class HtmlUtil {
    public static final String SPACE = "&nbsp;";

    public static final String BREAK = "<br>";

    public static final String PRE_START = "<pre>";

    public static final String PRE_END = "</pre>";

    public static final String CODE_START = "<code>";

    public static final String CODE_END = "</code>";


    public static final String DEL = "del";

    public static final String CODE = "code";

    public static final String PRE = "pre";


    public static String format(String html) {
        StringBuilder formatted = new StringBuilder(html);

        formatSpan(formatted);

        trim(formatted);

        return formatted.toString();
    }

    private static void trim(StringBuilder html) {
        int length = html.length();
        int breakLength = BREAK.length();

        while (length > 0) {
            if (html.indexOf(BREAK) == 0)
                html.delete(0, breakLength);
            else if (length >= breakLength
                    && html.lastIndexOf(BREAK) == length - breakLength)
                html.delete(length - breakLength, length);
            else if (Character.isWhitespace(html.charAt(0)))
                html.deleteCharAt(0);
            else if (Character.isWhitespace(html.charAt(length - 1)))
                html.deleteCharAt(length - 1);
            else
                break;
            length = html.length();
        }
    }

    public static void formatSpan(StringBuilder html) {
        int start = html.indexOf(PRE_START);
        int spaceAdd = SPACE.length() - 1;
        int breakAdd = BREAK.length() - 1;
        while (start != -1) {
            int end = html.indexOf(PRE_END, start + PRE_START.length());
            if (end == -1) {
                break;
            }
            if (html.indexOf(CODE_START, start) == start)
                start += CODE_START.length();
            if (html.indexOf(CODE_END, start) == end - CODE_END.length())
                end -= CODE_END.length();

            int index = start;
            while (index < end) {
                switch (html.charAt(index)) {
                    case ' ':
                        html.deleteCharAt(index);
                        html.insert(index, SPACE);
                        start += spaceAdd;
                        end += spaceAdd;
                        break;
                    case '\t':
                        html.deleteCharAt(index);
                        for (int i = 0; i < 4; i++) {
                            html.insert(index, SPACE);
                            if (i == 3) {
                                start += spaceAdd;
                                end += spaceAdd;
                            } else {
                                start += spaceAdd + 1;
                                end += spaceAdd + 1;
                            }
                        }
                        break;
                    case '\n':
                        html.deleteCharAt(index);
                        html.insert(index, BREAK);
                        start += breakAdd;
                        end += breakAdd;
                        break;
                    default:
                        break;
                }
                index++;
            }
            start = html.indexOf(PRE_START, end + PRE_END.length());
        }
    }

    public static String parseMarkdownMarkdownToHtml(String markdown) {
        try {
            return new Markdown4jProcessor().process(markdown);
        } catch (IOException e) {
            e.printStackTrace();
            return markdown;
        }
    }

    public static Html.TagHandler getTagHandler() {
        return new HtmlTagHandler();
    }

    public static class HtmlTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if (DEL.equalsIgnoreCase(tag)) {
                if (opening)
                    startSpan(new StrikethroughSpan(), output);
                else
                    endSpan(StrikethroughSpan.class, output);
                return;
            }

            if (CODE.equalsIgnoreCase(tag)) {
                if (opening) {
                    startSpan(new TypefaceSpan("monospace"), output);
                    startSpan(new BackgroundColorSpan(0x30aaaaaa), output);
                } else {
                    endSpan(TypefaceSpan.class, output);
                    endSpan(BackgroundColorSpan.class, output);
                }
                return;
            }

            if (PRE.equalsIgnoreCase(tag)) {
                output.append('\n');
                if (opening)
                    startSpan(new TypefaceSpan("monospace"), output);
                else
                    endSpan(TypefaceSpan.class, output);
                return;
            }
        }

        private void startSpan(Object span, Editable output) {
            int length = output.length();
            output.setSpan(span, length, length, Spanned.SPAN_MARK_MARK);
        }

        private void endSpan(Class<?> type, Editable output) {
            int length = output.length();
            Object span = getLast(output, type);
            int start = output.getSpanStart(span);
            output.removeSpan(span);
            if (start != length)
                output.setSpan(span, start, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        private Object getLast(final Spanned text, final Class<?> kind) {
            Object[] spans = text.getSpans(0, text.length(), kind);
            return spans.length > 0 ? spans[spans.length - 1] : null;
        }
    }

}
