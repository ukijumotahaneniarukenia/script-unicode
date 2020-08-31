package app;

import com.ibm.icu.lang.CharacterProperties;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.CodePointMap;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    private static final String PROGRAM_NAME = "uni-property-info";

    private static final String PROGRAM_VERSION = "-1-0-0";

    private static final String PROGRAM_CMD = "java -jar ";
    private static final String PROGRAM_SUFFIX = "-SNAPSHOT-jar-with-dependencies.jar";

    private static final String SEPARATOR = " ";

    private static final String GROUP = "grp";
    private static final String GROUP_SEQ = "grpSeq";
    private static final String PROPERTY_NAME = "propertyName";
    private static final String CODEPOINT_START = "startCodePoint";
    private static final String CODEPOINT_END = "endCodePoint";
    private static final String REGEXP_RANGE_PATTERN_UNICODE = "regexpRangePatternUnicode";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String OPTION_HELP = "--help";

    private static final String OPTION_PROPERTY_BIDI_CLASS = "--bidi-class";
    private static final String OPTION_PROPERTY_BLOCK = "--block";
    private static final String OPTION_PROPERTY_DECOMPOSITION_TYPE = "--decomposition-type";
    private static final String OPTION_PROPERTY_EAST_ASIAN_WIDTH = "--east-asian-width";
    private static final String OPTION_PROPERTY_GENERAL_CATEGORY = "--general-category";
    private static final String OPTION_PROPERTY_GRAPHEME_CLUSTER_BREAK = "--grapheme-cluster-break";
    private static final String OPTION_PROPERTY_HANGUL_SYLLABLE_TYPE = "--hangul-syllable-type";
    private static final String OPTION_PROPERTY_INDIC_MATRA_CATEGORY = "--indic-matra-category";
    private static final String OPTION_PROPERTY_INDIC_SYLLABIC_CATEGORY = "--indic-syllabic-category";
    private static final String OPTION_PROPERTY_JOINING_GROUP = "--joining-group";
    private static final String OPTION_PROPERTY_JOINING_TYPE = "--joining-type";
    private static final String OPTION_PROPERTY_LINE_BREAK = "--line-break";
    private static final String OPTION_PROPERTY_NF_QUICKCHECK = "--nf*-quickcheck";
    private static final String OPTION_PROPERTY_NUMERIC_TYPE = "--numeric-type";
    private static final String OPTION_PROPERTY_SCRIPT = "--script";
    private static final String OPTION_PROPERTY_SCRIPT_EXTENSIONS = "--script-extensions";
    private static final String OPTION_PROPERTY_SENTENCE_BREAK = "--sentence-break";
    private static final String OPTION_PROPERTY_WORD_BREAK = "--word-break";

    private static final String CONST_PROPERTY_BIDI_CLASS = "BIDI_CLASS";
    private static final String CONST_PROPERTY_BLOCK = "BLOCK";
    private static final String CONST_PROPERTY_DECOMPOSITION_TYPE = "DECOMPOSITION_TYPE";
    private static final String CONST_PROPERTY_EAST_ASIAN_WIDTH = "EAST_ASIAN_WIDTH";
    private static final String CONST_PROPERTY_GENERAL_CATEGORY = "GENERAL_CATEGORY";
    private static final String CONST_PROPERTY_GRAPHEME_CLUSTER_BREAK = "GRAPHEME_CLUSTER_BREAK";
    private static final String CONST_PROPERTY_HANGUL_SYLLABLE_TYPE = "HANGUL_SYLLABLE_TYPE";
    private static final String CONST_PROPERTY_INDIC_MATRA_CATEGORY = "INDIC_MATRA_CATEGORY";
    private static final String CONST_PROPERTY_INDIC_SYLLABIC_CATEGORY = "INDIC_SYLLABIC_CATEGORY";
    private static final String CONST_PROPERTY_JOINING_GROUP = "JOINING_GROUP";
    private static final String CONST_PROPERTY_JOINING_TYPE = "JOINING_TYPE";
    private static final String CONST_PROPERTY_LINE_BREAK = "LINE_BREAK";
    private static final String CONST_PROPERTY_NF_QUICKCHECK = "NF*_QUICKCHECK";
    private static final String CONST_PROPERTY_NUMERIC_TYPE = "NUMERIC_TYPE";
    private static final String CONST_PROPERTY_SCRIPT = "SCRIPT";
    private static final String CONST_PROPERTY_SCRIPT_EXTENSIONS = "SCRIPT_EXTENSIONS";
    private static final String CONST_PROPERTY_SENTENCE_BREAK = "SENTENCE_BREAK";
    private static final String CONST_PROPERTY_WORD_BREAK = "WORD_BREAK";

    private static void Usage(){

        System.out.println("Usageだよーん" +
                RS +
                RS +
                "PROPERTY-LIST:" +
                RS +
                RS +
                OPTION_PROPERTY_BIDI_CLASS+
                RS +
                OPTION_PROPERTY_BLOCK+
                RS +
                OPTION_PROPERTY_DECOMPOSITION_TYPE+
                RS +
                OPTION_PROPERTY_EAST_ASIAN_WIDTH+
                RS +
                OPTION_PROPERTY_GENERAL_CATEGORY+
                RS +
                OPTION_PROPERTY_GRAPHEME_CLUSTER_BREAK+
                RS +
                OPTION_PROPERTY_HANGUL_SYLLABLE_TYPE+
                RS +
                OPTION_PROPERTY_INDIC_MATRA_CATEGORY+
                RS +
                OPTION_PROPERTY_INDIC_SYLLABIC_CATEGORY+
                RS +
                OPTION_PROPERTY_JOINING_GROUP+
                RS +
                OPTION_PROPERTY_JOINING_TYPE+
                RS +
                OPTION_PROPERTY_LINE_BREAK+
                RS +
                OPTION_PROPERTY_NF_QUICKCHECK+
                RS +
                OPTION_PROPERTY_NUMERIC_TYPE+
                RS +
                OPTION_PROPERTY_SCRIPT+
                RS +
                OPTION_PROPERTY_SCRIPT_EXTENSIONS+
                RS +
                OPTION_PROPERTY_SENTENCE_BREAK+
                RS +
                OPTION_PROPERTY_WORD_BREAK+
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_HELP +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_PROPERTY_EAST_ASIAN_WIDTH + SEPARATOR + OPTION_PROPERTY_GRAPHEME_CLUSTER_BREAK +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_PROPERTY_DECOMPOSITION_TYPE +
                RS
        );
        System.exit(0);
    }

    private static List<String> excludeOptionalPropertyList = new LinkedList(){{
    }};

    private static List<String> excludeMustPropertyList = new LinkedList(){{
        add(CONST_PROPERTY_SCRIPT_EXTENSIONS);
    }};

    private static List<String> includeOptionalPropertyList = new LinkedList(){{

    }};

    private static List<String> includeMustPropertyList = new LinkedList(){{

    }};

    private static List<String> includePropertyList = new LinkedList(){{
        addAll(includeOptionalPropertyList);
        addAll(includeMustPropertyList);
    }};

    private static boolean excludeOptionalProperty(Map.Entry<String, Integer> map){
        return excludeOptionalPropertyList.stream().noneMatch(e-> e.equals(map.getKey()));
    }

    private static boolean excludeMustProperty(Map.Entry<String, Integer> map){
        return excludeMustPropertyList.stream().noneMatch(e-> e.equals(map.getKey()));
    }

    private static boolean includeProperty(Map.Entry<String, Integer> map){
        return includePropertyList.stream().anyMatch(e-> e.equals(map.getKey()));
    }

    public static void main(String... cmdLineArgs) {

        List<String> cmdLineArgList = new LinkedList(){{
            addAll(Arrays.asList(cmdLineArgs));
        }};

        for(String arg:cmdLineArgList){
            switch (arg){
                case OPTION_HELP:Usage();break;

                case OPTION_PROPERTY_BIDI_CLASS: includePropertyList.add(CONST_PROPERTY_BIDI_CLASS);break;
                case OPTION_PROPERTY_BLOCK: includePropertyList.add(CONST_PROPERTY_BLOCK);break;
                case OPTION_PROPERTY_DECOMPOSITION_TYPE: includePropertyList.add(CONST_PROPERTY_DECOMPOSITION_TYPE);break;
                case OPTION_PROPERTY_EAST_ASIAN_WIDTH: includePropertyList.add(CONST_PROPERTY_EAST_ASIAN_WIDTH);break;
                case OPTION_PROPERTY_GENERAL_CATEGORY: includePropertyList.add(CONST_PROPERTY_GENERAL_CATEGORY);break;
                case OPTION_PROPERTY_GRAPHEME_CLUSTER_BREAK: includePropertyList.add(CONST_PROPERTY_GRAPHEME_CLUSTER_BREAK);break;
                case OPTION_PROPERTY_HANGUL_SYLLABLE_TYPE: includePropertyList.add(CONST_PROPERTY_HANGUL_SYLLABLE_TYPE);break;
                case OPTION_PROPERTY_INDIC_MATRA_CATEGORY: includePropertyList.add(CONST_PROPERTY_INDIC_MATRA_CATEGORY);break;
                case OPTION_PROPERTY_INDIC_SYLLABIC_CATEGORY: includePropertyList.add(CONST_PROPERTY_INDIC_SYLLABIC_CATEGORY);break;
                case OPTION_PROPERTY_JOINING_GROUP: includePropertyList.add(CONST_PROPERTY_JOINING_GROUP);break;
                case OPTION_PROPERTY_JOINING_TYPE: includePropertyList.add(CONST_PROPERTY_JOINING_TYPE);break;
                case OPTION_PROPERTY_LINE_BREAK: includePropertyList.add(CONST_PROPERTY_LINE_BREAK);break;
                case OPTION_PROPERTY_NF_QUICKCHECK: includePropertyList.add(CONST_PROPERTY_NF_QUICKCHECK);break;
                case OPTION_PROPERTY_NUMERIC_TYPE: includePropertyList.add(CONST_PROPERTY_NUMERIC_TYPE);break;
                case OPTION_PROPERTY_SCRIPT: includePropertyList.add(CONST_PROPERTY_SCRIPT);break;
                case OPTION_PROPERTY_SCRIPT_EXTENSIONS: includePropertyList.add(CONST_PROPERTY_SCRIPT_EXTENSIONS);break;
                case OPTION_PROPERTY_SENTENCE_BREAK: includePropertyList.add(CONST_PROPERTY_SENTENCE_BREAK);break;
                case OPTION_PROPERTY_WORD_BREAK: includePropertyList.add(CONST_PROPERTY_WORD_BREAK);break;

                default:Usage();break;
            }
        }

        if(includePropertyList.size()==0){
            Usage();
        }

        Map<String,List<List<Integer>>> map = new LinkedHashMap<>();

        AppAvailableUnicodeProperty.getUnicodeAvailablePropertyMap().entrySet().stream().parallel()
                .filter(e->includeProperty(e))
                .filter(e->excludeMustProperty(e))
                .filter(e->excludeOptionalProperty(e))
                .forEach(entry ->
                        {
                            Integer unicodePropertyValue = entry.getValue();
                            Iterator<CodePointMap.Range> codePointMapRangeIterator = CharacterProperties.getIntPropertyMap(unicodePropertyValue).iterator();
                            List<List<Integer>> propertyList = new LinkedList<>();
                            while (codePointMapRangeIterator.hasNext()){
                                List<Integer> propertyStartEndList = new LinkedList<>();
                                CodePointMap.Range range = codePointMapRangeIterator.next();
                                propertyStartEndList.add(range.getStart());
                                propertyStartEndList.add(range.getEnd());
                                propertyList.add(propertyStartEndList);
                            }
                            map.put(entry.getKey(),propertyList);
                        }
                );

        Map<String,List<Map<String,List<String>>>> summaryMap = new LinkedHashMap<>();

        List<Map<String,List<String>>> summaryList = new LinkedList<>();

        for(Map.Entry<String,List<List<Integer>>> entry : map.entrySet()){

            UnicodeSet unicodeSet = new UnicodeSet();

            for(List<Integer> list :entry.getValue()){

                Map<String,List<String>> detailMap = new LinkedHashMap<>();

                StringBuffer sb = new StringBuffer();

                Integer startCodePoint = list.get(0);
                Integer endCodePoint = list.get(1);

                detailMap.put(CODEPOINT_START,Arrays.asList(String.valueOf(startCodePoint)));
                detailMap.put(CODEPOINT_END,Arrays.asList(String.valueOf(endCodePoint)));

                unicodeSet.set(startCodePoint,endCodePoint);

                unicodeSet._generatePattern(sb,true);

                detailMap.put(REGEXP_RANGE_PATTERN_UNICODE,Arrays.asList(sb.toString()));

                summaryList.add(detailMap);

            }

            summaryMap.put(entry.getKey(),summaryList);

        }

        int cnt = 0;
        int grp = 0;

        String prevPropertyName = null;
        for(Map.Entry<String,List<Map<String,List<String>>>> propertyMap : summaryMap.entrySet()){

            String propertyName = propertyMap.getKey();

            int grpseq = 0;

            for(Map<String,List<String>> summaryListMap : propertyMap.getValue()){

                if(cnt == 0){

                    System.out.println(GROUP + FS + GROUP_SEQ + FS +PROPERTY_NAME + FS + summaryListMap.keySet().stream().collect(Collectors.joining(FS)));

                }

                grpseq++;

                if(prevPropertyName != propertyName){

                    grp++;

                    System.out.println( grp + FS + grpseq + FS + propertyName + FS +summaryListMap.values().stream().flatMap(e->e.stream()).collect(Collectors.joining(FS)));

                }else{

                    System.out.println( grp + FS + grpseq + FS + propertyName + FS +summaryListMap.values().stream().flatMap(e->e.stream()).collect(Collectors.joining(FS)));

                }

                cnt++;

                prevPropertyName = propertyName;
            }
        }
    }
}