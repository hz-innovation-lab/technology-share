package com.dfire.share.dynamic;

/**
 * <p>Package:com.dfire.share.dynamic</p>
 * <p>Description: </p>
 * <p>Company: com.lowan</p>
 *
 * @author wjj
 * @date 2019/12/23 4:20
 */
public class TimeWatcher {

    private static ThreadLocal<Long> lastMills = new ThreadLocal<Long>();

    public static void printIfNotFirst() {
        if (lastMills.get() != null) {
            long temp = System.currentTimeMillis();
            System.out.println("cost " + (temp - lastMills.get()) + "  mills");
            lastMills.set(temp);
        } else {
            lastMills.set(System.currentTimeMillis());
        }
    }

    public static void clear() {
        lastMills.remove();
    }
}
