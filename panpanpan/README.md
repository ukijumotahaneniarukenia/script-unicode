# panpanpan

ユニコード正規表現のpatternから文字を生成し標準出力に出力するコマンド


ダウンロード

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/panpanpan/releases/download/1-0-0/panpanpan-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```


使い方


```
$ java -jar panpanpan-1.0.0-SNAPSHOT-jar-with-dependencies.jar
Usageだよーん

java -jar panpanpan-X-X-X-SNAPSHOT.jar '[\u0400-\u0404\U0001F100-\U0001F10A]'

```



IN


```
$ echo '[\u0400-\u0404\U0001F100-\U0001F10A]'
[\u0400-\u0404\U0001F100-\U0001F10A]
```


CMD


```
$ java -jar panpanpan-1.0.0-SNAPSHOT-jar-with-dependencies.jar '[\u0400-\u0404\U0001F100-\U0001F10A]'
```


OUT


```
grp	grpseq	startCodePoint	codePoint	endCodePoint	codePointCount	unicodeGraphList
1	1	1024	1024	1028	5	[Ѐ]
1	2	1024	1025	1028	5	[Ё]
1	3	1024	1026	1028	5	[Ђ]
1	4	1024	1027	1028	5	[Ѓ]
1	5	1024	1028	1028	5	[Є]
2	1	127232	127232	127242	11	[🄁]
2	2	127232	127233	127242	11	[🄀]
2	3	127232	127234	127242	11	[🄃]
2	4	127232	127235	127242	11	[🄂]
2	5	127232	127236	127242	11	[🄅]
2	6	127232	127237	127242	11	[🄄]
2	7	127232	127238	127242	11	[🄇]
2	8	127232	127239	127242	11	[🄆]
2	9	127232	127240	127242	11	[🄉]
2	10	127232	127241	127242	11	[🄈]
2	11	127232	127242	127242	11	[🄊]
```
