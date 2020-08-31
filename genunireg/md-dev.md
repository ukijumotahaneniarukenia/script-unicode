所感

結構怪しいが、ICUライブラリの使い方のたたき台にはなった

怪しいので、随時メンテなど

perl -C つけ忘れや\U形式への対応などが抜けがちなことが判明

定数クラスの自動生成

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/genlocale/releases/download/1-0-0/genlocale-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```

```
java -jar genlocale-1.0.0-SNAPSHOT-jar-with-dependencies.jar | awk '{print $1 ,$1}' | while read k v;do echo "public static final String AVAILABLE_LOCALE_"$(echo $k|tr '[:lower:]' '[:upper:]')" = "\"$v\"";" ;done|grep -v ヘッダーだよーん|pbcopy
```


switch文のcase式の自動生成


```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/genlocale/releases/download/1-0-0/genlocale-1.0.0-SNAPSHOT-jar-with-dependencies.jar

java -jar genlocale-1.0.0-SNAPSHOT-jar-with-dependencies.jar | awk '$0=$1'| xargs -I@ echo @|tr '[:lower:]' '[:upper:]'|xargs -I@ echo 'case AVAILABLE_LOCALE_@: localeList.add(AVAILABLE_LOCALE_@); break;'|grep -v ヘッダーだよーん | pbcopy
```

使い方

デフォルトでは自身のロケールを必ず含むようにした

デフォルトでは自身のロケールで管理されている文字列の正規表現パタンを標準出力に出力するようにした

標準エラー出力にはロケールと正規表現のスクリプトパタンを出力するようにした

CMD

単一ロケール

正規表現の確認

uUをxにしたりゼロ埋めを削除したりしているのはperlの正規表現でパタンがうまく生成できているか確認するためだ

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar 2>/dev/null | teip -Gog '(?<=(u|U))0+' -- sed 's/.//g' | sed 'y/uU/xx/' | sed 's/\+//'
```


OUT


```
[\x2E80-\x2E99\x2E9B-\x2EF3\x2F00-\x2FD5\x3005\x3007\x3021-\x3029\x3038-\x303B\x3041-\x3096\x309D-\x309F\x30A1-\x30FA\x30FD-\x30FF\x31F0-\x31FF\x32D0-\x32FE\x3300-\x3357\x3400-\x4DBF\x4E00-\x9FFC\xF900-\xFA6D\xFA70-\xFAD9\xFF66-\xFF6F\xFF71-\xFF9D\x16FF0\x16FF1\x1B000-\x1B11E\x1B150-\x1B152\x1B164-\x1B167\x1F200\x20000-\x2A6DD\x2A700-\x2B734\x2B740-\x2B81D\x2B820-\x2CEA1\x2CEB0-\x2EBE0\x2F800-\x2FA1D\x30000-\x3134A]
```


