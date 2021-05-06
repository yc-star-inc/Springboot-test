package com.tsmc.prequal.service.dto;

import java.util.List;

import com.tsmc.prequal.data.model.dto.MatchingResultVo;
import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import com.tsmc.prequal.utils.KpiDataSubjectEnum;

public interface IKpiJobAction {
	

	public KpiCheckResultVo doKpiCheck(KpiDataSubjectEnum dataSubject, RawmatMonitorResult curMonJob);

	public KpiCheckResultVo getKpiCriteria(KpiDataSubjectEnum dataSubject, RawmatMonitorResult curMonJob); 
	
	
}
