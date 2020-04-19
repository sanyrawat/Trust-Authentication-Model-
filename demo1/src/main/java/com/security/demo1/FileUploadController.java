// package com.security.demo1;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;


// @RestController("/file")
// public class FileUploadController {



// 	@GetMapping("/")
// 	public String listUploadedFiles() throws IOException {
// 		return "uploadForm";
// 	}


// 	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// 	public ResponseEntity <Object>uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
// 		File convertFile = new File("C:\\Users\\The Arav\\Downloads\\fileupload"+file.getOriginalFilename());
// 		convertFile.createNewFile();
// 		FileOutputStream fout = new FileOutputStream(convertFile);
// 		fout.write(file.getBytes());
// 		fout.close();
// 		return new ResponseEntity<>("File is Uploaded Successfully", HttpStatus.OK);
// 	}

// 	@ExceptionHandler(Exception.class)
// 	public ResponseEntity<?> handleStorageFileNotFound(Exception exc) {
// 		return ResponseEntity.notFound().build();
// 	}

// }