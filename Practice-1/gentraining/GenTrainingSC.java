/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GenTrainingSC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.03.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.03.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.gentraining;

import java.util.List;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.event.GenTrn0001Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.vo.ErrMsgVO;


/**
 * ALPS-GenTraining Business Logic ServiceCommand - ALPS-GenTraining 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Hai To
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */
//Now HTML Action will communicate with SC which extends SCSupport
/*
 * Implement the business processing using a database and associated data through the basic command(Business impl.java), as the class that implements the practical business logic
	Process the various Event such as search, modification, deletion, addition etc.
	Inherit the ServiceCommandSupport	
	As a class that implements practical business logic, it implements business processing using database and connection data through Basic Command (work impl.java).
	It processes various events such as inquiry, modification, deletion, and addition.
 */
public class GenTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * GenTraining system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 * GenTraining system task scenario precedent work<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("GenTrainingSC 시작");//debugging
		try {
			// 일단 comment --> 로그인 체크 부분
			//Once in the comment --> login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * GenTraining system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 * when business works are finished
	 */
	public void doEnd() {
		log.debug("GenTrainingSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-GenTraining system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * Carry out the business scene corresponding to each event 
	 * The result of the task is to create a GeneralEventResponse object and return the result.
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		//Which part to use if SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("GenTrn0001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {//HTML send fcommand to SC by its command
				eventResponse = searchErrMsgVO(e);//if it search, it will call searchErrMsgVO function
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = multiErrMsgVO(e);//if it save, delete, update, it will call multiErrMsgVO function
			}
		}
		return eventResponse;
	}
	/**
	 * GEN_TRN_0001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0001Event event = (GenTrn0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();//declare the BC object to communicate with SC 

		try{
			List<ErrMsgVO> list = command.ErrMsgVO(event.getErrMsgVO()); //save all the event to list, and it will call function in BC interface
			//List<ErrMsgVO> list = command.searchErrMsgVO(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){//catch the exception when error occurs 
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * GEN_TRN_0001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse multiErrMsgVO(Event e) throws EventException {//if it save, delete, update, multiErrMsgVO function is called
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0001Event event = (GenTrn0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl(); //declare the BC object to communicate with SC 
		try{
			begin(); //begin the transaction
			command.ErrMsgVO(event.getErrMsgVOS(),account); // implement the transcation
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage()); //set the message if its successful or failure 
			commit();//execute the transaction
		} catch(EventException ex) {// if it gets any exception
			rollback();//roll back the lastest transaction
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}