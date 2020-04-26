package com.security.demo1;

import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.net.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;


class RestResponse {
    private int id;
    private String name;

    public RestResponse(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }
}

@RestController("/")
public class Request {
    KeyGenerator keyGenerator = null;
    SecretKey secretKey = null;
    Cipher cipher = null;

    public Request() {
        try {

            keyGenerator = KeyGenerator.getInstance("Blowfish");
            secretKey = keyGenerator.generateKey();
            cipher = Cipher.getInstance("Blowfish");
        } catch (NoSuchPaddingException ex) {
            System.out.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadFileFromLocal(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") String file,@RequestParam("private-key") Integer privateKey) throws Exception {
        String home = System.getProperty("user.home");
        String folderName = "C://Users//The Arav//Downloads//fileUpload//";//home + "/Documents/fileUpload/";
        File getFile = new File(folderName + file + ".encrypted");
        if (getFile.exists()) {
            File tempFile = new File(folderName + file);
            if (!tempFile.exists())
                tempFile.createNewFile();
            TestFileEncryption.decrypt(getFile, tempFile,privateKey);
            Files.copy(Paths.get(tempFile.getAbsolutePath()), response.getOutputStream());
            response.getOutputStream().flush();
            if (tempFile.exists()) {
                tempFile.delete();
                
            }
        } else {
            throw new FileNotFoundException("file not found");
        }
    }


    @PostMapping("/getrequest")
    public ResponseEntity<RestResponse> postMethodName(@RequestBody RestResponse entity) {
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file,@RequestHeader("private-key") Integer privateKey) throws Exception {

        String home = System.getProperty("user.home");
        //String folderName = home + "/Documents/fileUpload/";
        String folderName = "C://Users//The Arav//Downloads//fileUpload//";
        File convertFile = new File(folderName + file.getOriginalFilename() + ".encrypted");
        if (!convertFile.exists())
            convertFile.createNewFile();
        TestFileEncryption.encrypt(file.getBytes(), convertFile, privateKey);
        return new ResponseEntity<>("File is Uploaded Successfully", HttpStatus.OK);
    }


    public byte[] encryptText(String plainText) {
        byte[] cipherBytes = null;
        try {

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainBytes = plainText.getBytes();

            cipherBytes = cipher.doFinal(plainBytes);
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            System.out.println(ex);
        }

        return cipherBytes;
    }

    public String decryptText(byte[] cipherBytes) {
        String plainText = null;
        try {

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] plainBytes = cipher.doFinal(cipherBytes);

            plainText = new String(plainBytes);
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            System.out.println(ex);
        }

        return plainText;
    }

    public String encrypt(String plainText) {
        String cipherText = null;
        byte[] cipherBytes = encryptText(plainText);
        cipherText = bytesToString(cipherBytes);
        return cipherText;
    }

    public String decrypt(String cipherText) {
        String plainText = null;
        byte[] cipherBytes = stringToBytes(cipherText);
        plainText = decryptText(cipherBytes);
        return plainText;
    }

    private String bytesToString(byte[] rawText) {
        String plainText = null;
        plainText = Base64.encode(rawText);
        return plainText;
    }

    private byte[] stringToBytes(String plainText) {
        byte[] rawText = null;
        try {
            rawText = Base64.decode(plainText);
        } catch (Base64DecodingException ex) {
            System.out.println(ex);
        }
        return rawText;
    }
}