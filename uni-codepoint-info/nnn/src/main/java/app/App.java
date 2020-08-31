package app;

import com.ibm.icu.impl.UCharacterName;
import com.ibm.icu.lang.UCharacter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final String PROGRAM_NAME = "uni-codepoint-info";
    private static final String PROGRAM_VERSION = "-1-0-0";

    private static final String PROGRAM_CMD = "java -jar";
    private static final String PROGRAM_SUFFIX = "-SNAPSHOT-jar-with-dependencies.jar";

    private static final String SEPARATOR = " ";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final byte DEFAULT_NONE_VALUE_BYTE = 0;
    private static final double DEFAULT_NONE_VALUE_DOUBLE = 0;
    private static final Integer DEFAULT_NONE_VALUE_INTEGER = 0;
    private static final String DEFAULT_NONE_VALUE_STRING = "-";
    private static final Boolean DEFAULT_NONE_VALUE_BOOLEAN = false;

    private static final String GRP="Grp";
    private static final String GRAM="Gram";
    private static final String CODEPOINT="CodePoint";
    private static final String CODEPOINT_MSB="CodepointMSB";

    //boolean型
    private static final String IS_B_M_P="isBMP";
    private static final String IS_BASE_FORM="isBaseForm";
    private static final String IS_DEFINED="isDefined";
    private static final String IS_DIGIT="isDigit";
    private static final String IS_I_S_O_CONTROL="isISOControl";
    private static final String IS_IDENTIFIER_IGNORABLE="isIdentifierIgnorable";
    private static final String IS_JAVA_IDENTIFIER_PART="isJavaIdentifierPart";
    private static final String IS_JAVA_IDENTIFIER_START="isJavaIdentifierStart";
    private static final String IS_JAVA_LETTER="isJavaLetter";
    private static final String IS_JAVA_LETTER_OR_DIGIT="isJavaLetterOrDigit";
    private static final String IS_LEGAL="isLegal";
    private static final String IS_LETTER="isLetter";
    private static final String IS_LETTER_OR_DIGIT="isLetterOrDigit";
    private static final String IS_LOWER_CASE="isLowerCase";
    private static final String IS_MIRRORED="isMirrored";
    private static final String IS_PRINTABLE="isPrintable";
    private static final String IS_SPACE="isSpace";
    private static final String IS_SPACE_CHAR="isSpaceChar";
    private static final String IS_SUPPLEMENTARY="isSupplementary";
    private static final String IS_TITLE_CASE="isTitleCase";
    private static final String IS_U_ALPHABETIC="isUAlphabetic";
    private static final String IS_U_LOWERCASE="isULowercase";
    private static final String IS_U_UPPERCASE="isUUppercase";
    private static final String IS_U_WHITE_SPACE="isUWhiteSpace";
    private static final String IS_UNICODE_IDENTIFIER_PART="isUnicodeIdentifierPart";
    private static final String IS_UNICODE_IDENTIFIER_START="isUnicodeIdentifierStart";
    private static final String IS_UPPER_CASE="isUpperCase";
    private static final String IS_WHITESPACE="isWhitespace";
    private static final String IS_SUPPLEMENTARY_CODE_POINT="isSupplementaryCodePoint";
    private static final String IS_VALID_CODE_POINT="isValidCodePoint";

    //int型
    private static final String CHAR_COUNT="charCount";
    private static final String DIGIT="digit";
    private static final String BIDI_PAIRED_BRACKET="BidiPairedBracket";
    private static final String COMBINING_CLASS="CombiningClass";
    private static final String DIRECTION="Direction";
    private static final String HAN_NUMERIC_VALUE="HanNumericValue";
    private static final String INT_PROPERTY_MAX_VALUE="IntPropertyMaxValue";
    private static final String INT_PROPERTY_MIN_VALUE="IntPropertyMinValue";
    private static final String MIRROR="Mirror";
    private static final String NUMERIC_VALUE="NumericValue";
    private static final String TYPE="Type";
    private static final String TO_LOWER_CASE="toLowerCase";
    private static final String TO_TITLE_CASE="toTitleCase";
    private static final String TO_UPPER_CASE="toUpperCase";

    //string型
    private static final String EXTENDED_NAME="ExtendedName";
    private static final String I_S_O_COMMENT="ISOComment";
    private static final String NAME="Name";
    private static final String NAME1_0="Name1_0";
    private static final String NAME_ALIAS="NameAlias";

    //double型
    private static final String UNICODE_NUMERIC_VALUE="UnicodeNumericValue";

    //byte型
    private static final String DIRECTIONALITY="Directionality";

    private static List<String> OUTPUT_HEADER_LIST = new LinkedList(){{

        //common
        add(GRP);
        add(GRAM);
        add(CODEPOINT);
        add(CODEPOINT_MSB);

        //boolean型
        add(IS_B_M_P);
        add(IS_BASE_FORM);
        add(IS_DEFINED);
        add(IS_DIGIT);
        add(IS_I_S_O_CONTROL);
        add(IS_IDENTIFIER_IGNORABLE);
        add(IS_JAVA_IDENTIFIER_PART);
        add(IS_JAVA_IDENTIFIER_START);
        add(IS_JAVA_LETTER);
        add(IS_JAVA_LETTER_OR_DIGIT);
        add(IS_LEGAL);
        add(IS_LETTER);
        add(IS_LETTER_OR_DIGIT);
        add(IS_LOWER_CASE);
        add(IS_MIRRORED);
        add(IS_PRINTABLE);
        add(IS_SPACE);
        add(IS_SPACE_CHAR);
        add(IS_SUPPLEMENTARY);
        add(IS_TITLE_CASE);
        add(IS_U_ALPHABETIC);
        add(IS_U_LOWERCASE);
        add(IS_U_UPPERCASE);
        add(IS_U_WHITE_SPACE);
        add(IS_UNICODE_IDENTIFIER_PART);
        add(IS_UNICODE_IDENTIFIER_START);
        add(IS_UPPER_CASE);
        add(IS_WHITESPACE);
        add(IS_SUPPLEMENTARY_CODE_POINT);
        add(IS_VALID_CODE_POINT);

        //int型
        add(CHAR_COUNT);
        add(DIGIT);
        add(BIDI_PAIRED_BRACKET);
        add(COMBINING_CLASS);
        add(DIRECTION);
        add(HAN_NUMERIC_VALUE);
        add(INT_PROPERTY_MAX_VALUE);
        add(INT_PROPERTY_MIN_VALUE);
        add(MIRROR);
        add(NUMERIC_VALUE);
        add(TYPE);
        add(TO_LOWER_CASE);
        add(TO_TITLE_CASE);
        add(TO_UPPER_CASE);

        //string型
        add(EXTENDED_NAME);
        add(I_S_O_COMMENT);
        add(NAME);
        add(NAME1_0);
        add(NAME_ALIAS);

        //double型
        add(UNICODE_NUMERIC_VALUE);

        //byte型
        add(DIRECTIONALITY);

    }};

    private static Set<Integer> EXCLUDE_CODEPOINT_LIST = new LinkedHashSet(){{
        add(157);
        add(158);
        add(159);
    }};

    private static void usage(){

        System.out.println("Usageだよーん" +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + "0" + SEPARATOR + "1114111" +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + "300" + SEPARATOR + "400" +
                RS +
                RS
        );
        System.exit(0);
    }

    private static Map<String,String> unicodeGraphDetailInfo(Integer codePoint){
        Map<String,String> detailMap = new LinkedHashMap<>();

        detailMap.put(GRAM,String.valueOf(Character.toChars(codePoint)));
        detailMap.put(CODEPOINT,String.valueOf(Optional.ofNullable(codePoint).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(CODEPOINT_MSB,String.valueOf(Optional.ofNullable(UCharacterName.getCodepointMSB(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));


        //boolean型
        detailMap.put(IS_B_M_P,String.valueOf(Optional.ofNullable(UCharacter.isBMP(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_BASE_FORM,String.valueOf(Optional.ofNullable(UCharacter.isBaseForm(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_DEFINED,String.valueOf(Optional.ofNullable(UCharacter.isDefined(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_DIGIT,String.valueOf(Optional.ofNullable(UCharacter.isDigit(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_I_S_O_CONTROL,String.valueOf(Optional.ofNullable(UCharacter.isISOControl(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_IDENTIFIER_IGNORABLE,String.valueOf(Optional.ofNullable(UCharacter.isIdentifierIgnorable(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_JAVA_IDENTIFIER_PART,String.valueOf(Optional.ofNullable(UCharacter.isJavaIdentifierPart(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_JAVA_IDENTIFIER_START,String.valueOf(Optional.ofNullable(UCharacter.isJavaIdentifierStart(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_JAVA_LETTER,String.valueOf(Optional.ofNullable(UCharacter.isJavaLetter(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_JAVA_LETTER_OR_DIGIT,String.valueOf(Optional.ofNullable(UCharacter.isJavaLetterOrDigit(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_LEGAL,String.valueOf(Optional.ofNullable(UCharacter.isLegal(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_LETTER,String.valueOf(Optional.ofNullable(UCharacter.isLetter(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_LETTER_OR_DIGIT,String.valueOf(Optional.ofNullable(UCharacter.isLetterOrDigit(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_LOWER_CASE,String.valueOf(Optional.ofNullable(UCharacter.isLowerCase(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_MIRRORED,String.valueOf(Optional.ofNullable(UCharacter.isMirrored(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_PRINTABLE,String.valueOf(Optional.ofNullable(UCharacter.isPrintable(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_SPACE,String.valueOf(Optional.ofNullable(UCharacter.isSpace(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_SPACE_CHAR,String.valueOf(Optional.ofNullable(UCharacter.isSpaceChar(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_SUPPLEMENTARY,String.valueOf(Optional.ofNullable(UCharacter.isSupplementary(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_TITLE_CASE,String.valueOf(Optional.ofNullable(UCharacter.isTitleCase(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_U_ALPHABETIC,String.valueOf(Optional.ofNullable(UCharacter.isUAlphabetic(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_U_LOWERCASE,String.valueOf(Optional.ofNullable(UCharacter.isULowercase(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_U_UPPERCASE,String.valueOf(Optional.ofNullable(UCharacter.isUUppercase(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_U_WHITE_SPACE,String.valueOf(Optional.ofNullable(UCharacter.isUWhiteSpace(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_UNICODE_IDENTIFIER_PART,String.valueOf(Optional.ofNullable(UCharacter.isUnicodeIdentifierPart(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_UNICODE_IDENTIFIER_START,String.valueOf(Optional.ofNullable(UCharacter.isUnicodeIdentifierStart(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_UPPER_CASE,String.valueOf(Optional.ofNullable(UCharacter.isUpperCase(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_WHITESPACE,String.valueOf(Optional.ofNullable(UCharacter.isWhitespace(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_SUPPLEMENTARY_CODE_POINT,String.valueOf(Optional.ofNullable(UCharacter.isSupplementaryCodePoint(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));
        detailMap.put(IS_VALID_CODE_POINT,String.valueOf(Optional.ofNullable(UCharacter.isValidCodePoint(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));


        //int型
        detailMap.put(CHAR_COUNT,String.valueOf(Optional.ofNullable(UCharacter.charCount(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(DIGIT,String.valueOf(Optional.ofNullable(UCharacter.digit(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(BIDI_PAIRED_BRACKET,String.valueOf(Optional.ofNullable(UCharacter.getBidiPairedBracket(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(COMBINING_CLASS,String.valueOf(Optional.ofNullable(UCharacter.getCombiningClass(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(DIRECTION,String.valueOf(Optional.ofNullable(UCharacter.getDirection(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(HAN_NUMERIC_VALUE,String.valueOf(Optional.ofNullable(UCharacter.getHanNumericValue(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(INT_PROPERTY_MAX_VALUE,String.valueOf(Optional.ofNullable(UCharacter.getIntPropertyMaxValue(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(INT_PROPERTY_MIN_VALUE,String.valueOf(Optional.ofNullable(UCharacter.getIntPropertyMinValue(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(MIRROR,String.valueOf(Optional.ofNullable(UCharacter.getMirror(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(NUMERIC_VALUE,String.valueOf(Optional.ofNullable(UCharacter.getNumericValue(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(TYPE,String.valueOf(Optional.ofNullable(UCharacter.getType(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(TO_LOWER_CASE,String.valueOf(Optional.ofNullable(UCharacter.toLowerCase(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(TO_TITLE_CASE,String.valueOf(Optional.ofNullable(UCharacter.toTitleCase(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));
        detailMap.put(TO_UPPER_CASE,String.valueOf(Optional.ofNullable(UCharacter.toUpperCase(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));



        //string型
        detailMap.put(EXTENDED_NAME,String.valueOf(Optional.ofNullable(UCharacter.getExtendedName(codePoint)).orElse(DEFAULT_NONE_VALUE_STRING)));
        detailMap.put(NAME,String.valueOf(Optional.ofNullable(UCharacter.getName(codePoint)).orElse(DEFAULT_NONE_VALUE_STRING)));
        detailMap.put(NAME_ALIAS,String.valueOf(Optional.ofNullable(UCharacter.getNameAlias(codePoint)).orElse(DEFAULT_NONE_VALUE_STRING)));

        //doble型
        detailMap.put(UNICODE_NUMERIC_VALUE,String.valueOf(Optional.ofNullable(UCharacter.getUnicodeNumericValue(codePoint)).orElse(DEFAULT_NONE_VALUE_DOUBLE)));


        //byte型
        detailMap.put(DIRECTIONALITY,String.valueOf(Optional.ofNullable(UCharacter.getDirectionality(codePoint)).orElse(DEFAULT_NONE_VALUE_BYTE)));

        return detailMap;
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

    private static boolean isNotNumeric(String s){
        return ! s.matches("[0-9]+");
    }

    public static void main(String... cmdLineArgs) {

        List<String> cmdLineArgList = new LinkedList(){{
            addAll(Arrays.asList(cmdLineArgs));
        }};

        if(cmdLineArgList.size() == 0){

            usage();

        }

        if(cmdLineArgList.size() != 2){

            usage();

        }

        boolean isNotNumeric = cmdLineArgList.stream().allMatch(e->isNotNumeric(e));

        if(isNotNumeric){

            usage();

        }

        Integer startCodePoint = Integer.valueOf(cmdLineArgList.get(0));
        Integer endCodePoint = Integer.valueOf(cmdLineArgList.get(1));

        Set<Integer> codePointList =  genUnicodeCodePoint(startCodePoint,endCodePoint);

        int grp = 0;

        if(codePointList.size() != 0){
            System.out.println(OUTPUT_HEADER_LIST.stream().collect(Collectors.joining(FS)));
        }

        for(Integer codePoint : codePointList){

            grp++;

            Map<String,String> detailMap = unicodeGraphDetailInfo(codePoint);

            {
                System.out.print(grp);
                System.out.print(FS);
                System.out.print(detailMap.get(GRAM));
                System.out.print(FS);
                System.out.print(detailMap.get(CODEPOINT));
                System.out.print(FS);
                System.out.print(detailMap.get(CODEPOINT_MSB));
                System.out.print(FS);

                System.out.print(detailMap.get(IS_B_M_P));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_BASE_FORM));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_DEFINED));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_DIGIT));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_I_S_O_CONTROL));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_IDENTIFIER_IGNORABLE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_JAVA_IDENTIFIER_PART));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_JAVA_IDENTIFIER_START));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_JAVA_LETTER));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_JAVA_LETTER_OR_DIGIT));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_LEGAL));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_LETTER));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_LETTER_OR_DIGIT));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_LOWER_CASE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_MIRRORED));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_PRINTABLE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_SPACE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_SPACE_CHAR));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_SUPPLEMENTARY));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_TITLE_CASE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_U_ALPHABETIC));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_U_LOWERCASE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_U_UPPERCASE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_U_WHITE_SPACE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_UNICODE_IDENTIFIER_PART));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_UNICODE_IDENTIFIER_START));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_UPPER_CASE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_WHITESPACE));
                System.out.print(FS);
                System.out.print(detailMap.get(DIRECTIONALITY));
                System.out.print(FS);
                System.out.print(detailMap.get(UNICODE_NUMERIC_VALUE));
                System.out.print(FS);
                System.out.print(detailMap.get(CHAR_COUNT));
                System.out.print(FS);
                System.out.print(detailMap.get(DIGIT));
                System.out.print(FS);
                System.out.print(detailMap.get(BIDI_PAIRED_BRACKET));
                System.out.print(FS);
                System.out.print(detailMap.get(COMBINING_CLASS));
                System.out.print(FS);
                System.out.print(detailMap.get(DIRECTION));
                System.out.print(FS);
                System.out.print(detailMap.get(HAN_NUMERIC_VALUE));
                System.out.print(FS);
                System.out.print(detailMap.get(INT_PROPERTY_MAX_VALUE));
                System.out.print(FS);
                System.out.print(detailMap.get(INT_PROPERTY_MIN_VALUE));
                System.out.print(FS);
                System.out.print(detailMap.get(MIRROR));
                System.out.print(FS);
                System.out.print(detailMap.get(NUMERIC_VALUE));
                System.out.print(FS);
                System.out.print(detailMap.get(TYPE));
                System.out.print(FS);
                System.out.print(detailMap.get(TO_LOWER_CASE));
                System.out.print(FS);
                System.out.print(detailMap.get(TO_TITLE_CASE));
                System.out.print(FS);
                System.out.print(detailMap.get(TO_UPPER_CASE));
                System.out.print(FS);
                System.out.print(detailMap.get(EXTENDED_NAME));
                System.out.print(FS);
                System.out.print(detailMap.get(I_S_O_COMMENT));
                System.out.print(FS);
                System.out.print(detailMap.get(NAME));
                System.out.print(FS);
                System.out.print(detailMap.get(NAME1_0));
                System.out.print(FS);
                System.out.print(detailMap.get(NAME_ALIAS));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_SUPPLEMENTARY_CODE_POINT));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_VALID_CODE_POINT));
            }
            System.out.println();
        }

        System.err.println("SkipCodePointList:" + EXCLUDE_CODEPOINT_LIST.stream().map(excludeCodePoint->String.valueOf(excludeCodePoint)).collect(Collectors.joining(SEPARATOR)));
    }
}