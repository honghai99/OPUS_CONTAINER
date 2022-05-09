package com.clt.apps.opus.dou.doutraining.codemgmt.integration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.codemgmt.event.DouTrn0002Event;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;



public class CodeMgmtDBDAO  extends DBDAOSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBRowSet searchAPPCodeList(Event e) throws DAOException {
		DBRowSet dbRowset = null;
		DouTrn0002Event event = (DouTrn0002Event) e;
		//Inquiry conditions
		String subsystem = event.getCodeMgmtCondVO().getSubsystem().toUpperCase(); 
		String searchCdTp = event.getCodeMgmtCondVO().getSearchCdTp();
		String searchtype = event.getCodeMgmtCondVO().getSearchtype();
		String codeVal = event.getCodeMgmtCondVO().getCodeVal().toUpperCase(); 
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subsystem", subsystem);
		param.put("codeVal", codeVal);
		param.put("searchCdTp", searchCdTp);
		Map<String, Object> velParam = new HashMap<String, Object>();
		velParam.put("subsystem", subsystem);
		velParam.put("searchCdTp", searchCdTp);
		velParam.put("searchtype", searchtype);
		velParam.put("codeVal", codeVal);
		try {
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeManagementDBDAOComIntgCdRSQL(), param, velParam);
		}catch(SQLException se){
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		}catch(Exception ex){
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return dbRowset;
		
	}

	public DBRowSet searchAPPCodeDetailList(Event e) throws DAOException {
		DBRowSet dbRowset = null;
		DouTrn0002Event event = (DouTrn0002Event) e;
		// form 조회조건
		String codeid = event.getCodeMgmtCondVO().getCodeid().toUpperCase(); 
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("codeid", codeid);
		try {
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeManagementDBDAOComIntgCdDtlRSQL(), param, null);
		}catch(SQLException se){
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		}catch(Exception ex){
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return dbRowset;
	}


	
}
