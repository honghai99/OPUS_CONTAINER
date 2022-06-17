/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTrainingSC.java
*@FileTitle : UI Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.event.DouTrn0003Event;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgMstVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;


/**
 * ALPS-DouTraining Business Logic ServiceCommand - ALPS-DouTraining 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Hai To
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class DouTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * DouTraining system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("DouTrainingSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * DouTraining system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("DouTrainingSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-DouTraining system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;	
		
		
		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("DouTrn0003Event")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = searchPartner(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchSummary(e);				
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchPartner(e);
				eventResponse = searchLane(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {		
				eventResponse = searchTrade(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH03)) {
				eventResponse = searchSummary(e);	
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH04)) {
				eventResponse = searchExcel(e);
			}
		}
		return eventResponse;
	}
	

	@SuppressWarnings("unchecked")
	private EventResponse searchExcel(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0003Event event = (DouTrn0003Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		List<Object> oList = new ArrayList<Object>();
		StringBuilder sbFileName = new StringBuilder();
		String fileName = "";
		String pgmNo = "";
		
		
		try {
			oList = command.searchExcel(event.getErrMsgMstVOs());
		
			fileName = sbFileName.append("DL.xls").toString();
			eventResponse.setCustomData("vos", (List<ErrMsgMstVO>)oList.get(0));
			eventResponse.setCustomData("title", (String[])oList.get(1));
			eventResponse.setCustomData("columns", (String[])oList.get(2));
			eventResponse.setCustomData("fileName", "DOU_TRN_0003DL.xls");
			eventResponse.setCustomData("isZip", false);
			eventResponse.setCustomData("sheetName", "honghai");
		} catch (Exception ex) {
			try {
				throw ex;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return eventResponse;
	}

	/**
	 * DOU_TRN_0003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 * @throws DAOException 
	 */
	private EventResponse searchSummary(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0003Event event = (DouTrn0003Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		
		try{
			
			List<ErrMsgMstVO> list = command.searchDetail(event.getErrMsgMstVOs());
			eventResponse.setRsVoList(list);

			
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	private	EventResponse searchLane(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0003Event event = (DouTrn0003Event) e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		try {
		
			List<ErrMsgMstVO> laneList = command.searchLane(event.getErrMsgMstVOs());
			StringBuilder laneBuilder = new StringBuilder();
			if(null != laneList && laneList.size() > 0){
				for(int i =0; i < laneList.size(); i++){
					laneBuilder.append(laneList.get(i).getRlaneCd());
					if (i < laneList.size() - 1){
						laneBuilder.append("|");
					}	
				}
			}
					
			eventResponse.setETCData("rlane_cd",  laneBuilder.toString());
					
		} catch (DAOException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 
		
		return eventResponse;		
	}
	
	private	EventResponse searchPartner(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0003Event event = (DouTrn0003Event) e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		try{
			List<ErrMsgMstVO> partnerList = command.searchPartner(event.getErrMsgMstVOs());		
			StringBuilder partnerBuilder = new StringBuilder();
					
			if(null != partnerList && partnerList.size() > 0){
				for(int i =0; i < partnerList.size(); i++){
					partnerBuilder.append(partnerList.get(i).getJoCrrCd());
					if (i < partnerList.size() - 1 ){
							partnerBuilder.append("|");
						}	
					}
				}
			
			eventResponse.setETCData("jo_crr_cd",  partnerBuilder.toString());		
					
			
		} catch (EventException | DAOException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 
		return eventResponse;		
	}
	
	private EventResponse searchTrade(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0003Event event = (DouTrn0003Event) e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		try {
			List<ErrMsgMstVO> tradeList = command.searchTrade(event.getErrMsgMstVOs());
			StringBuilder tradeBuilder = new StringBuilder();
			
			if(null != tradeList && tradeList.size() > 0){
				for(int i =0; i < tradeList.size(); i++){
					if(tradeList.get(i).getTrdCd()=="") {
						continue;
					}else
						tradeBuilder.append(tradeList.get(i).getTrdCd());
					if (i < tradeList.size() - 1){				
						tradeBuilder.append("|");
					}	
				}
			}
			
			eventResponse.setETCData("trd_cd",  tradeBuilder.toString());
		} catch (EventException | DAOException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 
		return eventResponse;
	}
		
	
	/**
	 * DOU_TRN_0003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
//	private EventResponse ErrMsgVO(Event e) throws EventException {
//		// PDTO(Data Transfer Object including Parameters)
//		GeneralEventResponse eventResponse = new GeneralEventResponse();
//		DouTrn0003Event event = (DouTrn0003Event)e;
//		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
//		try{
//			begin();
//			command.ErrMsgVO(event.getErrMsgVOS(),account);
//			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
//			commit();
//		} catch(EventException ex) {
//			rollback();
//			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
//		} catch(Exception ex) {
//			rollback();
//			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
//		}
//		return eventResponse;
//	}
}