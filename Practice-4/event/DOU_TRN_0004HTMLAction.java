/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0004HTMLAction.java
*@FileTitle : Practice 4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.newgen.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.newgen.errmsgmgmt.vo.ErrMsgVO;


/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.newgen 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 newgenSC로 실행요청<br>
 * - newgenSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Hai To
 * @see newgenEvent 참조
 * @since J2EE 1.6
 */

public class DOU_TRN_0004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_0004HTMLAction 객체를 생성
	 */
	public DOU_TRN_0004HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 newgenEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		DouTrn0004Event event = new DouTrn0004Event();
		ErrMsgVO errMsgVO = new ErrMsgVO();
		if(command.isCommand(FormCommand.MULTI)) {
			event.setErrMsgVOS((ErrMsgVO[])getVOs(request, ErrMsgVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {		
			errMsgVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", "")); //get param carrier
			errMsgVO.setVndrSeq(JSPUtil.getParameter(request, "vndr_seq", ""));  //get param lane
			errMsgVO.setCreDtFm(JSPUtil.getParameter(request, "cre_dt_fm", ""));
			errMsgVO.setCreDtTo(JSPUtil.getParameter(request, "cre_dt_to", ""));
			event.setErrMsgVO(errMsgVO); 
		} else if(command.isCommand(FormCommand.COMMAND01)) {
			event.setErrMsgVO((ErrMsgVO)getVO(request, ErrMsgVO.class,"")); //java reflection
		}
	

		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}