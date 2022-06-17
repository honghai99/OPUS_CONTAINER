/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBC.java
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

import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.*;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;

/**
 * ALPS-Doutraining Business Logic Command Interface<br>
 * - ALPS-Doutraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Hai To
 * @since J2EE 1.6
 */

public interface ErrMsgMgmtBC {

	public List<ErrMsgMstVO> searchPartner(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException;
	
	public List<ErrMsgMstVO> searchLane(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException;
	
	public List<ErrMsgMstVO> searchTrade(ErrMsgMstVO errMsgMstVO) throws EventException, DAOException;

	public List<ErrMsgMstVO> searchDetail(ErrMsgMstVO errMsgMstVO) throws EventException;

	public List<Object> searchExcel(ErrMsgMstVO errMsgMstVOs) throws EventException, Exception;

	
	
	
}