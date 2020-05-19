package cos.mos.toolkit.hardware;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;


/**
 * @Description: GPS工具类
 * @Author: Kosmos
 * @Date: 2018.11.27 18:32
 * @Email: KosmoSakura@gmail.com
 */
public class UGPS {

    /**
     * @return GPS是否打开
     */
    public static boolean isGPSOpne(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 跳转GPS设置界面
     */
    public static void openGPSSettings(Activity activity) {
        //相关提示提示
//        UDialog.builder(activity, false)
//            .msg("Please open the location service")
//            .button("To Open")
//            .build((result, dia) -> {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                activity.startActivityForResult(intent, 5);
//                dia.dismiss();
//            });
    }
}
