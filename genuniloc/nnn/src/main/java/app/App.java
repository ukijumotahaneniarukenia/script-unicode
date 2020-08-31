package app;

import com.ibm.icu.util.ULocale;

import java.util.*;
import java.util.stream.Collectors;

import static app.AppAvailableLocale.getAvailableLocaleMap;

public class App {

    private static final String PROGRAM_NAME = "genuniloc";

    private static final String PROGRAM_VERSION = "X-X-X";

    private static final String ITEM_JOINER = " ";

    private static final String DEFAULT_NONE_VALUE = "-";

    private static final String OPTION_HELP = "--help";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String BASE_NAME = "BaseName";
    private static final String CHARACTER_ORIENTATION = "CharacterOrientation";
    private static final String COUNTRY = "Country";
    private static final String DISPLAY_COUNTRY = "DisplayCountry";
    private static final String DISPLAY_LANGUAGE = "DisplayLanguage";
    private static final String DISPLAY_LANGUAGE_WITH_DIALECT = "DisplayLanguageWithDialect";
    private static final String DISPLAY_NAME = "DisplayName";
    private static final String DISPLAY_NAME_WITH_DIALECT = "DisplayNameWithDialect";
    private static final String DISPLAY_SCRIPT = "DisplayScript";
    private static final String DISPLAY_SCRIPT_IN_CONTEXT = "DisplayScriptInContext";
    private static final String DISPLAY_VARIANT = "DisplayVariant";
    private static final String I_S_O3_COUNTRY = "ISO3Country";
    private static final String I_S_O3_LANGUAGE = "ISO3Language";
    private static final String LANGUAGE = "Language";
    private static final String LINE_ORIENTATION = "LineOrientation";
    private static final String NAME = "Name";
    private static final String SCRIPT = "Script";
    private static final String VARIANT = "Variant";

    private static final List<String> OUTPUT_HEADER_LIST = new LinkedList(){{
        add(BASE_NAME);
        add(CHARACTER_ORIENTATION);
        add(COUNTRY);
        add(DISPLAY_COUNTRY);
        add(DISPLAY_LANGUAGE);
        add(DISPLAY_LANGUAGE_WITH_DIALECT);
        add(DISPLAY_NAME);
        add(DISPLAY_NAME_WITH_DIALECT);
        add(DISPLAY_SCRIPT);
        add(DISPLAY_SCRIPT_IN_CONTEXT);
        add(DISPLAY_VARIANT);
        add(I_S_O3_COUNTRY);
        add(I_S_O3_LANGUAGE);
        add(LANGUAGE);
        add(LINE_ORIENTATION);
        add(NAME);
        add(SCRIPT);
        add(VARIANT);
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
        System.out.println("Usageだよーん" + RS +
                "LOCALE_LIST:" +
                RS +
                Arrays.asList(ULocale.getAvailableLocales()).stream().map(uLocale -> uLocale.getName()).collect(Collectors.joining(ITEM_JOINER)) +
                RS +
                RS +
                localeList.stream().filter(e->! e.equals(OPTION_HELP)).map(locale->getAvailableLocale(locale)).collect(Collectors.joining(RS + RS)) +
                RS +
                RS +
                "java -jar "+PROGRAM_NAME+"-"+PROGRAM_VERSION+"-SNAPSHOT.jar " + OPTION_HELP + " af zh" +
                RS +
                RS +
                "java -jar "+PROGRAM_NAME+"-"+PROGRAM_VERSION+"-SNAPSHOT.jar " + OPTION_HELP + " zh" +
                RS +
                RS +
                "java -jar "+PROGRAM_NAME+"-"+PROGRAM_VERSION+"-SNAPSHOT.jar " + "zh" + RS
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

        Map<String,List<String>> summaryMap = new LinkedHashMap<>();

        includeLocaleList.stream()
            .filter(uLocale -> excludeMustLocale(uLocale)) //アンマッチ分は除外
            .filter(uLocale -> excludeOptionalLocale(uLocale)) //アンマッチ分は除外
            .filter(uLocale -> includeLocale(uLocale)) //引数に指定したロケールが定義ロケールに含まれている分を出力
            .forEach(uLocale -> {

                String key = uLocale.getBaseName().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getBaseName();

                List<String> list = new LinkedList<>();

                list.add(uLocale.getBaseName().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getBaseName());
                list.add(uLocale.getCharacterOrientation().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getCharacterOrientation());
                list.add(uLocale.getCountry().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getCountry());
                list.add(uLocale.getDisplayCountry().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayCountry());
                list.add(uLocale.getDisplayLanguage().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayLanguage());
                list.add(uLocale.getDisplayLanguageWithDialect().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayLanguageWithDialect());
                list.add(uLocale.getDisplayName().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayName());
                list.add(uLocale.getDisplayNameWithDialect().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayNameWithDialect());
                list.add(uLocale.getDisplayScript().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayScript());
                list.add(uLocale.getDisplayScriptInContext().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayScriptInContext());
                list.add(uLocale.getDisplayVariant().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getDisplayVariant());
                list.add(uLocale.getISO3Country().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getISO3Country());
                list.add(uLocale.getISO3Language().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getISO3Language());
                list.add(uLocale.getLanguage().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getLanguage());
                list.add(uLocale.getLineOrientation().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getLineOrientation());
                list.add(uLocale.getName().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getName());
                list.add(uLocale.getScript().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getScript());
                list.add(uLocale.getVariant().isBlank() ? DEFAULT_NONE_VALUE : uLocale.getVariant());

                summaryMap.put(key,list);
            });


        //header
        if(wantLocaleList.size() != 0){
            System.out.println(OUTPUT_HEADER_LIST.stream().collect(Collectors.joining(FS)));
        }

        //body
        summaryMap.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getValue().stream().collect(Collectors.joining(FS)));
        });
    }
}