CMD

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar  1>/dev/null
```


OUT


```
ja_JP###[[:Kana:]+[:Hira:]+[:Hani:]]
```


複数ロケール


CMD


```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar mni_Beng en 2>/dev/null | teip -Gog '(?<=(u|U))0+' -- sed 's/.//g' | sed 'y/uU/xx/' | sed 's/\+//'
```


OUT


```
[\x2E80-\x2E99\x2E9B-\x2EF3\x2F00-\x2FD5\x3005\x3007\x3021-\x3029\x3038-\x303B\x3041-\x3096\x309D-\x309F\x30A1-\x30FA\x30FD-\x30FF\x31F0-\x31FF\x32D0-\x32FE\x3300-\x3357\x3400-\x4DBF\x4E00-\x9FFC\xF900-\xFA6D\xFA70-\xFAD9\xFF66-\xFF6F\xFF71-\xFF9D\x16FF0\x16FF1\x1B000-\x1B11E\x1B150-\x1B152\x1B164-\x1B167\x1F200\x20000-\x2A6DD\x2A700-\x2B734\x2B740-\x2B81D\x2B820-\x2CEA1\x2CEB0-\x2EBE0\x2F800-\x2FA1D\x30000-\x3134A]
[\x980-\x983\x985-\x98C\x98F\x990\x993-\x9A8\x9AA-\x9B0\x9B2\x9B6-\x9B9\x9BC-\x9C4\x9C7\x9C8\x9CB-\x9CE\x9D7\x9DC\x9DD\x9DF-\x9E3\x9E6-\x9FE]
[A-Za-z\xAA\xBA\xC0-\xD6\xD8-\xF6\xF8-\x2B8\x2E0-\x2E4\x1D00-\x1D25\x1D2C-\x1D5C\x1D62-\x1D65\x1D6B-\x1D77\x1D79-\x1DBE\x1E00-\x1EFF\x2071\x207F\x2090-\x209C\x212A\x212B\x2132\x214E\x2160-\x2188\x2C60-\x2C7F\xA722-\xA787\xA78B-\xA7BF\xA7C2-\xA7CA\xA7F5-\xA7FF\xAB30-\xAB5A\xAB5C-\xAB64\xAB66-\xAB69\xFB00-\xFB06\xFF21-\xFF3A\xFF41-\xFF5A]
```


CMD

スクリプトパタン名のみ出力

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar mni_Beng en 1>/dev/null
```


OUT


```
ja_JP###[[:Kana:]+[:Hira:]+[:Hani:]]
mni_Beng###[[:Beng:]]
en###[[:Latn:]]
```


デフォルトの出力をミュートする場合は--suppressをつける


CMD


```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar --suppress mni_Beng en 2>/dev/null | teip -Gog '(?<=(u|U))0+' -- sed 's/.//g' | sed 'y/uU/xx/' | sed 's/\+//'
```


OUT


```
[\x980-\x983\x985-\x98C\x98F\x990\x993-\x9A8\x9AA-\x9B0\x9B2\x9B6-\x9B9\x9BC-\x9C4\x9C7\x9C8\x9CB-\x9CE\x9D7\x9DC\x9DD\x9DF-\x9E3\x9E6-\x9FE]
[A-Za-z\xAA\xBA\xC0-\xD6\xD8-\xF6\xF8-\x2B8\x2E0-\x2E4\x1D00-\x1D25\x1D2C-\x1D5C\x1D62-\x1D65\x1D6B-\x1D77\x1D79-\x1DBE\x1E00-\x1EFF\x2071\x207F\x2090-\x209C\x212A\x212B\x2132\x214E\x2160-\x2188\x2C60-\x2C7F\xA722-\xA787\xA78B-\xA7BF\xA7C2-\xA7CA\xA7F5-\xA7FF\xAB30-\xAB5A\xAB5C-\xAB64\xAB66-\xAB69\xFB00-\xFB06\xFF21-\xFF3A\xFF41-\xFF5A]
```




CMD

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar --suppress mni_Beng en 1>/dev/null 
```


OUT

```
mni_Beng###[[:Beng:]]
en###[[:Latn:]]
```


正規表現の確認


perl用にもうちょうい加工など

CMD

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar --suppress en 2>/dev/null | teip -Gog '(?<=(u|U))0+' -- sed 's/.//g' | sed 'y/uU/xx/' | sed 's/\+//'| teip -Gog '(?<=\\x)[0-9A-F]+'  -- sed -r 's/(.*)/{\1}/g'
```

OUT


```
[A-Za-z\x{AA}\x{BA}\x{C0}-\x{D6}\x{D8}-\x{F6}\x{F8}-\x{2B8}\x{2E0}-\x{2E4}\x{1D00}-\x{1D25}\x{1D2C}-\x{1D5C}\x{1D62}-\x{1D65}\x{1D6B}-\x{1D77}\x{1D79}-\x{1DBE}\x{1E00}-\x{1EFF}\x{2071}\x{207F}\x{2090}-\x{209C}\x{212A}\x{212B}\x{2132}\x{214E}\x{2160}-\x{2188}\x{2C60}-\x{2C7F}\x{A722}-\x{A787}\x{A78B}-\x{A7BF}\x{A7C2}-\x{A7CA}\x{A7F5}-\x{A7FF}\x{AB30}-\x{AB5A}\x{AB5C}-\x{AB64}\x{AB66}-\x{AB69}\x{FB00}-\x{FB06}\x{FF21}-\x{FF3A}\x{FF41}-\x{FF5A}]
```


