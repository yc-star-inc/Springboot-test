package com.tsmc.prequal.oldversion;

import java.util.ArrayList;
import java.util.List;

public class SpcKpiInspection {
	
	public void checkSpcKpi() {
		
		boolean isExpire = false;
		boolean isCaseFindLeading = false; 
		int intTimeDelay = 0; 
		
		checkAllResultByCaseId("fabName", "caseId", isCaseFindLeading, intTimeDelay,
				isExpire, "sConfirmLotList", "sCaseStatus", "sMatNo", "sLeadingLot", 
				"sStepId", "sStepIdList", "sToolIdList", "sPartId", "versionLabel",
				"sEndTime"); 
		
	}
	
	private void checkAllResultByCaseId(String fabName, String caseId, boolean isCaseFindLeading, int intTimeDelay,
			boolean isExpire, String sConfirmLotList, String sCaseStatus, String sMatNo, String sLeadingLot,
			String sStepId, String sStepIdList, String sToolIdList, String sPartId, String versionLabel,
			String sEndTime) {
		FindLeadingLotDao findLeadingLotDAO = new FindLeadingLotDao();

		boolean isInlineFinished = true, isOfflineFinished = true;

		List<MatMonitorResultVO> inlineMonitorList = findLeadingLotDAO.queryMonitorResult(fabName, caseId, Constant.LOOP_INLINE);
		List<MatMonitorResultVO> offlineMonitorList = findLeadingLotDAO.queryMonitorResult(fabName, caseId, Constant.LOOP_OFFLINE);

		boolean isAllCWInProcEnd = checkIFAllCWProdEnd(caseId);

		if (!inlineMonitorList.isEmpty()) {
			for (MatMonitorResultVO inlineMonitorResultRecVo : inlineMonitorList) {
				boolean chartFinished = true;

				List<SPCResultVO> inlineResultItemField = (inlineMonitorResultRecVo.getResultRaw() != null)
						? inlineMonitorResultRecVo.getResultRaw().getResult()
						: new ArrayList<SPCResultVO>();

				boolean chtWithData = false;

				for (SPCResultVO tmpRsltVo : inlineResultItemField) {
					if (tmpRsltVo.getRawData() == null || tmpRsltVo.getRawData().size() < 1) {
						
						//String spcChtName = String.format("");
						//Logger.info(String.format("1234"));
						continue; 
					}
					else {
						chtWithData = true; 
						int rawDataCount = tmpRsltVo.getRawData().size(); 
					}
				}
				if(!chtWithData) {
					isInlineFinished = false; 
					continue; 
				}

			}
		}

	}

	private boolean checkIFAllCWProdEnd(String caseId) {
		// TODO Auto-generated method stub
		return false;
	}

}
