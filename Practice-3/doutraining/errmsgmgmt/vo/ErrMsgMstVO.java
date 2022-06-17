package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

public class ErrMsgMstVO extends AbstractValueObject {

	static final long serialVersionUID = 1L;

	private HashMap<String, String> hashColumns = new HashMap<String, String>();

	private HashMap<String, String> hashFields = new HashMap<String, String>();
	
	private Collection<ErrMsgMstVO> models = new ArrayList<ErrMsgMstVO>();

	private String rlaneCd = null;
	private String loclCurrCd = null ;
	private String invNo = null;
	private String csrNo = null;
	private String aprfFlg = null;
	private String custVndrCntCd = null;
	private String custVndrSeq = null;
	private String prnrRefNo = null;
	private String custVndrEngNm = null;
	private String revAcrAmt = null;
	private String expActAmt = null;
	private String ibflag = null;
	private String joCrrCd = null;
	private String pagerows = null;
	private String frAcctYrmon = null;
	private String toAcctYrmon = null;
	private String trdCd = null;
	private String total = null;
	//RE_DIVR_CD
	//JO_STL_ITM_CD
	private String reDivrCd = null;
	private String joStlItmCd = null;
	
	public HashMap<String, String> getHashColumns() {
		return hashColumns;
	}

	public void setHashColumns(HashMap<String, String> hashColumns) {
		this.hashColumns = hashColumns;
	}

	public HashMap<String, String> getHashFields() {
		return hashFields;
	}

