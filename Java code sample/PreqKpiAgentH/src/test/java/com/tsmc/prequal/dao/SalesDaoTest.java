package com.tsmc.prequal.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tsmc.prequal.demo.dao.SalesDao;
import com.tsmc.prequal.demo.po.Sale;

class SalesDaoTest {

	private String DsString = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
	private String DsUserName = "ppmsappl"; 
	private String DsPassword = "ppmsappla"; 
	
	private SalesDao dao; 
	
	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
		dataSource.setUrl(this.DsString);
		dataSource.setUsername(this.DsUserName);
		dataSource.setPassword(this.DsPassword);
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		
		dao = new SalesDao(new JdbcTemplate(dataSource)); 
				
	}

	@Test
	void testList() {
		List<Sale> listSale = dao.List(); 
		
		assertFalse(listSale.isEmpty());
	}

	@Test
	void testSave() {
		Sale sale = new Sale("Cooler Fan", 1, 49.99f);
		dao.save(sale);
	}

	//@Test
	void testGet() {
		int id = 41; 
		Sale sale = dao.get(id) ;
		assertNotNull(sale);
	}
	
	@Test
	void testUpdate() {
		Sale sale = new Sale();
		sale.setId(81);
		sale.setItem("Fried Checken New");
		sale.setQuantity(4);
		sale.setAmount(20);
		
		dao.update(sale);
	}
	
	@Test
	void testDelete() {
		int id = 41; 
		dao.deleteSale(id);
	}

	@Test
	void testDeleteByItem() {
		String itemString = "Cooler Fan"; 
		dao.deleteSaleByItemName(itemString);
	}
}
