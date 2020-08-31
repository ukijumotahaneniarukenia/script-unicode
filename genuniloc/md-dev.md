```
AppAvailableLocaleクラスの作成


//複数
$ cat genlocale.tsv |grep -v ヘッダーだよーん| awk -v FS="\t" '{print $1,$7}' OFS=":" | sed 's/ //g' | xargs | ruby -anle '$F.map{|e|$a=e.split(/:/);$a[0].split(/_/).push($a[1])}.group_by{|e|e[0]}.map{|e|p e[0],e[1].map{|ee|ee.join("_").gsub(/_(?=[^a-zA-Z0-9])/,":")}.join(" ")}'|xargs -n2| while read k v;do echo "put(\"$k\",\"$v\");";done|pbcopy 


//単一
$ cat genlocale.tsv |grep -v ヘッダーだよーん| awk -v FS="\t" '{print $1,$7}' OFS=":" | sed 's/ //g' | xargs -n1 | sed 's/:/ /g' | while read k v;do echo "put(\"$k\",\"$v\");" ;done | pbcopy

com.ibm.icu.util.ULocaleクラスの引数が0のメソッド一覧の取得

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t"  '$5=="com.ibm.icu.util.ULocale" && $10==0{print $6,$7,$8}' OFS="\t" | sort
public	boolean	isRightToLeft
public	com.ibm.icu.util.ULocale	getFallback
public	int	hashCode
public	java.lang.Object	clone
public	java.lang.String	getBaseName
public	java.lang.String	getCharacterOrientation
public	java.lang.String	getCountry
public	java.lang.String	getDisplayCountry
public	java.lang.String	getDisplayLanguage
public	java.lang.String	getDisplayLanguageWithDialect
public	java.lang.String	getDisplayName
public	java.lang.String	getDisplayNameWithDialect
public	java.lang.String	getDisplayScript
public	java.lang.String	getDisplayScriptInContext
public	java.lang.String	getDisplayVariant
public	java.lang.String	getISO3Country
public	java.lang.String	getISO3Language
public	java.lang.String	getLanguage
public	java.lang.String	getLineOrientation
public	java.lang.String	getName
public	java.lang.String	getScript
public	java.lang.String	getVariant
public	java.lang.String	toLanguageTag
public	java.lang.String	toString
public	java.util.Iterator<java.lang.String>	getKeywords
public	java.util.Locale	toLocale
public	java.util.Set<java.lang.Character>	getExtensionKeys
public	java.util.Set<java.lang.String>	getUnicodeLocaleAttributes
public	java.util.Set<java.lang.String>	getUnicodeLocaleKeys
public final	void	wait
public final native	java.lang.Class<?>	getClass
public final native	void	notify
public final native	void	notifyAll
public static	com.ibm.icu.util.ULocale	getDefault
public static	com.ibm.icu.util.ULocale[]	getAvailableLocales
public static	java.lang.String[]	getISOCountries
public static	java.lang.String[]	getISOLanguages


複数件のこのあたりが気になるが、特に何も取れなかったので、一旦除外
public	java.util.Iterator<java.lang.String>	getKeywords
public	java.util.Set<java.lang.Character>	getExtensionKeys
public	java.util.Set<java.lang.String>	getUnicodeLocaleAttributes
public	java.util.Set<java.lang.String>	getUnicodeLocaleKeys


戻り値が文字列のみ採用

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t"  '$5=="com.ibm.icu.util.ULocale" && $10==0 && $7=="java.lang.String"{print $8}' OFS="\t" | sort|grep get
getBaseName
getCharacterOrientation
getCountry
getDisplayCountry
getDisplayLanguage
getDisplayLanguageWithDialect
getDisplayName
getDisplayNameWithDialect
getDisplayScript
getDisplayScriptInContext
getDisplayVariant
getISO3Country
getISO3Language
getLanguage
getLineOrientation
getName
getScript
getVariant


メソッドの生成

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t"  '$5=="com.ibm.icu.util.ULocale" && $10==0 && $7=="java.lang.String"{print $8}' OFS="\t" | sort|grep get|while read s;do echo "list.add(uLocale.$s().isBlank() ? DEFAULT_NONE_VALUE : uLocale.$s());";done


$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t"  '$5=="com.ibm.icu.util.ULocale" && $10==0 && $7=="java.lang.String"{print $8}' OFS="\t" | sort|grep get | sed 's/get//'


コンス卜定数の生成

$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t"  '$5=="com.ibm.icu.util.ULocale" && $10==0 && $7=="java.lang.String"{print $8}' OFS="\t" | sort|grep get | sed 's/get//' | while read s;do echo "private static final String $(echo $s | upcamel2screamsnake-ruby) = \"$s\";";done


add文の作成
$ java -jar jardump-4.3.0-SNAPSHOT-jar-with-dependencies.jar icu4j-67.1.jar --method 2>/dev/null | awk -v FS="\t"  '$5=="com.ibm.icu.util.ULocale" && $10==0 && $7=="java.lang.String"{print $8}' OFS="\t" | sort|grep get | sed 's/get//' | upcamel2screamsnake-ruby | xargs -I@ echo "add(@);";
add(BASE_NAME);
add(CHARACTER_ORIENTATION);
add(COUNTRY);
add(DISPLAY_COUNTRY);
add(DISPLAY_LANGUAGE);
add(DISPLAY_LANGUAGE_WITH_DIALECT);
add(DISPLAY_NAME);
add(DISPLAY_NAME_WITH_DIALECT);
add(DISPLAY_SCRIPT);
add(DISPLAY_SCRIPT_IN_CONTEXT);
add(DISPLAY_VARIANT);
add(I_S_O3_COUNTRY);
add(I_S_O3_LANGUAGE);
add(LANGUAGE);
add(LINE_ORIENTATION);
add(NAME);
add(SCRIPT);
add(VARIANT);



```

