package com.clt.apps.opus.dou.doutraining.codemgmt.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

public class CodeMgmtDtlVO extends AbstractValueObject {
private static final long serialVersionUID = 1L;
	
	private Collection<CodeMgmtDtlVO> models = new ArrayList<CodeMgmtDtlVO>();
	
	/* Column Info */
	private String updDt = null;
	/* Column Info */
	private String intgCdValDpSeq = null;
	/* Column Info */
	private String intgCdValDpDesc = null;
	/* Column Info */
	private String aplyEndDt = null;
	/* Column Info */
	private String creDt = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String intgCdId = null;
	/* Column Info */
	private String aplyStDt = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String intgCdValDesc = null;
	/* Column Info */
	private String creUsrId = null;
	/* Column Info */
	private String intgCdValCtnt = null;
	/* Column Info */
	private String updUsrId = null;
	
	/* Hashtable to store table column values ​​*/
	private HashMap<String, String> hashColumns = new HashMap<String, String>();


	/* Hashtable storing member variables corresponding to table columns */
	private HashMap<String, String> hashFields = new HashMap<String, String>();
	
	public CodeMgmtDtlVO() {}

	public CodeMgmtDtlVO(String updDt, String intgCdValDpSeq,
			String intgCdValDpDesc, String aplyEndDt, String creDt,
			String pagerows, String intgCdId, String aplyStDt, String ibflag,
			String intgCdValDesc, String creUsrId, String intgCdValCtnt,
			String updUsrId) {
		super();
		this.updDt = updDt;
		this.intgCdValDpSeq = intgCdValDpSeq;
		this.intgCdValDpDesc = intgCdValDpDesc;
		this.aplyEndDt = aplyEndDt;
		this.creDt = creDt;
		this.pagerows = pagerows;
		this.intgCdId = intgCdId;
		this.aplyStDt = aplyStDt;
		this.ibflag = ibflag;
		this.intgCdValDesc = intgCdValDesc;
		this.creUsrId = creUsrId;
		this.intgCdValCtnt = intgCdValCtnt;
		this.updUsrId = updUsrId;
	}

	@Override
	public HashMap<String, String> getColumnValues() {
		this.hashColumns.put("upd_dt", getUpdDt());
		this.hashColumns.put("intg_cd_val_dp_seq", getIntgCdValDpSeq());
		this.hashColumns.put("intg_cd_val_dp_desc", getIntgCdValDpDesc());
		this.hashColumns.put("aply_end_dt", getAplyEndDt());
		this.hashColumns.put("cre_dt", getCreDt());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("intg_cd_id", getIntgCdId());
		this.hashColumns.put("aply_st_dt", getAplyStDt());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("intg_cd_val_desc", getIntgCdValDesc());
		this.hashColumns.put("cre_usr_id", getCreUsrId());
		this.hashColumns.put("intg_cd_val_ctnt", getIntgCdValCtnt());
		this.hashColumns.put("upd_usr_id", getUpdUsrId());
		return this.hashColumns;
	}

	@Override
	public HashMap<String, String> getFieldNames() {
		this.hashFields.put("upd_dt", "updDt");
		this.hashFields.put("intg_cd_val_dp_seq", "intgCdValDpSeq");
		this.hashFields.put("intg_cd_val_dp_desc", "intgCdValDpDesc");
		this.hashFields.put("aply_end_dt", "aplyEndDt");
		this.hashFields.put("cre_dt", "creDt");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("intg_cd_id", "intgCdId");
		this.hashFields.put("aply_st_dt", "aplyStDt");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("intg_cd_val_desc", "intgCdValDesc");
		this.hashFields.put("cre_usr_id", "creUsrId");
		this.hashFields.put("intg_cd_val_ctnt", "intgCdValCtnt");
		this.hashFields.put("upd_usr_id", "updUsrId");
		return this.hashFields;
	}

