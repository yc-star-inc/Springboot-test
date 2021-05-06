package com.tsmc.prequal.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tsmc.prequal.demo.dao.SalesDao;
import com.tsmc.prequal.demo.po.Sale;

@Controller
public class SalesController {

	@Autowired
	private SalesDao dao;

	@RequestMapping("/demo")
	public String viewHomePage(Model _model) {

		List<Sale> listSale = dao.List();
		_model.addAttribute("listSale", listSale);

		return "index";
	}

	@RequestMapping("/new")
	public String showNewForm(Model _model) {

		Sale salePO = new Sale();
		_model.addAttribute("sale", salePO);

		return "new_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("sale") Sale sale) {

		dao.save(sale);

		return "redirect:/";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView shoeEditForm(@PathVariable(name = "id") int id) {

		ModelAndView mav = new ModelAndView("edit_form");

		Sale sale = dao.get(id);
		mav.addObject("sale", sale);

		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("sale") Sale sale) {
		dao.update(sale);
		return "redirect:/";
	}

	@RequestMapping(value = "/deleteSale/{id}")
	public String delete(@PathVariable(name = "id") int id) {

		dao.deleteSale(id);

		return "redirect:/";
	}

	@RequestMapping("/hello")
	public String hello(Model _model) {

		List<Sale> listSale = dao.List();

		_model.addAttribute("sayHello", String.format("hello, there're %s rows in the database.", listSale.size()));
		return "hello";
	}
}
