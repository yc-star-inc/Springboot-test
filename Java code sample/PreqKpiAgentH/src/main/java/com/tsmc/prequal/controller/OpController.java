package com.tsmc.prequal.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.tsmc.prequal.utils.FileUtil;

@RestController
@RequestMapping("/op")
public class OpController {

	final static Logger logger = LoggerFactory.getLogger(OpController.class);
	
	@GetMapping(value = "/getPath")
	// public StringBuilder getServicePath(@RequestParam(value = "name",
	// defaultValue = "World") String name)
	public Map<String, String> getServicePath() {
		Map<String, String> respResult = new HashMap<String, String>();

		String appPropPath = this.getClass().getClassLoader().getResource("application.properties").getPath();

		respResult.put("application.path", appPropPath);
		// "application.path":"/E:/Projects/workspace-sts4-4.9.0/PreqKpiAgentH/target/classes/application.properties"

		respResult.put("application.base", FilenameUtils.getBaseName(appPropPath));
		
		respResult.put("temp.path", FileUtils.getTempDirectory().getPath());

//		String fullPath = FilenameUtils.getFullPath(path);
//		String extension = FilenameUtils.getExtension(path);

		return respResult;
	}

	@GetMapping(value = "/getDisk")
	// public StringBuilder getServicePath(@RequestParam(value = "name",
	// defaultValue = "World") String name)
	public StringBuilder getFreeSpace() throws IOException {
		
		StringBuilder rtnSb = FileUtil.getFreeSpace(); 
		logger.info(String.format("[%s] Available Free Space, \n%s", LocalDateTime.now(), rtnSb.toString()));
		
		return rtnSb;
	}
	
	@GetMapping(value = "/getFile")
	public StringBuilder getFile(
			@RequestParam(value = "name", defaultValue = "application.properties") String _appPropName) throws IOException {

		return FileUtil.getFileAsStringBuilder(_appPropName); 
	}

	@GetMapping(value = "/getFileAsStreamData")
	public ResponseEntity<StreamingResponseBody> streamData(
			@RequestParam(value = "name", defaultValue = "application.properties") String _appPropName) {

		StreamingResponseBody responseBody = response -> {

			try (BufferedReader br = new BufferedReader(
					new FileReader(this.getClass().getClassLoader().getResource(_appPropName).getPath(), StandardCharsets.UTF_8))) {

				String line;
				while ((line = br.readLine()) != null) {
					response.write((line.concat("\n")).getBytes(StandardCharsets.UTF_8));
					response.flush();
					Thread.sleep(1);
				}

			} catch (IOException e) {
				throw e; 
			} catch (InterruptedException e) {
				
				e.printStackTrace(); // TODO Auto-generated catch block
				return; 
			}

		};
		return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(responseBody);
	}

}
