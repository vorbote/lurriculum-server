package cn.vorbote.curriculum.enums;

import cn.vorbote.curriculum.models.RegionModel;
import com.baomidou.mybatisplus.annotation.EnumValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 国家或地区电话号码<br>
 * Created at 20/09/2022 20:16
 *
 * @author theod
 */
public enum Region {

    //
    // Starts with 1
    //
    /**
     * United States of America
     */
    NORTH_AMERICA(1, "加拿大 / 美国"),
    US_VIRGIN_ISLANDS(1340, "美属维尔京群岛"),
    NORTHERN_MARIANA_ISLANDS(1670, "马里亚纳群岛"),
    GUAM(1671, "关岛"),
    AMERICAN_SAMOA(1684, "美属萨摩亚"),
    PUERTO_RICO_787(1787, "波多黎各 (787)"),
    PUERTO_RICO_939(1939, "波多黎各 (939)"),
    BAHAMAS(1242, "巴哈马"),
    BARBADOS(1246, "巴巴多斯"),
    ANGUILLA(1264, "安圭拉"),
    ANTIGUA_AND_BARBUDA(1268, "安地卡及巴布达"),
    BRITISH_VIRGIN_ISLANDS(1284, "英属维尔京群岛"),
    CAYMAN_ISLANDS(1345, "开曼群岛"),
    BERMUDA(1441, "百慕大"),
    GRENADA(1473, "格瑞纳达"),
    TURKS_AND_CAICOS_ISLANDS(1649, "特克斯和凯科斯群岛"),
    JAMAICA_658(1658, "牙买加 (658)"),
    JAMAICA_876(1876, "牙买加 (876)"),
    MONTSERRAT(1664, "蒙特塞拉特"),
    SINT_MAARTEN(1721, "荷属圣马丁"),
    SAINT_LUCIA(1758, "圣卢西亚"),
    DOMINICA(1767, "多米尼克"),
    SAINT_VINCENT_AND_THE_GRENADINES(1784, "圣文森特和格林纳丁斯"),
    DOMINICA_REPUBLIC_809(1809, "多米尼加共和国 (809)"),
    DOMINICA_REPUBLIC_829(1829, "多米尼加共和国 (829)"),
    DOMINICA_REPUBLIC_849(1849, "多米尼加共和国 (849)"),
    TRINIDAD_AND_TOBAGO(1868, "千里达及托巴哥"),
    SAINT_KITTS_AND_NEVIS(1869, "圣基茨和尼维斯"),


    //
    // Starts with 2
    //
    EGYPT(20, "埃及"),
    SOUTH_SUDAN(211, "南苏丹"),
    MOROCCO(212, "摩洛哥"),
    ALGERIA(213, "阿尔及利亚"),
    TUNISIA(216, "突尼斯"),
    LIBYA(218, "利比亚"),
    GAMBIA(220, "冈比亚"),
    SENEGAL(221, "塞内加尔"),
    MAURITANIA(222, "毛里塔尼亚"),
    MALI(223, "马里"),
    GUINEA(224, "几内亚"),
    IVORY_COAST(225, "科特迪瓦"),
    BURKINA_FASO(226, "布基纳法索"),
    NIGER(227, "尼日尔"),
    TOGO(228, "多哥"),
    BENIN(229, "贝宁"),
    MAURITIUS(230, "毛里求斯"),
    LIBERIA(231, "利比里亚"),
    SIERRA_LEONE(232, "塞拉利昂"),
    GHANA(233, "加纳"),
    NIGERIA(234, "尼日利亚"),
    CHAD(235, "乍得"),
    CENTRAL_AFRICAN_REPUBLIC(236, "中非"),
    CAMEROON(237, "喀麦隆"),
    CAPE_VERDE(238, "佛得角"),
    SAO_TOME_AND_PRINCIPE(239, "圣多美和普林西比"),
    EQUATORIAL_GUINEA(240, "赤道几内亚"),
    GABON(241, "加蓬"),
    REPUBLIC_OF_THE_CONGO(242, "刚果 (布)"),
    DEMOCRATIC_REPUBLIC_OF_THE_CONGO(243, "刚果 (金)"),
    ANGOLA(244, "安哥拉"),
    GUINEA_BISSAU(245, "几内亚比绍"),
    BRITISH_INDIAN_OCEAN_TERRITORY(246, "英属印度洋领地"),
    ASCENSION_ISLAND(247, "阿森松岛"),
    SEYHCHELLES(248, "塞舌尔"),
    SUDAN(249, "苏丹"),
    RWANDA(250, "卢旺达"),
    ETHIOPIA(251, "埃塞尔比亚"),
    SOMALIA(252, "索马里"),
    DJIBOUTI(253, "吉布提"),
    KENYA(254, "肯尼亚"),
    TANZANIA(255, "坦桑尼亚"),
    ZANZIBAR(25524, "桑给巴尔"),
    UGANDA(256, "乌干达"),
    BURUNDI(257, "布隆迪"),
    MOZAMBIQUE(258, "莫桑比克"),
    ZAMBIA(260, "赞比亚"),
    MADAGASCAR(261, "马达加斯加"),
    REUNION(262, "留尼汪"),
    MAYOTTE(262639, "马约特"),
    ZIMBABWE(263, "津巴布韦"),
    NAMIBIA(264, "纳米比亚"),
    MALAWI(265, "马拉维"),
    LESOTHO(266, "莱索托"),
    BOTSWANA(267, "博茨瓦纳"),
    ESWATINI(268, "斯威士兰"),
    COMOROS(269, "科摩罗"),
    SOUTH_AFRICA(27, "南非"),
    SAINT_HELENA(290, "圣赫勒拿"),
    TRISTAN_DA_CUNHA(2908, "特里斯坦-达库尼亚"),
    ERITREA(2908, "厄立特里亚"),
    ARUBA(297, "阿鲁巴"),
    FAROE_ISLANDS(298, "法罗群岛"),
    GREENLAND(299, "格林兰"),

