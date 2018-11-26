package cos.mos.library.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import cos.mos.library.constant.KConfig;

/**
 * @Description: SharedPreferences工具
 * @Author: Kosmos
 * @Date: 2018年08月03日 14:08
 * @Email: KosmoSakura@gmail.com
 * @eg: 最新修改日期：2018年10月8日
 */
public class USP {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private USP(Context context) {
        sp = context.getSharedPreferences(KConfig.getSPName(), Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static USP with(Context context) {
        return new USP(context);
    }


    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultObject) {
        return sp.getLong(key, defaultObject);
    }

    public int getInt(String key, int defaultObject) {
        return sp.getInt(key, defaultObject);
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * 存
     */
    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
//        boolean commit = editor.commit();
        editor.apply();
    }

    /**
     * 取
     */
    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {
            return sp.getString(key, null);
        }
    }

    /**
     * 删
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清空
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }
}
