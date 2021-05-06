package com.tsmc.prequal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

import com.tsmc.prequal.data.model.mapper.RawmatMonitorResultMapper;
import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import com.tsmc.prequal.data.model.po.RawmatPreBatchStatus;
import com.tsmc.prequal.demo.po.Sale;
import com.tsmc.prequal.utils.DateUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.sql.DataSource;

@Repository
public class PreqMonitorResultDao {

	private Logger LOG = LoggerFactory.getLogger(PreqMonitorResultDao.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource ecpDataSource;

//	@Autowired
//	private NamedParameterJdbcTemplate namedJdbcTemplate;

	private String tableNameString = "ppmsdm.rawmat_monitor_result";

	private String findAllSqlString = String.format("select * from %s", tableNameString);

	public PreqMonitorResultDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insertMonitorResult(RawmatMonitorResult _monRsltObj) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(ecpDataSource).withTableName(tableNameString)
				.usingColumns("CASE_ID", "FAB_NAME", "MAIN_CRTRN", "SUB_CRTRN", "MEAS_DATA_TYPE", "MONITOR_CRI",
						"LAST_CHK_DT");

//		String sql = "INSERT INTO Contact (name, email, address) VALUES (:name, :email, :address)";
//		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(_monRsltObj);
//		template.update(sql, paramSource);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CASE_ID", _monRsltObj.getCaseId());

		parameters.put("FAB_NAME", _monRsltObj.getFabName());
		parameters.put("MAIN_CRTRN", _monRsltObj.getMainCriteria());
		parameters.put("SUB_CRTRN", _monRsltObj.getSubCriteria());
		parameters.put("MEAS_DATA_TYPE", _monRsltObj.getMeasDataType());

		byte[] monCriBytes = _monRsltObj.getMonitorCriteria().getBytes(StandardCharsets.UTF_8);
		parameters.put("MONITOR_CRI", monCriBytes);

		parameters.put("LAST_CHK_DT", (new java.sql.Date(new java.util.Date().getTime())));

		int result = simpleJdbcInsert.execute(parameters);
		return result;
	}
//    MapSqlParameterSource in = new MapSqlParameterSource();
//    in.addValue("CASE_ID", _monRsltObj.getCaseId());
//    in.addValue("MONITOR_CRI",  new SqlLobValue(new ByteArrayInputStream(monCriBytes), 
//  		  monCriBytes.length, new DefaultLobHandler()), Types.BLOB);
//
//    String SQL = "update Student set image = :image where id = :id";
//
//    NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
//    jdbcTemplateObject.update(SQL, in);
//    System.out.println("Updated Record with ID = " + id );

//	public void test(RawmatMonitorResult _monRsltObj) {
//		
//		String cirJsonString = ""; 
//		Stream blobIs = new Stream(_monRsltObj.getMonitorCriteria()); 
//		final File clobIn = new File("large.txt");
//		final InputStream clobIs = new FileInputStream(clobIn);
//		final InputStreamReader clobReader = new InputStreamReader(clobIs);
//		jdbcTemplate.execute(
//		  "INSERT INTO ppmsdm.rawmat_monitor_result (, a_clob, a_blob) VALUES (?, ?, ?)",
//		  new AbstractLobCreatingPreparedStatementCallback(lobHandler) {                                                       (1)
//		      protected void setValues(PreparedStatement ps, LobCreator lobCreator) 
//		          throws SQLException {
//		        ps.setLong(1, 1L);
//		        lobCreator.setClobAsCharacterStream(ps, 2, clobReader, (int)clobIn.length());                                  (2)
//		        lobCreator.setBlobAsBinaryStream(ps, 3, blobIs, (int)blobIn.length());                                         (3)
//		      }
//		  }
//		);
//		blobIs.close();
//		clobReader.close();
//		
//	}

	public List<RawmatMonitorResult> findAll() {

		List<RawmatMonitorResult> batchStatusLst = jdbcTemplate.query(findAllSqlString,
				(new RawmatMonitorResultMapper()));

		return batchStatusLst;
	}

	public List<RawmatMonitorResult> findTopNJobsByStatusAndDataType(List<Integer> _jobStatus, List<String> _measDataType, int _topN) {

		List<String> topNCaseIdList = this.findDistTopNCaseIds(_jobStatus, _measDataType, _topN); 
		
		String whereClause = String.format(" t where t.case_id in (:caseIds)"); 
		String sqlString = String.format("%s %s", findAllSqlString, whereClause);		

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("caseIds", topNCaseIdList);

		List<RawmatMonitorResult> monitorJobList = 
				jdbcTemplate.query(sqlString, parameters, (new RawmatMonitorResultMapper()));

		return monitorJobList ;
	}