これをパタン引数に指定してperlプログラムで判定できるか確認

CMD


```
$ genuniscript-perl Latin | grep -o . |head|perl -C -nlE '/[A-Za-z\x{AA}\x{BA}\x{C0}-\x{D6}\x{D8}-\x{F6}\x{F8}-\x{2B8}\x{2E0}-\x{2E4}\x{1D00}-\x{1D25}\x{1D2C}-\x{1D5C}\x{1D62}-\x{1D65}\x{1D6B}-\x{1D77}\x{1D79}-\x{1DBE}\x{1E00}-\x{1EFF}\x{2071}\x{207F}\x{2090}-\x{209C}\x{212A}\x{212B}\x{2132}\x{214E}\x{2160}-\x{2188}\x{2C60}-\x{2C7F}\x{A722}-\x{A787}\x{A78B}-\x{A7BF}\x{A7C2}-\x{A7CA}\x{A7F5}-\x{A7FF}\x{AB30}-\x{AB5A}\x{AB5C}-\x{AB64}\x{AB66}-\x{AB69}\x{FB00}-\x{FB06}\x{FF21}-\x{FF3A}\x{FF41}-\x{FF5A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "
```

OUT

それっぽい

```
ᶁ	\N{LATIN SMALL LETTER D WITH PALATAL HOOK}
ꞵ	\N{LATIN SMALL LETTER BETA}
Ṣ	\N{LATIN CAPITAL LETTER S WITH DOT BELOW}
Ạ	\N{LATIN CAPITAL LETTER A WITH DOT BELOW}
H	\N{LATIN CAPITAL LETTER H}
ɜ	\N{LATIN SMALL LETTER REVERSED OPEN E}
Ụ	\N{LATIN CAPITAL LETTER U WITH DOT BELOW}
ȅ	\N{LATIN SMALL LETTER E WITH DOUBLE GRAVE}
ꞌ	\N{LATIN SMALL LETTER SALTILLO}
ﬂ	\N{LATIN SMALL LIGATURE FL}
```

ラテン語のパタンがひらがな、カタカナ、漢字にマッチしないことの確認

