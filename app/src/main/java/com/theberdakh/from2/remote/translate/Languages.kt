package com.theberdakh.from2.remote.translate

enum class Languages {
    CYRILLIC, LATIN, UZBEK, KARAKALPAK, UNDEFINED, CYRILLIC_KARAKALPAK, CYRILLIC_LATIN
}

enum class TranslateLanguages(val code: String) {

    /* Language codes are from https://cdn.from-to.uz/languages.json */

    UZBEK("uz"),
    KARAKALPAK("kaa"),

    //LATIN
    ENGLISH_LATIN("eng_Latn"),
    UZBEK_LATIN("uzn_Latn"),
    ACEHNESE_LATIN("ace_Latn"),
    AKAN("aka_Latn"),
    AFRIKAANS("afr_Latn"),
    MODERN_STANDARD_ARABIC_ROMANIZED("arb_Latn"),
    ASTURIAN("ast_Latn"),
    CENTRAL_AYMARA("ayr_Latn"),
    NORTH_AZERBAIJANI("azj_Latn"),
    BAMBARA("bam_Latn"),
    BALINESE("ban_Latn"),
    BEMBA("bem_Latn"),
    BANJAR_LATIN("bjn_Latn"),
    BOSNIAN("bos_Latn"),
    BUGINESE("bug_Latn"),
    CATALAN("cat_Latn"),
    CEBUANO("ceb_Latn"),
    CZECH("ces_Latn"),
    CHOKWE("cjk_Latn"),
    CRIMEAN_TATAR("crh_Latn"),
    WELSH("cym_Latn"),
    DANISH("dan_Latn"),
    GERMAN("deu_Latn"),
    SOUTHWESTERN_DINKA("dik_Latn"),
    DYULA("dyu_Latn"),
    ESPERANTO("epo_Latn"),
    ESTONIAN("est_Latn"),
    BASQUE("eus_Latn"),
    EWE("ewe_Latn"),
    FAROESE("fao_Latn"),
    FIJIAN("fij_Latn"),
    FINNISH("fin_Latn"),
    FON("fon_Latn"),
    FRENCH("fra_Latn"),
    FRIULIAN("fur_Latn"),
    FUV_LATIN("fuv_Latn"),
    SCOTTISH_GAELIC("gla_Latn"),
    IRISH("gle_Latn"),
    GALICIAN("glg_Latn"),
    GUARANI("grn_Latn"),
    IGBO("ibo_Latn"),
    ILOCANO("ilo_Latn"),
    INDONESIAN("ind_Latn"),
    ICELANDIC("isl_Latn"),
    ITALIAN("ita_Latn"),
    JAVANESE("jav_Latn"),
    KABYLE("kab_Latn"),
    JINGPHO("kac_Latn"),
    KAMBA("kam_Latn"),
    STANDARD_MALAY("zsm_Latn"),
    ZULU("zul_Latn"),
    HAITIAN_CREOLE("hat_Latn"),
    HAUSE("hau_Latn"),
    CROATIAN("hrv_Latn"),
    HUNGARIAN("hun_Latn"),
    LIGURIAN("lij_Latn"),
    LIMBURGISH("lim_Latn"),
    LINGALA("lin_Latn"),
    LITHUANIAN("lit_Latn"),
    LOMBARD("lmo_Latn"),
    LATGALIAN("ltg_Latn"),
    LUXEMBOURGISH("ltz_Latn"),
    LUBA_KASAI("lua_Latn"),
    GANDA("lug_Latn"),
    LUO("luo_Latn"),
    MIZO("lus_Latn"),
    STANDARD_LATVIAN("lvs_Latn"),
    SLOVENIAN("slv_Latn"),
    SAMOAN("smo_Latn"),
    SHONA("sna_Latn"),
    SOMALI("som_Latn"),
    SOUTHERN_SOTHO("sot_Latn"),
    SPANISH("spa_Latn"),
    TOSK_ALBANIAN("als_Latn"),
    SARDINIAN("srd_Latn"),
    SWATI("ssw_Latn"),
    SUNDANESE("sun_Latn"),
    SWEDISH("swe_Latn"),
    SWAHILI("swh_Latn"),
    SILESIAN("szl_Latn"),
    VENETIAN("vec_Latn"),
    VIETNAMESE("vie_Latn"),
    WARAY("war_Latn"),
    WOLOF("wol_Latn"),
    XHOSA("xho_Latn"),
    TAMASHEQ_LATIN("taq_Latn"),
    TAMASHEQ_TIFINAGH("taq_Latn"),
    TOK_PISIN("tpi_Latn"),
    TSWANA("tsn_Latn"),
    TSONGA("tso_Latn"),
    TURKMEN("tuk_Latn"),
    TUMBUKA("tum_Latn"),
    TURKISH("tur_Latn"),
    TWI("twi_Latn"),
    YORUBA("yor_Latn"),
    UMBUNDU("umb_Latn"),
    TAGALOG("tgl_Latn"),
    SICILIAN("scn_Latn"),
    AYACUCHO_QUECHUA("quy_Latn"),
    ROMANIAN("ron_Latn"),
    RUNDI("run_Latn"),
    SANGO("sag_Latn"),
    POLISH("pol_Latn"),
    PORTUGUESE("por_Latn"),
    PAPIA_MENTO("pap_Latn"),
    PANGASINAN("pag_Latn"),
    NORTHERN_SATHO("nso_Latn"),
    NUER("nus_Latn"),
    NYANJA("nya_Latn"),
    OCCITAN("oci_Latn"),
    WEST_CENTRAL_OROMO("gaz_Latn"),
    DUTCH("nld_Latn"),
    NORWEGIAN_NYNORSK("nno_Latn"),
    NORWEGIAN_BOKMAL("nob_Latn"),
    MOSSI("mos_Latn"),
    MAORI("mri_Latn"),
    PLATEAU_MALAGASY("plt_Latn"),
    MALTESE("mlt_Latn"),
    MINANGKABAU_LATIN("min_Latn"),
    KIMBUNDU("kmb_Latn"),
    NORTHERN_KURDISH("kmr_Latn"),
    KIKONGO("kon_Latn"),
    KIKUYU_LATIN("kik_Latn"),
    KINYARWANDA_LATIN("kin_Latn"),
    KABIYE("kbp_Latn"),
    KABUVERDIANU("kea_Latn"),
    CENTRAL_KANURI_LATIN("knc_Latn"),


