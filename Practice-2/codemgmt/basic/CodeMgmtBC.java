package com.clt.apps.opus.dou.doutraining.codemgmt.basic;

import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;

public interface CodeMgmtBC {
	/**
	 * 조회 이벤트 처리<br>
	 * Codepublish화면에 대한 조회 이벤트 처리<br>
	 * 
	 * @param Event e
	 * @return DBRowSet
	 * @exception EventException
	 * @throws DAOException 
	 */
	public DBRowSet searchAPPCodeList(Event e) throws EventException, DAOException;

	/**
	 * 조회 이벤트 처리<br>
	 * Codepublish화면에 대한 조회 이벤트 처리<br>
	 * 
	 * @param Event e
	 * @return DBRowSet
	 * @exception EventException
	 * @throws DAOException 
	 */
	public DBRowSet searchAPPCodeDetailList(Event e) throws EventException, DAOException;


	
	
	
	
}
