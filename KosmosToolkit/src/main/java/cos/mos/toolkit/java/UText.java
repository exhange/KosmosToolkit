package cos.mos.toolkit.java;

import android.widget.TextView;

import java.util.List;

/**
 * @Description 字符校验类
 * @Author Kosmos
 * @Date 2016年8月29日 11:32
 * @Email KosmoSakura@gmail.com
 * @Tip 2018.9.12:转录
 * @Tip 2018.9.22:重载方法
 * @Tip 2018.11.27:重构
 * @Tip 2019.3.7:优化文字处理的执行效率
 * @Tip 2019.4.8:优化重构变量
 * @Tip 2019.10.25:优化判断逻辑执行效率
 * @Tip 2019.11.7:追加空处理类型
 * @Tip 2020.5.6:新增几个函数
 * @apiNote 本类中的空值判断：长度为0都为false
 */
public class UText {
    /**
     * @apiNote String为空返回缺省字符
     */
    public static String isNull(CharSequence str, final String defaul) {
        return isEmpty(str) ? defaul : str.toString();
    }

    public static String isNull(CharSequence str) {
        return isNull(str, "");
    }

    public static String isNull(TextView tv) {
        return isEmpty(tv) ? "" : tv.getText().toString();
    }
//-------------------------------------------------------------------------------------------------------------------

    /**
     * @return digit为空返回缺省数字
     */
    public static double isNull(Double digit, final double defasult) {
        return digit == null ? defasult : digit;
    }

    public static double isNull(Double digit) {
        return isNull(digit, 0.0D);
    }

    public static long isNull(Long digit, final long defasult) {
        return digit == null ? defasult : digit;
    }

    public static long isNull(Long digit) {
        return isNull(digit, 0L);
    }

    public static float isNull(Float digit) {
        return isNull(digit, 0f);
    }

    public static float isNull(Float digit, final float defasult) {
        return digit == null ? defasult : digit;
    }

    public static int isNull(Integer digit) {
        return isNull(digit, 0);
    }

    public static int isNull(Integer digit, final int defasult) {
        return digit == null ? defasult : digit;
    }

    public static short isNull(Short digit) {
        return isNull(digit, (short) 0);
    }

    public static short isNull(Short digit, final short defasult) {
        return digit == null ? defasult : digit;
    }

    public static byte isNull(Byte digit) {
        return isNull(digit, (byte) 0);
    }

    public static byte isNull(Byte digit, final byte defasult) {
        return digit == null ? defasult : digit;
    }

//-------------------------------------------------------------------------------------------------------------------

    /**
     * @param sequence 被校验的字符
     * @return 字符是否为空{@code true}: 空  {@code false}: 不为空
     * @Tip 字符串是否为空(只有空格也为空 ）
     * @Tip sequence.length () == 0:可以减少一次判断
     * @Tip null.toString().length =4
     * @Tip str.equalsIgnoreCase(null.toString ())=true
     */
    public static boolean isEmpty(CharSequence sequence) {
        return sequence == null || sequence.length() == 0 || sequence.toString().trim().length() == 0;
    }

    public static boolean isEmpty(TextView tv) {
        return tv == null || isEmpty(tv.getText());
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static <Z> boolean isEmpty(Z[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isTrue(Boolean b) {
        return b == null ? false : b;
    }

    //-------------------------------------------------------------------------------------------------------------------
    public static boolean isNotEmpty(CharSequence sequence) {
        return sequence != null && sequence.length() > 0 && sequence.toString().trim().length() > 0;
    }

    public static boolean isNotEmpty(TextView tv) {
        return tv != null && isNotEmpty(tv.getText());
    }

    public static boolean isNotEmpty(List list) {
        return list != null && list.size() > 0;
    }

    public static <Z> boolean isNotEmpty(Z[] arr) {
        return arr != null && arr.length > 0;
    }

    //------------------------------------------------------------------------------------------------

    /**
     * @return 返回str长度，为空返回指定值
     */
    public static int getLength(String str, final int defaul) {
        return isEmpty(str) ? defaul : str.length();
    }

    /**
     * @return 返回str长度，为空返回0
     */
    public static int getLength(String str) {
        return getLength(str, 0);
    }

    /**
     * @return 文本控件内容长度，为空返回0
     */
    public static int getLength(TextView tv) {
        return isEmpty(tv) ? 0 : tv.length();
    }

    /**
     * @apiNote 获取数组长度(为空返回0)
     */
    public static int getLength(String[] arr) {
        return arr == null ? 0 : arr.length;
    }

    /**
     * @return 获取list长度(为空返回0)
     */
    public static int getLength(List list) {
        return list == null ? 0 : list.size();
    }

//-------------------------------------------------------------------------------------------------------------------
}
