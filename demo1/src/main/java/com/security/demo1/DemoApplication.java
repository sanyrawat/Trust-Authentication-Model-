package com.security.demo1;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
public class DemoApplication {
/*
	@GetMapping(value="/hello")
	public String getMethodName()  {
		return "Java Asignment ";
		
	}*/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @RequestMapping(value="/upload", method=RequestMethod.POST)
	// public ResponseEntity<Object>uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
	// 	File convertFile = new File("C:\\Users\\The Arav\\Downloads\\fileupload" + file.getOriginalFilename());
	// 	convertFile.createNewFile();
	// 	FileOutputStream fout = new FileOutputStream(convertFile);
	// 	fout.write(file.getBytes());
	// 	fout.close();
	// 	return new ResponseEntity<>("File is Uploaded Successfully", HttpStatus.OK);
	// }

}
