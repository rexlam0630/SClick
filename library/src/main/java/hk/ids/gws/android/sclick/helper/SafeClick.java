package hk.ids.gws.android.sclick.helper;

public class SafeClick {
    private static SafeClick sInstance;

    public static SafeClick getDefault() {
        return sInstance == null ? sInstance = new SafeClick() : sInstance;
    }


    private int mInterval;


    private SafeClick() {
        mInterval = 750;
    }


    public int interval() {
        return mInterval;
    }

    public SafeClick setInterval(int interval) {
        mInterval = interval;

        return this;
    }
}
