package com.tsmc.prequal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tsmc.prequal.dao.PreBatchStatusDao;
import com.tsmc.prequal.data.model.po.RawmatPreBatchStatus;
import com.tsmc.prequal.demo.dao.SalesDao;
import com.tsmc.prequal.demo.po.Sale;

@RequestMapping("/prequal")
@Controller
public class PreqUIContoller {
	
	@Autowired
	private PreBatchStatusDao preqDao;  

	@RequestMapping("/hello")
	public String hello(Model _model) {

		List<RawmatPreBatchStatus> listSale = preqDao.fineAll();

		_model.addAttribute("sayHello", String.format("hello, there're %s rows in the database.", listSale.size()));
		return "hello";
	}
}
