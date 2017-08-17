package mallcollection.joinearn.com.mymall.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import mallcollection.joinearn.com.mymall.App.MyApplication;


/**
 * 对SharedPreference文件中的各种类型的数据进行存取操作
 */
public class SPUtil {
    private static SharedPreferences spf;
    public static final String SP_NAME = "shared_prefs";
    public static final String SP_IS_FIRST_LOGIN = "isFirstLogin";
    public static final String IS_LOGIN = "isLogin";
    public static final String LOGINMSG = "loginMsg";
    public static final String FIRST_INSPECT = "first_inspect";
    public static final String FIRST_MEASURE = "first_measure";
    public static final String CHART_YESTERDAY = "chart_yesterday";
    public static final String BODY_YESTERDAY = "body_yesterday";


    private SPUtil() {

    }

    static {
        spf = MyApplication.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 通过 instanceof 来判断数据类型再强转成对应的数据类型进行存储
     *
     * @param key
     * @param obj
     */
    public static void put(String key, Object obj) {
        SharedPreferences.Editor editor = spf.edit();

        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (int) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else{
            setObject(key, obj);
        }

        editor.apply();
    }
    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     * @param object
     */
    public static void setObject(String key, Object object) {

        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = spf.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(String key, Class<T> clazz) {
        if (spf.contains(key)) {
            String objectVal = spf.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 通过instanceof 来判断数据类型再进行取值然后再进行类型转换，这种方法比较麻烦取出值之后在应用的地方还要进行类型转换
     *
     * @param key
     * @param obj
     * @return
     */
    public static Object get(String key, Object obj) {
        if (obj instanceof String) {
            return spf.getString(key, (String) obj);
        } else if (obj instanceof String) {
            return spf.getLong(key, (Long) obj);
        } else if (obj instanceof Integer) {
            return spf.getInt(key, (int) obj);
        } else if (obj instanceof Boolean) {
            return spf.getBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            return spf.getFloat(key, (Float) obj);
        }

        return null;
    }

    /**
     * 全部清除文件中的内容
     */
    public static void clearSpf() {
        SharedPreferences.Editor editor = spf.edit();
        editor.clear().commit();
    }

    /**
     * 清除指定key 的内容
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = spf.edit();
        editor.remove(key).commit();
    }


    /**
     * 获取String值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key, String defValue) {
        return spf.getString(key, defValue);
    }

    /**
     * 获取int值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(String key, int defValue) {
        return spf.getInt(key, defValue);
    }

    /**
     * 获取long值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(String key, long defValue) {
        return spf.getLong(key, defValue);
    }

    /**
     * 获取float值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(String key, float defValue) {
        return spf.getFloat(key, defValue);
    }

    /**
     * 获取bo0lean值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return spf.getBoolean(key, defValue);
    }

}