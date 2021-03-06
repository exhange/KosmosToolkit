package cos.mos.toolkit.system;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;

import java.io.File;

import cos.mos.toolkit.constant.Code;
import cos.mos.toolkit.init.KApp;
import cos.mos.toolkit.log.ULog;


/**
 * @Description 各中页面跳转
 * @Author Kosmos
 * @Date 2018.12.17 15:16
 * @Email KosmoSakura@gmail.com
 * @tip 2018.12.24:特殊权限页面跳转
 * @tip 2019.2.26:辅助通道页面跳转
 * @tip 2019.3.5:打开系统视频播放器
 * @tip 2019.3.7:分享文字
 * @tip 2019.3.18: 跳转应用商店
 * @tip 2019.3.21: 跳转邮箱、拨号等界面
 * @tip 2020.5.23 apk安装，兼容至“Android 10”
 */
public class UIntent {
    private static void start(Intent intent) {
        KApp.instance().startActivity(intent);
    }

    /**
     * 去系统授权页面
     * 普通权限
     */
    public static void goSys() {
        Uri packageURI = Uri.parse("package:" + KApp.instance().getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(intent);
    }

    /**
     * 去系统授权页面（悬浮窗权限）
     * 高级权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void goSysOverlay() {
        Uri packageURI = Uri.parse("package:" + KApp.instance().getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, packageURI);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(intent);
    }

    /**
     * 去系统授权页面（有权限查看使用情况的应用）
     * 高级权限
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void goSysAdvanced() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(intent);
    }

    /**
     * @param activity 页面
     * @param request  请求值
     * @apiNote 去系统授权页面（有权限查看使用情况的应用）
     * 高级权限-带返回值
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void goSysAdvanced(Activity activity, int request) {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, request);
    }

    /**
     * 去系统授权页面（自启动管理）
     * 高级权限
     * 注意，很多国外的手机没有这个页面，直接跳过去会崩
     */
    public static void goSysBKG() {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.android.settings",
            "com.android.settings.BackgroundApplicationsManager");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(comp);
        start(intent);
    }

