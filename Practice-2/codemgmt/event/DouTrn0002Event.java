/*=========================================================
*Copyright(c) 2020 CyberLogitec
*@FileName : DouTrn001Event.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2020.03.17
*@LastModifier : 
*@LastVersion : 1.0
* 2020.03.17 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.codemgmt.event;

import java.util.Arrays;

import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtCondVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDtlVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMstVO;


/**
 * DOU_TRN_001 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  DOU_TRN_0002HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Hai To
 * @see DOU_TRN_001HTMLAction 참조
 * @since J2EE 1.6
 */

public class DouTrn0002Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** id 변수 (Form 객체) */
	private String id = null;

	/** codeid 변수 (Form 객체) */
	private String codeid = null;

	/** name 변수 (Form 객체) */
	private String name = null;

	
	private String searchCdTp = null;


	
	private String selectedcodes = null;
	
	private DBRowSet dbrowset1 = null;
	
	private DBRowSet dbrowset2 = null;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CodeMgmtCondVO codeMgmtCondVO = null;
			
	/** Table Value Object Mult Data i처리 */
	private CodeMgmtMstVO[] codemgmtMstVOs = null;

	private CodeMgmtDtlVO[] codemgmtDtlVOs = null;

	public DouTrn0002Event(){}

	public CodeMgmtCondVO getCodeMgmtCondVO() {
		return codeMgmtCondVO;
	}

	public void setCodeMgmtCondVO(CodeMgmtCondVO codeMgmtCondVO) {
		this.codeMgmtCondVO = codeMgmtCondVO;
	}

	public CodeMgmtMstVO[] getCodemgmtMstVOs() {
		CodeMgmtMstVO[] rtnVOs = null;
		if (this.codemgmtMstVOs != null) {
			rtnVOs = Arrays.copyOf(codemgmtMstVOs, codemgmtMstVOs.length);
		}
		return rtnVOs;
	}

	public void setCodemgmtMstVOs(CodeMgmtMstVO[] codemgmtMstVOs) {
		if(codemgmtMstVOs != null){
			CodeMgmtMstVO[] tmpVOs = Arrays.copyOf(codemgmtMstVOs, codemgmtMstVOs.length);
			this.codemgmtMstVOs  = tmpVOs;
		}
	}

	public CodeMgmtDtlVO[] getCodemgmtDtlVOs() {
		CodeMgmtDtlVO[] rtnVOs = null;
		if (this.codemgmtDtlVOs != null) {
			rtnVOs = Arrays.copyOf(codemgmtDtlVOs, codemgmtDtlVOs.length);
		}
		return rtnVOs;
	}

	public void setCodemgmtDtlVOs(CodeMgmtDtlVO[] codemgmtDtlVOs) {
		if(codemgmtDtlVOs != null){
			CodeMgmtDtlVO[] tmpVOs = Arrays.copyOf(codemgmtDtlVOs, codemgmtDtlVOs.length);
			this.codemgmtDtlVOs  = tmpVOs;
		}
	}
	
	public String getId() {
		return id;
	}

	public String getCodeid() {
		return codeid;
	}


	public String getName() {
		return name;
	}


	public String getSearchCdTp() {
		return searchCdTp;
	}

	public void setSearchCdTp(String searchCdTp) {
		this.searchCdTp = searchCdTp;
	}

	public String getSelectedcodes() {
		return selectedcodes;
	}

	public void setSelectedcodes(String selectedcodes) {
		this.selectedcodes = selectedcodes;
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
		return "DouTrn0002Event";
	}

	@Override
	public String toString() {
		return "DouTrn0002Event";
	}
	
	
	
	
	
	
	
	

}