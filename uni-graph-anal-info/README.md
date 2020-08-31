標準入力をアナライズして結果をTSV形式で標準出力に出力するコマンド

ダウンロード

```
PROGRAM_NAME=uni-graph-anal-info
curl -fsSLO https://github.com/ukijumotahaneniarukenia/$PROGRAM_NAME/releases/download/1-0-0/$PROGRAM_NAME-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```



使い方

```
$ java -jar uni-graph-anal-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar
^CUsageだよーん

echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . | xargs -n4 |java -jar uni-graph-anal-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar

echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . |java -jar uni-graph-anal-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar

echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'|java -jar uni-graph-anal-info-1-0-0-SNAPSHOT-jar-with-dependencies.jar

```