```
$ genuniscript-perl Katakana | grep -o . |perl -C -nlE '/[A-Za-z\x{AA}\x{BA}\x{C0}-\x{D6}\x{D8}-\x{F6}\x{F8}-\x{2B8}\x{2E0}-\x{2E4}\x{1D00}-\x{1D25}\x{1D2C}-\x{1D5C}\x{1D62}-\x{1D65}\x{1D6B}-\x{1D77}\x{1D79}-\x{1DBE}\x{1E00}-\x{1EFF}\x{2071}\x{207F}\x{2090}-\x{209C}\x{212A}\x{212B}\x{2132}\x{214E}\x{2160}-\x{2188}\x{2C60}-\x{2C7F}\x{A722}-\x{A787}\x{A78B}-\x{A7BF}\x{A7C2}-\x{A7CA}\x{A7F5}-\x{A7FF}\x{AB30}-\x{AB5A}\x{AB5C}-\x{AB64}\x{AB66}-\x{AB69}\x{FB00}-\x{FB06}\x{FF21}-\x{FF3A}\x{FF41}-\x{FF5A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "

$ genuniscript-perl Han | grep -o . |perl -C -nlE '/[A-Za-z\x{AA}\x{BA}\x{C0}-\x{D6}\x{D8}-\x{F6}\x{F8}-\x{2B8}\x{2E0}-\x{2E4}\x{1D00}-\x{1D25}\x{1D2C}-\x{1D5C}\x{1D62}-\x{1D65}\x{1D6B}-\x{1D77}\x{1D79}-\x{1DBE}\x{1E00}-\x{1EFF}\x{2071}\x{207F}\x{2090}-\x{209C}\x{212A}\x{212B}\x{2132}\x{214E}\x{2160}-\x{2188}\x{2C60}-\x{2C7F}\x{A722}-\x{A787}\x{A78B}-\x{A7BF}\x{A7C2}-\x{A7CA}\x{A7F5}-\x{A7FF}\x{AB30}-\x{AB5A}\x{AB5C}-\x{AB64}\x{AB66}-\x{AB69}\x{FB00}-\x{FB06}\x{FF21}-\x{FF3A}\x{FF41}-\x{FF5A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "

$ genuniscript-perl Hiragana | grep -o . |perl -C -nlE '/[A-Za-z\x{AA}\x{BA}\x{C0}-\x{D6}\x{D8}-\x{F6}\x{F8}-\x{2B8}\x{2E0}-\x{2E4}\x{1D00}-\x{1D25}\x{1D2C}-\x{1D5C}\x{1D62}-\x{1D65}\x{1D6B}-\x{1D77}\x{1D79}-\x{1DBE}\x{1E00}-\x{1EFF}\x{2071}\x{207F}\x{2090}-\x{209C}\x{212A}\x{212B}\x{2132}\x{214E}\x{2160}-\x{2188}\x{2C60}-\x{2C7F}\x{A722}-\x{A787}\x{A78B}-\x{A7BF}\x{A7C2}-\x{A7CA}\x{A7F5}-\x{A7FF}\x{AB30}-\x{AB5A}\x{AB5C}-\x{AB64}\x{AB66}-\x{AB69}\x{FB00}-\x{FB06}\x{FF21}-\x{FF3A}\x{FF41}-\x{FF5A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "
```


逆にひらがな、カタカナ、漢字のパタンにマッチするかなどの確認

パタン生成

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar --suppress ja | teip -Gog '(?<=(u|U))0+' -- sed 's/.//g' | sed 'y/uU/xx/' | sed 's/\+//'| teip -Gog '(?<=\\x)[0-9A-F]+'  -- sed -r 's/(.*)/{\1}/g'
ja###[[:Kana:]+[:Hira:]+[:Hani:]]
[\x{2E80}-\x{2E99}\x{2E9B}-\x{2EF3}\x{2F00}-\x{2FD5}\x{3005}\x{3007}\x{3021}-\x{3029}\x{3038}-\x{303B}\x{3041}-\x{3096}\x{309D}-\x{309F}\x{30A1}-\x{30FA}\x{30FD}-\x{30FF}\x{31F0}-\x{31FF}\x{32D0}-\x{32FE}\x{3300}-\x{3357}\x{3400}-\x{4DBF}\x{4E00}-\x{9FFC}\x{F900}-\x{FA6D}\x{FA70}-\x{FAD9}\x{FF66}-\x{FF6F}\x{FF71}-\x{FF9D}\x{16FF0}\x{16FF1}\x{1B000}-\x{1B11E}\x{1B150}-\x{1B152}\x{1B164}-\x{1B167}\x{1F200}\x{20000}-\x{2A6DD}\x{2A700}-\x{2B734}\x{2B740}-\x{2B81D}\x{2B820}-\x{2CEA1}\x{2CEB0}-\x{2EBE0}\x{2F800}-\x{2FA1D}\x{30000}-\x{3134A}]
```


ひらがな

変態がなにもマッチした

```
$ genuniscript-perl Hiragana | grep -o . | head |perl -C -nlE '/[\x{2E80}-\x{2E99}\x{2E9B}-\x{2EF3}\x{2F00}-\x{2FD5}\x{3005}\x{3007}\x{3021}-\x{3029}\x{3038}-\x{303B}\x{3041}-\x{3096}\x{309D}-\x{309F}\x{30A1}-\x{30FA}\x{30FD}-\x{30FF}\x{31F0}-\x{31FF}\x{32D0}-\x{32FE}\x{3300}-\x{3357}\x{3400}-\x{4DBF}\x{4E00}-\x{9FFC}\x{F900}-\x{FA6D}\x{FA70}-\x{FAD9}\x{FF66}-\x{FF6F}\x{FF71}-\x{FF9D}\x{16FF0}\x{16FF1}\x{1B000}-\x{1B11E}\x{1B150}-\x{1B152}\x{1B164}-\x{1B167}\x{1F200}\x{20000}-\x{2A6DD}\x{2A700}-\x{2B734}\x{2B740}-\x{2B81D}\x{2B820}-\x{2CEA1}\x{2CEB0}-\x{2EBE0}\x{2F800}-\x{2FA1D}\x{30000}-\x{3134A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "
𛂩	\N{HENTAIGANA LETTER HI-1}
つ	\N{HIRAGANA LETTER TU}
ぁ	\N{HIRAGANA LETTER SMALL A}
𛄌	\N{HENTAIGANA LETTER WA-5}
ん	\N{HIRAGANA LETTER N}
た	\N{HIRAGANA LETTER TA}
𛀡	\N{HENTAIGANA LETTER KA-11}
に	\N{HIRAGANA LETTER NI}
ち	\N{HIRAGANA LETTER TI}
𛁭	\N{HENTAIGANA LETTER TU-TO}
````


