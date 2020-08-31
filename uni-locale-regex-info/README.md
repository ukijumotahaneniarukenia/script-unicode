国ごとに使用する言語のユニコード正規表現を標準出力に出力するコマンド



ダウンロード

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/genunireg/releases/download/1-0-0/genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```



使い方


```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar
Usageだよーん

LOCALE_LIST:

af af_NA af_ZA agq agq_CM ak ak_GH am am_ET ar ar_001 ar_AE ar_BH ar_DJ ar_DZ ar_EG ar_EH ar_ER ar_IL ar_IQ ar_JO ar_KM ar_KW ar_LB ar_LY ar_MA ar_MR ar_OM ar_PS ar_QA ar_SA ar_SD ar_SO ar_SS ar_SY ar_TD ar_TN ar_YE as as_IN asa asa_TZ ast ast_ES az az_Cyrl az_Cyrl_AZ az_Latn az_Latn_AZ bas bas_CM be be_BY bem bem_ZM bez bez_TZ bg bg_BG bm bm_ML bn bn_BD bn_IN bo bo_CN bo_IN br br_FR brx brx_IN bs bs_Cyrl bs_Cyrl_BA bs_Latn bs_Latn_BA ca ca_AD ca_ES ca_FR ca_IT ccp ccp_BD ccp_IN ce ce_RU ceb ceb_PH cgg cgg_UG chr chr_US ckb ckb_IQ ckb_IR cs cs_CZ cy cy_GB da da_DK da_GL dav dav_KE de de_AT de_BE de_CH de_DE de_IT de_LI de_LU dje dje_NE dsb dsb_DE dua dua_CM dyo dyo_SN dz dz_BT ebu ebu_KE ee ee_GH ee_TG el el_CY el_GR en en_001 en_150 en_AE en_AG en_AI en_AS en_AT en_AU en_BB en_BE en_BI en_BM en_BS en_BW en_BZ en_CA en_CC en_CH en_CK en_CM en_CX en_CY en_DE en_DG en_DK en_DM en_ER en_FI en_FJ en_FK en_FM en_GB en_GD en_GG en_GH en_GI en_GM en_GU en_GY en_HK en_IE en_IL en_IM en_IN en_IO en_JE en_JM en_KE en_KI en_KN en_KY en_LC en_LR en_LS en_MG en_MH en_MO en_MP en_MS en_MT en_MU en_MW en_MY en_NA en_NF en_NG en_NL en_NR en_NU en_NZ en_PG en_PH en_PK en_PN en_PR en_PW en_RW en_SB en_SC en_SD en_SE en_SG en_SH en_SI en_SL en_SS en_SX en_SZ en_TC en_TK en_TO en_TT en_TV en_TZ en_UG en_UM en_US en_US_POSIX en_VC en_VG en_VI en_VU en_WS en_ZA en_ZM en_ZW eo eo_001 es es_419 es_AR es_BO es_BR es_BZ es_CL es_CO es_CR es_CU es_DO es_EA es_EC es_ES es_GQ es_GT es_HN es_IC es_MX es_NI es_PA es_PE es_PH es_PR es_PY es_SV es_US es_UY es_VE et et_EE eu eu_ES ewo ewo_CM fa fa_AF fa_IR ff ff_Adlm ff_Adlm_BF ff_Adlm_CM ff_Adlm_GH ff_Adlm_GM ff_Adlm_GN ff_Adlm_GW ff_Adlm_LR ff_Adlm_MR ff_Adlm_NE ff_Adlm_NG ff_Adlm_SL ff_Adlm_SN ff_Latn ff_Latn_BF ff_Latn_CM ff_Latn_GH ff_Latn_GM ff_Latn_GN ff_Latn_GW ff_Latn_LR ff_Latn_MR ff_Latn_NE ff_Latn_NG ff_Latn_SL ff_Latn_SN fi fi_FI fil fil_PH fo fo_DK fo_FO fr fr_BE fr_BF fr_BI fr_BJ fr_BL fr_CA fr_CD fr_CF fr_CG fr_CH fr_CI fr_CM fr_DJ fr_DZ fr_FR fr_GA fr_GF fr_GN fr_GP fr_GQ fr_HT fr_KM fr_LU fr_MA fr_MC fr_MF fr_MG fr_ML fr_MQ fr_MR fr_MU fr_NC fr_NE fr_PF fr_PM fr_RE fr_RW fr_SC fr_SN fr_SY fr_TD fr_TG fr_TN fr_VU fr_WF fr_YT fur fur_IT fy fy_NL ga ga_GB ga_IE gd gd_GB gl gl_ES gsw gsw_CH gsw_FR gsw_LI gu gu_IN guz guz_KE gv gv_IM ha ha_GH ha_NE ha_NG haw haw_US he he_IL hi hi_IN hr hr_BA hr_HR hsb hsb_DE hu hu_HU hy hy_AM ia ia_001 id id_ID ig ig_NG ii ii_CN is is_IS it it_CH it_IT it_SM it_VA ja ja_JP jgo jgo_CM jmc jmc_TZ jv jv_ID ka ka_GE kab kab_DZ kam kam_KE kde kde_TZ kea kea_CV khq khq_ML ki ki_KE kk kk_KZ kkj kkj_CM kl kl_GL kln kln_KE km km_KH kn kn_IN ko ko_KP ko_KR kok kok_IN ks ks_Arab ks_Arab_IN ksb ksb_TZ ksf ksf_CM ksh ksh_DE ku ku_TR kw kw_GB ky ky_KG lag lag_TZ lb lb_LU lg lg_UG lkt lkt_US ln ln_AO ln_CD ln_CF ln_CG lo lo_LA lrc lrc_IQ lrc_IR lt lt_LT lu lu_CD luo luo_KE luy luy_KE lv lv_LV mai mai_IN mas mas_KE mas_TZ mer mer_KE mfe mfe_MU mg mg_MG mgh mgh_MZ mgo mgo_CM mi mi_NZ mk mk_MK ml ml_IN mn mn_MN mni mni_Beng mni_Beng_IN mr mr_IN ms ms_BN ms_ID ms_MY ms_SG mt mt_MT mua mua_CM my my_MM mzn mzn_IR naq naq_NA nb nb_NO nb_SJ nd nd_ZW nds nds_DE nds_NL ne ne_IN ne_NP nl nl_AW nl_BE nl_BQ nl_CW nl_NL nl_SR nl_SX nmg nmg_CM nn nn_NO nnh nnh_CM nus nus_SS nyn nyn_UG om om_ET om_KE or or_IN os os_GE os_RU pa pa_Arab pa_Arab_PK pa_Guru pa_Guru_IN pcm pcm_NG pl pl_PL ps ps_AF ps_PK pt pt_AO pt_BR pt_CH pt_CV pt_GQ pt_GW pt_LU pt_MO pt_MZ pt_PT pt_ST pt_TL qu qu_BO qu_EC qu_PE rm rm_CH rn rn_BI ro ro_MD ro_RO rof rof_TZ ru ru_BY ru_KG ru_KZ ru_MD ru_RU ru_UA rw rw_RW rwk rwk_TZ sah sah_RU saq saq_KE sat sat_Olck sat_Olck_IN sbp sbp_TZ sd sd_Arab sd_Arab_PK sd_Deva sd_Deva_IN se se_FI se_NO se_SE seh seh_MZ ses ses_ML sg sg_CF shi shi_Latn shi_Latn_MA shi_Tfng shi_Tfng_MA si si_LK sk sk_SK sl sl_SI smn smn_FI sn sn_ZW so so_DJ so_ET so_KE so_SO sq sq_AL sq_MK sq_XK sr sr_Cyrl sr_Cyrl_BA sr_Cyrl_ME sr_Cyrl_RS sr_Cyrl_XK sr_Latn sr_Latn_BA sr_Latn_ME sr_Latn_RS sr_Latn_XK su su_Latn su_Latn_ID sv sv_AX sv_FI sv_SE sw sw_CD sw_KE sw_TZ sw_UG ta ta_IN ta_LK ta_MY ta_SG te te_IN teo teo_KE teo_UG tg tg_TJ th th_TH ti ti_ER ti_ET tk tk_TM to to_TO tr tr_CY tr_TR tt tt_RU twq twq_NE tzm tzm_MA ug ug_CN uk uk_UA ur ur_IN ur_PK uz uz_Arab uz_Arab_AF uz_Cyrl uz_Cyrl_UZ uz_Latn uz_Latn_UZ vai vai_Latn vai_Latn_LR vai_Vaii vai_Vaii_LR vi vi_VN vun vun_TZ wae wae_CH wo wo_SN xh xh_ZA xog xog_UG yav yav_CM yi yi_001 yo yo_BJ yo_NG yue yue_Hans yue_Hans_CN yue_Hant yue_Hant_HK zgh zgh_MA zh zh_Hans zh_Hans_CN zh_Hans_HK zh_Hans_MO zh_Hans_SG zh_Hant zh_Hant_HK zh_Hant_MO zh_Hant_TW zu zu_ZA



