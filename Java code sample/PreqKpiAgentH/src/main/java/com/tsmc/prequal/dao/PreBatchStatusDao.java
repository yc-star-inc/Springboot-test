package com.tsmc.prequal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tsmc.prequal.data.model.po.RawmatPreBatchStatus;

import java.util.List;

@Repository
public class PreBatchStatusDao {

	private Logger LOG = LoggerFactory.getLogger(PreqMonitorResultDao.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

//	@Autowired
//	private JdbcTemplate jdbcTemplate; 

	private String tableNameString = "ppmsdm.rawmat_pre_batch_status";

	public PreBatchStatusDao(NamedParameterJdbcTemplate _jdbcTemplate) {
		this.jdbcTemplate = _jdbcTemplate;
	}

	public List<RawmatPreBatchStatus> fineAll() {

		String sqlString = String.format("select * from %s", tableNameString);
		List<RawmatPreBatchStatus> batchStatusLst = jdbcTemplate.query(sqlString,
				BeanPropertyRowMapper.newInstance(RawmatPreBatchStatus.class));

		return batchStatusLst;
	}
	
	public List<RawmatPreBatchStatus> fineAllByCaseIds(List<String> _caseIdList) {

		SqlParameterSource parameters = new MapSqlParameterSource("caseId", _caseIdList);
		
		String sqlString = String.format("select * from %s where t.case_id = :caseId", tableNameString);
		List<RawmatPreBatchStatus> batchStatusLst = jdbcTemplate.query(sqlString, parameters, 
				BeanPropertyRowMapper.newInstance(RawmatPreBatchStatus.class)); 
				//BeanPropertyRowMapper.newInstance(RawmatPreBatchStatus.class));

		return batchStatusLst;
	}


//	public void save(Sale sale) {
//		SimpleJdbcInsert insertActor = new SimpleJdbcInsert((DataSource) jdbcTemplate);
//		insertActor.withTableName(tableNameString).usingColumns("item", "quantity", "amount");
//
//		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
//		insertActor.execute(param);
//	}

	public RawmatPreBatchStatus get(int id) {
		String sql = String.format("select * from %s t where t.case_id = :caseId", tableNameString);

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("caseId", id);

		RawmatPreBatchStatus preqBatchCase = jdbcTemplate.queryForObject(sql, parameters,
				BeanPropertyRowMapper.newInstance(RawmatPreBatchStatus.class));

		return preqBatchCase;
	}

	public int update(RawmatPreBatchStatus _updRecord) {

		String sql = String.format(
				"update %s " + " set case_status=:caseStatus" + ", last_chk_dt=sysdate" + " where case_id=:caseId",
				tableNameString);

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("caseStatus", _updRecord.getCaseStatus()).addValue("caseId", _updRecord.getCaseId());

		int rowAffected = jdbcTemplate.update(sql, namedParameters);
		return rowAffected;
	}

	public void deleteSale(int id) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);

		String sql = "delete from ppmsdm.sales where id = :id";
		int delRslt = jdbcTemplate.update(sql, parameters);
	}

}
