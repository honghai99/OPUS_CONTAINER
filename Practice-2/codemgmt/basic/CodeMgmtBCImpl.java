package com.clt.apps.opus.dou.doutraining.codemgmt.basic;

import com.clt.apps.opus.dou.doutraining.codemgmt.integration.CodeMgmtDBDAO;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;

public class CodeMgmtBCImpl extends BasicCommandSupport implements CodeMgmtBC  {
	
	
	// Database Access Object
		private transient CodeMgmtDBDAO dbDao = null;
	
	public CodeMgmtBCImpl() {
		dbDao = new CodeMgmtDBDAO();
	}
		
	public DBRowSet searchAPPCodeList(Event e) throws EventException {
		try {
			return dbDao.searchAPPCodeList(e);
		} catch (DAOException de) {
			log.error("err " + de.toString(), de);
			throw new EventException(de.getMessage());
		}
	}

	public DBRowSet searchAPPCodeDetailList(Event e) throws EventException {
		try {
			return dbDao.searchAPPCodeDetailList(e);
		} catch (DAOException de) {
			log.error("err " + de.toString(), de);
			throw new EventException(de.getMessage());
		}
	}
	
	public void doEnd() {
		dbDao = null;
	}

	
	
}