カタカナ

組文字などにもマッチした

```
$ genuniscript-perl Katakana | grep -o . | head |perl -C -nlE '/[\x{2E80}-\x{2E99}\x{2E9B}-\x{2EF3}\x{2F00}-\x{2FD5}\x{3005}\x{3007}\x{3021}-\x{3029}\x{3038}-\x{303B}\x{3041}-\x{3096}\x{309D}-\x{309F}\x{30A1}-\x{30FA}\x{30FD}-\x{30FF}\x{31F0}-\x{31FF}\x{32D0}-\x{32FE}\x{3300}-\x{3357}\x{3400}-\x{4DBF}\x{4E00}-\x{9FFC}\x{F900}-\x{FA6D}\x{FA70}-\x{FAD9}\x{FF66}-\x{FF6F}\x{FF71}-\x{FF9D}\x{16FF0}\x{16FF1}\x{1B000}-\x{1B11E}\x{1B150}-\x{1B152}\x{1B164}-\x{1B167}\x{1F200}\x{20000}-\x{2A6DD}\x{2A700}-\x{2B734}\x{2B740}-\x{2B81D}\x{2B820}-\x{2CEA1}\x{2CEB0}-\x{2EBE0}\x{2F800}-\x{2FA1D}\x{30000}-\x{3134A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "
㋤	\N{CIRCLED KATAKANA NA}
㋭	\N{CIRCLED KATAKANA HO}
ロ	\N{KATAKANA LETTER RO}
㌲	\N{SQUARE HUARADDO}
㋖	\N{CIRCLED KATAKANA KI}
ﾚ	\N{HALFWIDTH KATAKANA LETTER RE}
ヴ	\N{KATAKANA LETTER VU}
ァ	\N{KATAKANA LETTER SMALL A}
ッ	\N{KATAKANA LETTER SMALL TU}
ｸ	\N{HALFWIDTH KATAKANA LETTER KU}
```

漢字

CJKにもマッチした

