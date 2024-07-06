package com.example.demo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

public class Utilitarios {

	public static String guardarImagen(MultipartFile foto) {
		try {
			Path pathDire = Paths.get("src/main/resources/static/foto_peliculas/");
			if(!Files.exists(pathDire)) {
				Files.createDirectories(pathDire);
			}
			
			byte[] bytes = foto.getBytes();
			Path path = Paths.get("src/main/resources/static/foto_peliculas/" + 
			foto.getOriginalFilename());
			
			Files.write(path, bytes);
			return foto.getOriginalFilename();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
		
		public static String extraerHash(String passwordInput) {
			return BCrypt.hashpw(passwordInput, BCrypt.gensalt());
		}
		
		
		public static boolean checkPassword(String passwordInput, String hashPassword) {
			return BCrypt.checkpw(passwordInput, hashPassword);
		}
}
