/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTrn0003Event.java
*@FileTitle : UI Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.event;

import java.util.Arrays;
import java.util.HashMap;

import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgCondVO;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgMstVO;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.SearchPartnerVO;


/**
 * DOU_TRN_0003 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  DOU_TRN_0003HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Hai To
 * @see DOU_TRN_0003HTMLAction 참조
 * @since J2EE 1.6
 */

public class DouTrn0003Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */

	
	ErrMsgMstVO errMsgMstVOs = null;
	
	/** Table Value Object Multi Data 처리 */
	
	
//	SearchPartnerVO searchVO = null;
	
	private DBRowSet dbrowset1 = null;
	
	private DBRowSet dbrowset2 = null;
	
//	
//	public SearchPartnerVO getSearchVO() {
//		return searchVO;
//	}
//
//	public void setSearchVO(SearchPartnerVO searchVO) {
//		this.searchVO = searchVO;
//	}

	

	public ErrMsgMstVO getErrMsgMstVOs() {
		return errMsgMstVOs;
	}

	public void setErrMsgMstVOs(ErrMsgMstVO errMsgMstVOs) {
		this.errMsgMstVOs = errMsgMstVOs;
	}


	public DBRowSet getDbrowset1() {
		return dbrowset1;
	}

	public void setDbrowset1(DBRowSet dbrowset1) {
		this.dbrowset1 = dbrowset1;
	}

	public DBRowSet getDbrowset2() {
		return dbrowset2;
	}

	public void setDbrowset2(DBRowSet dbrowset2) {
		this.dbrowset2 = dbrowset2;
	}

	@Override
	public String getEventName() {
		return "DouTrn0003Event";
	}


	@Override
	public String toString() {
		return "DouTrn0003Event";
	}

	
	
	


}
