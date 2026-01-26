package com.zenika.rclens.social.core;

import com.zenika.rclens.social.model.Match;

import java.util.Comparator;

public class MatchDateComparator implements Comparator<Match> {

    @Override
    public int compare(Match o1, Match o2) {
        if (o1.getDate().isAfter(o2.getDate())) {
            return 1;
        }
        if (o1.getDate().isBefore(o2.getDate())) {
            return -1;
        }
        return 0;
    }

}
