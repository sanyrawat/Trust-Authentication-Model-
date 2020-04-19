package com.security.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TestFileEncryption {

    private static final String ALGORITHM = "Blowfish";
    private static String keyString = "DesireSecretKey";

    public static void encrypt(byte[] inputFile, File outputFile)
            throws Exception {
        doEncrypto( inputFile, outputFile);
    }

    public static void decrypt(File inputFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
    }

    private static void doEncrypto(byte [] inputBytes,
                                   File outputFile) throws Exception {

        Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);


        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);
        outputStream.close();

    }

    private static void doCrypto(int cipherMode, File inputFile,
                                 File outputFile) throws Exception {

        Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipherMode, secretKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

    }

//    public static void main(String[] args) {
//
//        File inputFile = new File("C:/temp/File.docx");
//        File encryptedFile = new File("C:/temp/file.encrypted");
//
//        File decryptedFile = new File("C:/temp/DecryptedFile.docx");
//
//        try {
//            TestFileEncryption.encrypt(inputFile, encryptedFile);
//            TestFileEncryption.decrypt(encryptedFile, decryptedFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
