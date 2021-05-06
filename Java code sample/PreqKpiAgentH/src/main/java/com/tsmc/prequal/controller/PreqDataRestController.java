package com.tsmc.prequal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.tsmc.prequal.dao.PreBatchStatusDao;
import com.tsmc.prequal.dao.PreqMonitorResultDao;
import com.tsmc.prequal.dao.PreqMonitorResultDao;
import com.tsmc.prequal.data.model.dto.ResultPathVo;
import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import com.tsmc.prequal.data.model.po.RawmatPreBatchStatus;
import com.tsmc.prequal.demo.po.Greeting;

@RestController
public class PreqDataRestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private PreBatchStatusDao preqCaseDao; 
	
	@Autowired
	private PreqMonitorResultDao preqNamedMonRsltDao; 
	
	@GetMapping("/getCaseList")
	public List<RawmatPreBatchStatus> getCaseList() // @RequestParam(value = "name", defaultValue = "World") String name 
	{
		List<RawmatPreBatchStatus> caseList = preqCaseDao.fineAll(); 
		
		return caseList;
	}
	
	@GetMapping("/getMonRsltList")
	public List<RawmatMonitorResult> getMonRsltList() // @RequestParam(value = "name", defaultValue = "World") String name 
	{
		List<RawmatMonitorResult> caseList = preqNamedMonRsltDao.findAll(); 
		
		return caseList;
	}
	
	@GetMapping("/getMonRsltListByStatus")
	public List<RawmatMonitorResult> getMonRsltListByStatus() // @RequestParam(value = "name", defaultValue = "World") String name 
	{
		List<Integer> selJobStatus = Arrays.asList(0, 3); 
		List<String> targetDataTypes = Arrays.asList("FAC"); 
		List<RawmatMonitorResult> caseList = preqNamedMonRsltDao.findTopNJobsByStatusAndDataType(selJobStatus, targetDataTypes, 5); 
		return caseList;
	}
		
	
	@GetMapping("/updMonRsltList")
	public List<RawmatMonitorResult> updMonRsltList(@RequestParam(value = "fabName", defaultValue = "FAB14") String _fabName ) // @RequestParam(value = "name", defaultValue = "World") String name 
	{
		List<RawmatMonitorResult> caseList = preqNamedMonRsltDao.findAll(); 
		
		RawmatMonitorResult _curMonJob = null; 
		for(RawmatMonitorResult tmpMonJob : caseList )
		{
			if (tmpMonJob.getJobId() == 1)
			{
				_curMonJob = tmpMonJob; 
				break;
			}
		}
		if(_curMonJob != null)
		{	
			_curMonJob.setMonitorCriteria(_fabName);
			//_curMonJob.setMonitorCriteria(_fabName.split(""));
			preqNamedMonRsltDao.update(_curMonJob);
		}
		caseList = preqNamedMonRsltDao.findAll();
		
		return caseList;
	}
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
		return String.format(template, name);
	}
	
	@GetMapping("/getResultPath")
	public ResultPathVo getResultPath(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
		List<String> facTagList = new ArrayList<String>(); 
		facTagList.add("123"); 
		String facTagString = "1234"; 
		
		String imgSaoId = ""; 
		String imgUrl = ""; 
		String rawDataSaoId = ""; 
		String rawDataStartDate = ""; 
		String rawDataEndDate = ""; 
		
		String targetStartDate = ""; 
		String targetEndDate = "";
		
		Map<String, String> criteria = new HashMap<String, String>(); 
		criteria.put("facTag", facTagString); 
		
		return new ResultPathVo(imgSaoId, imgUrl, rawDataSaoId, rawDataStartDate, rawDataEndDate, targetStartDate, targetEndDate, criteria);
		// https://spring.io/guides/gs/rest-service/
	}
	
	
}
