package com.tsmc.prequal.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.stereotype.Repository;

import com.tsmc.prequal.demo.po.Sale;

import java.util.List;
import java.util.Map;

@Repository
public class SalesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public SalesDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Sale> List() {
		
		String sqlString = "select * from ppmsdm.sales"; 
		List<Sale> listSale = jdbcTemplate.query(sqlString, BeanPropertyRowMapper.newInstance(Sale.class)); 
		
		return listSale; 
	}
		
	public void save(Sale sale) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate); 
		insertActor.withTableName("sales").usingColumns("item", "quantity", "amount"); 
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale); 
		insertActor.execute(param); 
		
	}
	
	public Sale get(int id) {
		String sql = "select * from ppmsdm.sales where id = ?"; 
		Object[] args = {id};
		
		//Sale sale = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Sale.class)); 
		Sale sale = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Sale.class), id); 
		
//		String testString = ""; 
//		try {
//			Map<String, Object> result
//			 = jdbcTemplate.queryForMap(sql, args, BeanPropertyRowMapper.newInstance(Sale.class) ); 
//			
//			testString = String.format("%s", result.keySet().size()); 
//		} catch (Exception e) {
//			// TODO: handle exception
//			testString = String.format("%s", e.getMessage()); 
//		}
		
		return sale;
	}
	
	public void update(Sale _sale) {
		String sql = "update ppmsdm.sales set item=:item, quantity=:quantity, amount=:amount where id=:id"; 
		 BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(_sale); 
		 
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate); 
		template.update(sql, param);		
		
	}
	
	public void deleteSale (int id) {
		String sql = "delete from ppmsdm.sales where id = ?"; 
		int delRslt = jdbcTemplate.update(sql, id); 
	}
	
	public void deleteSaleByItemName (String _item) {
		String sql = "delete from ppmsdm.sales where item= ?"; 
		int delRslt = jdbcTemplate.update(sql, _item); 
	}
}
