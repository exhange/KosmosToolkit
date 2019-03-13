package cos.mos.utils.utils.system;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import cos.mos.utils.init.k.KApp;

/**
 * @Description: 屏幕信息
 * @Author: Kosmos
 * @Date: 2018.11.26 11:27
 * @Email: KosmoSakura@gmail.com
 * @eg: 2019.2.25:基本函数抽取
 */
public class UScreen {
    private static final DisplayMetrics metric = KApp.instance().getResources().getDisplayMetrics();
    private static final float scale = metric.density;

    /**
     * @param activity Activity引用
     * @param color    int型色值
     * @apiNote 设置顶部状态栏、底部导航栏颜色
     */
    protected void setBarColor(Activity activity, int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);//顶部状态栏
                window.setNavigationBarColor(color);//底部导航栏
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 获取DisplayMetrics对象
     */
    public static DisplayMetrics getDisPlayMetrics() {
        return metric;
    }

    /**
     * @param pxValue 像素单位
     * @return dp单位
     */
    public static int px2dp(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * @param dpValue dp单位
     * @return 像素单位
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @return 屏幕宽度（像素）
     */
    public static int getScreenWidth() {
        return metric.widthPixels;
    }

    /**
     * @return 高度（像素）
     */
    public static int getScreenHeight() {
        return metric.heightPixels;
    }


    /**
     * @return 屏幕密度(0.75 / 1.0 / 1.5)
     */
    public static float getDensity() {
        return metric.density;
    }

    /**
     * @return 屏幕密度DPI(120 / 160 / 240)
     */
    public static int getDensityDpi() {
        return metric.densityDpi;
    }

}
