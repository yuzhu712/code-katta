package com.ashishpaliwal.codekatta.topcoder.simple;

import java.util.Arrays;

/**
 *
 */
public class Trekking {

    public int findCamps(String trail, String[] plans) {

        char[] trailArray = trail.toCharArray();

        int minNights = -1;

        for (int i = 0; i < plans.length; i++) {
            char[] plan = plans[i].toCharArray();

            int campingNight = 0;
            boolean invalidEntry = false;
            for (int j = 0; j < trailArray.length; j++) {
                if(trailArray[j] == '^' && plan[j] == 'C') {
                    invalidEntry = true;
                    break;
                }
                if(plan[j] == 'C') {
                    campingNight++;
                }
            }
            if(!invalidEntry) {
                if(minNights < 0 || minNights > campingNight) {
                    minNights = campingNight;
                }
            }
        }
        return minNights;
    }

    public static void main(String[] args) {
        String trail = "..............";
        String[] plans = new String[] { "CwCwCwCwCwCwCw",
                "CwwCwwCwwCwwCw" };

        Trekking trekking = new Trekking();
        System.out.println(trekking.findCamps(trail, plans));
    }

}
