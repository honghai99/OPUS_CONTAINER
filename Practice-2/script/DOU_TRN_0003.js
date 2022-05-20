/*=========================================================
*Copyright(c) 2020 CyberLogitec
*@FileName : DOU_TRN_0002.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2020.03.17
*@LastModifier : 
*@LastVersion : 1.0
* 2020.03.17 
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
     */

    /**
     * @extends 
     * @class DOU_TRN_0002 : DOU_TRN_0002 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */

	var sheetObjects=new Array();
	var sheetCnt=0;
	document.onclick=processButtonClick;

	
	function processButtonClick(){
        /***** 탭당 시트가 2개 이상인 경우엔 추가 시트변수 지정하여 사용한 *****/
        /*******************************************************/
        var formObject=document.form1; //1
   	try {
   		var srcName=ComGetEvent("name");
           switch(srcName) {
       	    case "btn_Retrieve":
   	            doActionIBSheet(sheetObjects[0],formObject,IBSEARCH);
   	            //doActionIBSheet(sheetObjects[1],formObject,IBSEARCH);
       	        break;
         
       			/*****************grid button ************************/				
				case "btn_rowadd_mst": //add row  
	                doActionIBSheet(sheetObjects[0],	formObject,	IBINSERT);
					break;
				case "btn_rowdelete_mst": //delete row
					doActionIBSheet(sheetObjects[0],	formObject,	IBDELETE);					
					break;        	        
				case "btn_rowadd_dtl": //add row  
	                doActionIBSheet(sheetObjects[1],	formObject,	IBINSERT);
					break;
				case "btn_rowdelete_dtl": //delete row
					doActionIBSheet(sheetObjects[1],	formObject,	IBDELETE);					
					break;  

           } // end switch
   	}catch(e) {
   		if( e == "[object Error]") {
   			ComShowMessage(ComGetMsg('COM12111'));
   		} else {
   			ComShowMessage(e.message);
   		}
   	}
   }

	
	function loadPage() {
        for(i=0;i<sheetObjects.length;i++){
        //khlee-시작 환경 설정 함수 이름 변경
            ComConfigSheet(sheetObjects[i]);
            initSheet(sheetObjects[i],i+1);
        //khlee-마지막 환경 설정 함수 추가
            ComEndConfigSheet(sheetObjects[i]);
        }

    }

	
	function setSheetObject(sheet_obj){
		sheetObjects[sheetCnt++]=sheet_obj;
	}
	
	function initSheet(sheetObj,sheetNo) {
		switch (sheetNo) {
		case 1: // sheet1 init
			with (sheetObj) {
	
				var HeadTitle="|SubSystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date" ;
				var prefix="sheet1_";
	
				SetConfig({SearchMode : 2, MergeSheet : 5, Page : 20, DataRowMerge : 0});
	
				var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
				var headers = [ { Text : HeadTitle, Align : "Center" }];
				InitHeaders(headers, info);
	
	            var cols = [ {Type:"Status",    Hidden:1, Width:10,     Align:"Center",  ColMerge:0,   SaveName:prefix+"ibflag",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Text",      Hidden:0,  Width:70,    Align:"Center",  ColMerge:0,   SaveName:prefix+"ownr_sub_sys_cd", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Text",      Hidden:0,  Width:60,    Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_id",      KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
				             {Type:"Text",      Hidden:0,  Width:200,   Align:"Left",    ColMerge:0,   SaveName:prefix+"intg_cd_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Text",      Hidden:0,  Width:50,    Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_len",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Combo",     Hidden:0,  Width:100,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_tp_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Text",      Hidden:0,  Width:150,   Align:"Left",    ColMerge:0,   SaveName:prefix+"mng_tbl_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
				             {Type:"Text",      Hidden:0,  Width:350,   Align:"Left",    ColMerge:0,   SaveName:prefix+"intg_cd_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Combo",     Hidden:0,  Width:40,    Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_use_flg", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
				             {Type:"Text",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:prefix+"cre_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
				             {Type:"Date",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:prefix+"cre_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
				             {Type:"Text",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:prefix+"upd_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
				             {Type:"Date",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:prefix+"upd_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 } ];
	                     
	
				InitColumns(cols);
//				var tmp=subSysCd.substring(1,subSysCd.length-1).split(", ");
//				
//				SetColProperty(prefix+"ownr_sub_sys_cd", {ComboText:tmp.join("|"), ComboCode:tmp.join("|")} );
//            	SetColProperty(prefix+"intg_cd_tp_cd", {ComboText:"General Code|Table Code", ComboCode:"G|T"} );
//            	SetColProperty(prefix+"intg_cd_use_flg", {ComboText:"Y|N", ComboCode:"Y|N"} );
            	//sheetObj.SetMergeSheet(msAll);
            	SetEditable(1);
	            SetSheetHeight(240);
				
			
			}
			break;
		case 2:      //IBSheet2 init
        	with(sheetObj){
				//js common de xem function
                var HeadTitle="|Cd ID|Cd Val|Display Name|Description Remark|Order" ;//lam them 1 cai header title nua, cai nao giong de chung, nao khac de rieng, tuong tu 3-4 cai.
                var prefix="sheet2_";

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:1,  Width:10,   Align:"Center",  ColMerge:0,   SaveName:prefix+"ibflag",              KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
						     {Type:"Text",      Hidden:1,  Width:10,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_id",          KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_val_ctnt",    KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:200,  Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_val_dp_desc", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:600,  Align:"Left",    ColMerge:0,   SaveName:prefix+"intg_cd_val_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_val_dp_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                 
                InitColumns(cols);
                
                SetEditable(1);
//	            SetSheetHeight(150);
	            resizeSheet();
    		}
            break;
        }
	}
	
	function resizeSheet() {
		ComResizeSheet(sheetObjects[1]);
	}
	
	
	function doActionIBSheet(sheetObj,formObj,sAction) {
		sheetObj.ShowDebugMsg(false);
	//	if (!validateForm(sheetObj, formObj, sAction)) {
	//		return false;
	//	}
		switch (sAction) {
		 case IBSEARCH:      // retrieve	
			
				
                 if ( sheetObj.id == "sheet1" ) {           
 					formObj.f_cmd.value=SEARCH01;		
 					var arr1=new Array("sheet1_", "");
 					var sParam1=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr1);
// 					var sXml1=sheetObj.GetSearchData("DOU_TRN_0002GS.do", sParam1);
 					sheetObj.DoSearch("DOU_TRN_0002GS.do", sParam1,{Sync:1} );
// 					if(sXml1.length>0){
//						sheetObj.LoadSearchData(sXml1,{Sync:1} );
//					}
//					sheetObjects[1].RemoveAll();
//					formObj.codeid.value='';
                 } else if ( sheetObj.id == "sheet2" ) {
 					formObj.f_cmd.value=SEARCH02;
 					var arr2=new Array("sheet2_", "");
 		        	var sParam2=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr2);
  					var sXml2=sheetObj.GetSearchData("DOU_TRN_0002GS.do", sParam2);
  					console.log(typeof(sXml2));
 					if(sXml2.length>0){
 						sheetObj.LoadSearchData(sXml2,{Sync:1} );					
//                 	formObj.f_cmd.value = SEARCH02;
//                   sheetObj.DoSearch4Post("ADM_EDM_0001GS.do", FormQueryString(formObj));
                 }
             }
             break;
		case IBSAVE: 
			if(confirm("Do you save selected codes?")){//khong dung pop up
                //doActionIBSheet(sheetObjects[2],formObject,IBSEARCH);
            	  if((sheetObjects[0].RowCount("I")+sheetObjects[0].RowCount("U")+sheetObjects[0].RowCount("D")) >0 ){
            		  doActionIBSheet(sheetObjects[0],formObject,IBSAVE);
            	  } 
            	  if((sheetObjects[1].RowCount("I")+sheetObjects[1].RowCount("U")+sheetObjects[1].RowCount("D")) >0 ) {
            		  doActionIBSheet(sheetObjects[1],formObject,IBSAVE);
            	  }
              }
			break;
		case IBINSERT: //Row Add button event
			sheetObj.DataInsert(-1);
			break;
		case IBDELETE: //Row Delete button event
			var sheetprefix=sheetObj.id+"_";
        	var j=sheetObj.GetSelectRow();
        	sheetObj.SetCellValue(j, sheetprefix+"ibflag","D");
        	sheetObj.SetRowHidden(j,1);
        	//sheet1을 삭세하면 sheet2 하위 아이템 역시 삭제 처리함
        	if( sheetObj.id == "sheet1" ){
        		var codeid=sheetObj.GetCellValue(j, "sheet1_intg_cd_id");
        		if( sheetObjects[1].RowCount()> 0 && codeid==document.form1.codeid.value){
        		      for(i=sheetObjects[1].LastRow();i>0;i--){
        		    	  sheetObjects[1].SetCellValue(i, "sheet2_ibflag","D");
        		    	  sheetObjects[1].SetRowHidden(i,1);
        		        }
        		}
        	}
	 	    break; 
		}
	}
	
	
	
	function validateForm(sheetObj, formObj, sAction) {
		sheetObj.ShowDebugMsg(false);
		
	}
	
	function sheet1_OnDblClick(sheetObj, Row, Col) {
    	ComSetObjValue(document.form1.codeid, sheetObj.GetCellValue(Row, "sheet1_intg_cd_id"));
    	doActionIBSheet(sheetObjects[1],document.form1,IBSEARCH);
    	//truyen id cua event, bat su kien, id cua master roi truyen vao detail,
    	//GetSelectRow, lay id master 
    }
	
	

	    