```
$ genuniscript-perl Han | grep -o . | head |perl -C -nlE '/[\x{2E80}-\x{2E99}\x{2E9B}-\x{2EF3}\x{2F00}-\x{2FD5}\x{3005}\x{3007}\x{3021}-\x{3029}\x{3038}-\x{303B}\x{3041}-\x{3096}\x{309D}-\x{309F}\x{30A1}-\x{30FA}\x{30FD}-\x{30FF}\x{31F0}-\x{31FF}\x{32D0}-\x{32FE}\x{3300}-\x{3357}\x{3400}-\x{4DBF}\x{4E00}-\x{9FFC}\x{F900}-\x{FA6D}\x{FA70}-\x{FAD9}\x{FF66}-\x{FF6F}\x{FF71}-\x{FF9D}\x{16FF0}\x{16FF1}\x{1B000}-\x{1B11E}\x{1B150}-\x{1B152}\x{1B164}-\x{1B167}\x{1F200}\x{20000}-\x{2A6DD}\x{2A700}-\x{2B734}\x{2B740}-\x{2B81D}\x{2B820}-\x{2CEA1}\x{2CEB0}-\x{2EBE0}\x{2F800}-\x{2FA1D}\x{30000}-\x{3134A}]/ and say' | xargs -n1 -I@ bash -c "echo -ne '@\t'; echo -ne @ | uconv -x any-name|grep -Po '\\\\N{.*?}' "
𨯡	\N{CJK UNIFIED IDEOGRAPH-28BE1}
𡮪	\N{CJK UNIFIED IDEOGRAPH-21BAA}
𩫎	\N{CJK UNIFIED IDEOGRAPH-29ACE}
𮣚	\N{CJK UNIFIED IDEOGRAPH-2E8DA}
𩑅	\N{CJK UNIFIED IDEOGRAPH-29445}
𤈭	\N{CJK UNIFIED IDEOGRAPH-2422D}
𤙭	\N{CJK UNIFIED IDEOGRAPH-2466D}
䖠	\N{CJK UNIFIED IDEOGRAPH-45A0}
𩠛	\N{CJK UNIFIED IDEOGRAPH-2981B}
𠬖	\N{CJK UNIFIED IDEOGRAPH-20B16}
```


利用可能なロケール一覧


先頭２文字でグルーピング


CMD

```
$ java -jar genlocale-1.0.0-SNAPSHOT-jar-with-dependencies.jar | awk '$0=$1' | grep -v ヘッダーだよーん | xargs | ruby -anle '$F.group_by{|e|e.slice(0..1)}.map{|e|p e[0],e[1].join(",")}'|xargs -n2 | sed 's/,/ /g'
```

OUT