    /**
     * 去系统授权页面（自启动管理,适配重载）
     * 高级权限
     * 注意，很多国外的手机没有这个页面，直接跳过去会崩
     * 数据来自：https://blog.csdn.net/gxp1182893781/article/details/78027863
     */
    public static void goSysBKGAda() {
        Intent intent = new Intent();
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ULog.commonI("******************当前手机型号为：" + Build.MANUFACTURER);
            ComponentName componentName = null;
            switch (Build.MANUFACTURER) {
                case "Xiaomi":  // 红米Note4测试通过
                    componentName = new ComponentName("com.miui.securitycenter",
                        "com.miui.permcenter.autostart.AutoStartManagementActivity");
                    break;
                case "Letv":  // 乐视2测试通过
                    intent.setAction("com.letv.android.permissionautoboot");
                    break;
                case "samsung":  // 三星Note5测试通过
                    componentName = new ComponentName("com.samsung.android.sm_cn",
                        "com.samsung.android.sm.ui.ram.AutoRunActivity");
                    break;
                case "HUAWEI":  // 华为测试通过
                    componentName = new ComponentName("com.huawei.systemmanager",
                        "com.huawei.systemmanager.optimize.process.ProtectActivity");
                    break;
                case "vivo":  // VIVO测试通过
                    componentName = ComponentName.unflattenFromString("com.iqoo.secure" +
                        "/.safeguard.PurviewTabActivity");
                    break;
                case "Meizu":  //万恶的魅族
                    // 通过测试，发现魅族是真恶心，也是够了，之前版本还能查看到关于设置自启动这一界面，
                    // 系统更新之后，完全找不到了，心里默默Fuck！
                    // 针对魅族，我们只能通过魅族内置手机管家去设置自启动，
                    // 所以我在这里直接跳转到魅族内置手机管家界面，具体结果请看图
                    componentName = ComponentName.unflattenFromString("com.meizu.safe" +
                        "/.cos.mos.utils.ui.permission.PermissionMainActivity");
                    break;
                case "OPPO":  // OPPO R8205测试通过
                    componentName = ComponentName.unflattenFromString("com.oppo.safe" +
                        "/.cos.mos.utils.ui.permission.startup.StartupAppListActivity");
                    Intent intentOppo = new Intent();
                    intentOppo.setClassName("com.oppo.safe/.cos.mos.utils.ui.permission.startup",
                        "StartupAppListActivity");
                    if (KApp.instance().getPackageManager().resolveActivity(intentOppo, 0) == null) {
                        componentName = ComponentName.unflattenFromString("com.coloros.safecenter" +
                            "/.startupapp.StartupAppListActivity");
                    }

                    break;
                case "ulong":  // 360手机 未测试
                    componentName = new ComponentName("com.yulong.android.coolsafe",
                        ".ui.activity.autorun.AutoRunListActivity");
                    break;
                default:
                    // 以上只是市面上主流机型，由于公司你懂的，所以很不容易才凑齐以上设备
                    // 针对于其他设备，我们只能调整当前系统app查看详情界面
                    // 在此根据用户手机当前版本跳转系统设置界面
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", KApp.instance().getPackageName(), null));
                    break;
            }
            intent.setComponent(componentName);
            start(intent);
        } catch (Exception e) {//抛出异常就直接打开设置页面
            intent = new Intent(Settings.ACTION_SETTINGS);
            start(intent);
        }
    }

    /**
     * 回到桌面
     */
    public static void goHome() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        start(homeIntent);
    }

    /**
     * 去无障碍授权页面
     */
    public static void goAssist() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(intent);
    }


    /**
     * @param dir 视频地址
     * @apiNote 打开系统视频播放器
     */
    public static void toVideo(String dir) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse(dir), "video/*");
        try {
            start(intent);
        } catch (Exception e) {
//            UToast.show("No default player");//显示提示
        }
    }

    /**
     * @param dir 音频地址
     * @apiNote 打开系统音频播放器
     */
    public static void toAudio(String dir) {
        //
    }

    /**
     * @param dir      音频地址
     * @param listener 链接开始、扫描结束回调监听
     * @apiNote 更新媒体库
     */
    public static void addMediaLibrary(String dir, MediaScannerConnection.MediaScannerConnectionClient listener) {
        MediaScannerConnection.scanFile(KApp.instance(), new String[]{dir}, null, listener);
    }

    /**
     * @apiNote 跳转当前应用的GooglePlay商店 评分
     * Google Play:com.android.vending
     * 应用宝:com.tencent.android.qqdownloader
     * 360手机助手:com.qihoo.appstore
     * 百度手机助:com.baidu.appsearch
     * 小米应用商店:com.xiaomi.market
     * 豌豆荚:com.wandoujia.phoenix2
     * 华为应用市场;com.huawei.appmarket
     * 淘宝手机助手：com.taobao.appcenter
     * 安卓市场：com.hiapk.marketpho
     * 安智市场：cn.goapk.market
     */
    public static void toPlayStore() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + KApp.instance().getPackageName()));
            intent.setPackage("com.android.vending");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            start(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param email 收件人地址
     * @apiNote 打开邮件，并填入收件人
     */
    public static void toEmail(String email) {
        try {
            Intent data = new Intent(Intent.ACTION_SENDTO);
            data.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            data.setData(Uri.parse("mailto:way.ping.li@gmail.com"));
            data.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
            data.putExtra(Intent.EXTRA_SUBJECT, "This is a title");
            data.putExtra(Intent.EXTRA_TEXT, "This is the content");
            start(data);
        } catch (Exception e) {
//            UToast.show("No mail client found");//显示提示
        }
    }

    /**
     * @param link 地址
     * @apiNote 用默认浏览器打开
     */
    public static void toBrowser(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(intent);
    }

    /**
     * @param telephone 电话号
     * @apiNote 打开拨号页面，并填入电话号
     */
    public static void toPhone(String telephone) {
        Intent phone = new Intent(Intent.ACTION_DIAL);
        phone.setData(Uri.parse("tel:" + telephone));
        phone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(phone);
    }

    /**
     * @param path new File("apk地址")
     * @tip apk安装，兼容至“Android 10”
     * @tip 需要权限（待考证是否真的需要）： <uses-permission android:name="android.permission.INSTALL_PACKAGES"/>
     * @tip 需要权限（待考证是否真的需要）： <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
     * @tip AndroidManifest中-application标签下面：
     * <provider
     * android:name="android.support.v4.content.FileProvider"
     * android:authorities="ehanghai.hanxinhangtong.ehangtong.fileprovider"
     * android:exported="false"
     * android:grantUriPermissions="true">
     * <meta-data
     * android:name="android.support.FILE_PROVIDER_PATHS"
     * android:resource="@xml/file_paths"/>
     * </provider>
     * 其中：
     * android:name => FileProvider类的完整包名，区分：support.v4 、androidx等
     * android:authorities => 一般"包名.fileprovider"
     * android:resource => 参考本module中：./res/xml/file_paths.xml文件
     */
    public static void installApk(File path, Activity act) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        //7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //与manifest中定义的provider中的authorities="包名.fileprovider"保持一致
            Uri apkUri = FileProvider.getUriForFile(act, "ehanghai.hanxinhangtong.ehangtong.fileprovider", path);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, Code.FILE_TYPE_apk);
        } else {
            intent.setDataAndType(Uri.fromFile(path), Code.FILE_TYPE_apk);
        }
        act.startActivity(intent);
    }
}
