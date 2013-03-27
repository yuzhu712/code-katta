package com.ashishpaliwal.codekatta.topcoder.simple;

/**
 *
 */
public class CityMap {

    /**
     * Parse the array and update the info a char array
     *
     *
     * @param cityMap
     * @param pois
     * @return
     */
    public String getLegend(String[] cityMap, int[] pois) {
        int[] symbols = new int[26];
        char[] syms = new char[pois.length];

        for (String s : cityMap) {
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if('.' == c) {
                    continue;
                }
                int index = c - 'A';
                symbols[index]++;
            }
        }

         // The Array is prepared

        for (int i = 0; i < pois.length; i++) {
            int index = -1;
            for (int j = 0; j < symbols.length; j++) {
                if(pois[i] == symbols[j]) {
                    index = j;
                    break;
                }
            }
            char ch = (char) ('A' + index);
            syms[i] = ch;
        }
        return new String(syms);
    }

    public static void main(String[] args) {
        String[] map = {"AIAAARRI.......GOAI.",
                ".O..AIIGI.OAAAGI.A.I",
                ".A.IAAAARI..AI.AAGR.",
                "....IAI..AOIGA.GAIA.",
                "I.AIIIAG...GAR.IIAGA",
                "IA.AOA....I....I.GAA",
                "IOIGRAAAO.AI.AA.RAAA",
                "AI.AAA.AIR.AGRIAAG..",
                "AAAAIAAAI...AAG.RGRA",
                ".J.IA...G.A.AA.II.AA"};

        int[] poi = {16,7,1,35,11,66};

        CityMap cityMap = new CityMap();
        System.out.println(cityMap.getLegend(map, poi));
    }


}
