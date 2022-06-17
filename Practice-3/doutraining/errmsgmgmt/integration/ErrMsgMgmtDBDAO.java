/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtDBDAO.java
*@FileTitle : UI Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgMstVO;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVORSQL;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;


/**
 * ALPS ErrMsgMgmtDBDAO <br>
 * - ALPS-DouTraining system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Hai To
 * @see ErrMsgMgmtBCImpl 참조
 * @since J2EE 1.6
 */
public class ErrMsgMgmtDBDAO extends DBDAOSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception DAOException
	 */
	 
	@SuppressWarnings("unchecked")
	public List<ErrMsgMstVO> searchPartner(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;	
		List<ErrMsgMstVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			if(errMsgMstVO!=null) {
				Map<String, String> mapVO = errMsgMstVO.getColumnValues();
				
				
				param.putAll(mapVO);
								
				velParam.putAll(mapVO);

			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOSearchPartnerRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	public List<ErrMsgMstVO> searchLane(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;		
		List<ErrMsgMstVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			if(errMsgMstVO != null) {
				Map<String, String> mapVO = errMsgMstVO.getColumnValues();
				
				List<String>  jo_crr_cds = new ArrayList<>();
				if(errMsgMstVO.getJoCrrCd() != null) {
					String[] crr_cd = errMsgMstVO.getJoCrrCd().split(",");
					for(int i = 0; i<crr_cd.length; i++) {
						jo_crr_cds.add(crr_cd[i]);
					}
				}
				
				param.putAll(mapVO);
				param.put("jo_crr_cds", jo_crr_cds);
									
				velParam.putAll(mapVO);
				velParam.put("jo_crr_cds", jo_crr_cds);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOSearchRLaneCdRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);		
			
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	public List<ErrMsgMstVO> searchTrade(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<ErrMsgMstVO> list = new ArrayList();
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		
		
		try {
			if(errMsgMstVO != null) {
				Map<String, String> mapVO = errMsgMstVO.getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			}	
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOSearchTrdCdRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);	
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ErrMsgMstVO> searchDetail(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<ErrMsgMstVO> list = null;
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(errMsgMstVO != null){
				Map<String, String> mapVO = errMsgMstVO .getColumnValues();
				List<String>  jo_crr_cds = new ArrayList<>();
				if(errMsgMstVO.getJoCrrCd() != null) {
					String[] crr_cd = errMsgMstVO.getJoCrrCd().split(",");
					for(int i = 0; i<crr_cd.length; i++) {
						jo_crr_cds.add(crr_cd[i]);
					}
				}
				
				param.putAll(mapVO);
				param.put("jo_crr_cds", jo_crr_cds);
									
				velParam.putAll(mapVO);
				velParam.put("jo_crr_cds", jo_crr_cds);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgMstVO .class);	
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	public DBRowSet searchExcel(ErrMsgMstVO errMsgMstVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<ErrMsgMstVO> list = null;
		//query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			
			if(errMsgMstVO != null){
				Map<String, String> mapVO = errMsgMstVO .getColumnValues();
				List<String>  jo_crr_cds = new ArrayList<>();
				if(errMsgMstVO.getJoCrrCd() != null) {
					String[] crr_cd = errMsgMstVO.getJoCrrCd().split(",");
					for(int i = 0; i<crr_cd.length; i++) {
						jo_crr_cds.add(crr_cd[i]);
					}
				}
				
				param.putAll(mapVO);
				param.put("jo_crr_cds", jo_crr_cds);
									
				velParam.putAll(mapVO);
				velParam.put("jo_crr_cds", jo_crr_cds);
			}
						
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOExcel(), param, velParam);
				
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return dbRowset;
		
	}
	
	
	
	
}