    //CYRILLIC
    RUSSIAN_CYRILLIC("rus_Cyrl"),
    BASHKIR("bak_Cyrl"),
    BELARUSIAN("bel_Cyrl"),
    BULGARIAN("bul_Cyrl"),
    SERBIAN("srp_Cyrl"),
    KAZAKH_CYRILLIC("kaz_Cyrl"),
    KYRGYZ("kir_Cyrl"),
    MACEDONIAN("mkd_Cyrl"),
    HALH_MONGOLIAN("khk_Cyrl"),
    TATAR("tat_Cyrl"),
    TAJIK("tgk_Cyrl"),
    UKRAINIAN("ukr_Cyrl"),


    //ARAB SCRIPT
    SINDHI("snd_Arab"),
    ACEHNESE_ARABIC("ace_Arab"),
    MESOPOTAMIAN_ARABIC("acm_Arab"),
    TAIZZI_ADENI_ARABIC("acq_Arab"),
    TUNISIAN_ARABIC("aeb_Arab"),
    SOUTH_LEVANTINE_ARABIC("ajp_Arab"),
    NORTH_LEVANTINE_ARABIC("apc_Arab"),
    MODERN_STANDARD_ARABIC("arb_Arab"),
    NAJDI_ARABIC("ars_Arab"),
    MOROCCAN_ARABIC("ary_Arab"),
    EGYPTIAN_ARABIC("arz_Arab"),
    SOUTH_AZERBAIJANI("azb_Arab"),
    BANJAR_ARAB("bjn_Arab"),
    CENTRAL_KURDISH("ckb_Arab"),
    KASHMIRI_ARAB("kas_Arab"),
    CENTRAL_KANURI_ARABIC("knc_Arab"),
    MINANGKABAU_ARABIC("min_Arab"),
    WESTERN_PERSIAN("pes_Arab"),
    DARI("prs_Arab"),
    SOUTHERN_PASHTO("pbt_Arab"),
    UYGHUR("uig_Arab"),
    URDU("urd_Arab"),


    //DEVA
    AWADHI("awa_Deva"),
    BHO_DEVA("bho_Deva"),
    HINDI("hin_Deva"),
    CHHATTISGARHI("hne_Deva"),
    KASHMIRI_DEVANGARI("kas_Deva"),
    MAGAHI("mag_Deva"),
    MAITHILI("mai_Deva"),
    MARATHI("mar_Deva"),
    NEPALI("npi_Deva"),
    SANSKRIT("san_Deva"),


    //BENG
    ASSAMESE("asm_Beng"),
    BENGALI("ben_Beng"),

    //TIBETAN
    STANDARD_TIBETAN("bod_Tibt"),
    DZONGKHA("dzo_Tibt"),


    //SINH
    SINHALA("sin_Sinh"),
    SLOVAK("sin_Sinh"),


    //MYMR
    SHAN("shn_Mymr"),
    BURMESE("mya_Mymr"),


    //ETHI
    AMHARIC("amh_Ethi"),
    TIGRINYA("tir_Ethi"),


    //HEBR
    HEBREW("heb_Hebr"),
    EASTERN_YIDDISH("ydd_Hebr"),


    //HANT
    YUE_CHINESE("yue_Hant"),
    CHINESE_TRADITIONAL("zho_Hant"),


    //OTHER SCRIPTS
    GREEK("ell_Grek"),
    GUJARATI("guj_Gujr"),
    ARMENIAN("hye_Armn"),
    JAPANESE("jpn_Jpan"),
    KANNADA("kan_Knda"),
    GEORGIAN("kat_Geor"),
    KHMER("khm_Khmr"),
    KOREAN("kor_Hang"),
    LAO("lao_Laoo"),
    MALAYALAM("mal_Mlym"),
    MEITEI("mni_Beng"),
    ODIA("ory_Orya"),
    EASTERN_PANJABI("pan_Guru"),
    SANTALI("sat_Olck"),
    TAMIL("tam_Taml"),
    TELEGU("tel_Telu"),
    THAI("tha_Thai"),
    CENTRAL_ATLAS_TAMAZIGHT("tzm_Tfng"),
    CHINESE_SIMPLIFIED("zho_Hans"),


}