```
af af af_NA af_ZA
ag agq agq_CM
ak ak ak_GH
am am am_ET
ar ar ar_001 ar_AE ar_BH ar_DJ ar_DZ ar_EG ar_EH ar_ER ar_IL ar_IQ ar_JO ar_KM ar_KW ar_LB ar_LY ar_MA ar_MR ar_OM ar_PS ar_QA ar_SA ar_SD ar_SO ar_SS ar_SY ar_TD ar_TN ar_YE
as as as_IN asa asa_TZ ast ast_ES
az az az_Cyrl az_Cyrl_AZ az_Latn az_Latn_AZ
ba bas bas_CM
be be be_BY bem bem_ZM bez bez_TZ
bg bg bg_BG
bm bm bm_ML
bn bn bn_BD bn_IN
bo bo bo_CN bo_IN
br br br_FR brx brx_IN
bs bs bs_Cyrl bs_Cyrl_BA bs_Latn bs_Latn_BA
ca ca ca_AD ca_ES ca_FR ca_IT
cc ccp ccp_BD ccp_IN
ce ce ce_RU ceb ceb_PH
cg cgg cgg_UG
ch chr chr_US
ck ckb ckb_IQ ckb_IR
cs cs cs_CZ
cy cy cy_GB
da da da_DK da_GL dav dav_KE
de de de_AT de_BE de_CH de_DE de_IT de_LI de_LU
dj dje dje_NE
ds dsb dsb_DE
du dua dua_CM
dy dyo dyo_SN
dz dz dz_BT
eb ebu ebu_KE
ee ee ee_GH ee_TG
el el el_CY el_GR
en en en_001 en_150 en_AE en_AG en_AI en_AS en_AT en_AU en_BB en_BE en_BI en_BM en_BS en_BW en_BZ en_CA en_CC en_CH en_CK en_CM en_CX en_CY en_DE en_DG en_DK en_DM en_ER en_FI en_FJ en_FK en_FM en_GB en_GD en_GG en_GH en_GI en_GM en_GU en_GY en_HK en_IE en_IL en_IM en_IN en_IO en_JE en_JM en_KE en_KI en_KN en_KY en_LC en_LR en_LS en_MG en_MH en_MO en_MP en_MS en_MT en_MU en_MW en_MY en_NA en_NF en_NG en_NL en_NR en_NU en_NZ en_PG en_PH en_PK en_PN en_PR en_PW en_RW en_SB en_SC en_SD en_SE en_SG en_SH en_SI en_SL en_SS en_SX en_SZ en_TC en_TK en_TO en_TT en_TV en_TZ en_UG en_UM en_US en_US_POSIX en_VC en_VG en_VI en_VU en_WS en_ZA en_ZM en_ZW
eo eo eo_001
es es es_419 es_AR es_BO es_BR es_BZ es_CL es_CO es_CR es_CU es_DO es_EA es_EC es_ES es_GQ es_GT es_HN es_IC es_MX es_NI es_PA es_PE es_PH es_PR es_PY es_SV es_US es_UY es_VE
et et et_EE
eu eu eu_ES
ew ewo ewo_CM
fa fa fa_AF fa_IR
ff ff ff_Adlm ff_Adlm_BF ff_Adlm_CM ff_Adlm_GH ff_Adlm_GM ff_Adlm_GN ff_Adlm_GW ff_Adlm_LR ff_Adlm_MR ff_Adlm_NE ff_Adlm_NG ff_Adlm_SL ff_Adlm_SN ff_Latn ff_Latn_BF ff_Latn_CM ff_Latn_GH ff_Latn_GM ff_Latn_GN ff_Latn_GW ff_Latn_LR ff_Latn_MR ff_Latn_NE ff_Latn_NG ff_Latn_SL ff_Latn_SN
fi fi fi_FI fil fil_PH
fo fo fo_DK fo_FO
fr fr fr_BE fr_BF fr_BI fr_BJ fr_BL fr_CA fr_CD fr_CF fr_CG fr_CH fr_CI fr_CM fr_DJ fr_DZ fr_FR fr_GA fr_GF fr_GN fr_GP fr_GQ fr_HT fr_KM fr_LU fr_MA fr_MC fr_MF fr_MG fr_ML fr_MQ fr_MR fr_MU fr_NC fr_NE fr_PF fr_PM fr_RE fr_RW fr_SC fr_SN fr_SY fr_TD fr_TG fr_TN fr_VU fr_WF fr_YT
fu fur fur_IT
fy fy fy_NL
ga ga ga_GB ga_IE
gd gd gd_GB
gl gl gl_ES
gs gsw gsw_CH gsw_FR gsw_LI
gu gu gu_IN guz guz_KE
gv gv gv_IM
ha ha ha_GH ha_NE ha_NG haw haw_US
he he he_IL
hi hi hi_IN
hr hr hr_BA hr_HR
hs hsb hsb_DE
hu hu hu_HU
hy hy hy_AM
ia ia ia_001
id id id_ID
ig ig ig_NG
ii ii ii_CN
is is is_IS
it it it_CH it_IT it_SM it_VA
ja ja ja_JP
jg jgo jgo_CM
jm jmc jmc_TZ
jv jv jv_ID
ka ka ka_GE kab kab_DZ kam kam_KE
kd kde kde_TZ
ke kea kea_CV
kh khq khq_ML
ki ki ki_KE
kk kk kk_KZ kkj kkj_CM
kl kl kl_GL kln kln_KE
km km km_KH
kn kn kn_IN
ko ko ko_KP ko_KR kok kok_IN
ks ks ks_Arab ks_Arab_IN ksb ksb_TZ ksf ksf_CM ksh ksh_DE
ku ku ku_TR
kw kw kw_GB
ky ky ky_KG
la lag lag_TZ
lb lb lb_LU
lg lg lg_UG
lk lkt lkt_US
ln ln ln_AO ln_CD ln_CF ln_CG
lo lo lo_LA
lr lrc lrc_IQ lrc_IR
lt lt lt_LT
lu lu lu_CD luo luo_KE luy luy_KE
lv lv lv_LV
ma mai mai_IN mas mas_KE mas_TZ
me mer mer_KE
mf mfe mfe_MU
mg mg mg_MG mgh mgh_MZ mgo mgo_CM
mi mi mi_NZ
mk mk mk_MK
ml ml ml_IN
mn mn mn_MN mni mni_Beng mni_Beng_IN
mr mr mr_IN
ms ms ms_BN ms_ID ms_MY ms_SG
mt mt mt_MT
mu mua mua_CM
my my my_MM
mz mzn mzn_IR
na naq naq_NA
nb nb nb_NO nb_SJ
nd nd nd_ZW nds nds_DE nds_NL
ne ne ne_IN ne_NP
nl nl nl_AW nl_BE nl_BQ nl_CW nl_NL nl_SR nl_SX
nm nmg nmg_CM
nn nn nn_NO nnh nnh_CM
nu nus nus_SS
ny nyn nyn_UG
om om om_ET om_KE
or or or_IN
os os os_GE os_RU
pa pa pa_Arab pa_Arab_PK pa_Guru pa_Guru_IN
pc pcm pcm_NG
pl pl pl_PL
ps ps ps_AF ps_PK
pt pt pt_AO pt_BR pt_CH pt_CV pt_GQ pt_GW pt_LU pt_MO pt_MZ pt_PT pt_ST pt_TL
qu qu qu_BO qu_EC qu_PE
rm rm rm_CH
rn rn rn_BI
ro ro ro_MD ro_RO rof rof_TZ
ru ru ru_BY ru_KG ru_KZ ru_MD ru_RU ru_UA
rw rw rw_RW rwk rwk_TZ
sa sah sah_RU saq saq_KE sat sat_Olck sat_Olck_IN
sb sbp sbp_TZ
sd sd sd_Arab sd_Arab_PK sd_Deva sd_Deva_IN
se se se_FI se_NO se_SE seh seh_MZ ses ses_ML
sg sg sg_CF
sh shi shi_Latn shi_Latn_MA shi_Tfng shi_Tfng_MA
si si si_LK
sk sk sk_SK
sl sl sl_SI
sm smn smn_FI
sn sn sn_ZW
so so so_DJ so_ET so_KE so_SO
sq sq sq_AL sq_MK sq_XK
sr sr sr_Cyrl sr_Cyrl_BA sr_Cyrl_ME sr_Cyrl_RS sr_Cyrl_XK sr_Latn sr_Latn_BA sr_Latn_ME sr_Latn_RS sr_Latn_XK
su su su_Latn su_Latn_ID
sv sv sv_AX sv_FI sv_SE
sw sw sw_CD sw_KE sw_TZ sw_UG
ta ta ta_IN ta_LK ta_MY ta_SG
te te te_IN teo teo_KE teo_UG
tg tg tg_TJ
th th th_TH
ti ti ti_ER ti_ET
tk tk tk_TM
to to to_TO
tr tr tr_CY tr_TR
tt tt tt_RU
tw twq twq_NE
tz tzm tzm_MA
ug ug ug_CN
uk uk uk_UA
ur ur ur_IN ur_PK
uz uz uz_Arab uz_Arab_AF uz_Cyrl uz_Cyrl_UZ uz_Latn uz_Latn_UZ
va vai vai_Latn vai_Latn_LR vai_Vaii vai_Vaii_LR
vi vi vi_VN
vu vun vun_TZ
wa wae wae_CH
wo wo wo_SN
xh xh xh_ZA
xo xog xog_UG
ya yav yav_CM
yi yi yi_001
yo yo yo_BJ yo_NG
yu yue yue_Hans yue_Hans_CN yue_Hant yue_Hant_HK
zg zgh zgh_MA
zh zh zh_Hans zh_Hans_CN zh_Hans_HK zh_Hans_MO zh_Hans_SG zh_Hant zh_Hant_HK zh_Hant_MO zh_Hant_TW
zu zu zu_ZA
```
