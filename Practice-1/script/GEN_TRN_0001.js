/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Gen_TRN_0001.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.03.24
*@LastModifier : 
*@LastVersion : 1.0
* 2022.03.26 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
   /**
     * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
     * @author 한진해운
     * HongHai
     */

    /**
     * @extends 
     * @class GEN_TRN_0001 : GEN_TRN_0001 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    function GEN_TRN_0001() {// co the bo duoc, 
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;	
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    // tim hieu setSheetObject
   	/* 개발자 작업	*/ 
    /* Develop works */

	var sheetObjects=new Array();//global declaration for manipulating in any function
	var sheetCnt=0; // counting sheet, = 0: first sheet
	var rowcount=0; // counting row for add row, display total row
	
	document.onclick=processButtonClick;// event fires when click on button
	// 버튼 네임으로 구분하여 프로세스를 분기처리하는 이벤트핸들러 */
    function processButtonClick(){
         /***** 탭당 시트가 2개 이상인 경우엔 추가 시트변수 지정하여 사용한 *****
          * 
If there are more than two sheets per tab, additional sheet variables are specified and used./
         
         /*******************************************************/
    	 var sheetObject1=sheetObjects[0];//set array[0] for first sheet
         var formObject=document.form;//assgin form to formObject
    	try {
    		var srcName=ComGetEvent("name"); // Receive the name of event and based on which clicked button, it will go to identified situation.
            switch(srcName) {
            	case "btn_Add"://if add button is clicked, it will call doActionIBSheet with IBINSERT parameter.
            		doActionIBSheet(sheetObject1,formObject,IBINSERT);
            		break;
            	case "btn_Retrieve"://if retrieve button is clicked, it will call doActionIBSheet with IBSEARCH parameter.
            		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
            		break;
            	case "btn_Save"://if save button is clicked, it will call doActionIBSheet with IBSAVE parameter.
            		doActionIBSheet(sheetObject1,formObject,IBSAVE);
            		break;
            	case "btn_DownExcel"://if DownExcel button is clicked, it will call doActionIBSheet with IBDOWNEXCEL parameter.
            		doActionIBSheet(sheetObject1,formObject,IBDOWNEXCEL);
            		break;
            	case "btn_Del"://if btn_Del button is clicked, it will call doActionIBSheet with IBDELETE parameter.
               		doActionIBSheet(sheetObject1,formObject,IBDELETE);
               		break;
               	default:
               		break;
               		
            } // end switch
    	} catch(e) {
    		if( e == "[object Error]") {
    			ComShowMessage(OBJECT_ERROR);//in CoMessage, it contains all the errors according to code, and popups the suitable error.
    		} else {
    			ComShowMessage(e);
    		}
    	}
    }
    	
    	function setSheetObject(sheet_obj){//put sheet in global variable, comsheetobject call this function
    	       sheetObjects[sheetCnt++]=sheet_obj;//the more sheets in project, the more variables they have
    	}
    	
    	function loadPage() {// function that calls a common function, first call when fire jsp onload Event
    		for(i=0;i<sheetObjects.length;i++){ // set for-loop for sheet based on quantity
    			//khlee-시작 환경 설정 함수 이름 변경
    			ComConfigSheet (sheetObjects[i]);//
    			initSheet(sheetObjects[i],i+1);// initialize the sheet, with configuration for sheet, (title, columns...)
    			//khlee-마지막 환경 설정 함수 추가
    			ComEndConfigSheet(sheetObjects[i]);//
    			doActionIBSheet(sheetObjects[i],document.form,IBSEARCH) //after loading event fires, it will retrive when first load
    		}
    	}
	
    	function initSheet(sheetObj,sheetNo) { // setting some configurations for sheet
            var cnt=0;
    		var sheetID=sheetObj.id;
            switch(sheetID) {
                case "sheet1":
                	with(sheetObj){
                    var HeadTitle="|Del|Msg Cd|Msg Type|Msg level|Message|Description";
                    
                    //22/4: giao dien cho ibsheet/data colorder;
                    //de customize thi extend viewadapter, de chinh sua
                    //var headCount=ComCountHeadTitle(HeadTitle);

                    SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );

                    var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                    var headers = [ { Text:HeadTitle, Align:"Center"} ];
                    InitHeaders(headers, info);

                    var cols = [ {Type:"Status",    Hidden:0, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
                                 {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
                                 {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_msg_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
                                 {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_tp_cd",   KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"Server|UI|Both", ComboCode:"S|U|B" },
	    	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_lvl_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"ERR|WARNING|INFO", ComboCode:"E|W|I" },
	    	                     {Type:"Text",      Hidden:0, Width:400,  Align:"Left",    ColMerge:0,   SaveName:"err_msg",     KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, MultiLineText:1 },
	    	                     {Type:"Text",      Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"err_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                      
                    InitColumns(cols);
                    SetWaitImageVisible(0);
                    resizeSheet();
                    }
                break;
            }
        }
    	// lack of skill: framework: deeper 
    	//cần hiểu sâu hơn về framework. 
    	//improve: 
    	//+ source code: clean code, format(đẹp easy to maintain)
    	//+ standard UI: giao diện. 
    	//+ 
	
    	function resizeSheet(){
    	   	         ComResizeSheet(sheetObjects[0]);
    	}
    	
 
    	function sheet1_OnSaveEnd(sheetObj, Code, Msg, StCode, StMsg) { 
    		ComShowCodeMessage("COM130102");
//    		msgs['COM130101'] = 'Do you want to save {?msg1}?';
//    		msgs['COM130102'] = '{?msg1} was saved successfully.';
         }
    	
    	function doActionIBSheet(sheetObj,formObj,sAction) {
            switch(sAction) {
    			case IBSEARCH:      //조회
    				if(!validateForm(sheetObj,formObj,sAction)) return
                    //조회처리
    				ComOpenWait(true);
    				formObj.f_cmd.value=SEARCH;
     				sheetObj.DoSearch("GEN_TRN_0001GS.do", FormQueryString(formObj) );
    				break;
    			case IBSAVE:        //저장
    				if(!validateForm(sheetObj,formObj,sAction))return;
                    //저장처리
                	formObj.f_cmd.value=MULTI;
                    sheetObj.DoSave("GEN_TRN_0001GS.do", FormQueryString(formObj));
    				break;
    			case IBINSERT:      // 입력
    				rowcount=sheetObj.RowCount();
    				row=sheetObj.DataInsert();
    				break;
    			case IBDOWNEXCEL:	//엑셀다운로드
    				if(sheetObj.RowCount() < 1){
    					ComShowCodeMessage("COM132501");
    				}else{
    					sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
    				}
    				break;
    			case IBDELETE: //Row Delete button event
    				for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
    					if(sheetObj.GetCellValue(i, "DEL") == 1){
    						sheetObj.SetRowHidden(i, 1);
    						sheetObj.SetRowStatus(i,"D");
    					}
    				}
    				break;
            }
        }
	
    	function validateForm(sheetObj,formObj,sAction){
            with(formObj){
//                if (!isNumber(formObj.iPage)) {
//                    return false;
//                }
            }
            return true;
        }
    	
    	function sheet1_OnPopupClick(sheetObj,Row,Col){
            switch (sheetObj.ColSaveName(Col)) {
           	case "dev_dt" :
    			var cal=new ComCalendarGrid();
    			cal.select(sheetObj, Row, Col, 'yyyyMMdd');
                break;
            case "roles" :
    			if(sheetObj.GetCellValue(Row,"ibflag")=="I" || sheetObj.GetCellValue(Row,"ibflag")=="U"){
                	 ComShowCodeMessage('COM12151','Program');
    			}else if(sheetObj.GetCellValue(Row,"ibflag")=="R" && sheetObj.GetCellValue(Row,"pgm_mnu_div_cd")=="01"){
    				 ComShowCodeMessage('COM12190');
                 }else{
                	 alert('wait!!!!!!!!')// khong duoc dung
        		       //noRtnPopup('progRoleMapping.do?pgm_no='+sheetObj.CellValue(Row,"pgm_no")+"&pgm_nm="+escape(sheetObj.CellValue(Row,"pgm_nm")),'width=700,height=474,left=200,top=100,menubar=0,status=0,scrollbars=0,resizable=1');
                 }
                 break;
            }
    	}
    	
//event fires when cell in sheet1 is changed
   	function sheet1_OnChange(sheetObj,Row,Col){
      	 if(Col == 2){ // dung switch case 
  			var code=sheetObj.GetCellValue(Row, Col);
  			var regex = /^[A-Z]{3}[0-9]{5}$/;
  			console.log(regex.test(code));
  			console.log(code.length);
  			if(regex.test(code) == true && code.length == 8) {
	       	    for(var int=1; int < sheetObj.RowCount(); int++) {
	   			var orlcode=sheetObj.GetCellValue(int, Col);
	   			/* null 인 경우와 자기 자신은 비교할 필요가 없음 */
	   				if(code != '' && int != Row && code == orlcode){
	       				 //ComShowMessage("동일한 Message Code가 존재합니다.");
	       				 ComShowCodeMessage('COM12115',code);
	       				 sheetObj.SetCellValue(Row, Col,"");
	       				 return;
	       			 }
	       		 }
  			} else {
  				ComShowCodeMessage('COM12115',code);
  				return;
  			}
      	 }
       }
    	
    	function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
         	ComOpenWait(false);
         }