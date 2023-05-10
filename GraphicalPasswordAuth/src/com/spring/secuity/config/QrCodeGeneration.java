package com.spring.secuity.config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import sun.misc.BASE64Encoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import de.taimos.totp.TOTP;

public class QrCodeGeneration {
	
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    /**
     * Obtain the correct 6-bit number according to the 32-bit random code
     *
     * @param secretKey
     * @return
     */
    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }


    /**
     * Generate binding QR code (string)
     *
     * @param account   Account information (displayed in Google Authenticator App)
     * @param secretKey secret key
     * @param title     Title (displayed in Google Authenticator App)
     * @return
     */
    public static String spawnScanQRString(String account, String secretKey, String title) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(title + ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(title, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Generate QR code (file) [return base64 of the picture, and output to the file synchronously if the output path is specified]
     *
     * @param barCodeData QR code string information
     * @param outPath     Output address
     * @param height
     * @param width
     * @throws WriterException
     * @throws IOException
     */
    public static String createQRCode(String barCodeData, String outPath, int height, int width)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
                width, height);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);

        ByteArrayOutputStream bof = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", bof);
        String base64 = imageToBase64(bof.toByteArray());
        if(outPath!=null&&!outPath.equals("")) {
            try (FileOutputStream out = new FileOutputStream(outPath)) {
                MatrixToImageWriter.writeToStream(matrix, "png", out);
            }
        }
        return base64;
    }

    /**
     * Convert the picture file to base64 string, and the parameter is the path of the picture
     *
     * @param dataBytes
     * @return java.lang.String
     */
    private static String imageToBase64(byte[] dataBytes) {
        // Encode byte array Base64
        BASE64Encoder encoder = new BASE64Encoder();
        if (dataBytes != null) {
            return "data:image/jpeg;base64," + encoder.encode(dataBytes);// Returns a Base64 encoded byte array string
        }
        return null;
    }
	
}