java -jar genunireg-X-X-X-SNAPSHOT.jar --help af su az_Cyrl_AZ

java -jar genunireg-X-X-X-SNAPSHOT.jar --help zh

java -jar genunireg-X-X-X-SNAPSHOT.jar af su az_Cyrl_AZ br_FR zh ja az ast


```

CMD

```
$ java -jar genunireg-1.0.0-SNAPSHOT-jar-with-dependencies.jar  af su az_Cyrl_AZ br_FR zh ja az ast
```

OUT

```
LocaleName	DisplayName	DisplayNameWithDialect	PatternClass	RegexPattern
af	アフリカーンス語	アフリカーンス語	[[:Latn:]]	[A-Za-z\u00AA\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02B8\u02E0-\u02E4\u1D00-\u1D25\u1D2C-\u1D5C\u1D62-\u1D65\u1D6B-\u1D77\u1D79-\u1DBE\u1E00-\u1EFF\u2071\u207F\u2090-\u209C\u212A\u212B\u2132\u214E\u2160-\u2188\u2C60-\u2C7F\uA722-\uA787\uA78B-\uA7BF\uA7C2-\uA7CA\uA7F5-\uA7FF\uAB30-\uAB5A\uAB5C-\uAB64\uAB66-\uAB69\uFB00-\uFB06\uFF21-\uFF3A\uFF41-\uFF5A]
su	スンダ語	スンダ語	[[:Latn:]]	[A-Za-z\u00AA\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02B8\u02E0-\u02E4\u1D00-\u1D25\u1D2C-\u1D5C\u1D62-\u1D65\u1D6B-\u1D77\u1D79-\u1DBE\u1E00-\u1EFF\u2071\u207F\u2090-\u209C\u212A\u212B\u2132\u214E\u2160-\u2188\u2C60-\u2C7F\uA722-\uA787\uA78B-\uA7BF\uA7C2-\uA7CA\uA7F5-\uA7FF\uAB30-\uAB5A\uAB5C-\uAB64\uAB66-\uAB69\uFB00-\uFB06\uFF21-\uFF3A\uFF41-\uFF5A]
az_Cyrl_AZ	アゼルバイジャン語(キリル文字、アゼルバイジャン)	アゼルバイジャン語(キリル文字、アゼルバイジャン)	[[:Cyrl:]]	[\u0400-\u0484\u0487-\u052F\u1C80-\u1C88\u1D2B\u1D78\u2DE0-\u2DFF\uA640-\uA69F\uFE2E\uFE2F]
br_FR	ブルトン語(フランス)	ブルトン語(フランス)	[[:Latn:]]	[A-Za-z\u00AA\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02B8\u02E0-\u02E4\u1D00-\u1D25\u1D2C-\u1D5C\u1D62-\u1D65\u1D6B-\u1D77\u1D79-\u1DBE\u1E00-\u1EFF\u2071\u207F\u2090-\u209C\u212A\u212B\u2132\u214E\u2160-\u2188\u2C60-\u2C7F\uA722-\uA787\uA78B-\uA7BF\uA7C2-\uA7CA\uA7F5-\uA7FF\uAB30-\uAB5A\uAB5C-\uAB64\uAB66-\uAB69\uFB00-\uFB06\uFF21-\uFF3A\uFF41-\uFF5A]
zh	中国語	中国語	[[:Hani:]]	[\u2E80-\u2E99\u2E9B-\u2EF3\u2F00-\u2FD5\u3005\u3007\u3021-\u3029\u3038-\u303B\u3400-\u4DBF\u4E00-\u9FFC\uF900-\uFA6D\uFA70-\uFAD9\U00016FF0\U00016FF1\U00020000-\U0002A6DD\U0002A700-\U0002B734\U0002B740-\U0002B81D\U0002B820-\U0002CEA1\U0002CEB0-\U0002EBE0\U0002F800-\U0002FA1D\U00030000-\U0003134A]
ja	日本語	日本語	[[:Kana:]+[:Hira:]+[:Hani:]]	[\u2E80-\u2E99\u2E9B-\u2EF3\u2F00-\u2FD5\u3005\u3007\u3021-\u3029\u3038-\u303B\u3041-\u3096\u309D-\u309F\u30A1-\u30FA\u30FD-\u30FF\u31F0-\u31FF\u32D0-\u32FE\u3300-\u3357\u3400-\u4DBF\u4E00-\u9FFC\uF900-\uFA6D\uFA70-\uFAD9\uFF66-\uFF6F\uFF71-\uFF9D\U00016FF0\U00016FF1\U0001B000-\U0001B11E\U0001B150-\U0001B152\U0001B164-\U0001B167\U0001F200\U00020000-\U0002A6DD\U0002A700-\U0002B734\U0002B740-\U0002B81D\U0002B820-\U0002CEA1\U0002CEB0-\U0002EBE0\U0002F800-\U0002FA1D\U00030000-\U0003134A]
az	アゼルバイジャン語	アゼルバイジャン語	[[:Latn:]]	[A-Za-z\u00AA\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02B8\u02E0-\u02E4\u1D00-\u1D25\u1D2C-\u1D5C\u1D62-\u1D65\u1D6B-\u1D77\u1D79-\u1DBE\u1E00-\u1EFF\u2071\u207F\u2090-\u209C\u212A\u212B\u2132\u214E\u2160-\u2188\u2C60-\u2C7F\uA722-\uA787\uA78B-\uA7BF\uA7C2-\uA7CA\uA7F5-\uA7FF\uAB30-\uAB5A\uAB5C-\uAB64\uAB66-\uAB69\uFB00-\uFB06\uFF21-\uFF3A\uFF41-\uFF5A]
ast	アストゥリアス語	アストゥリアス語	[[:Latn:]]	[A-Za-z\u00AA\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02B8\u02E0-\u02E4\u1D00-\u1D25\u1D2C-\u1D5C\u1D62-\u1D65\u1D6B-\u1D77\u1D79-\u1DBE\u1E00-\u1EFF\u2071\u207F\u2090-\u209C\u212A\u212B\u2132\u214E\u2160-\u2188\u2C60-\u2C7F\uA722-\uA787\uA78B-\uA7BF\uA7C2-\uA7CA\uA7F5-\uA7FF\uAB30-\uAB5A\uAB5C-\uAB64\uAB66-\uAB69\uFB00-\uFB06\uFF21-\uFF3A\uFF41-\uFF5A]
```