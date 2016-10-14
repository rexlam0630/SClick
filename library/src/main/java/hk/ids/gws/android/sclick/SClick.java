package hk.ids.gws.android.sclick;

import android.os.SystemClock;

import java.util.concurrent.ConcurrentHashMap;

import hk.ids.gws.android.sclick.helper.SafeClick;

public class SClick {
    // Default tags, for common usage
    public static final String BUTTON_CLICK = "sclick:button-click";
    public static final String REFRESH_CLICK = "sclick:refresh-click";
    public static final String NAVIGATION_CLICK = "sclick:navigation-click";


    private static ConcurrentHashMap<String, Long> mHashMap = new ConcurrentHashMap<>();


    /**
     * Check if the given tag able to do next event using setting interval
     */
    public static boolean check(String tag) {
        return check(tag, SafeClick.getDefault().interval());
    }

    /**
     * Check if the given tag able to do next event using given interval
     */
    public static boolean check(String tag, int intervalMs) {
        boolean result = false;

        Long current = SystemClock.elapsedRealtime();
        Long last = mHashMap.get(tag);
        if (last != null) {
            if (last != -1L && current - last >= intervalMs) {
                result = true;
            }
        } else {
            result = true;
        }

        if (result) {
            mHashMap.put(tag, current);
        }

        return result;
    }

    /**
     * Check if the given tag able to do next event using setting interval,
     * auto lock the given tag if return is true, until unlock(tag) called
     */
    public static boolean checkAndLock(String tag) {
        return checkAndLock(tag, SafeClick.getDefault().interval());
    }

    /**
     * Check if the given tag able to do next event using given interval,
     * auto lock the given tag if return is true, until unlock(tag) called
     */
    public static boolean checkAndLock(String tag, int interval) {
        boolean result = check(tag, interval);
        if (result) {
            mHashMap.put(tag, -1L);
        }

        return result;
    }

    /**
     * Lock the given tag until unlock(tag) called
     */
    public static void lock(String tag) {
        mHashMap.put(tag, -1L);
    }

    /**
     * Unlock the given tag if locked manually
     */
    public static void unlock(String tag) {
        Long last = mHashMap.get(tag);
        if (last != null && last == -1L) {
            mHashMap.remove(tag);
        }
    }

    /**
     * Unlock the given tag, even not locked manually
     */
    public static void forceUnlock(String tag) {
        mHashMap.remove(tag);
    }
}
