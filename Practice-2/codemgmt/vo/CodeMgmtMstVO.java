package com.clt.apps.opus.dou.doutraining.codemgmt.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

public class CodeMgmtMstVO extends AbstractValueObject {
	private static final long serialVersionUID = 1L;

	private Collection<CodeMgmtMstVO> models = new ArrayList<CodeMgmtMstVO>();
	
	/* Column Info */
	private String updDt = null;
	/* Column Info */
	private String intgCdUseFlg = null;
	/* Column Info */
	private String intgCdNm = null;
	/* Column Info */
	private String intgCdLen = null;
	/* Column Info */
	private String creDt = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String intgCdId = null;
	/* Column Info */
	private String intgCdTpNm = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String intgCdDesc = null;
	/* Column Info */
	private String creUsrId = null;
	/* Column Info */
	private String ownrSubSysCd = null;
	/* Column Info */
	private String updUsrId = null;
	
	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new HashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new HashMap<String, String>();
	
	public CodeMgmtMstVO() {}

	
	public CodeMgmtMstVO(String updDt, String intgCdUseFlg, String intgCdNm,
			String intgCdLen, String creDt, String pagerows, String intgCdId,
			String intgCdTpNm, String ibflag, String intgCdDesc,
			String creUsrId, String ownrSubSysCd, String updUsrId) {
		this.updDt = updDt;
		this.intgCdUseFlg = intgCdUseFlg;
		this.intgCdNm = intgCdNm;
		this.intgCdLen = intgCdLen;
		this.creDt = creDt;
		this.pagerows = pagerows;
		this.intgCdId = intgCdId;
		this.intgCdTpNm = intgCdTpNm;
		this.ibflag = ibflag;
		this.intgCdDesc = intgCdDesc;
		this.creUsrId = creUsrId;
		this.ownrSubSysCd = ownrSubSysCd;
		this.updUsrId = updUsrId;
	}

	@Override
	public HashMap<String, String> getColumnValues() {
		this.hashColumns.put("upd_dt", getUpdDt());
		this.hashColumns.put("intg_cd_use_flg", getIntgCdUseFlg());
		this.hashColumns.put("intg_cd_nm", getIntgCdNm());
		this.hashColumns.put("intg_cd_len", getIntgCdLen());
		this.hashColumns.put("cre_dt", getCreDt());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("intg_cd_id", getIntgCdId());
		this.hashColumns.put("intg_cd_tp_cd", getIntgCdTpNm());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("intg_cd_desc", getIntgCdDesc());
		this.hashColumns.put("cre_usr_id", getCreUsrId());
		this.hashColumns.put("ownr_sub_sys_cd", getOwnrSubSysCd());
		this.hashColumns.put("upd_usr_id", getUpdUsrId());
		return this.hashColumns;
	}

	@Override
	public HashMap<String, String> getFieldNames() {
		this.hashFields.put("upd_dt", "updDt");
		this.hashFields.put("intg_cd_use_flg", "intgCdUseFlg");
		this.hashFields.put("intg_cd_nm", "intgCdNm");
		this.hashFields.put("intg_cd_len", "intgCdLen");
		this.hashFields.put("cre_dt", "creDt");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("intg_cd_id", "intgCdId");
		this.hashFields.put("intg_cd_tp_cd", "intgCdTpNm");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("intg_cd_desc", "intgCdDesc");
		this.hashFields.put("cre_usr_id", "creUsrId");
		this.hashFields.put("ownr_sub_sys_cd", "ownrSubSysCd");
		this.hashFields.put("upd_usr_id", "updUsrId");
		return this.hashFields;
	}


