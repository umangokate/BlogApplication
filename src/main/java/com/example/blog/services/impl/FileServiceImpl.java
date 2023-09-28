package com.example.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//Get File name
		String name = file.getOriginalFilename();
		
		String random = UUID.randomUUID().toString();
		String fileName = random.concat(name.substring(name.lastIndexOf(".")));
		
		//FULL path
		String filePath = path+File.separator+fileName;
		
		File f = new File(path);
		
		if(!f.exists())
		{
			f.mkdir();
		}
		
		//file copy
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullpath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullpath);
		return is;
	}

}