    //
    // Starts with 3
    //
    GREECE(30, "希腊"),
    NETHERLANDS(31, "荷兰"),
    BELGIUM(32, "比利时"),
    FRANCE(33, "法国"),
    SPAIN(34, "西班牙"),
    GIBRALTAR(350, "直布罗陀"),
    PORTUGAL(351, "葡萄牙"),
    LUXEMBOURG(352, "卢森堡"),
    IRELAND(353, "爱尔兰"),
    ICELAND(354, "冰岛"),
    ALBANIA(355, "阿尔巴尼亚"),
    MALTA(356, "马耳他"),
    CYPRUS(357, "赛普勒斯"),
    FINLAND(358, "芬兰"),
    BULGARIA(359, "保加利亚"),
    HUNGARY(36, "匈牙利"),
    LITHUANIA(370, "立陶宛"),
    LATVIA(371, "拉脱维亚"),
    ESTONIA(372, "爱沙尼亚"),
    MOLDOVA(373, "摩尔多瓦"),
    ARMENIA(374, "亚美尼亚"),
    BELARUS(375, "白俄罗斯"),
    ANDORRA(376, "安道尔"),
    MONACO(377, "摩纳哥"),
    SAN_MARINO(378, "圣马力诺"),
    VATICAN_CITY(3906698, "梵蒂冈城"),
    UKRAINE(380, "乌克兰"),
    SERBIA(381, "塞尔维亚"),
    MONTENEGRO(382, "黑山"),
    KOSOVO(383, "科索沃"),
    CROATIA(385, "克罗地亚"),
    SLOVENIA(386, "斯洛文尼亚"),
    BOSNIA_AND_HERZEGOVINA(387, "波黑"),
    NORTH_MACEDONIA(389, "北马其顿"),
    ITALY(39, "意大利"),



    //
    // Starts with 4
    //
    ROMANIA(40, "罗马尼亚"),
    SWITZERLAND(41, "瑞士"),
    CZECH_REPUBLIC(420, "捷克"),
    SLOVAKIA(421, "斯洛伐克"),
    LIECHTENSTEIN(423, "列支敦士登"),
    AUSTRIA(43, "奥地利"),
    UNITED_KINGDOM(44, "英国"),
    DENMARK(45, "丹麦"),
    SWEDEN(46, "瑞典"),
    NORWAY(47, "挪威"),
    POLAND(48, "波兰"),
    GERMANY(49, "德国"),


    //
    // Starts with 5
    //
    FALKLAND_ISLANDS(500, "福克兰群岛"),
    BELIZE(501, "伯利兹"),
    GUATEMALA(502, "危地马拉"),
    EL_SALVADOR(503, "萨尔瓦多"),
    HONDURAS(504, "洪都拉斯"),
    NICARAGUA(505, "尼加拉瓜"),
    COSTA_RICA(506, "哥斯达黎加"),
    PANAMA(507, "巴拿马"),
    SAINT_PIERRE_AND_MIQUELON(508, "圣皮埃尔和密克隆"),
    HAITI(509, "海地"),
    PERU(51, "秘鲁"),
    MEXICO(52, "墨西哥"),
    CUBA(53, "古巴"),
    ARGENTINA(54, "阿根廷"),
    BRAZIL(55, "巴西"),
    CHILE(56, "智利"),
    COLOMBIA(57, "哥伦比亚"),
    VENEZUELA(58, "委内瑞拉"),
    GUADELOUPE(590, "瓜德罗普"),
    BOLIVIA(591, "玻利维亚"),
    GUYANA(592, "圭亚那"),
    ECUADOR(593, "厄瓜多尔"),
    FRENCH_GUIANA(594, "法属圭亚那"),
    PARAGUAY(595, "巴拉圭"),
    MARTINIQUE(596, "马提尼克"),
    SURINAME(597, "苏里南"),
    URUGUAY(598, "乌拉圭"),
    NETHERLANDS_ANTILLES(599, "荷属安第列斯"),


