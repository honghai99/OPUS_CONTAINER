/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : newgenSC.java
*@FileTitle : Practice 4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.newgen;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.newgen.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clv.newgen.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.newgen.errmsgmgmt.event.DouTrn0004Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.newgen.errmsgmgmt.vo.ErrMsgVO;


/**
 * ALPS-newgen Business Logic ServiceCommand - ALPS-newgen 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Hai To
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class newgenSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * newgen system 업무 시나리오 선행작업<br>
	 * 
	 */
	public void doStart() {
		log.debug("newgenSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * newgen system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("newgenSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-newgen system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("DouTrn0004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsgVO(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageErrMsgVO(e);
			} else if(e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initData(e);
			} else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = checkDuplicated(e);
			}
		}
		return eventResponse;
	}
	/**
	 * DOU_TRN_0004 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0004Event event = (DouTrn0004Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();

		try{
			List<ErrMsgVO> list = command.searchErrMsgVO(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * DOU_TRN_0004 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0004Event event = (DouTrn0004Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		try{
			begin();
			command.manageErrMsgVO(event.getErrMsgVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("BKG06071").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	//set ETC data for UI to make combobox
	private EventResponse initData(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0004Event event = (DouTrn0004Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		try {
			List<ErrMsgVO> carriers = command.searchCarrier(event.getErrMsgVO());
			StringBuilder carrierBuilder = new StringBuilder();
			
			if(carriers!=null && carriers.size()>0) {
				for(int i = 0; i<carriers.size(); i++) {
					carrierBuilder.append(carriers.get(i).getJoCrrCd());	
					if (i<carriers.size()-1) {
						carrierBuilder.append("|");
					}
				}
			}		
			
			List<ErrMsgVO> lanes = command.searchLane(event.getErrMsgVO());
			StringBuilder laneBuilder = new StringBuilder();
			
			if(lanes!=null && lanes.size()>0) {
				for(int i = 0; i<lanes.size(); i++) {
					laneBuilder.append(lanes.get(i).getRlaneCd());
					if(i<lanes.size()-1) {
						laneBuilder.append("|");
					}
				}
			}
			
		eventResponse.setETCData("jo_crr_cd", carrierBuilder.toString());
		eventResponse.setETCData("rlane_cd", laneBuilder.toString());
					
		} catch (EventException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 	
		
		return eventResponse;		
	}
	// check dupliacte from server side
	private EventResponse checkDuplicated(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0004Event event = (DouTrn0004Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		try {
			//if any carrier and code exists in List, it will be > than 0 
			List<ErrMsgVO> carriers = command.searchErrMsgVO(event.getErrMsgVO());
			if(null == carriers){
				carriers = new ArrayList<>();
			}
			eventResponse.setETCData("ISEXIST", carriers.size() > 0 ? "Y" : "N");		 //catch ETC data from client "ISEXIST"
		} catch (EventException de) {
			log.error("err " +de.getMessage(),de );
			throw new EventException(de.getMessage());
			} 	
		
		return eventResponse;
		
	}
	
	
}