package com.tsmc.prequal.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileUtilTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetFileAsStringBuilder() {
		
		String appPropName = "application.properties";
		StringBuilder sb;
		try {
			sb = FileUtil.getFileAsStringBuilder(appPropName);
			assertTrue(sb.capacity() > 0);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
