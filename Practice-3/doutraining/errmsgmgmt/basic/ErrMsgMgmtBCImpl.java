/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBCImpl.java
*@FileTitle : UI Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration.ErrMsgMgmtDBDAO;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgMstVO;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.SearchPartnerVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.layer.basic.BasicCommandSupport;

/**
 * ALPS-DouTraining Business Logic Command Interface<br>
 * - ALPS-DouTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Hai To
 * @since J2EE 1.6
 */
public class ErrMsgMgmtBCImpl extends BasicCommandSupport implements ErrMsgMgmtBC {

	// Database Access Object
	private transient ErrMsgMgmtDBDAO dbDao = null;

	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	

	@Override
	public List<ErrMsgMstVO> searchPartner(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException {
		// TODO Auto-generated method stub
		return dbDao.searchPartner(errMsgMstVO);
	}


	@Override
	public List<ErrMsgMstVO> searchLane(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException {
		// TODO Auto-generated method stub
		return dbDao.searchLane(errMsgMstVO);
	}
	
	@Override
	public List<ErrMsgMstVO> searchTrade(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException {
		// TODO Auto-generated method stub
		return dbDao.searchTrade(errMsgMstVO);
	}


	@Override
	public List<ErrMsgMstVO> searchDetail(ErrMsgMstVO errMsgMstVO)
			throws EventException {

		try {
			return dbDao.searchDetail(errMsgMstVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}


	@Override
	public List<Object> searchExcel(ErrMsgMstVO errMsgMstVOs)
			throws Exception {
		DBRowSet rowSet = null;
		List<Object> sList = new ArrayList();
		List<ErrMsgMstVO> list = null;
		int colCnt = 0;
		String[] sTitle = null;
		String[] sColum = null;
		
		try {
			rowSet = dbDao.searchExcel(errMsgMstVOs);
			list = (List)RowSetUtil.rowSetToVOs(rowSet, ErrMsgMstVO.class);
			sList.add(list);
			rowSet.next();
			colCnt = rowSet.getMetaData().getColumnCount();
			sTitle = new String[colCnt];
			sColum = new String[colCnt];
			
			for(int k = 1; k<colCnt; k++) {
				sTitle[k-1] = rowSet.getMetaData().getColumnLabel(k);
				sColum[k-1] = rowSet.getMetaData().getColumnLabel(k).toLowerCase();
			}
			sList.add(sTitle);
			sList.add(sColum);
			return sList;
 		}
		catch (Exception ex) {
			throw ex;
		}
		
	}
	
}