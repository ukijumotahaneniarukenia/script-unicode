```
boolean型


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="boolean"{print $4}'|while read s;do echo "private static final String $(echo $s|downcamel2screamsnake-ruby)=\"$s\";";done


java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,    $11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="boolean"{print $4}'|downcamel2screamsnake-ruby|xargs -I@ echo "add(@);"


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="boolean"{print $4}'|while read s;do echo "detailMap.put($(echo $s|downcamel2screamsnake-ruby),String.valueOf(Optional.ofNullable(UCharacter.$s(codePoint)).orElse(DEFAULT_NONE_VALUE_BOOLEAN)));";done


int型


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="int"{print $4}'|while read s;do echo "private static final String $(echo $s|downcamel2screamsnake-ruby)=\"$s\";";done|sed -r 's/GET_|get//g'



$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="int"{print $4}'|downcamel2screamsnake-ruby|xargs -I@ echo "add(@);"|sed -r 's/GET_|get//g'



$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="int"{print $4}'|while read s;do echo "detailMap.put($(echo $s|downcamel2screamsnake-ruby),String.valueOf(Optional.ofNullable(UCharacter.$s(codePoint)).orElse(DEFAULT_NONE_VALUE_INTEGER)));";done|sed -r 's/GET_//g'

string型


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="java.lang.String"{print $4}'| grep -v toString|while read s;do echo "private static final String $(echo $s|downcamel2screamsnake-ruby)=\"$s\";";done|sed -r 's/GET_|get//g'


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="java.lang.String"{print $4}'| grep -v toString|downcamel2screamsnake-ruby|xargs -I@ echo "add(@);"|sed -r 's/GET_|get//g'

$  java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="java.lang.String"{print $4}'| grep -v toString|while read s;do echo "detailMap.put($(echo $s|downcamel2screamsnake-ruby),String.valueOf(Optional.ofNullable(UCharacter.$s(codePoint)).orElse(DEFAULT_NONE_VALUE_STRING)));";done|sed -r 's/GET_//g'


double型


$  java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="double"{print $4}'|while read s;do echo "private static final String $(echo $s|downcamel2screamsnake-ruby)=\"$s\";";done|sed -r 's/GET_|get//g'

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="double"{print $4}'|downcamel2screamsnake-ruby|xargs -I@ echo "add(@);"|sed -r 's/GET_|get//g'


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="double"{print $4}'|while read s;do echo "detailMap.put($(echo $s|downcamel2screamsnake-ruby),String.valueOf(Optional.ofNullable(UCharacter.$s(codePoint)).orElse(DEFAULT_NONE_VALUE_DOUBLE)));";done|sed -r 's/GET_//g'





byte型
$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="byte"{print $4}'|while read s;do echo "private static final String $(echo $s|downcamel2screamsnake-ruby)=\"$s\";";done|sed -r 's/GET_|get//g'



$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="byte"{print $4}'|downcamel2screamsnake-ruby|xargs -I@ echo "add(@);"|sed -r 's/GET_|get//g'

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,$11,$12,$13}' OFS="\t" | sort | awk -v FS="\t" '$3=="byte"{print $4}'|while read s;do echo "detailMap.put($(echo $s|downcamel2screamsnake-ruby),String.valueOf(Optional.ofNullable(UCharacter.$s(codePoint)).orElse(DEFAULT_NONE_VALUE_BYTE)));";done|sed -r 's/GET_//g'

```



マップで取得項目選択

```
$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t" '$5=="com.ibm.icu.lang.UCharacter"&&$13=="int"{print $5,$6,$7,$8,$9,$10,    $11,$12,$13}' OFS="\t" | sort|awk -v FS="\t" '$3!="com.ibm.icu.util.VersionInfo" && $3 != "char[]" && $4 != "toString" {print $4} ' | downcamel2screamsnake-ruby|sed -r 's/GET_//' | while read s;do echo "System.out.print(detailMap.get($s));";echo "System.out.print(FS);";done | pbcopy 
```
