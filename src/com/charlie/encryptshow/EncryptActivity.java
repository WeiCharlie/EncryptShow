package com.charlie.encryptshow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class EncryptActivity extends Activity implements Runnable{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Base64 编码
        // 英文 I love Android  --SSBsb3ZlIEFuZHJvaWQh
        // 中文 I love 安卓 --SSBsb3ZlIOWuieWNkyE=
        // 中文 i love 安卓 --aSBsb3ZlIOWuieWNkyE=
        String str = "i love 安卓!";
        // 将字节数组参数，转换为Base64字符串，第二个参数是不进行换行处理
        // ！！！参数二采用什么内容，那么在解码时也要设置这样的内容
        String encoded = Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);

        Log.d("Base64"," 编码结果-->" + encoded);

        //  -------------------------------------------

        // Base64 解码
        // 使用直接针对String操作的解码方法
        byte[] decodeBytes = Base64.decode(encoded, Base64.NO_WRAP);
        String ns = new String(decodeBytes);
        Log.d("Base64","解码结果" + ns);

        // -------------------------
        // Base64 编码字节数组,

        byte[] data = new byte[]{0,0,0
                                ,0,0,0
                                ,0,0,0};
        encoded = Base64.encodeToString(data,Base64.NO_WRAP);
        Log.d("Base","data 0 = " + encoded);

        // ----------------------------------

        // 解码，还原字节数组
        byte[] d1 = Base64.decode(encoded, Base64.NO_WRAP);
        Log.d("Base64" ,"d1 = " + Arrays.toString(d1));

        // -------------------------
        // 开启网络 检查URLEncoding编码的设置
        Thread thread = new Thread(this);
        thread.start();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("abd");
            DataInputStream din = new DataInputStream(fis);

            din.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void run(){
        try {

            // 将变形金刚转换为URLEcoding处理的”%xx%xx。。。“
            String sWord = URLEncoder.encode("变形金刚", "utf-8");
            Log.d("URLEcoding","编码 = " + sWord);
            // --------------
            // URLEncoding 的解码
            String decode = URLDecoder.decode(sWord, "utf-8");
            Log.d("URLEncoding","解码 = " + decode);

            URL url = new URL("http://news.baidu.com/ns?word=" + sWord);
            Log.d("URL"," url = " + url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int code = conn.getResponseCode();
            Log.d("URLEndcoding","---> code = "+code);
            if (code == 200){
                // TODO 进行内容获取
                InputStream inputStream = conn.getInputStream();
                // TODO 检查网络数据是否经过GZIP的压缩，如果压缩，需要解压缩
                // 1 检测 Content-Encoding
                String encoding = conn.getContentEncoding();
                // 2 判断是否经过GZIP
                if("gzip".equals(encoding)){
                    inputStream = new GZIPInputStream(inputStream);

                }

                // inflater 解压缩的意思

            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 1 实现内容的压缩
     *   一层一层关流，
     * 2 解压缩
     *   如果输入流没有经过压缩，那么不允许使用GZIPInputStream，因为没有压缩会抛异常
     *
     */
}
