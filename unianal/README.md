# unianal

標準入力をアナライズして結果をTSV形式で標準出力に出力するコマンド

ダウンロード

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/unianal/releases/download/1-0-0/unianal-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```



使い方

```
$ java -jar unianal-1.0.0-SNAPSHOT-jar-with-dependencies.jar
^CUsageだよーん

echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . | xargs -n4 |java -jar unianal-X-X-X-SNAPSHOT.jar

echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . |　java -jar unianal-X-X-X-SNAPSHOT.jar

echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| java -jar unianal-X-X-X-SNAPSHOT.jar
```

IN

```
$ echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . | xargs -n4
Ѐ Ё Ђ Ѓ
Є Ѕ І Ї
Ј Љ Њ Ћ
Ќ Ѝ Ў Џ
А Б В Г
Д Е Ж З
И Й К Л
М Н О П
Р С Т У
Ф Х Ц Ч
Ш Щ Ъ Ы
```


CMD

```
$ echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . | xargs -n4 | java -jar unianal-1.0.0-SNAPSHOT-jar-with-dependencies.jar >sample-grp.tsv
```



IN

```
$ echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po .
Ѐ
Ё
Ђ
Ѓ
Є
Ѕ
І
Ї
Ј
Љ
Њ
Ћ
Ќ
Ѝ
Ў
Џ
А
Б
В
Г
Д
Е
Ж
З
И
Й
К
Л
М
Н
О
П
Р
С
Т
У
Ф
Х
Ц
Ч
Ш
Щ
Ъ
Ы
```

CMD


```
$ echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'| grep -Po . | java -jar unianal-1.0.0-SNAPSHOT-jar-with-dependencies.jar >sample.tsv
```



IN

```
$ echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'
ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ
```


CMD

```
$ echo 'ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫ'|java -jar unianal-1.0.0-SNAPSHOT-jar-with-dependencies.jar >sample-gram.tsv
```
