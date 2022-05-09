/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0001HTMLAction.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.03.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.03.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.gentraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.gentraining 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 GenTrainingSC로 실행요청<br>
 * - GenTrainingSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * * - Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.gentraining screen as a Java variable<br>
 * - Convert the parsing information into an Event, put it in the request and request execution with GenTrainingSC<br>
 * - Set EventResponse in request to send execution result from GenTrainingSC to View (JSP)<br>
 * @author Hai To
 * @see GenTrainingEvent 참조
 * @since J2EE 1.6
 */

public class GEN_TRN_0001HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * GEN_TRN_0001HTMLAction 객체를 생성
	 */
	public GEN_TRN_0001HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 GenTrainingEvent로 파싱하여 request에 셋팅<br>
	 * Parsing HTML DOM object's Value into Java variable<br>
	 * Parsing the information of HttpRequst as GenTrainingEvent 
	 * and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request); // declare the f command to receive the command from JS
		GenTrn0001Event event = new GenTrn0001Event(); // initialize the the Event object to catch event from front end
		
		ErrMsgVO ErrMsgVO  = new ErrMsgVO(); 
		ErrMsgVO.setErrMsgCd(JSPUtil.getParameter(request, "s_err_msg_cd", "")); // receive param from front end - search by error message code
		ErrMsgVO.setErrMsg(JSPUtil.getParameter(request, "s_err_msg", "")); // receive param from front end - search by error message
		ErrMsgVO.setLangTpCd(JSPUtil.getParameter(request, "lang_tp_cd", ""));
		event.setErrMsgVO(ErrMsgVO);  // set param to object
		
		if(command.isCommand(FormCommand.MULTI)) { // if its command is multi: delete, update, or add, it is assigned in js file
			event.setErrMsgVOS((ErrMsgVO[])getVOs(request, ErrMsgVO .class,""));//if it's a list, getVOs
		}
		else if(command.isCommand(FormCommand.SEARCH)) {// if its command is search, it will move to here
			event.getErrMsgVO(); //get the parameter
			event.setErrMsgVO((ErrMsgVO)getVO(request, ErrMsgVO .class));// and search by command, only search so it's getVO
		}
		
		request.setAttribute("Event", event);//set attribute to event
		
		
		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * Saving the business scenario execution result value in the attribute of HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from ServiceCommand
	 * to View (JSP) in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);//response from Event to JSP
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 * 
	/**
	* Save HttpRequest parsing result value in HttpRequest attribute<br>
	* HttpRequest parsing result value set in request<br>
	*
	* @param request HttpServletRequest HttpRequest
	* @param event An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {// receive attribute from Event
		request.setAttribute("Event", event);
	}
}