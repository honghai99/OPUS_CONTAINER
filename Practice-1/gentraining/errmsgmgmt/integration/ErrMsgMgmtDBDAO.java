/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtDBDAO.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.03.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.03.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS ErrMsgMgmtDBDAO <br>
 * - ALPS-GenTraining system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Hai To
 * @see ErrMsgMgmtBCImpl 참조
 * @since J2EE 1.6
 */
/**
 * Business DBDAO is the class that is doing the DB related process by connecting to JDBC using the SQLExecuter class among integration packages
	SQLExecuter generates the data model with retrieving query and return it.

 */
public class ErrMsgMgmtDBDAO extends DBDAOSupport {

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * [Act] to [process] information
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception DAOException
	 */
	//skip the warning when it receive raw data
	 @SuppressWarnings("unchecked")
	public List<ErrMsgVO> searchErrMsgVO(ErrMsgVO errMsgVO) throws DAOException { //for searching data
		DBRowSet dbRowset = null;
		List<ErrMsgVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(errMsgVO != null){// if errMsgVO has data
				Map<String, String> mapVO = errMsgVO .getColumnValues();//it gets the column values and assign to mapVO
			
				param.putAll(mapVO); //assgin param to mapVO
				velParam.putAll(mapVO); // assign velparam to mapVO , to the SQL query, vel param is for some condition, loop, raw data type 
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVORSQL(), param, velParam);//then execute  the query and transmit param, velparam to sqlexecute
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgVO .class);//set the returned data to list in order to send back to BCImpl
		} catch(SQLException se) { // it catches the Exception from SQL
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list; //return the list to BCImpl
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * [Act] to [process] information
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @exception DAOException
	 * @exception Exception
	 */
//	public void addErrMsgVO(ErrMsgVO errMsgVO) throws DAOException,Exception { //for adding new data
//		
//		//query parameter
//		Map<String, Object> param = new HashMap<String, Object>();
//		//velocity parameter
//		Map<String, Object> velParam = new HashMap<String, Object>();
//		try {
//			Map<String, String> mapVO = errMsgVO .getColumnValues();//it gets the column values and assign to mapVO
//			
//			param.putAll(mapVO);//assgin param to mapVO
//			velParam.putAll(mapVO);// assign velparam to mapVO , to the SQL query, vel param is for some condition, loop, raw data type 
//			
//			SQLExecuter sqlExe = new SQLExecuter("");
//			int result = sqlExe.executeUpdate((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVOCSQL(), param, velParam); // result will return the number for each description, if returned number is -2: successfully, -3 fail
//			if(result == Statement.EXECUTE_FAILED)
//					throw new DAOException("Fail to insert SQL");
//		} catch(SQLException se) { // it catches the Exception from SQL
//			log.error(se.getMessage(),se);
//			throw new DAOException(new ErrorHandler(se).getMessage());
//		} catch(Exception ex) {
//			log.error(ex.getMessage(),ex);
//			throw new DAOException(new ErrorHandler(ex).getMessage());
//		}
//	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
//	public int modifyErrMsgVO(ErrMsgVO errMsgVO) throws DAOException,Exception {//for updating new data
//		//query parameter
//		Map<String, Object> param = new HashMap<String, Object>();
//		//velocity parameter
//		Map<String, Object> velParam = new HashMap<String, Object>();
//		
//		int result = 0;
//		try {
//			Map<String, String> mapVO = errMsgVO .getColumnValues();//it gets the column values and assign to mapVO
//			
//			param.putAll(mapVO);//assgin param to mapVO
//			velParam.putAll(mapVO);// assign velparam to mapVO , to the SQL query, vel param is for some condition, loop, raw data type 
//			
//			SQLExecuter sqlExe = new SQLExecuter("");
//			result = sqlExe.executeUpdate((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVOUSQL(), param, velParam);// result will return the number for each description, if returned number is -2: successfully, -3 fail
//			if(result == Statement.EXECUTE_FAILED)
//					throw new DAOException("Fail to insert SQL");
//		} catch(SQLException se) { // it catches the Exception from SQL
//			log.error(se.getMessage(),se);
//			throw new DAOException(new ErrorHandler(se).getMessage());
//		} catch(Exception ex) {
//			log.error(ex.getMessage(),ex);
//			throw new DAOException(new ErrorHandler(ex).getMessage());
//		}
//		return result; //return number to BCImpl, if it > 0, it will execute successfully 
//	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
//	public int removeErrMsgVO(ErrMsgVO errMsgVO) throws DAOException,Exception {
//		//query parameter
//		Map<String, Object> param = new HashMap<String, Object>();
//		//velocity parameter
//		Map<String, Object> velParam = new HashMap<String, Object>();
//		
//		int result = 0;
//		try {
//			Map<String, String> mapVO = errMsgVO .getColumnValues();
//			
//			param.putAll(mapVO);
//			velParam.putAll(mapVO);
//			
//			SQLExecuter sqlExe = new SQLExecuter("");
//			result = sqlExe.executeUpdate((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVODSQL(), param, velParam);
//			if(result == Statement.EXECUTE_FAILED)
//					throw new DAOException("Fail to insert SQL");
//		} catch(SQLException se) {
//			log.error(se.getMessage(),se);
//			throw new DAOException(new ErrorHandler(se).getMessage());
//		} catch(Exception ex) {
//			log.error(ex.getMessage(),ex);
//			throw new DAOException(new ErrorHandler(ex).getMessage());
//		}
//		return result; //return number to BCImpl, if it > 0, it will execute successfully 
//	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addErrMsgVOS(List<ErrMsgVO> errMsgVO) throws DAOException,Exception { //for adding new data
		int insCnt[] = null;
		
		
		try {
			
			SQLExecuter sqlExe = new SQLExecuter("");
			if(errMsgVO .size() > 0){ //if it contains data --> execute sql
				insCnt = sqlExe.executeBatch((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVOCSQL(), errMsgVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " It's duplicated");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler("Duplicated data").getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler("Duplicated data").getMessage());
		}
		return insCnt; 
	}
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifyErrMsgVOS(List<ErrMsgVO> errMsgVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(errMsgVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVOUSQL(), errMsgVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removeErrMsgVOS(List<ErrMsgVO> errMsgVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(errMsgVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVODSQL(), errMsgVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	
}