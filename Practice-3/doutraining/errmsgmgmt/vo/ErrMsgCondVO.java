package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;



import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

public class ErrMsgCondVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;

	private Collection<ErrMsgCondVO> models = new ArrayList<ErrMsgCondVO>();
	
	private String dateFrom;
	private String dateTo;
	private String partner;
	private String lane;
	private String trade;
	
	private HashMap<String, String> hashColumns = new HashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new HashMap<String, String>();
	
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public ErrMsgCondVO() {
	}

	public ErrMsgCondVO(String dateFrom, String dateTo, String partner,
			String lane, String trade) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.partner = partner;
		this.lane = lane;
		this.trade = trade;
	}

	@Override
	public HashMap<String, String> getColumnValues() {
		this.hashColumns.put("dateFrom", getDateFrom());
		this.hashColumns.put("dateTo", getDateTo());
		this.hashColumns.put("jo_crr_cd", getPartner());
		this.hashColumns.put("rlane_cd", getLane());
		this.hashColumns.put("trd_cd", getTrade());
		return this.hashColumns;
	}

	@Override
	public HashMap<String, String> getFieldNames() {
		this.hashFields.put("dateFrom", "dateFrom");
		this.hashFields.put("dateTo", "dateTo");
		this.hashFields.put("jo_crr_cd", "partner");
		this.hashFields.put("rlane_cd", "lane");
		this.hashFields.put("trd_cd", "trade");
		return this.hashFields;
	}
	
	public void fromRequest(HttpServletRequest request) {
		setDateFrom(JSPUtil.getParameter(request, "dateFrom", ""));
		setDateTo(JSPUtil.getParameter(request, "dateTo", ""));
		setPartner(JSPUtil.getParameter(request, "jo_crr_cd", ""));
		setLane(JSPUtil.getParameter(request, "rlane_cd", ""));
		setTrade(JSPUtil.getParameter(request, "trd_cd", ""));
	}
	
	public ErrMsgCondVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}
	
	public ErrMsgCondVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		ErrMsgCondVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] dateFrom = (JSPUtil.getParameter(request, prefix	+ "dateFrom".trim(), length));
			String[] dateTo = (JSPUtil.getParameter(request, prefix	+ "dateTo".trim(), length));
			String[] partner = (JSPUtil.getParameter(request, prefix	+ "jo_crr_cd".trim(), length));
			String[] lane = (JSPUtil.getParameter(request, prefix	+ "rlane_cd".trim(), length));
			String[] trade = (JSPUtil.getParameter(request, prefix	+ "trd_cd".trim(), length));
			
			for (int i = 0; i < length; i++) {
				model = new ErrMsgCondVO();
				if (dateFrom[i] != null)
					model.setDateFrom(dateFrom[i]);
				if (dateTo[i] != null)
					model.setDateTo(dateTo[i]);
				if (partner[i] != null)
					model.setPartner(partner[i]);
				if (lane[i] != null)
					model.setLane(lane[i]);
				if (trade[i] != null)
					model.setTrade(trade[i]);
				
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getErrMsgCondVOs();
		
	}
	
	public ErrMsgCondVO[] getErrMsgCondVOs(){
		ErrMsgCondVO[] vos = (ErrMsgCondVO[])models.toArray(new ErrMsgCondVO[models.size()]);
		return vos;
	}
	
	
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
		this.dateFrom = this.dateFrom .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.dateTo = this.dateTo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.lane = this.lane .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.partner = this.partner .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.trade = this.trade .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}

}
