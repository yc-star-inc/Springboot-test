package com.tsmc.prequal.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.util.ResourceUtils;

public class FileUtil {
	
	private static String FilePathTemplate = "%s"; 
	/// "classpath:%s": \\target\\classes\\{file-name} 
	
	public static byte[] getFileAsByteArray(String _fileName) throws FileNotFoundException, IOException, Exception {

		//String _fileName = "classpath:config/sample.txt"; 
		//File file = ResourceUtils.getFile(String.format(FilePathTemplate, _fileName));
		File file = new File(FileUtil.class.getClassLoader().getResource(_fileName).getPath()); 
		
		if (!file.exists()) {
			return null;  
		} else {
			// Read File Content
			return Files.readAllBytes(file.toPath());
			// String content = new String(Files.readAllBytes(file.toPath()));
		}
	}
	
	public static String getFileAsSring(String _fileName) throws IOException {

		ClassLoader classLoader = FileUtil.class.getClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(_fileName)) {
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw e; 
		}
	}
	public static String readFromTextFile(String _fileName) throws IOException {

		File file = new File(FileUtil.class.getClassLoader().getResource(_fileName).getPath());
		String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		return data;
	}
	public static InputStream getFileAsStream(String _fileName) throws IOException {
		
		//String _fileName = "classpath:config/sample.txt"; 
		String fileName = String.format(FilePathTemplate, _fileName);

		try (InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName)) {
//			String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//			System.out.println(result);
			return inputStream; 

		} catch (IOException e) {
			throw e; 
		}
	}
	public static StringBuilder getFileAsStringBuilder(String _fileName) throws IOException {
		
		StringBuilder rtnSb = new StringBuilder(); 
		String filePath = FileUtil.class.getClassLoader().getResource(_fileName).getPath(); 
		
	    LineIterator lineIterator = FileUtils.lineIterator(new File(filePath));
	    while (lineIterator.hasNext()) {
	    	rtnSb.append(lineIterator.nextLine().concat("\r\n"));
	    }
	    return rtnSb; 
		
	}



	public static StringBuilder getFreeSpace() throws IOException {

		StringBuilder sb = new StringBuilder();
		for (FileStore fileStore : FileSystems.getDefault().getFileStores()) {

			long sizeInGB = fileStore.getUsableSpace() / (1024 * 1024);
			sb.append(String.format("fileStore:%s, freeSpace: %s GB\\r\\n", fileStore.name(), sizeInGB));
		}
		return sb;
	}

	public static StringBuilder getFreeSpace(String _fileName) throws IOException {

		StringBuilder sb = new StringBuilder();

		Path path = Paths.get(FileUtil.class.getClassLoader().getResource(_fileName).getPath());
		FileStore fileStore = Files.getFileStore(path);
		long sizeInGB = fileStore.getUsableSpace() / (1024 * 1024);

		sb.append(String.format("fileStore:%s, freeSpace: %s GB\r\n", fileStore.name(), sizeInGB));
		return sb;
	}

	/// No used !!
//	public static void doTest(String _fileName) throws IOException, Exception {
//		File file = FileUtils.getFile(FileUtil.class.getClassLoader().getResource(_fileName).getPath());
//		File tempDir = FileUtils.getTempDirectory();
//		FileUtils.copyFileToDirectory(file, tempDir);
//		File newTempFile = FileUtils.getFile(tempDir, file.getName());
//		String data = FileUtils.readFileToString(newTempFile, Charset.defaultCharset());
//	}
}
