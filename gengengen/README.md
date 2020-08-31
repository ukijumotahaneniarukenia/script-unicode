# gengengen

ユニコードプロパティを指定して紐づく正規表現パタンなどを標準出力にTSV形式で出力するコマンド

jqとコラボする予定

ダウンロード

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/gengengen/releases/download/1-0-0/gengengen-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```

使い方

```
$ java -jar gengengen-1.0.0-SNAPSHOT-jar-with-dependencies.jar 
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

java -jar gengengen-X-X-X-SNAPSHOT.jar --help

java -jar gengengen-X-X-X-SNAPSHOT.jar --east-asian-width --grapheme-cluster-break

java -jar gengengen-X-X-X-SNAPSHOT.jar --decomposition-type
```


```
$ java -jar gengengen-1.0.0-SNAPSHOT-jar-with-dependencies.jar --numeric-type >numeric-type.tsv


$ java -jar gengengen-1.0.0-SNAPSHOT-jar-with-dependencies.jar --decomposition-type >decomposition-type.tsv
```
