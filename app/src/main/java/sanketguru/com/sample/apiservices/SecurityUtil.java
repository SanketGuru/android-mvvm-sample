package sanketguru.com.sample.apiservices;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Sanket Gurav on 12/20/2017.
 */

public class SecurityUtil {
    private static String TAG = SecurityUtil.class.getName();

    private static String ZERO_PADDING_KEY = "8080808080808090";
    private static String IV = "8080808080808090";
    private static String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final byte[] keyValue = ZERO_PADDING_KEY.getBytes();
    private static final byte[] ivValue = IV.getBytes();

    private static final IvParameterSpec ivspec = new IvParameterSpec(ivValue);
    private static final SecretKeySpec keyspec = new SecretKeySpec(keyValue, "AES");

    public static String encrypt(String Data) {
        try {
            Cipher c = Cipher.getInstance(CIPHER_ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encVal = c.doFinal(Data.getBytes());
            String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
            encryptedValue = encryptedValue.replace("\n", "").replace("\r", "");
            return encryptedValue;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(String Data) {
        //Log.d(TAG, ">>decrypt("+Data+")");
        try {
            Data = Data.replace("\n", "").replace("\r", "").replace("Output", "").replace(":", "");
            byte[] decryptedValue = Base64.decode(Data, Base64.DEFAULT);
            Cipher c = Cipher.getInstance(CIPHER_ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            return (c.doFinal(decryptedValue));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
