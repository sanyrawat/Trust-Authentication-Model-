package com.security.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.omg.CORBA.portable.ApplicationException;

public class TestFileEncryption {

    private static final String ALGORITHM = "Blowfish";
    private static int P = 23;
    private static int G = 9;

    // private static int user_b=3;

    public static void encrypt(byte[] inputFile, File outputFile, Integer privateKey) throws Exception {
        doEncrypto(inputFile, outputFile, privateKey);
    }

    public static void decrypt(File inputFile, File outputFile, Integer privateKey) throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile, privateKey);
    }

    private static void doEncrypto(byte[] inputBytes, File outputFile, Integer user_a) throws Exception {
        long keyString = (int) (Math.pow(G, user_a)) % P;

        Key secretKey = new SecretKeySpec(Long.toString(keyString).getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);
        outputStream.close();

    }

    private static void doCrypto(int cipherMode, File inputFile, File outputFile, Integer user_a)
            throws Exception {
        try {
            long keyString = (int) (Math.pow(G, user_a)) % P;

            Key secretKey = new SecretKeySpec(Long.toString(keyString).getBytes(), ALGORITHM);
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
        } catch (Exception e) {
            // throw new MyException();
            // System.out.println(e.getMessage());
            throw new Exception("Invalid Secret Key");
        }


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