	public String getUpdDt() {
		return updDt;
	}


	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}


	public String getIntgCdUseFlg() {
		return intgCdUseFlg;
	}


	public void setIntgCdUseFlg(String intgCdUseFlg) {
		this.intgCdUseFlg = intgCdUseFlg;
	}


	public String getIntgCdNm() {
		return intgCdNm;
	}


	public void setIntgCdNm(String intgCdNm) {
		this.intgCdNm = intgCdNm;
	}


	public String getIntgCdLen() {
		return intgCdLen;
	}


	public void setIntgCdLen(String intgCdLen) {
		this.intgCdLen = intgCdLen;
	}


	public String getCreDt() {
		return creDt;
	}


	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}


	public String getPagerows() {
		return pagerows;
	}


	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}


	public String getIntgCdId() {
		return intgCdId;
	}


	public void setIntgCdId(String intgCdId) {
		this.intgCdId = intgCdId;
	}


	public String getIntgCdTpNm() {
		return intgCdTpNm;
	}


	public void setIntgCdTpNm(String intgCdTpNm) {
		this.intgCdTpNm = intgCdTpNm;
	}


	public String getIbflag() {
		return ibflag;
	}


	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}


	public String getIntgCdDesc() {
		return intgCdDesc;
	}


	public void setIntgCdDesc(String intgCdDesc) {
		this.intgCdDesc = intgCdDesc;
	}


	public String getCreUsrId() {
		return creUsrId;
	}


	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}


	public String getOwnrSubSysCd() {
		return ownrSubSysCd;
	}


	public void setOwnrSubSysCd(String ownrSubSysCd) {
		this.ownrSubSysCd = ownrSubSysCd;
	}


	public String getUpdUsrId() {
		return updUsrId;
	}


	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}
	//Extract the request data and set it in the member variable of VO.
	public void fromRequest(HttpServletRequest request) {
		setUpdDt(JSPUtil.getParameter(request, "upd_dt", ""));
		setIntgCdUseFlg(JSPUtil.getParameter(request, "intg_cd_use_flg", ""));
		setIntgCdNm(JSPUtil.getParameter(request, "intg_cd_nm", ""));
		setIntgCdLen(JSPUtil.getParameter(request, "intg_cd_len", ""));
		setCreDt(JSPUtil.getParameter(request, "cre_dt", ""));
		setPagerows(JSPUtil.getParameter(request, "pagerows", ""));
		setIntgCdId(JSPUtil.getParameter(request, "intg_cd_id", ""));
		setIntgCdTpNm(JSPUtil.getParameter(request, "intg_cd_tp_cd", ""));
		setIbflag(JSPUtil.getParameter(request, "ibflag", ""));
		setIntgCdDesc(JSPUtil.getParameter(request, "intg_cd_desc", ""));
		setCreUsrId(JSPUtil.getParameter(request, "cre_usr_id", ""));
		setOwnrSubSysCd(JSPUtil.getParameter(request, "ownr_sub_sys_cd", ""));
		setUpdUsrId(JSPUtil.getParameter(request, "upd_usr_id", ""));
	}
	/*Converts Request data to VO array and returns it.
	* @param request
	* @return CodeMgmtMstVO[]
	*/
	public CodeMgmtMstVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request,"");
	}
	/**
	* Several requests passed over are DATA in VO Class.
	* @param request
	* @param prefix
	* @return CodeMgmtMstVO[]
	*/
	public CodeMgmtMstVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		CodeMgmtMstVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] updDt = (JSPUtil.getParameter(request, prefix	+ "upd_dt".trim(), length));
			String[] intgCdUseFlg = (JSPUtil.getParameter(request, prefix	+ "intg_cd_use_flg".trim(), length));
			String[] intgCdNm = (JSPUtil.getParameter(request, prefix	+ "intg_cd_nm".trim(), length));
			String[] intgCdLen = (JSPUtil.getParameter(request, prefix	+ "intg_cd_len".trim(), length));
			String[] creDt = (JSPUtil.getParameter(request, prefix	+ "cre_dt".trim(), length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows".trim(), length));
			String[] intgCdId = (JSPUtil.getParameter(request, prefix	+ "intg_cd_id".trim(), length));
			String[] intgCdTpNm = (JSPUtil.getParameter(request, prefix	+ "intg_cd_tp_cd".trim(), length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag".trim(), length));
			String[] intgCdDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_desc".trim(), length));
			String[] creUsrId = (JSPUtil.getParameter(request, prefix	+ "cre_usr_id".trim(), length));
			String[] ownrSubSysCd = (JSPUtil.getParameter(request, prefix	+ "ownr_sub_sys_cd".trim(), length));
			String[] updUsrId = (JSPUtil.getParameter(request, prefix	+ "upd_usr_id".trim(), length));
			
			for (int i = 0; i < length; i++) {
				model = new CodeMgmtMstVO();
				if (updDt[i] != null)
					model.setUpdDt(updDt[i]);
				if (intgCdUseFlg[i] != null)
					model.setIntgCdUseFlg(intgCdUseFlg[i]);
				if (intgCdNm[i] != null)
					model.setIntgCdNm(intgCdNm[i]);
				if (intgCdLen[i] != null)
					model.setIntgCdLen(intgCdLen[i]);
				if (creDt[i] != null)
					model.setCreDt(creDt[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (intgCdId[i] != null)
					model.setIntgCdId(intgCdId[i]);
				if (intgCdTpNm[i] != null)
					model.setIntgCdTpNm(intgCdTpNm[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (intgCdDesc[i] != null)
					model.setIntgCdDesc(intgCdDesc[i]);
				if (creUsrId[i] != null)
					model.setCreUsrId(creUsrId[i]);
				if (ownrSubSysCd[i] != null)
					model.setOwnrSubSysCd(ownrSubSysCd[i]);
				if (updUsrId[i] != null)
					model.setUpdUsrId(updUsrId[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getCodeMgmtMstVOs();
	}
	/**
	* return VO array
	* @return CodeMgmtMstVO[]
	*/
	public CodeMgmtMstVO[] getCodeMgmtMstVOs(){
		CodeMgmtMstVO[] vos = (CodeMgmtMstVO[])models.toArray(new CodeMgmtMstVO[models.size()]);
		return vos;
	}
	
	//Convert the contents of VO Class to String
	public String toString() {
		StringBuffer ret = new StringBuffer();
		Field[] field = this.getClass().getDeclaredFields();
		String space = "";
		try{
			for(int i = 0; i < field.length; i++){
				String[] arr = null;
				arr = getField(field, i);
				if(arr != null){
					for(int j = 0; j < arr.length; j++) {
						ret.append(field[i].getName().concat(space).substring(0, 30).concat("= ") + arr[j] + "\n");
					}
				} else {
					ret.append(field[i].getName() + " =  null \n");
				}
			}
		} catch (Exception ex) {
			return null;
		}
		return ret.toString();
	}
	
	//Convert the contents of VO Class to String
	private String[] getField(Field[] field, int i) {
		String[] arr = null;
		try{
			arr = (String[]) field[i].get(this);
		}catch(Exception ex){
			arr = null;
		}
		return arr;
	}

	public void unDataFormat(){
		this.updDt = this.updDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdUseFlg = this.intgCdUseFlg .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdNm = this.intgCdNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdLen = this.intgCdLen .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creDt = this.creDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdId = this.intgCdId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdTpNm = this.intgCdTpNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdDesc = this.intgCdDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creUsrId = this.creUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ownrSubSysCd = this.ownrSubSysCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.updUsrId = this.updUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}

}
