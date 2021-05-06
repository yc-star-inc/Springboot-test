package com.tsmc.prequal.data.model.mapper;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import com.tsmc.prequal.data.model.po.RawmatPreBatchStatus;

public class RawmatPreBatchStatusMapper implements RowMapper<RawmatPreBatchStatus> {

	@Override
	public RawmatPreBatchStatus mapRow(ResultSet rs, int rowNum) throws SQLException {

		RawmatPreBatchStatus target = new RawmatPreBatchStatus();

		target.setFabName(rs.getNString("FAB_NAME"));
		target.setCaseId(rs.getInt("CASE_ID"));


		// .setMonitorCriteria(new String[] { new String(requestData,
		// StandardCharsets.UTF_8) });

		target.setLastCheckTime(rs.getTimestamp("LAST_CHK_DT"));

//				Blob blob = resultSet.getBlob("MONITOR_CRI");
//				byte[] bytes = blob.getBytes(1, (int) blob.length());

		/// TODO: Complete remaining mapper columns...

		return target;
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