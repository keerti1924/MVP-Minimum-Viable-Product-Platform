package com.mvp.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String timeAgo(Date date) {
        long duration = new Date().getTime() - date.getTime();
        if (duration < 1000) return "just now";

        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        if (seconds < 60) return seconds + " seconds ago";

        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        if (minutes < 60) return minutes + " minutes ago";

        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        if (hours < 24) return hours + " hours ago";

        long days = TimeUnit.MILLISECONDS.toDays(duration);
        return days + " days ago";
    }
}
