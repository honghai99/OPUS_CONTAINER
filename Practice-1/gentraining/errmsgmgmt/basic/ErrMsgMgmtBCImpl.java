/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBCImpl.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.03.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.03.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.basic;

import java.util.ArrayList;
import java.util.List;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.integration.ErrMsgMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS-GenTraining Business Logic Command Interface<br>
 * - ALPS-GenTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Hai To
 * @since J2EE 1.6
 */
//this class will calls the DBDAO layer
//this class extends BasicCommandSupport and implements method from interface BC
public class ErrMsgMgmtBCImpl extends BasicCommandSupport implements ErrMsgMgmtBC {

	// Database Access Object
	private transient ErrMsgMgmtDBDAO dbDao = null;

	/**
	 * ErrMsgMgmtBCImpl 객체 생성<br>
	 * ErrMsgMgmtDBDAO를 생성한다.<br>
	 * create ErrMsgMgmtBCImpl and ErrMsgMgmtDBDAO object.
	 */
	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<ErrMsgVO> ErrMsgVO(ErrMsgVO errMsgVO) throws EventException {// it will return the List of error message, so data type of this method will be set to List<>
		try {
			return dbDao.searchErrMsgVO(errMsgVO); //it call the DBDAO layer to get data
		} catch(DAOException ex) {//if it gets any error, exception will catch it
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void ErrMsgVO(ErrMsgVO[] errMsgVO, SignOnUserAccount account) throws EventException{ // because delete, update, add will not return any value, so set it void
		try {
			List<ErrMsgVO> insertVoList = new ArrayList<ErrMsgVO>();//declare the List for handling insert command
			List<ErrMsgVO> updateVoList = new ArrayList<ErrMsgVO>();//declare the List for handling update command
			List<ErrMsgVO> deleteVoList = new ArrayList<ErrMsgVO>();//declare the List for handling delete command
			List<ErrMsgVO> list = null;

			for ( int i=0; i<errMsgVO .length; i++ ) {// the loop will check the ckeckflag from Event-->HTMLACtion-->SC--> BC
				//duplicate
				if (errMsgVO[i].getIbflag().equals("I") || errMsgVO[i].getIbflag().equals("U")) {
					list = ErrMsgVO(errMsgVO[i]);
					if(list!=null && list.size()>0) {
						throw new EventException(new ErrorHandler("Duplicated data").getMessage());
					}
				}
				
				
				
				if ( errMsgVO[i].getIbflag().equals("I")){ // if it's status (ibflag) is "I": Insert 
					errMsgVO[i].setCreUsrId(account.getUsr_id()); //set the userid which added the data
					insertVoList.add(errMsgVO[i]);//add new error message
				} else if ( errMsgVO[i].getIbflag().equals("U")){// if it's status (ibflag) is "U": Update 
					errMsgVO[i].setUpdUsrId(account.getUsr_id()); //set the userid which update the data
					updateVoList.add(errMsgVO[i]);//update and add the new error message
				} else if ( errMsgVO[i].getIbflag().equals("D")){ // if it's status (ibflag) is "D": Delete 
					deleteVoList.add(errMsgVO[i]); //set the blank to new array
				}
			}
			
			if ( insertVoList.size() > 0 ) { //check, does it have any modifications, if it contains data --> call to dbdao
				dbDao.addErrMsgVOS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyErrMsgVOS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeErrMsgVOS(deleteVoList);
			}
		} catch(DAOException ex) { //catch exception if it get any error
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}