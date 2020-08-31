定数クラスの作り方
```

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar 2>/dev/null | grep -P 'com.ibm.icu.lang.UProperty(?!\$)' | awk -v FS='\t' '{print $6}' OFS="\t" | sort | while read s;do echo "put(\"$s\",UProperty.$s);";done


$ curl -fsSL http://userguide.icu-project.org/strings/properties -o properties.html


ブラウザの開発者ツールでXPATH FULL PATHをコピー
$ echo '/html/body/div[3]/div/div[1]/div/div[2]/div/table/tbody/tr/td[2]/div/div[3]/div/table/tbody/tr/td/div/table'
/html/body/div[3]/div/div[1]/div/div[2]/div/table/tbody/tr/td[2]/div/div[3]/div/table/tbody/tr/td/div/table


パタン作成
$ echo '/html/body/div[3]/div/div[1]/div/div[2]/div/table/tbody/tr/td[2]/div/div[3]/div/table/tbody/tr/td/div/table' | teip -Gog '[0-9]' -- sed -r 's/(.*)/:nth-of-type(\1)/' | sed -r 's/\[|\]//g' | tr '/' ' '
 html body div:nth-of-type(3) div div:nth-of-type(1) div div:nth-of-type(2) div table tbody tr td:nth-of-type(2) div div:nth-of-type(3) div table tbody tr td div table



目的のものがとれるまで、左から一つずつタグ指定を増やして試す
cat properties.html | pup 'html body div:nth-of-type(3) div div:nth-of-type(1) table'

$ cat properties.html | pup 'html body div:nth-of-type(3) div div:nth-of-type(1) table' | html2 | grep -B3 enum | sed -n '1~5p' | awk '{print $2}' | sort
Bidi_Class
Block
Decomposition_Type
East_Asian_Width
General_Category
Grapheme_Cluster_Break
Hangul_Syllable_Type
Indic_Matra_Category
Indic_Syllabic_Category
Joining_Group
Joining_Type
Line_Break
NF*_QuickCheck
Numeric_Type
Script
Script_Extensions
Sentence_Break
Word_Break


cat properties.html | pup 'html body div:nth-of-type(3) div div:nth-of-type(1) table' | html2 | grep -B3 enum | sed -n '1~5p' | awk '{print $2}' | sort >list.txt
```

オプション定数の作成

```
$ cat list.txt | tr '[a-z]' '[A-Z]' | while read ptn;do  echo "private static final String OPTION_PROPERTY_$ptn = \"--$(echo $ptn | tr '[:upper:]' '[:lower:]' | tr '_' '-')\";";done
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
private static final String OPTION_PROPERTY_NF*_QUICKCHECK = "--nf*-quickcheck";
private static final String OPTION_PROPERTY_NUMERIC_TYPE = "--numeric-type";
private static final String OPTION_PROPERTY_SCRIPT = "--script";
private static final String OPTION_PROPERTY_SCRIPT_EXTENSIONS = "--script-extensions";
private static final String OPTION_PROPERTY_SENTENCE_BREAK = "--sentence-break";
private static final String OPTION_PROPERTY_WORD_BREAK = "--word-break";
```


コンスト定数の作成


```
$ cat list.txt | tr '[a-z]' '[A-Z]' |  while read ptn;do  echo "private static final String CONST_PROPERTY_$ptn = \"$ptn\";";done
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
private static final String CONST_PROPERTY_NF*_QUICKCHECK = "NF*_QUICKCHECK";
private static final String CONST_PROPERTY_NUMERIC_TYPE = "NUMERIC_TYPE";
private static final String CONST_PROPERTY_SCRIPT = "SCRIPT";
private static final String CONST_PROPERTY_SCRIPT_EXTENSIONS = "SCRIPT_EXTENSIONS";
private static final String CONST_PROPERTY_SENTENCE_BREAK = "SENTENCE_BREAK";
private static final String CONST_PROPERTY_WORD_BREAK = "WORD_BREAK";
```


スウィッチ文の作成


```
$ cat list.txt | tr '[a-z]' '[A-Z]' |  while read ptn;do  echo "case OPTION_PROPERTY_$ptn: includePropertyList.add(CONST_PROPERTY_$ptn);break;";done
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
case OPTION_PROPERTY_NF*_QUICKCHECK: includePropertyList.add(CONST_PROPERTY_NF*_QUICKCHECK);break;
case OPTION_PROPERTY_NUMERIC_TYPE: includePropertyList.add(CONST_PROPERTY_NUMERIC_TYPE);break;
case OPTION_PROPERTY_SCRIPT: includePropertyList.add(CONST_PROPERTY_SCRIPT);break;
case OPTION_PROPERTY_SCRIPT_EXTENSIONS: includePropertyList.add(CONST_PROPERTY_SCRIPT_EXTENSIONS);break;
case OPTION_PROPERTY_SENTENCE_BREAK: includePropertyList.add(CONST_PROPERTY_SENTENCE_BREAK);break;
case OPTION_PROPERTY_WORD_BREAK: includePropertyList.add(CONST_PROPERTY_WORD_BREAK);break;
```



元ネタ削除

```
rm properties.html
```
