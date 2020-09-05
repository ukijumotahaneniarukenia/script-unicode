package app;

import com.ibm.icu.lang.UScript;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.ULocale;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final String SEPARATOR = " ";

    private static final String UNICODE_PREFIX_CAPITAL = "\\U";
    private static final String UNICODE_PREFIX_NON_CAPITAL = "\\u";

    private static final String GRAPH_ALPHABETICAL_CAPITAL = "A-Z";
    private static final String GRAPH_ALPHABETICAL_NON_CAPITAL = "a-z";

    private static final String UNICODE_ALPHABETICAL_CAPITAL = "\\u0041-\\u005A";
    private static final String UNICODE_ALPHABETICAL_NON_CAPITAL = "\\u0061-\\u007A";

    private static final String PREFIX_REPEAT_SALT = "[:";
    private static final String SUFFIX_REPEAT_SALT = ":]";
    private static final String JOINING_SALT = "+";
    private static final String SPLITTER = "-";
    private static final String PREFIX_WRAPPER_SALT = "[";
    private static final String SUFFIX_WRAPPER_SALT = "]";
    private static final String EMPTY = "";
    private static final Integer UNICODE_MAX_LENGTH = 8;

    private static final String LOCALE_NAME = "localeName";
    private static final String DISPLAY_NAME = "displayName";
    private static final String DISPLAY_NAME_WITH_DIALECT = "displayNameWithDialect";
    private static final String PATTERN_CLASS = "patternClass";
    private static final String UNICODE_START_END = "unicodeStartEnd";
    private static final String CODE_POINT_START_END = "codePointStartEnd";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final List<String> OUTPUT_HEADER_LIST = new LinkedList(){{
        add(LOCALE_NAME);
        add(DISPLAY_NAME);
        add(DISPLAY_NAME_WITH_DIALECT);
        add(PATTERN_CLASS);
        add(UNICODE_START_END);
        add(CODE_POINT_START_END);
    }};

    private static List<Map<String,String>> getLocaleCodePointRangeInfo (ULocale uLocale){

        List<Map<String,String>> summaryList = new LinkedList<>();

        StringBuffer sb = new StringBuffer();

        String patternClass = Arrays.stream(UScript.getCode(uLocale)).boxed().map(code-> PREFIX_REPEAT_SALT + UScript.getShortName(code) + SUFFIX_REPEAT_SALT).collect(Collectors.joining(JOINING_SALT,PREFIX_WRAPPER_SALT,SUFFIX_WRAPPER_SALT));

        UnicodeSet unicodeSet = new UnicodeSet(patternClass);
        unicodeSet._generatePattern(sb,true);
        String s = sb.toString()
                .replace(PREFIX_WRAPPER_SALT+JOINING_SALT,EMPTY)
                .replace(SUFFIX_WRAPPER_SALT,EMPTY)
                .replace(GRAPH_ALPHABETICAL_CAPITAL,UNICODE_ALPHABETICAL_CAPITAL)
                .replace(GRAPH_ALPHABETICAL_NON_CAPITAL,UNICODE_ALPHABETICAL_NON_CAPITAL)
                .replace(PREFIX_WRAPPER_SALT,EMPTY);

        List<String> list = Arrays.stream(s.split("(?<!-)(?=\\\\(u|U))")).collect(Collectors.toList());

        for (String unicodePointStartEnd : list){

            Map<String,String> detailMap = new LinkedHashMap<>();

            List<String> l = Arrays.asList(unicodePointStartEnd.split(SPLITTER));

            String unicodeStartPoint = l.get(0).replace(UNICODE_PREFIX_NON_CAPITAL,EMPTY).replace(UNICODE_PREFIX_CAPITAL,EMPTY);

            String unicodeEndPoint;
            if(l.size() == 1){
                unicodeEndPoint = unicodeStartPoint;
            }else{
                unicodeEndPoint = l.get(1).replace(UNICODE_PREFIX_NON_CAPITAL,EMPTY).replace(UNICODE_PREFIX_CAPITAL,EMPTY);
            }

            if(unicodeEndPoint.length() == UNICODE_MAX_LENGTH){
                detailMap.put(UNICODE_START_END,UNICODE_PREFIX_CAPITAL+unicodeStartPoint+SPLITTER+UNICODE_PREFIX_CAPITAL+unicodeEndPoint);
            }else{
                detailMap.put(UNICODE_START_END,UNICODE_PREFIX_NON_CAPITAL+unicodeStartPoint+SPLITTER+UNICODE_PREFIX_NON_CAPITAL+unicodeEndPoint);
            }

            String startCodePoint = IntStream.rangeClosed(Integer.parseInt(unicodeStartPoint,16),Integer.parseInt(unicodeEndPoint,16)).boxed().min(Integer::compareTo).get().toString();
            String endCodePoint = IntStream.rangeClosed(Integer.parseInt(unicodeStartPoint,16),Integer.parseInt(unicodeEndPoint,16)).boxed().max(Integer::compareTo).get().toString();

            detailMap.put(CODE_POINT_START_END,startCodePoint+SPLITTER+endCodePoint);

            detailMap.put(LOCALE_NAME,uLocale.getName());
            detailMap.put(DISPLAY_NAME,uLocale.getDisplayName().replace(SEPARATOR,EMPTY));
            detailMap.put(DISPLAY_NAME_WITH_DIALECT,uLocale.getDisplayNameWithDialect().replace(SEPARATOR,EMPTY));
            detailMap.put(PATTERN_CLASS,patternClass);

            summaryList.add(detailMap);
        }
        
        return summaryList;
    }


    public static void main( String[] args ) {

        ULocale uLocale = ULocale.getDefault();

//        ULocale uLocale = new ULocale("af");

        List<Map<String,String>> summaryList = getLocaleCodePointRangeInfo(uLocale);

        int cnt = summaryList.size();

        for(int i=0; i < cnt; i++){
            if (i == 0){

                System.out.println(OUTPUT_HEADER_LIST.stream().collect(Collectors.joining(FS)));

            }
            {
                System.out.print(summaryList.get(i).get(LOCALE_NAME));
                System.out.print(FS);
                System.out.print(summaryList.get(i).get(DISPLAY_NAME));
                System.out.print(FS);
                System.out.print(summaryList.get(i).get(DISPLAY_NAME_WITH_DIALECT));
                System.out.print(FS);
                System.out.print(summaryList.get(i).get(PATTERN_CLASS));
                System.out.print(FS);
                System.out.print(summaryList.get(i).get(LOCALE_NAME));
                System.out.print(FS);
                System.out.print(summaryList.get(i).get(UNICODE_START_END));
                System.out.print(FS);
                System.out.print(summaryList.get(i).get(CODE_POINT_START_END));
            }
            System.out.println();
        }
    }
}
