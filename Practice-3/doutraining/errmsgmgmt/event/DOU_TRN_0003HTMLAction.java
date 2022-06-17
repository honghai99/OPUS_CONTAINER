/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0003HTMLAction.java
*@FileTitle : UI Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgMstVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.doutraining 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 DouTrainingSC로 실행요청<br>
 * - DouTrainingSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Hai To
 * @see DouTrainingEvent 참조
 * @since J2EE 1.6
 */

public class DOU_TRN_0003HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_0003HTMLAction 객체를 생성
	 */
	public DOU_TRN_0003HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 DouTrainingEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		DouTrn0003Event event = new DouTrn0003Event();
		ErrMsgMstVO searchVO = new ErrMsgMstVO();

		
		if(command.isCommand(FormCommand.SEARCH)) {		
			searchVO.setFrAcctYrmon(JSPUtil.getParameter(request, "fr_acct_yrmon", ""));
			searchVO.setToAcctYrmon(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
			searchVO.setJoCrrCd(JSPUtil.getParameter(request, "jo_crr_cd", ""));
			searchVO.setRlaneCd(JSPUtil.getParameter(request, "rlane_cd", ""));
			searchVO.setTrdCd(JSPUtil.getParameter(request, "trd_cd", ""));	
//			event.setErrMsgMstVOs(searchVO);
		} else if(command.isCommand(FormCommand.SEARCH01)) {
			searchVO.setJoCrrCd(JSPUtil.getParameter(request, "jo_crr_cd", ""));
			searchVO.setRlaneCd(JSPUtil.getParameter(request, "rlane_cd", ""));
		} else if(command.isCommand(FormCommand.SEARCH02)) {
			searchVO.setRlaneCd(JSPUtil.getParameter(request, "rlane_cd", ""));
			searchVO.setTrdCd(JSPUtil.getParameter(request, "trd_cd", ""));
		} else if(command.isCommand(FormCommand.SEARCH03)) {		
			searchVO.setFrAcctYrmon(JSPUtil.getParameter(request, "fr_acct_yrmon", ""));
			searchVO.setToAcctYrmon(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
			searchVO.setJoCrrCd(JSPUtil.getParameter(request, "jo_crr_cd", ""));
			searchVO.setRlaneCd(JSPUtil.getParameter(request, "rlane_cd", ""));
			searchVO.setTrdCd(JSPUtil.getParameter(request, "trd_cd", ""));			
		} else if(command.isCommand(FormCommand.SEARCH04)) {		
			searchVO.setFrAcctYrmon(JSPUtil.getParameter(request, "fr_acct_yrmon", ""));
			searchVO.setToAcctYrmon(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
//			event.setErrMsgMstVOs((ErrMsgMstVO)getVO(request, ErrMsgMstVO.class, ""));	
		}
		event.setErrMsgMstVOs(searchVO);
		System.out.println(searchVO.getJoCrrCd());
		System.out.println(searchVO.getToAcctYrmon());
		System.out.println(searchVO.getFrAcctYrmon());
		System.out.println(searchVO.getRlaneCd());
		System.out.println(searchVO.getTrdCd());
		
//		event.setErrMsgMstVOs(searchVO);
		//request.setAttribute("Event", event);
		
		return event;
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