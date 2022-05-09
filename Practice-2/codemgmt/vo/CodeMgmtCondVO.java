package com.clt.apps.opus.dou.doutraining.codemgmt.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;


public class CodeMgmtCondVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<CodeMgmtCondVO> models = new ArrayList<CodeMgmtCondVO>();
	
	/* Column Info */
	private String searchtype = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String codeid = null;
	/* Column Info */
	private String codeVal = null;
	/* Column Info */
	private String searchCdTp = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String subsystem = null;

	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new HashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new HashMap<String, String>();
	
	public CodeMgmtCondVO() {}

	public CodeMgmtCondVO(String ibflag, String pagerows, String codeid, String codeVal, String subsystem, String searchtype, String searchCdTp) {
		this.searchtype = searchtype;
		this.pagerows = pagerows;
		this.codeid = codeid;
		this.codeVal = codeVal;
		this.searchCdTp = searchCdTp;
		this.ibflag = ibflag;
		this.subsystem = subsystem;
	}
	
	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("searchtype", getSearchtype());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("codeid", getCodeid());
		this.hashColumns.put("codeVal", getCodeVal());
		this.hashColumns.put("searchCdTp", getSearchCdTp());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("subsystem", getSubsystem());
		return this.hashColumns;
	}
	
	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환   
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("searchtype", "searchtype");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("codeid", "codeid");
		this.hashFields.put("codeVal", "codeVal");
		this.hashFields.put("searchCdTp", "searchCdTp");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("subsystem", "subsystem");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return searchtype
	 */
	public String getSearchtype() {
		return this.searchtype;
	}
	
	/**
	 * Page Number
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}
	
	/**
	 * Column Info
	 * @return codeid
	 */
	public String getCodeid() {
		return this.codeid;
	}
	
	/**
	 * Column Info
	 * @return codeVal
	 */
	public String getCodeVal() {
		return this.codeVal;
	}
	
	/**
	 * Column Info
	 * @return searchCdTp
	 */
	public String getSearchCdTp() {
		return this.searchCdTp;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}
	
	/**
	 * Column Info
	 * @return subsystem
	 */
	public String getSubsystem() {
		return this.subsystem;
	}
	
	/**
	 * Column Info
	 * @param searchtype
	 */
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
	/**
	 * Column Info
	 * @param codeid
	 */
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	
	/**
	 * Column Info
	 * @param codeVal
	 */
	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}
	
	/**
	 * Column Info
	 * @param searchCdTp
	 */
	public void setSearchCdTp(String searchCdTp) {
		this.searchCdTp = searchCdTp;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}
	
	/**
	 * Column Info
	 * @param subsystem
	 */
	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}
	
	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		setSearchtype(JSPUtil.getParameter(request, "searchtype", ""));
		setPagerows(JSPUtil.getParameter(request, "pagerows", ""));
		setCodeid(JSPUtil.getParameter(request, "codeid", ""));
		setCodeVal(JSPUtil.getParameter(request, "code_val", ""));
		setSearchCdTp(JSPUtil.getParameter(request, "searchCdTp", ""));
		setIbflag(JSPUtil.getParameter(request, "ibflag", ""));
		setSubsystem(JSPUtil.getParameter(request, "subsystem", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * @param request
	 * @return CodeMgmtCondVO[]
	 */
	public CodeMgmtCondVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return CodeMgmtCondVO[]
	 */
	public CodeMgmtCondVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		CodeMgmtCondVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] searchtype = (JSPUtil.getParameter(request, prefix	+ "searchtype".trim(), length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows".trim(), length));
			String[] codeid = (JSPUtil.getParameter(request, prefix	+ "codeid".trim(), length));
			String[] codeVal = (JSPUtil.getParameter(request, prefix	+ "code_val".trim(), length));
			String[] searchCdTp = (JSPUtil.getParameter(request, prefix	+ "searchCdTp".trim(), length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag".trim(), length));
			String[] subsystem = (JSPUtil.getParameter(request, prefix	+ "subsystem".trim(), length));
			
			for (int i = 0; i < length; i++) {
				model = new CodeMgmtCondVO();
				if (searchtype[i] != null)
					model.setSearchtype(searchtype[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (codeid[i] != null)
					model.setCodeid(codeid[i]);
				if (codeVal[i] != null)
					model.setCodeVal(codeVal[i]);
				if (searchCdTp[i] != null)
					model.setSearchCdTp(searchCdTp[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (subsystem[i] != null)
					model.setSubsystem(subsystem[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getCodeMgmtCondVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return CodeMgmtCondVO[]
	 */
	public CodeMgmtCondVO[] getCodeMgmtCondVOs(){
		CodeMgmtCondVO[] vos = (CodeMgmtCondVO[])models.toArray(new CodeMgmtCondVO[models.size()]);
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
		this.searchtype = this.searchtype .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.codeid = this.codeid .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.codeVal = this.codeVal .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.searchCdTp = this.searchCdTp .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.subsystem = this.subsystem .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}