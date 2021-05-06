package com.tsmc.prequal.data.model.mapper;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.tsmc.prequal.data.model.po.RawmatMonitorResult;

public class RawmatMonitorResultMapper implements RowMapper<RawmatMonitorResult> {

	@Override
	public RawmatMonitorResult mapRow(ResultSet rs, int rowNum) throws SQLException {

		RawmatMonitorResult rsMonitorResult = new RawmatMonitorResult();

		rsMonitorResult.setFabName(rs.getNString("FAB_NAME"));
		rsMonitorResult.setJobId(rs.getInt("JOB_ID"));

		LobHandler lobHandler = new DefaultLobHandler();
		byte[] requestData = lobHandler.getBlobAsBytes(rs, "MONITOR_CRI");
		if (requestData != null && requestData.length > 0) {
			rsMonitorResult.setMonitorCriteria(new String(requestData, StandardCharsets.UTF_8));
		}
		// .setMonitorCriteria(new String[] { new String(requestData,
		// StandardCharsets.UTF_8) });

		rsMonitorResult.setLastCheckTime(rs.getTimestamp("LAST_CHK_DT"));

//				Blob blob = resultSet.getBlob("MONITOR_CRI");
//				byte[] bytes = blob.getBytes(1, (int) blob.length());

		/// TODO: Complete remaining mapper columns...

		return rsMonitorResult;
	}

}

//public class StudentMapper implements RowMapper<Student> {
//	   public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//	      Student student = new Student();
//	      student.setId(rs.getInt("id"));
//	      student.setName(rs.getString("name"));
//	      student.setAge(rs.getInt("age"));
//	      return student;
//	   }
//	}