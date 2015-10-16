package com.charlie.encryptshow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.charlie.encryptshow.utils.EncryptUtil;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created
 * Author:Charlie Wei[]
 * Email:charlie_net@163.com
 * Date:2015/10/16
 */

/**
 * 对称加密
 */
public class SythEncryptActivity extends Activity {

    private EditText textContent;
    private EditText textPassword;
    private EditText textResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syth_encrypt_activity);

        textContent = (EditText) findViewById(R.id.txt_content);
        textPassword = (EditText) findViewById(R.id.txt_password);
        textResult = (EditText) findViewById(R.id.txt_result);

    }

    /**
     * DES加密
     * @param view
     */
    public void btnDESEncrypt(View view)  {

        String content = textContent.getText().toString();
        String password = textPassword.getText().toString();

        // 注意 加密解密 密钥都需要是字节数组
        byte[] keyData = password.getBytes();
        // 需要加密的内容
        byte[] contentData = content.getBytes();
        // DES 加密
        if (keyData.length == 8) {

            byte[] encryptData = EncryptUtil.desEncrypt(contentData, keyData);
            String str = Base64.encodeToString(encryptData,Base64.NO_WRAP);
            textResult.setText(str);

        }
    }

    /**
     * DES解密
     * @param view
     */
    public void btnDESDecrypt(View view) {
        String encryptedString = textResult.getText().toString();
        if (encryptedString.length() > 0){

            String password = textPassword.getText().toString();
            // 1 因为在加密方法中，使用Base64对加密的内容进行了编码，那么要解密的时候，要进行Base64的解码
            //
            byte[] encryptedData = Base64.decode(encryptedString, Base64.NO_WRAP);
            byte[] keyData = password.getBytes();

            // DES要求八个字节
            if (keyData.length == 8){

                // 形成原始数据
                byte[] orignData = EncryptUtil.desDecrypt(encryptedData,keyData);
                String string = new String(orignData);
                Toast.makeText(this,"加密数据为--" +string,Toast.LENGTH_SHORT).show();
            }
        }
    }
}