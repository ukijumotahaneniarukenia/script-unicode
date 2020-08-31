AppAvailableUnicodePropertyの作成


```
$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --constant 2>/dev/null | grep -P 'com.ibm.icu.lang.UProperty(?!\$)' | awk '$0=$6' | sort | xargs -I@ echo 'put("@",UProperty.@);'|pbcopy
```


