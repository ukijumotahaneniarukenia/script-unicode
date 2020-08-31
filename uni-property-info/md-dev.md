```
SCRIPT	startCodePoint	4824
SCRIPT	endCodePoint	4880
SCRIPT	regexpRangePatternProperty	[\u12D8-\u1310]	[ዘ-ጐ]

$ jq -nr '[range(4824;4880+1)]|implode'
ዘዙዚዛዜዝዞዟዠዡዢዣዤዥዦዧየዩዪያዬይዮዯደዱዲዳዴድዶዷዸዹዺዻዼዽዾዿጀጁጂጃጄጅጆጇገጉጊጋጌግጎጏጐ

$ jq -n '[range(4824;4840+1)]|implode|split("")|map(test("[\u12e1]"))'
[
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  true,
  false,
  false,
  false,
  false,
  false,
  false,
  false
]

$ jq -nr '[range(4824;4840+1)]|implode|split("")[]' | teip -Gog '\u12e1'
ዘ
ዙ
ዚ
ዛ
ዜ
ዝ
ዞ
ዟ
ዠ
[ዡ]
ዢ
ዣ
ዤ
ዥ
ዦ
ዧ
የ

$ jq -nr '[range(4824;4840+1)]|implode|split("")[]' | teip -Gog '[\u12e1\u12e2]'
ዘ
ዙ
ዚ
ዛ
ዜ
ዝ
ዞ
ዟ
ዠ
[ዡ]
[ዢ]
ዣ
ዤ
ዥ
ዦ
ዧ
የ

$ jq -nr '[range(4824;4840+1)]|implode|split("")[]' | teip -Gog '[\u12d9-\u12df\u12e1-\u12e4]'
ዘ
[ዙ]
[ዚ]
[ዛ]
[ዜ]
[ዝ]
[ዞ]
[ዟ]
ዠ
[ዡ]
[ዢ]
[ዣ]
[ዤ]
ዥ
ዦ
ዧ
የ


$ jq -n '[range(4824;4880+1)]|implode|split("")|map(test("[\u12D8-\u1310]"))'
[
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true,
  true
]


SCRIPT	startCodePoint	128992
SCRIPT	endCodePoint	129003
SCRIPT	regexpRangePatternProperty	[\U0001F7E0-\U0001F7EB]	[🟠-🟫]

$ jq -n '[range(128992;129003+1)]|implode|split("")'
[
  "🟠",
  "🟡",
  "🟢",
  "🟣",
  "🟤",
  "🟥",
  "🟦",
  "🟧",
  "🟨",
  "🟩",
  "🟪",
  "🟫"
]


$ jq -nr '[range(128992;129003+1)]|implode'
🟠🟡🟢🟣🟤🟥🟦🟧🟨🟩🟪🟫


perlだけ

$ jq -nr '[range(128992;129003+1)]|implode|split("")[]' | perl -C -nlE '$,="\t";if (/[\x{1F7E4}-\x{1F7E7}]/){say $_,"match" }else{ say $_,"unmatch"}'
🟠	unmatch
🟡	unmatch
🟢	unmatch
🟣	unmatch
🟤	match
🟥	match
🟦	match
🟧	match
🟨	unmatch
🟩	unmatch
🟪	unmatch
🟫	unmatch

$ perl -C -E '$,="\t";($s,$e)=@ARGV;foreach($s..$e){$g=sprintf("\"\\x{%x}\"",$_);$h=sprintf("\\x{%x}",$_);say $_,$h,eval $g}' 127232 127242
127232	\x{1f100}	🄀
127233	\x{1f101}	🄁
127234	\x{1f102}	🄂
127235	\x{1f103}	🄃
127236	\x{1f104}	🄄
127237	\x{1f105}	🄅
127238	\x{1f106}	🄆
127239	\x{1f107}	🄇
127240	\x{1f108}	🄈
127241	\x{1f109}	🄉
127242	\x{1f10a}	🄊

$ perl -C -E '$,="\t";($s,$e)=@ARGV;foreach($s..$e){$g=sprintf("\"\\x{%x}\"",$_);$h=sprintf("\\x{%x}",$_);say $_,$h,eval $g}' 128992 129003
128992	\x{1f7e0}	🟠
128993	\x{1f7e1}	🟡
128994	\x{1f7e2}	🟢
128995	\x{1f7e3}	🟣
128996	\x{1f7e4}	🟤
128997	\x{1f7e5}	🟥
128998	\x{1f7e6}	🟦
128999	\x{1f7e7}	🟧
129000	\x{1f7e8}	🟨
129001	\x{1f7e9}	🟩
129002	\x{1f7ea}	🟪
129003	\x{1f7eb}	🟫

```