	public void setHashFields(HashMap<String, String> hashFields) {
		this.hashFields = hashFields;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRlaneCd() {
		return rlaneCd;
	}

	public void setRlaneCd(String rlaneCd) {
		this.rlaneCd = rlaneCd;
	}

	public String getLoclCurrCd() {
		return loclCurrCd;
	}

	public void setLoclCurrCd(String loclCurrCd) {
		this.loclCurrCd = loclCurrCd;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getCsrNo() {
		return csrNo;
	}

	public void setCsrNo(String csrNo) {
		this.csrNo = csrNo;
	}

	public String getAprfFlg() {
		return aprfFlg;
	}

	public void setAprfFlg(String aprfFlg) {
		this.aprfFlg = aprfFlg;
	}

	public String getCustVndrCntCd() {
		return custVndrCntCd;
	}

	public void setCustVndrCntCd(String custVndrCntCd) {
		this.custVndrCntCd = custVndrCntCd;
	}

	public String getCustVndrSeq() {
		return custVndrSeq;
	}

	public void setCustVndrSeq(String custVndrSeq) {
		this.custVndrSeq = custVndrSeq;
	}

	public String getPrnrRefNo() {
		return prnrRefNo;
	}

	public void setPrnrRefNo(String prnrRefNo) {
		this.prnrRefNo = prnrRefNo;
	}

	public String getCustVndrEngNm() {
		return custVndrEngNm;
	}

	public void setCustVndrEngNm(String custVndrEngNm) {
		this.custVndrEngNm = custVndrEngNm;
	}

	public String getRevAcrAmt() {
		return revAcrAmt;
	}

	public void setRevAcrAmt(String revAcrAmt) {
		this.revAcrAmt = revAcrAmt;
	}

	public String getExpActAmt() {
		return expActAmt;
	}

	public void setExpActAmt(String expActAmt) {
		this.expActAmt = expActAmt;
	}

	public String getIbflag() {
		return ibflag;
	}

	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}

	public String getJoCrrCd() {
		return joCrrCd;
	}

	public void setJoCrrCd(String joCrrCd) {
		this.joCrrCd = joCrrCd;
	}

	public String getPagerows() {
		return pagerows;
	}

	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
	public ErrMsgMstVO() {
	
	}	


	public String getFrAcctYrmon() {
		return frAcctYrmon;
	}

	public void setFrAcctYrmon(String frAcctYrmon) {
		this.frAcctYrmon = frAcctYrmon;
	}

	public String getToAcctYrmon() {
		return toAcctYrmon;
	}

	public void setToAcctYrmon(String toAcctYrmon) {
		this.toAcctYrmon = toAcctYrmon;
	}

	public String getTrdCd() {
		return trdCd;
	}

	public void setTrdCd(String trdCd) {
		this.trdCd = trdCd;
	}

	public String getReDivrCd() {
		return reDivrCd;
	}

	public void setReDivrCd(String reDivrCd) {
		this.reDivrCd = reDivrCd;
	}

	public String getJoStlItmCd() {
		return joStlItmCd;
	}

	public void setJoStlItmCd(String joStlItmCd) {
		this.joStlItmCd = joStlItmCd;
	}

	public ErrMsgMstVO(String rlaneCd, String loclCurrCd, String invNo,
			String csrNo, String aprfFlg, String custVndrCntCd,
			String custVndrSeq, String prnrRefNo, String custVndrEngNm,
			String revAcrAmt, String expActAmt, String ibflag, String joCrrCd,
			String pagerows, String frAcctYrmon, String toAcctYrmon, String trdCd, String total, String reDivrCd, String joStlItmCd) {
		this.rlaneCd = rlaneCd;
		this.loclCurrCd = loclCurrCd;
		this.invNo = invNo;
		this.csrNo = csrNo;
		this.aprfFlg = aprfFlg;
		this.custVndrCntCd = custVndrCntCd;
		this.custVndrSeq = custVndrSeq;
		this.prnrRefNo = prnrRefNo;
		this.custVndrEngNm = custVndrEngNm;
		this.revAcrAmt = revAcrAmt;
		this.expActAmt = expActAmt;
		this.ibflag = ibflag;
		this.joCrrCd = joCrrCd;
		this.pagerows = pagerows;
		this.frAcctYrmon = frAcctYrmon;
		this.toAcctYrmon = toAcctYrmon;	
		this.trdCd = trdCd;
		this.total = total;
		this.reDivrCd = reDivrCd;
		this.joStlItmCd = joStlItmCd;
	}

	@Override
	public HashMap<String, String> getColumnValues() {
		this.hashColumns.put("rlane_cd", getRlaneCd());
		this.hashColumns.put("locl_curr_cd", getLoclCurrCd());
		this.hashColumns.put("inv_no", getInvNo());
		this.hashColumns.put("csr_no", getCsrNo());
		this.hashColumns.put("apro_flg", getAprfFlg());
		this.hashColumns.put("cust_vndr_cnt_cd", getCustVndrCntCd());
		this.hashColumns.put("cust_vndr_seq", getCustVndrSeq());
		this.hashColumns.put("prnr_ref_no",getPrnrRefNo());
		this.hashColumns.put("cust_vndr_eng_nm", getCustVndrEngNm());
		this.hashColumns.put("inv_rev_act_amt", getRevAcrAmt());
		this.hashColumns.put("inv_exp_act_amt", getExpActAmt());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("jo_crr_cd", getJoCrrCd());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("fr_acct_yrmon", getFrAcctYrmon());
		this.hashColumns.put("to_acct_yrmon", getToAcctYrmon());
		this.hashColumns.put("trd_cd", getTrdCd());
		this.hashColumns.put("total", getTotal());
		this.hashColumns.put("re_divr_cd", getReDivrCd());
		this.hashColumns.put("jo_stl_itm_cd", getJoStlItmCd());
		return this.hashColumns;
	}

	@Override
	public HashMap<String, String> getFieldNames() {
		this.hashFields.put("rlane_cd", "rlaneCd");
		this.hashFields.put("locl_curr_cd","loclCurrCd");
		this.hashFields.put("inv_no","invNo");
		this.hashFields.put("csr_no", "csrNo"); //slp no
		this.hashFields.put("apro_flg", "aprfFlg");//approved flag 
		this.hashFields.put("cust_vndr_cnt_cd", "custVndrCntCd");//
		this.hashFields.put("cust_vndr_seq","custVndrSeq");
		this.hashFields.put("prnr_ref_no", "prnrRefNo");
		this.hashFields.put("cust_vndr_eng_nm", "custVndrEngNm");
		this.hashFields.put("inv_rev_act_amt", "revAcrAmt");
		this.hashFields.put("inv_exp_act_amt", "expActAmt");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("jo_crr_cd", "joCrrCd");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("fr_acct_yrmon", "frAcctYrmon");
		this.hashFields.put("to_acct_yrmon", "toAcctYrmon");
		this.hashFields.put("trd_cd", "trdCd");
		this.hashFields.put("total", "total");
		this.hashFields.put("re_divr_cd", "reDivrCd");
		this.hashFields.put("jo_stl_itm_cd", "joStlItmCd");
		return this.hashFields;
	}
	
	public void fromRequest(HttpServletRequest request, String prefix) {
		setRlaneCd(JSPUtil.getParameter(request, prefix + "rlane_cd", ""));
		setLoclCurrCd(JSPUtil.getParameter(request,prefix + "locl_curr_cd", ""));
		setInvNo(JSPUtil.getParameter(request,prefix + "inv_no", ""));
		setCsrNo(JSPUtil.getParameter(request,prefix + "csr_no", ""));
		setAprfFlg(JSPUtil.getParameter(request,prefix + "apro_flg", ""));
		setCustVndrCntCd(JSPUtil.getParameter(request,prefix + "cust_vndr_cnt_cd", ""));
		setCustVndrSeq(JSPUtil.getParameter(request,prefix + "cust_vndr_seq", ""));
		setPrnrRefNo(JSPUtil.getParameter(request,prefix + "prnr_ref_no", ""));
		setCustVndrEngNm(JSPUtil.getParameter(request,prefix + "cust_vndr_eng_nm", ""));
		setRevAcrAmt(JSPUtil.getParameter(request, prefix +"inv_rev_act_amt", ""));
		setExpActAmt(JSPUtil.getParameter(request, prefix +"inv_exp_act_amt", ""));
		setIbflag(JSPUtil.getParameter(request,prefix + "ibflag", ""));
		setJoCrrCd(JSPUtil.getParameter(request,prefix + "jo_crr_cd", ""));
		setPagerows(JSPUtil.getParameter(request,prefix + "pagerows", ""));
		setFrAcctYrmon(JSPUtil.getParameter(request, prefix + "fr_acct_yrmon", ""));
		setToAcctYrmon(JSPUtil.getParameter(request, prefix + "to_acct_yrmon", ""));
		setTrdCd(JSPUtil.getParameter(request, prefix + "trd_cd", ""));
		setTotal(JSPUtil.getParameter(request, prefix + "total", ""));
		setJoStlItmCd(JSPUtil.getParameter(request, prefix + "jo_stl_itm_cd", ""));
		setReDivrCd(JSPUtil.getParameter(request, prefix + "re_divr_cd", ""));
		
	}
	
	public ErrMsgMstVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request,"");
	}
	
	public ErrMsgMstVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		ErrMsgMstVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] rlaneCd = (JSPUtil.getParameter(request, prefix	+ "rlane_cd", length));
			String[] loclCurrCd = (JSPUtil.getParameter(request, prefix	+ "locl_curr_cd", length));
			String[] invNo = (JSPUtil.getParameter(request, prefix	+ "inv_no", length));
			String[] csrNo = (JSPUtil.getParameter(request, prefix	+ "csr_no", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] aprfFlg = (JSPUtil.getParameter(request, prefix	+ "apro_flg", length));
			String[] custVndrCntCd = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_cnt_cd", length));
			String[] custVndrSeq = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_seq", length));
			String[] custVndrEngNm = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_eng_nm", length));
			String[] prnrRefNo = (JSPUtil.getParameter(request, prefix	+ "prnr_ref_no", length));
			String[] revAcrAmt = (JSPUtil.getParameter(request, prefix	+ "inv_rev_act_amt", length));
			String[] expActAmt = (JSPUtil.getParameter(request, prefix	+ "inv_exp_act_amt", length));
			String[] joCrrCd = (JSPUtil.getParameter(request, prefix	+ "jo_crr_cd", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			String[] frAcctYrmon = (JSPUtil.getParameter(request, prefix	+ "fr_acct_yrmon", length));
			String[] toAcctYrmon = (JSPUtil.getParameter(request, prefix	+ "to_acct_yrmon", length));
			String[] trdCd = (JSPUtil.getParameter(request, prefix	+ "trd_cd", length));
			String[] total = (JSPUtil.getParameter(request, prefix	+ "total", length));
			String[] reDivrCd = (JSPUtil.getParameter(request, prefix	+ "re_divr_cd", length));
			String[] joStlItmCd = (JSPUtil.getParameter(request, prefix	+ "jo_stl_itm_cd", length));

			
			for (int i = 0; i < length; i++) {
				model = new ErrMsgMstVO();
				if (rlaneCd[i] != null)
					model.setRlaneCd(rlaneCd[i]);
				if (loclCurrCd[i] != null)
					model.setLoclCurrCd(loclCurrCd[i]);
				if (invNo[i] != null)
					model.setInvNo(invNo[i]);
				if (csrNo[i] != null)
					model.setCsrNo(csrNo[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (aprfFlg[i] != null)
					model.setAprfFlg(aprfFlg[i]);
				if (custVndrCntCd[i] != null)
					model.setCustVndrCntCd(custVndrCntCd[i]);
				if (custVndrSeq[i] != null)
					model.setCustVndrSeq(custVndrSeq[i]);
				if (custVndrEngNm[i] != null)
					model.setCustVndrEngNm(custVndrEngNm[i]);
				if (prnrRefNo[i] != null)
					model.setPrnrRefNo(prnrRefNo[i]);
				if (revAcrAmt[i] != null)
					model.setRevAcrAmt(revAcrAmt[i]);
				if (expActAmt[i] != null)
					model.setExpActAmt(expActAmt[i]);
				if (joCrrCd[i] != null)
					model.setJoCrrCd(joCrrCd[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (frAcctYrmon[i] != null)
					model.setFrAcctYrmon(frAcctYrmon[i]);
				if (toAcctYrmon[i] != null)
					model.setToAcctYrmon(toAcctYrmon[i]);
				if (trdCd[i] != null)
					model.setTrdCd(trdCd[i]);
				if (total[i] != null)
					model.setTrdCd(total[i]);
				if (reDivrCd[i] != null)
					model.setReDivrCd(reDivrCd[i]);
				if (joStlItmCd[i] != null)
					model.setJoStlItmCd(joStlItmCd[i]);
				
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getErrMsgMstVO();
	}

	public ErrMsgMstVO[] getErrMsgMstVO(){
		ErrMsgMstVO[] vos = (ErrMsgMstVO[])models.toArray(new ErrMsgMstVO[models.size()]);
		return vos;
	}
	
	//Convert the contents of VO Class to String
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );

	}
	
	public void unDataFormat(){
		this.frAcctYrmon = this.frAcctYrmon .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.toAcctYrmon = this.toAcctYrmon .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.rlaneCd = this.rlaneCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.loclCurrCd = this.loclCurrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invNo = this.invNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.csrNo = this.csrNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aprfFlg = this.aprfFlg .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrCntCd = this.custVndrCntCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrSeq = this.custVndrSeq .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrEngNm = this.custVndrEngNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.prnrRefNo = this.prnrRefNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.revAcrAmt = this.revAcrAmt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.expActAmt = this.expActAmt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.joCrrCd = this.joCrrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows.replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.trdCd = this.trdCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.total = this.total .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.reDivrCd = this.reDivrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.joStlItmCd = this.joStlItmCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");

	}
	

}