	// List<RawmatMonitorResult> batchStatusLst = jdbcTemplate.query(sqlString,
	// PreparedStatementSetter, (new RawmatMonitorResultMapper()));

//	SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("jobStatus", _jobStatus);
//	return namedParameterJdbcTemplate.queryForObject(
//	  "SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = :id", namedParameters, String.class);

//	@SuppressWarnings("unchecked")
//	List<RawmatMonitorResult> batchStatusLst 
//		= (List<RawmatMonitorResult>) namedJdbcTemplate.queryForObject(sqlString, namedParameters, (new RawmatMonitorResultMapper()));
	
	public List<String> findDistTopNCaseIds(List<Integer> _jobStatus, List<String> _measDataType, int _topN) {
		
		if( _topN < 1) _topN = 3;

		String mainSqlString = String.format("select t.case_id, t.last_chk_dt from %s t", tableNameString);
		String whereClause = String.format("where MEAS_DATA_TYPE in (:measDataType)"
				+ " and job_status in (:jobStatus)"
				+ " order by last_chk_dt  fetch first %s rows only", 
				_topN * 10);
		
		String sqlString = String.format("select distinct case_id from (%s %s) fetch first %s rows only",mainSqlString, whereClause,  _topN);
		
//	    select distinct case_id from (
//	    	select  /*+ index(PPMSDM.RAWMAT_MONITOR_RESULT IDX_2) */t.case_id, t.last_chk_dt from PPMSDM.RAWMAT_MONITOR_RESULT t 
//	    	 where  t.job_status in (0) and t.MEAS_DATA_TYPE in ('FAC')
//	    	 order by t.last_chk_dt desc
//	    	 fetch first 20 rows only
//		)
//	    fetch first 2 rows only

		SqlParameterSource parameters = 
				new MapSqlParameterSource().addValue("jobStatus", _jobStatus).addValue("measDataType", _measDataType);

		List<String> batchStatusLst = jdbcTemplate.query(sqlString, parameters, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				LOG.info(String.format("%s -> %s", rowNum, rs.getString("CASE_ID")));
				return rs.getNString("CASE_ID");
			}
		});

		return batchStatusLst;
	}

//	public void save(Sale sale) {
//		SimpleJdbcInsert insertActor = new SimpleJdbcInsert((DataSource) jdbcTemplate);
//		insertActor.withTableName(tableNameString).usingColumns("item", "quantity", "amount");
//
//		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
//		insertActor.execute(param);
//	}

	public List<RawmatMonitorResult> getAll(List<Integer> _caseIdList) {
		//String sql = String.format("select * from %s t where t.case_id = :caseId", tableNameString);
		
		String whereClause = String.format(" t where t.case_id in (:caseIds)"); 
		String sqlString = String.format("%s %s", findAllSqlString, whereClause);		

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("caseId", _caseIdList);

		List<RawmatMonitorResult> sale = jdbcTemplate.query(sqlString, parameters,
				BeanPropertyRowMapper.newInstance(RawmatMonitorResult.class));

		return sale;
	}
	
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RawmatMonitorResult> findAllByCaseId(int id) {

		String sqlString = String.format("select * from %s t where t.case_id = :caseId", tableNameString);

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("caseId", id);

		List<RawmatMonitorResult> sale = jdbcTemplate.query(sqlString, parameters,
				BeanPropertyRowMapper.newInstance(RawmatMonitorResult.class)); 
				//new BeanPropertyRowMapper(RawmatMonitorResult.class));

		return sale;

	}
	
	

	public int update(RawmatMonitorResult _updRecord) {
		// RawmatMonitorResult
		String sql = String.format("update %s"
				+ " set monitor_cri=rawtohex(:monitorCriteria)" + ", last_chk_dt=sysdate"
				+ " where job_id=:jobId", tableNameString);
		// "update ppmsdm.sales set item=:item, quantity=:quantity, amount=:amount where
		// id=:id";
		// BeanPropertySqlParameterSource param = new
		// BeanPropertySqlParameterSource(_updRecord);

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("monitorCriteria", _updRecord.getMonitorCriteria())
				.addValue("jobId", _updRecord.getJobId());

		// NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		int rowAffected = jdbcTemplate.update(sql, namedParameters);
		return rowAffected;
	}

	public void deleteSale(int id) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);

		String sql = "delete from ppmsdm.sales where id = :id";
		int delRslt = jdbcTemplate.update(sql, parameters);
	}

	public void deleteSaleByItemName(String _item) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("item", _item);

		String sql = "delete from ppmsdm.sales where item= :item";
		int delRslt = jdbcTemplate.update(sql, parameters);
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RawmatMonitorResult> findAllByCaseIdAndDataType(Long _caseId, String _measDataType) {

		String sql = String.format("select * from %s t where t.case_id = :caseId and t.meas_data_type=:measDataType", tableNameString);

		SqlParameterSource parameters 
				= new MapSqlParameterSource().addValue("caseId", _caseId).addValue("measDataType", _measDataType);

		List<RawmatMonitorResult> rsltList = jdbcTemplate.query(sql, parameters,
				new BeanPropertyRowMapper(RawmatMonitorResult.class));

		return rsltList;
	}
}
