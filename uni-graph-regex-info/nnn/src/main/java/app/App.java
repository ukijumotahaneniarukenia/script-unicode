package app;

import com.ibm.icu.text.UnicodeSet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final String PROGRAM_NAME = "uni-graph-regex-info";

    private static final String PROGRAM_VERSION = "-1-0-0";

    private static final String PROGRAM_CMD = "java -jar";
    private static final String PROGRAM_SUFFIX = "-SNAPSHOT-jar-with-dependencies.jar";

    private static final String SEPARATOR = " ";
    private static final String SINGLE_QUOTE = "'";

    private static final String PREFIX_REPEAT_SALT = "[";

    private static final String SUFFIX_REPEAT_SALT = "]";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String STRING_JOINER = ",";

    private static final String OPTION_USAGE_PATTERN = "[\\u0400-\\u0404\\U0001F100-\\U0001F10A]";

    private static final String GRP = "grp";
    private static final String GRPSEQ = "grpseq";
    private static final String CODEPOINT = "codePoint";
    private static final String CODEPOINT_START = "startCodePoint";
    private static final String CODEPOINT_END = "endCodePoint";
    private static final String CODEPOINT_COUNT = "codePointCount";
    private static final String UNICODE_GRAPH_LIST = "graph";

    private static final List<String> OUTPUT_HEADER_LIST = new LinkedList(){{
        add(GRP);
        add(GRPSEQ);
        add(CODEPOINT_START);
        add(CODEPOINT);
        add(CODEPOINT_END);
        add(CODEPOINT_COUNT);
        add(UNICODE_GRAPH_LIST);
    }};

    private static Set<Integer> EXCLUDE_CODEPOINT_LIST = new LinkedHashSet(){{
        add(157);
        add(158);
        add(159);
    }};

    private static void Usage(){
        System.out.println("Usageだよーん" +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + SINGLE_QUOTE + OPTION_USAGE_PATTERN + SINGLE_QUOTE +
                RS
        );
        System.exit(0);
    }

    private static boolean excludeCodePoint(Integer codePoint){
        return EXCLUDE_CODEPOINT_LIST.stream().noneMatch(excludeCodePoint->excludeCodePoint.equals(codePoint));
    }

    private static Set<Integer> genUnicodeCodePoint(Integer s,Integer e){
        Set<Integer> codePointList = new LinkedHashSet<>();
        Set<Integer> lowerLeftCodePointList = IntStream.rangeClosed(s,e).boxed().filter(codePoint->excludeCodePoint(codePoint)).filter(codePoint -> codePoint < (int) Character.MIN_HIGH_SURROGATE).collect(Collectors.toSet());
        Set<Integer> lowerRightCodePointList = IntStream.rangeClosed(EXCLUDE_CODEPOINT_LIST.stream().max(Integer::compareTo).get() + 1,e).boxed().filter(codePoint->excludeCodePoint(codePoint)).filter(codePoint -> codePoint < (int) Character.MIN_HIGH_SURROGATE).collect(Collectors.toSet());
        Set<Integer> upperCodePointList = IntStream.rangeClosed(s,e).boxed().filter(codePoint -> codePoint > (int) Character.MAX_HIGH_SURROGATE).collect(Collectors.toSet());
        codePointList.addAll(lowerLeftCodePointList);
        codePointList.addAll(lowerRightCodePointList);
        codePointList.addAll(upperCodePointList);
        return codePointList;
    }

    public static void main(String... cmdLineArgs) {

        if(cmdLineArgs.length == 0){
            Usage();
        }

        if(cmdLineArgs.length != 1){
            Usage();
        }

        String pattern = cmdLineArgs[0];

        UnicodeSet unicodeSet = new UnicodeSet();

        unicodeSet.applyPattern(pattern);

        Iterator<UnicodeSet.EntryRange> entryRangeIterator = unicodeSet.ranges().iterator();

        List<Map<String,String>> summaryList = new LinkedList<>();

        while (entryRangeIterator.hasNext()){

            Map<String,String> detailMap = new LinkedHashMap<>();

            UnicodeSet.EntryRange entryRange = entryRangeIterator.next();

            Integer startCodePoint = entryRange.codepoint;
            Integer endCodePoint = entryRange.codepointEnd;
            Integer CodePointCount = genUnicodeCodePoint(startCodePoint,endCodePoint).size();

            detailMap.put(CODEPOINT_START,String.valueOf(startCodePoint));
            detailMap.put(CODEPOINT_END,String.valueOf(endCodePoint));
            detailMap.put(CODEPOINT_COUNT,String.valueOf(CodePointCount));

            detailMap.put(UNICODE_GRAPH_LIST,genUnicodeCodePoint(startCodePoint,endCodePoint).stream().map(e->PREFIX_REPEAT_SALT + String.valueOf(Character.toChars(e)) + SUFFIX_REPEAT_SALT ).collect(Collectors.joining(STRING_JOINER)));

            summaryList.add(detailMap);

        }

        int cnt = summaryList.size();

        int grp = 0;

        for(int i = 0;i<cnt;i++){

            if (i == 0){

                System.out.println(OUTPUT_HEADER_LIST.stream().collect(Collectors.joining(FS)));

            }

            grp++;

            for(int j = 0;j < Integer.parseInt(summaryList.get(i).get(CODEPOINT_END)) - Integer.parseInt(summaryList.get(i).get(CODEPOINT_START)) + 1;j++){
                {
                    System.out.print(grp);
                    System.out.print(FS);
                    System.out.print(j + 1);
                    System.out.print(FS);
                    System.out.print(summaryList.get(i).get(CODEPOINT_START));
                    System.out.print(FS);
                    System.out.print(Integer.parseInt(summaryList.get(i).get(CODEPOINT_START)) + j);
                    System.out.print(FS);
                    System.out.print(summaryList.get(i).get(CODEPOINT_END));
                    System.out.print(FS);
                    System.out.print(summaryList.get(i).get(CODEPOINT_COUNT));
                    System.out.print(FS);
                    System.out.print(Arrays.asList(summaryList.get(i).get(UNICODE_GRAPH_LIST).split("(?<=\\]),(?=\\[)")).get(j));
                }
                System.out.println();
            }
        }
        System.err.println("SkipCodePointList:" + EXCLUDE_CODEPOINT_LIST.stream().map(excludeCodePoint->String.valueOf(excludeCodePoint)).collect(Collectors.joining(SEPARATOR)));
   }
}