	public String getUpdDt() {
		return updDt;
	}

	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}

	public String getIntgCdValDpSeq() {
		return intgCdValDpSeq;
	}

	public void setIntgCdValDpSeq(String intgCdValDpSeq) {
		this.intgCdValDpSeq = intgCdValDpSeq;
	}

	public String getIntgCdValDpDesc() {
		return intgCdValDpDesc;
	}

	public void setIntgCdValDpDesc(String intgCdValDpDesc) {
		this.intgCdValDpDesc = intgCdValDpDesc;
	}

	public String getAplyEndDt() {
		return aplyEndDt;
	}

	public void setAplyEndDt(String aplyEndDt) {
		this.aplyEndDt = aplyEndDt;
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

	public String getAplyStDt() {
		return aplyStDt;
	}

	public void setAplyStDt(String aplyStDt) {
		this.aplyStDt = aplyStDt;
	}

	public String getIbflag() {
		return ibflag;
	}

	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}

	public String getIntgCdValDesc() {
		return intgCdValDesc;
	}

	public void setIntgCdValDesc(String intgCdValDesc) {
		this.intgCdValDesc = intgCdValDesc;
	}

	public String getCreUsrId() {
		return creUsrId;
	}

	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}

	public String getIntgCdValCtnt() {
		return intgCdValCtnt;
	}

	public void setIntgCdValCtnt(String intgCdValCtnt) {
		this.intgCdValCtnt = intgCdValCtnt;
	}

	public String getUpdUsrId() {
		return updUsrId;
	}

	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}
	
	public void fromRequest(HttpServletRequest request) {
		setUpdDt(JSPUtil.getParameter(request, "upd_dt", ""));
		setIntgCdValDpSeq(JSPUtil.getParameter(request, "intg_cd_val_dp_seq", ""));
		setIntgCdValDpDesc(JSPUtil.getParameter(request, "intg_cd_val_dp_desc", ""));
		setAplyEndDt(JSPUtil.getParameter(request, "aply_end_dt", ""));
		setCreDt(JSPUtil.getParameter(request, "cre_dt", ""));
		setPagerows(JSPUtil.getParameter(request, "pagerows", ""));
		setIntgCdId(JSPUtil.getParameter(request, "intg_cd_id", ""));
		setAplyStDt(JSPUtil.getParameter(request, "aply_st_dt", ""));
		setIbflag(JSPUtil.getParameter(request, "ibflag", ""));
		setIntgCdValDesc(JSPUtil.getParameter(request, "intg_cd_val_desc", ""));
		setCreUsrId(JSPUtil.getParameter(request, "cre_usr_id", ""));
		setIntgCdValCtnt(JSPUtil.getParameter(request, "intg_cd_val_ctnt", ""));
		setUpdUsrId(JSPUtil.getParameter(request, "upd_usr_id", ""));
	}
	
	public CodeMgmtDtlVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return CodeMgmtDtlVO[]
	 */
	public CodeMgmtDtlVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		CodeMgmtDtlVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] updDt = (JSPUtil.getParameter(request, prefix	+ "upd_dt".trim(), length));
			String[] intgCdValDpSeq = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_dp_seq".trim(), length));
			String[] intgCdValDpDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_dp_desc".trim(), length));
			String[] aplyEndDt = (JSPUtil.getParameter(request, prefix	+ "aply_end_dt".trim(), length));
			String[] creDt = (JSPUtil.getParameter(request, prefix	+ "cre_dt".trim(), length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows".trim(), length));
			String[] intgCdId = (JSPUtil.getParameter(request, prefix	+ "intg_cd_id".trim(), length));
			String[] aplyStDt = (JSPUtil.getParameter(request, prefix	+ "aply_st_dt".trim(), length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag".trim(), length));
			String[] intgCdValDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_desc".trim(), length));
			String[] creUsrId = (JSPUtil.getParameter(request, prefix	+ "cre_usr_id".trim(), length));
			String[] intgCdValCtnt = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_ctnt".trim(), length));
			String[] updUsrId = (JSPUtil.getParameter(request, prefix	+ "upd_usr_id".trim(), length));
			
			for (int i = 0; i < length; i++) {
				model = new CodeMgmtDtlVO();
				if (updDt[i] != null)
					model.setUpdDt(updDt[i]);
				if (intgCdValDpSeq[i] != null)
					model.setIntgCdValDpSeq(intgCdValDpSeq[i]);
				if (intgCdValDpDesc[i] != null)
					model.setIntgCdValDpDesc(intgCdValDpDesc[i]);
				if (aplyEndDt[i] != null)
					model.setAplyEndDt(aplyEndDt[i]);
				if (creDt[i] != null)
					model.setCreDt(creDt[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (intgCdId[i] != null)
					model.setIntgCdId(intgCdId[i]);
				if (aplyStDt[i] != null)
					model.setAplyStDt(aplyStDt[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (intgCdValDesc[i] != null)
					model.setIntgCdValDesc(intgCdValDesc[i]);
				if (creUsrId[i] != null)
					model.setCreUsrId(creUsrId[i]);
				if (intgCdValCtnt[i] != null)
					model.setIntgCdValCtnt(intgCdValCtnt[i]);
				if (updUsrId[i] != null)
					model.setUpdUsrId(updUsrId[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getCodeMgmtDtlVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return CodeMgmtDtlVO[]
	 */
	public CodeMgmtDtlVO[] getCodeMgmtDtlVOs(){
		CodeMgmtDtlVO[] vos = (CodeMgmtDtlVO[])models.toArray(new CodeMgmtDtlVO[models.size()]);
		return vos;
	}
	
	/**
	 * VO Class의 내용을 String으로 변환
	 */
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
	
	/**
	 * 필드에 있는 값을 스트링 배열로 반환.
	 * @param field
	 * @param i
	 * @return String[]
	 */
	private String[] getField(Field[] field, int i) {
		String[] arr = null;
		try{
			arr = (String[]) field[i].get(this);
		}catch(Exception ex){
			arr = null;
		}
		return arr;
	}

	/**
	* 포맷팅된 문자열에서 특수문자 제거("-","/",",",":")
	*/
	public void unDataFormat(){
		this.updDt = this.updDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpSeq = this.intgCdValDpSeq .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpDesc = this.intgCdValDpDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aplyEndDt = this.aplyEndDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creDt = this.creDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdId = this.intgCdId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aplyStDt = this.aplyStDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDesc = this.intgCdValDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creUsrId = this.creUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValCtnt = this.intgCdValCtnt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.updUsrId = this.updUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
	
	
	
}
