package app;

import com.ibm.icu.lang.UScript;
import com.ibm.icu.text.*;
import com.ibm.icu.util.ULocale;

import java.util.*;
import java.util.stream.Collectors;

import static app.AppAvailableLocale.*;

public class App {

    private static final String PROGRAM_NAME = "genunireg";
    private static final String PROGRAM_VERSION = "X-X-X";
    private static final String PREFIX_REPEAT_SALT = "[:";
    private static final String SUFFIX_REPEAT_SALT = ":]";
    private static final String JOINING_SALT = "+";
    private static final String PREFIX_WRAPPER_SALT = "[";
    private static final String SUFFIX_WRAPPER_SALT = "]";
    private static final String DEFAULT_NONE_VALUE = "-";
    private static final String EMPTY = "";
    private static final String ITEM_JOINER = "+";
    private static final String SEPARATOR = " ";

    private static final String LOCALE_NAME = "LocaleName";
    private static final String DISPLAY_NAME = "DisplayName";
    private static final String DISPLAY_NAME_WITH_DIALECT = "DisplayNameWithDialect";
    private static final String PATTERN_CLASS = "PatternClass";
    private static final String REGEX_PATTERN = "RegexPattern";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String OPTION_HELP = "--help";

    private static final List<String> OUTPUT_HEADER_LIST = new LinkedList(){{
        add(LOCALE_NAME);
        add(DISPLAY_NAME);
        add(DISPLAY_NAME_WITH_DIALECT);
        add(PATTERN_CLASS);
        add(REGEX_PATTERN);
    }};

    private static String getAvailableLocale(String locale){
        return Optional.ofNullable(getAvailableLocaleMap().get(locale)).orElse(DEFAULT_NONE_VALUE+FS+locale);
    }

    private static List<ULocale> excludeOptionalLocaleList = new LinkedList(){{
    }};

    private static List<ULocale> excludeMustLocaleList = new LinkedList(){{

    }};

    private static List<ULocale> includeOptionalLocaleList = new LinkedList(){{
    }};

    private static List<ULocale> includeMustLocaleList = new LinkedList(){{
        // 利用可能なロケールの一覧取得
        addAll(Arrays.stream(ULocale.getAvailableLocales()).collect(Collectors.toList()));
    }};

    private static List<ULocale> includeLocaleList = new LinkedList(){{
        addAll(includeOptionalLocaleList);
        addAll(includeMustLocaleList);
    }};

    private static List<ULocale> wantLocaleList = new LinkedList(){{

    }};

    private static boolean excludeOptionalLocale(ULocale uLocale){
        return excludeOptionalLocaleList.stream().noneMatch(e-> e.equals(uLocale));
    }

    private static boolean excludeMustLocale(ULocale uLocale){
        return excludeMustLocaleList.stream().noneMatch(e-> e.equals(uLocale));
    }

    private static boolean includeLocale(ULocale uLocale){
        return wantLocaleList.stream().anyMatch(e-> e.equals(uLocale));
    }

    private static void Usage(List<String> localeList){
        System.out.println("Usageだよーん" +
                RS +
                RS +
                "LOCALE_LIST:" +
                RS +
                RS +
                Arrays.asList(ULocale.getAvailableLocales()).stream().map(uLocale -> uLocale.getName()).collect(Collectors.joining(SEPARATOR)) +
                RS +
                RS +
                localeList.stream().filter(e->! e.equals(OPTION_HELP)).map(locale->getAvailableLocale(locale)).collect(Collectors.joining(RS + RS)) +
                RS +
                RS +
                "java -jar "+PROGRAM_NAME+"-"+PROGRAM_VERSION+"-SNAPSHOT.jar " + OPTION_HELP + " af su az" +
                RS +
                RS +
                "java -jar "+PROGRAM_NAME+"-"+PROGRAM_VERSION+"-SNAPSHOT.jar " + OPTION_HELP + " zh" +
                RS +
                RS +
                "java -jar "+PROGRAM_NAME+"-"+PROGRAM_VERSION+"-SNAPSHOT.jar " + "af su az_Cyrl_AZ br_FR zh ja az ast" +
                RS
        );
        System.exit(0);
    }

    public static void main(String... cmdLineArgs) {

        List<String> cmdLineArgList = new LinkedList(){{
            addAll(Arrays.asList(cmdLineArgs));
        }};

        if(cmdLineArgList.size() == 0){
            Usage(Arrays.asList(OPTION_HELP));
        }

        for(String arg : cmdLineArgList){
            switch (arg){
                case OPTION_HELP:
                    Usage(cmdLineArgList);
                    break;
                default:
                    wantLocaleList.add(new ULocale(arg));
                    break;
            }
        }

        List<Map<String,String>> summaryList = new LinkedList<>();

        for(ULocale uLocale : wantLocaleList){

            Map<String,String> detailMap = new LinkedHashMap<>();

            StringBuffer sb = new StringBuffer();

            String patternClass = Arrays.stream(UScript.getCode(uLocale)).boxed().map(code-> PREFIX_REPEAT_SALT + UScript.getShortName(code) + SUFFIX_REPEAT_SALT).collect(Collectors.joining(JOINING_SALT,PREFIX_WRAPPER_SALT,SUFFIX_WRAPPER_SALT));

            detailMap.put(LOCALE_NAME,uLocale.getName());
            detailMap.put(DISPLAY_NAME,uLocale.getDisplayName().replace(SEPARATOR,EMPTY));
            detailMap.put(DISPLAY_NAME_WITH_DIALECT,uLocale.getDisplayNameWithDialect().replace(SEPARATOR,EMPTY));
            detailMap.put(PATTERN_CLASS,patternClass);

            UnicodeSet unicodeSet = new UnicodeSet(patternClass);
            unicodeSet._generatePattern(sb,true);

            detailMap.put(REGEX_PATTERN,sb.toString().replace(ITEM_JOINER,EMPTY));

            summaryList.add(detailMap);

        }

        //header
        if(wantLocaleList.size() != 0){
            System.out.println(OUTPUT_HEADER_LIST.stream().collect(Collectors.joining(FS)));
        }

        //body
        for(Map<String,String> detailMap : summaryList){
            {
                System.out.print(detailMap.get(LOCALE_NAME));
                System.out.print(FS);
                System.out.print(detailMap.get(DISPLAY_NAME));
                System.out.print(FS);
                System.out.print(detailMap.get(DISPLAY_NAME_WITH_DIALECT));
                System.out.print(FS);
                System.out.print(detailMap.get(PATTERN_CLASS));
                System.out.print(FS);
                System.out.print(detailMap.get(REGEX_PATTERN));
            }
            System.out.println();
        }
    }
}