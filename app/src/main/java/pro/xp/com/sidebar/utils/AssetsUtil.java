package pro.xp.com.sidebar.utils;

import android.content.Context;
import android.util.Log;

import org.apache.http.util.EncodingUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 从assets 文件夹中获取文件并读取数据
 *
 * @author xiep
 */
@SuppressWarnings("all")
public class AssetsUtil {

    public static String getFromAssets(Context context, String fileName) {
        String result = "";
        InputStream in = null;
        try {
            in = context.getResources().getAssets().open(fileName);
            // 获取文件的字节数
            int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            while (in.read(buffer) > 0) {
                // 你的文件的编码
                result = EncodingUtils.getString(buffer, "UTF-8");
            }
        } catch (Exception e) {
            Log.e("AssetsUtil", "Error AssetsUtil:", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("AssetsUtil", "Error AssetsUtil:", e);
                }
            }
        }
        return result;
    }
}