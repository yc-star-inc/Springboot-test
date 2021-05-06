package com.tsmc.prequal.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsmc.prequal.data.model.dto.MonitorCriteriaVo;
import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import org.apache.commons.lang3.StringUtils; 

public class StringUtil {

	public static String DateFormatString = "yyyy/MM/dd HH:mm:ss";
//	public static String readFromInputStream(InputStream _inputStream) throws IOException {
//
//		StringBuilder resultStringBuilder = new StringBuilder();
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(_inputStream))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				resultStringBuilder.append(line).append("\n");
//			}
//		}
//		return resultStringBuilder.toString();
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object readFromJson(String _jsonString, Class _targetClass ) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Object rtnObj = objectMapper.readValue(_jsonString, _targetClass);
		return rtnObj;
	}
	
	public static String writeAsJson(Object _curObj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(DateUtils.DateTimeFormat);
		return objectMapper.writeValueAsString(_curObj);
	}
	
	public static String getFromInputStream(InputStream _InputStream) throws IOException, Exception {
		return IOUtils.toString(_InputStream, StandardCharsets.UTF_8);
	}
	

	/// Using a function converting an hexa string to byte[], you could do
	/// byte[] CDRIVES = hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d");
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

}