    //
    // Starts with 6
    //
    MALAYSIA(60, "马来西亚"),
    AUSTRALIA(61, "澳大利亚"),
    INDONESIA(62, "印度尼西亚"),
    PHILIPPINES(63, "菲律宾"),
    NEW_ZEALAND(64, "新西兰"),
    SINGAPORE(65, "新加坡"),
    THAILAND(66, "泰国"),
    EAST_TIMOR(670, "东帝汶"),
    AUSTRALIA_EXTERNAL_TERRITORIES(672, "澳大利亚海外领地"),
    BRUNEI(673, "文莱"),
    NAURU(674, "瑙鲁"),
    PAPUA_NEW_GUINEA(675, "巴布亚新几内亚"),
    TONGA(676, "汤加"),
    SOLOMON_ISLANDS(677, "所罗门群岛"),
    VANUATU(678, "瓦努阿图"),
    FIJI(679, "斐济"),
    PALAU(680, "帛琉"),
    WALLIS_AND_FUTUNA(681, "瓦利斯和富图纳"),
    COOK_ISLANDS(682, "库克群岛"),
    NIUE(683, "纽埃"),
    SAMOA(685, "萨摩亚"),
    KIRIBATI(686, "基里巴斯"),
    NEW_CALEDONIA(687, "新喀里多尼亚"),
    TUVALU(688, "图瓦卢"),
    FRENCH_POLYNESIA(689, "法属波利尼西亚"),
    TOKELAU(690, "托克劳"),
    FEDERATED_STATES_OF_MICRONESIA(691, "密克罗尼西亚联邦"),
    MARSHALL_ISLANDS(692, "马绍尔群岛"),


    //
    // Starts with 7
    //
    RUSSIA(7, "俄罗斯"),


    //
    // Starts with 8
    //
    JAPAN(81, "日本"),
    SOUTH_KOREA(82, "韩国"),
    VIETNAM(84, "越南"),
    NORTH_KOREA(850, "朝鲜"),

    /**
     * Hong Kong SAR, China
     */
    HONG_KONG(852, "中国香港"),

    /**
     * Macau SAR, China
     */
    MACAU(853, "中国澳门"),

    CAMBODIA(855, "柬埔寨"),
    LAOS(856, "老挝"),

    /**
     * China mainland
     */
    CHINA_MAINLAND(86, "中国大陆"),

    BANGLADESH(880, "孟加拉国"),

    /**
     * Taiwan Province, China
     */
    TAIWAN(886, "中国台湾"),

    //
    // Starts with 9
    //
    TURKIYE(90, "土耳其"),
    INDIA(91, "印度"),
    PAKISTAN(92, "巴基斯坦"),
    AFGHANISTAN(93, "阿富汗"),
    SRI_LANKA(94, "斯里兰卡"),
    MYANMAR(95, "缅甸"),
    MALDIVIES(960, "马尔代夫"),
    LEBANON(961, "黎巴嫩"),
    JORDAN(962, "约旦"),
    SYRIA(963, "叙利亚"),
    IRAQ(964, "伊拉克"),
    KUWAIT(965, "科威特"),
    SAUDI_ARABIA(966, "沙特阿拉伯"),
    YEMEN(967, "也门"),
    OMAN(968, "阿曼"),
    PALESTINE(970, "巴勒斯坦"),
    UNITED_ARAB_EMIRATES(971, "阿联酋"),
    ISRAEL(972, "以色列"),
    BAHRAIN(973, "巴林"),
    QATAR(974, "卡塔尔"),
    BHUTAN(975, "不丹"),
    MONGOLIA(976, "蒙古国"),
    NEPAL(977, "尼泊尔"),
    IRAN(98, "伊朗"),
    TAJIKISTAN(992, "塔吉克斯坦"),
    TURKMENISTAN(993, "土库曼斯坦"),
    AZERBAIJAN(994, "阿塞拜疆"),
    GEORGIA(995, "格鲁吉亚"),
    KYRGYZSTAN(996, "吉尔吉斯斯坦"),
    KAZAKHSTAN(997, "哈萨克斯坦"),
    UZBEKISTAN(998, "乌兹别克斯坦"),


    //
    // Undefined
    //
    UNDEFINED(0, "未定义");

    @EnumValue
    private final int code;

    private final String label;

    Region(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    /**
     * 根据国际区号判断
     *
     * @param code 国际区号
     * @return 找到的地区
     */
    public static Region byCode(Integer code) {
        return Arrays.stream(values())
                .filter((item) -> Objects.equals(item.code, code))
                .findFirst()
                .orElse(UNDEFINED);
    }

    public static List<RegionModel> regionModels() {
        var regions = new ArrayList<RegionModel>();
        for (var region : Region.values()) {
            if (region != Region.UNDEFINED) {
                regions.add(RegionModel.fromRegion(region));
            }
        }
        return regions;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
