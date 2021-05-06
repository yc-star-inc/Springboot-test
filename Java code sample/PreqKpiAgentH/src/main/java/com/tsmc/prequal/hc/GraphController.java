package com.tsmc.prequal.hc;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphController {

	@GetMapping("/barChart")
	public String barChart(Model model) {

		Map<String, Integer> data =  new LinkedHashMap<String, Integer>(); 
		data.put("Ashish", 30); 
		data.put("Ankit", 50);
		data.put("Gurpreet", 70);
		data.put("Mohit", 90);
		data.put("Monish", 25); 
		model.addAttribute("keySet", data.keySet()); 
		model.addAttribute("vlues", data.values()); 
		
		return "barChart"; 
	}
	
	@GetMapping("/pirChart")
	public String pirChart(Model model) {

		Map<String, Integer> data =  new LinkedHashMap<String, Integer>(); 
		data.put("Ashish", 30); 
		data.put("Ankit", 70);
		
		return "pirChart"; 
	}

}
