ユニコードプロパティを指定して紐づく正規表現パタンなどを標準出力にTSV形式で出力するコマンド

jqとコラボする予定

ダウンロード

```
PROGRAM_NAME=uni-property-info
curl -fsSLO https://github.com/ukijumotahaneniarukenia/$PROGRAM_NAME/releases/download/1-0-0/$PROGRAM_NAME-1-0-0-SNAPSHOT-jar-with-dependencies.jar
```

使い方

```
$ java -jar uni-property-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar
Usageだよーん

PROPERTY-LIST:

--bidi-class
--block
--decomposition-type
--east-asian-width
--general-category
--grapheme-cluster-break
--hangul-syllable-type
--indic-matra-category
--indic-syllabic-category
--joining-group
--joining-type
--line-break
--nf*-quickcheck
--numeric-type
--script
--script-extensions
--sentence-break
--word-break

java -jar  uni-property-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar --help

java -jar  uni-property-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar --east-asian-width --grapheme-cluster-break

java -jar  uni-property-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar --decomposition-type


```
