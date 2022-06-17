/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0003.js
*@FileTitle : UI Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.06 
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
     * @class DOU_TRN_0003 : DOU_TRN_0003 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
  
   	/* 개발자 작업	*/
    //set sheet objects in an array
    var sheetObjects=new Array();
    var rows;
    //counting for multiple sheets
	var sheetCnt=0;
	
	//counting for multiple combobox
	var comboCnt = 0;
	
	//counting for multiple tab
	var tabCnt = 0;
	
	//set this equals to 1, because 
	var beforetab=1;
	document.onclick=processButtonClick;
	var comboObjects = new Array();
	var partnerIndex = 0;
	var laneIndex = 0;
	var laneComboObject = " |";
	var tradeComboObject = " |";
	var tabObjects = new Array();
	
	
    //handle the click event from jsp button 
	function processButtonClick(){
        /***** 탭당 시트가 2개 이상인 경우엔 추가 시트변수 지정하여 사용한 *****/
        /*******************************************************/
		var sheetObject = getCurrentSheet();
        var formObject=document.form; //1
   	try {
   		var srcName=ComGetEvent("name");
           switch(srcName) {
	       	    case "btn_Retrieve":
	       	    	if(informTimeRange()) {	
	   	            doActionIBSheet(sheetObject,formObject,IBSEARCH);
	       	    	}
	   	            //doActionIBSheet(sheetObjects[1],formObject,IBSEARCH);  	   
	       	        break;
         
       			/*****************grid button ************************/				
				case "btn_New": //New
	                doActionIBSheet(sheetObjects[0],formObject,	IBRESET);
					break;
				case "btn_DownExcel": // down Excel
					doActionIBSheet(sheetObject,formObject,	IBDOWNEXCEL);
					break;
				case "btn_DownExcel2": // down Excel
					doActionIBSheet(sheetObject,formObject,	IBDELETE);
					break;				
				case "btn_from_back":
	                UF_addMonth(formObject.fr_acct_yrmon, -1);
//	                sheetObject.RemoveAll();
	                break;
	            case "btn_from_next":
	                if (!GetCheckConditionPeriod()){
	                    ComShowCodeMessage("COM132905");
	                    return;
	                }
	                UF_addMonth(formObject.fr_acct_yrmon, +1);
//	                sheetObject.RemoveAll();
	                break;
	            case "btn_to_back":
	                if (!GetCheckConditionPeriod()){
	                    ComShowCodeMessage("COM132905");
	                    return;
	                }
	                UF_addMonth(formObject.to_acct_yrmon, -1);
//	                sheetObject.RemoveAll();
	                break;
	            case "btn_to_next":
	                UF_addMonth(formObject.to_acct_yrmon, +1);
//	                sheetObject.RemoveAll();
	                break;
	                
	                
				case "summary":
					doActionIBSheet(sheetObjects[0],formObject,IBSEARCH,0);
					break;
				case "detail":
					doActionIBSheet(sheetObjects[1],formObject,IBSEARCH,1);
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
	
	function getCurrentSheet(){
	    var sheetObj=null;
	    if(beforetab == 0){
	        sheetObj=sheetObjects[0];
	    }else{
	        sheetObj=sheetObjects[1];
	    }
	    
	    return sheetObj;
	}
	
	function setTabObject(tab_obj){
	    tabObjects[tabCnt++]=tab_obj;
	}
	
	function initTab(tabObj , tabNo) {
	     switch(tabNo) {
	         case 1:
	            with (tabObj) {
	                var cnt=0 ;
	                InsertItem( "Summary" , "");
	                InsertItem( "Detail" , "");
	            }
	         break;
	     }
	}
	
	/**
	 * Event when clicking Tab
	 * activating selected tab items
	 * nItem --> the number of tab that user click in
	 */
	function tab1_OnChange(tabObj , nItem) {	
		var formObj = document.form;
		// it handles the case where there are multiple elements with the same name properly
	    var objs=document.all.item("tabLayer");
	    //set the style display for next tab
	    objs[nItem].style.display="inline-table";
	    //hide the current tab
	    objs[beforetab].style.display="none";
	    //--------------- important --------------------------//
	    //display next tab and replace the current tab
	    objs[beforetab].style.zIndex=objs[nItem].style.zIndex -1 ;
	    //------------------------------------------------------//
	    //
	    beforetab=nItem;
	    
	    if (beforetab == 0) {
	        ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
//	        doActionIBSheet(sheetObject,formObject,IBSEARCH);
	    }else{
	    	ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
//	    	doActionIBSheet(sheetObject,formObject,IBSEARCH);
	    }
	    
	    resizeSheet();
	}
	
	
	//set array for sheets
	function setSheetObject(sheet_obj){
	       sheetObjects[sheetCnt++]=sheet_obj;
	}
	//set array for combobox
	function setComboObject(combo_obj) {
		comboObjects[comboCnt++] = combo_obj;
	}
	//load config, ComConfigsheet, init sheet, combobox, and tab
	function loadPage() {
		//set sheet
        for(i=0;i<sheetObjects.length;i++){
            ComConfigSheet(sheetObjects[i]);
            initSheet(sheetObjects[i],i+1);
            ComEndConfigSheet(sheetObjects[i]);
        }
        
        //initializing IBMultiCombo
        for ( var k = 0; k < comboObjects.length; k++) {
    		initCombo(comboObjects[k], k + 1);
    	}
        
        //initializing tabobject
        for (var k  = 0; k<tabObjects.length; k++) {
        	initTab(tabObjects[k], k+1);
        	tabObjects[k].SetSelectedIndex(0);
        }              
    }
	
	//click new to reset form
	function resetForm(formObj){
//		formObj.reset();
		sheetObjects[0].RemoveAll();
		jo_crr_cd.SetSelectIndex(0);
		rlane_cd.SetSelectIndex(0);
		trd_cd.SetSelectIndex(0);
		getCurrentDate();
		
	}
	
	function informTimeRange() {
		var flag = false;
		var formObj=document.form;
		if (ComGetDaysBetween(formObj.fr_acct_yrmon, formObj.to_acct_yrmon)>=90){ 
			if(!flag){
				let choice =confirm("Year Month over 3 months, do you realy want to get data?");
				if(choice){
					flag = true
					return true
				}else{
					 return false;
				}
	    	}
		} else if (ComGetDaysBetween(formObj.fr_acct_yrmon, formObj.to_acct_yrmon)<0) {
			ComShowMessage(ComGetMsg('COM132905'));
			return false;
		}
		return true;
	}
	
	function getCurrentDate() {
		today=new Date();
	    var year=today.getFullYear();
	    var lastMonth=today.getMonth();
	   	var thisMonth=today.getMonth()+1;
	   	if(lastMonth<10) {
	   		var prev = year + "-" +"0"+ lastMonth;
	   		var now = year + "-" +"0"+ thisMonth;
	   	} else {
	   		var prev = year + "-" + lastMonth;
	   		var now = year + "-" + thisMonth;
	   	}
	   	
	   	
	    document.getElementById("fr_acct_yrmon").value = prev ;
		document.getElementById("to_acct_yrmon").value = now ;
	}
	
	//initialize the combobox
	function initCombo(comboObj, comboNo, condition) {
		comboObj.RemoveAll();
		var formObj = document.form
		switch (comboNo) {
		case 1:
			comboObj.RemoveAll();
			with (comboObj) {
				SetMultiSelect(1);
		        SetDropHeight(200);
		        ValidChar(2,1);
			}
   
			var comboItems = partner.split('|');
			addComboItem(comboObj, comboItems);
			comboObj.SetSelectIndex(0);
			break;
		case 2:
			condition = " | " + condition;
			var laneComboObject = condition;
			comboObj.RemoveAll();
			with (comboObj) {
				SetMultiSelect(1);
		        SetDropHeight(200);
		        ValidChar(2,1);
			}
			var comboItems = laneComboObject.split("|");
			addComboItem(comboObj, comboItems);
			comboObj.SetSelectIndex(0);
			break;
		case 3:
			condition = " |" + condition;
			var tradeComboObject = condition;
			with (comboObj) {
				SetMultiSelect(1);
		        SetDropHeight(200);
		        ValidChar(2,1);
			}
	
			var comboItems = tradeComboObject.split('|');
			addComboItem(comboObj, comboItems);
			comboObj.SetSelectIndex(0);
			break;
		}
	}
	
	//add data to combobox
	function addComboItem(comboObj, comboItems) {
		for (var i=0 ; i < comboItems.length ; i++) {
			var comboItem=comboItems[i].split(",");
			//comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
			//NYK Modify 2014.10.21
			if(comboItem.length == 1){
				comboObj.InsertItem(i, comboItem[0], comboItem[0]);
			}else{
				comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
			}
			
		}   		
	}
	
	//event fires when partner combobox is clicked 
	function jo_crr_cd_OnCheckClick(comboObj, index, code, formObj, sheetObj) {	
		
		if(index==0) {          
	        var bChk=comboObj.GetItemCheck(index);
	        if(bChk){
	            for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
	                comboObj.SetItemCheck(i,0);
	            }
	        }
	    } else {
	        //ALL 이 아닌 다른 Item 체크.
	        var bChk=comboObj.GetItemCheck(index);
	        if (bChk) {
	            comboObj.SetItemCheck(0,0);           
	        }
	        partnerIndex =1;
	        if(index != 0) {
				var formObj=document.form;
				var sheetObj = sheetObjects[0];
				
				//config for fcommand
				formObj.f_cmd.value=SEARCH01;
				
				//config param and search
				var param=FormQueryString(formObj);
					param += "&" + ComGetPrefixParam(sheetObj.id+"_");
				ComOpenWait(true);
				//getXml
				var sXml = sheetObj.GetSearchData("DOU_TRN_0003GS.do",param);
				
				laneComboObject = ComGetEtcData(sXml, "rlane_cd");
				console.log("lane:", laneComboObject);
				initCombo(comboObjects[1], 2,laneComboObject)
				
				ComOpenWait(false);
			}
			
	    }
	    //Combo Item이 전부 Uncheck 일때 자동으로 All 선택이 되도록 한다.
	    //When all Combo Items are Unchecked, All is automatically selected.
	    var checkCnt=0;
	    var count = parseInt(comboObj.GetItemCount());
	    for(var i = 1 ; i <  count; i++) {
	        if(comboObj.GetItemCheck(i)) {
	            checkCnt++;
	            
	        }
	    }
	    if(checkCnt == 0) {
	        comboObj.SetItemCheck(0,true, null, null, false);
	        partnerIndex =0;
	    }
	}
	
	function rlane_cd_OnCheckClick(comboObj, index, code, formObj) {
		if(index==0 || partnerIndex ==0) {          
	        var bChk=comboObj.GetItemCheck(index);
	        if(bChk){
	            for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
	                comboObj.SetItemCheck(i,0);
	            }
	        }
	    } else {
	        //ALL 이 아닌 다른 Item 체크.
	        var bChk=comboObj.GetItemCheck(index);
	        if (bChk) {
	            comboObj.SetItemCheck(0,0);
	        }
	        laneIndex=1;
	        if(index != 0) {
				var formObj=document.form;
				var sheetObj = sheetObjects[0];
				
				//config for fcommand
				formObj.f_cmd.value=SEARCH02;
				
				//config param and search
				var param=FormQueryString(formObj);
					param += "&" + ComGetPrefixParam(sheetObj.id+"_");
				ComOpenWait(true);
				//getXml
				var sXml = sheetObj.GetSearchData("DOU_TRN_0003GS.do",param);
				
				tradeComboObject = ComGetEtcData(sXml, "tr_cd");
				console.log("tr_cd:", tradeComboObject);
				
				if(tradeComboObject==null) {
					tradeComboObject == " |";
					initCombo(comboObjects[2], 3," |");
				} else {
					initCombo(comboObjects[2], 3,tradeComboObject);
				}
				
				
				ComOpenWait(false);
			}
	    }
	    //Combo Item이 전부 Uncheck 일때 자동으로 All 선택이 되도록 한다.
	    var checkCnt=0;
	    var count = parseInt(comboObj.GetItemCount());
	    for(var i = 1 ; i <  count; i++) {
	        if(comboObj.GetItemCheck(i)) {
	            checkCnt++;
	        }
	    }
	    if(checkCnt == 0) {
	        comboObj.SetItemCheck(0,true, null, null, false);
	        laneIndex=0;
	    }
	}
	
	function trd_cd_OnCheckClick(comboObj, index, code, formObj) {
	    if(index==0 || laneIndex==0) {          
	        var bChk=comboObj.GetItemCheck(index);
	        if(bChk){
	            for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
	                comboObj.SetItemCheck(i,0);
	            }
	        }
	    } else {
	        //ALL 이 아닌 다른 Item 체크.
	        var bChk=comboObj.GetItemCheck(index);
	        if (bChk) {
	            comboObj.SetItemCheck(0,0);
	        }
	    }
	    //Combo Item이 전부 Uncheck 일때 자동으로 All 선택이 되도록 한다.
	    var checkCnt=0;
	    var count = parseInt(comboObj.GetItemCount());
	    for(var i = 1 ; i <  count; i++) {
	        if(comboObj.GetItemCheck(i)) {
	            checkCnt++;
	        }
	    }
	    if(checkCnt == 0) {
	        comboObj.SetItemCheck(0,true, null, null, false);
	    }
	}
	
	
	function initSheet(sheetObj,sheetNo) {
//		var formObject=document.form;
		switch (sheetObj.id) {
		case "t1sheet1": // sheet1 init
			with (sheetObj) {
			
				var HeadTitle1 = "|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider|";
				var HeadTitle2 = "|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name|";
				var headCount=ComCountHeadTitle(HeadTitle1);				
				SetConfig({SearchMode : 2, MergeSheet : 5, Page : 10, DataRowMerge : 1});	
				var info = {Sort : 1, ColMove : 1, HeaderCheck : 1, ColResize : 1};
				var headers = [ { Text:HeadTitle1, Align:"Center"} ,  { Text:HeadTitle2, Align:"Center"}];
				sheetObj.InitHeaders(headers, info);
				var prefix="t1sheet1_";
				
	            var cols = [ {Type:"Status",    Hidden:1,  Width:10,     Align:"Center",  ColMerge:0,   SaveName:prefix+"ibflag",       	 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },// 
				             {Type:"Text",     	Hidden:0,  Width:50,     Align:"Center",  ColMerge:1,   SaveName:prefix+"jo_crr_cd", 		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },// partner
				             {Type:"Text",      Hidden:0,  Width:50,     Align:"Center",  ColMerge:0,   SaveName:prefix+"rlane_cd",     	 KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//lane
				             {Type:"Text",      Hidden:0,  Width:150,    Align:"Center",  ColMerge:0,   SaveName:prefix+"inv_no", 	 		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//invoice no
				             {Type:"Text",      Hidden:0,  Width:150,    Align:"Center",  ColMerge:0,   SaveName:prefix+"csr_no",     		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//slipno
				             {Type:"Text",     	Hidden:0,  Width:70,     Align:"Center",  ColMerge:0,   SaveName:prefix+"apro_flg",   		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//approved 
				             {Type:"Text",    	Hidden:0,  Width:40,     Align:"Center",  ColMerge:0,   SaveName:prefix+"locl_curr_cd",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//currency 
				             {Type:"Float",   	Hidden:0,  Width:100,    Align:"Right",   ColMerge:0,   SaveName:prefix+"inv_rev_act_amt",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//revenue
				             {Type:"Float",   	Hidden:0,  Width:100,    Align:"Right",   ColMerge:0,   SaveName:prefix+"inv_exp_act_amt",   KeyField:0,   CalcLogic:"",   Format:"",         	 PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//expense	
				             {Type:"Text",      Hidden:0,  Width:100,    Align:"left",    ColMerge:0,   SaveName:prefix+"prnr_ref_no",  	 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//code
				             {Type:"Text",     	Hidden:0,  Width:40,     Align:"Center",  ColMerge:0,   SaveName:prefix+"cust_vndr_eng_nm",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }//name			             
				             ];

				InitColumns(cols);     	
            	SetEditable(0);	       
            	SetCountPosition();
	            sheetObj.ShowSubSum([{StdCol:3, SumCols:"7|8", ShowCumulate:0, CaptionText:"", CaptionCol:0}]);
	            SetSheetHeight(300);
	            
	            break;
			}
		case "t2sheet1": //sheet 2 init
			with (sheetObj) {	

			
			var HeadTitle1 = "|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider|";
			var HeadTitle2 = "|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Code|Name|";
			
			
			var prefix="t2sheet1_";		
			SetConfig({SearchMode : 2, MergeSheet : 5, Page : 10, DataRowMerge : 1});
			//DataRowMerge: Whether to allow horizontal merge of the entire row (Default=0)

			var info = {Sort : 1, ColMove : 1, HeaderCheck : 1, ColResize : 1};
			var headers = [ { Text:HeadTitle1, Align:"Center"} ,  { Text:HeadTitle2, Align:"Center"}];
			sheetObj.InitHeaders(headers, info);

            var cols = [ {Type:"Status",    Hidden:1,  Width:10,     Align:"Center",  ColMerge:0,   SaveName:prefix+"ibflag",       	 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },// 
			             {Type:"Text",     	Hidden:0,  Width:50,     Align:"Center",  ColMerge:1,   SaveName:prefix+"jo_crr_cd", 		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },// partner
			             {Type:"Text",      Hidden:0,  Width:50,     Align:"Center",  ColMerge:0,   SaveName:prefix+"rlane_cd",     	 KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//lane
			             {Type:"Text",      Hidden:0,  Width:150,    Align:"Center",  ColMerge:0,   SaveName:prefix+"inv_no", 	 		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//invoice no
			             {Type:"Text",      Hidden:0,  Width:150,    Align:"Center",  ColMerge:0,   SaveName:prefix+"csr_no",     		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//slipno
			             {Type:"Text",     	Hidden:0,  Width:70,     Align:"Center",  ColMerge:0,   SaveName:prefix+"apro_flg",   		 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//approved 
			             {Type:"Text",      Hidden:0,  Width:70,     Align:"Center",  ColMerge:0,   SaveName:prefix+"re_divr_cd",  	 	 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//rev/exp
			             {Type:"Text",     	Hidden:0,  Width:60,     Align:"Center",  ColMerge:0,   SaveName:prefix+"jo_stl_itm_cd",  	 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//item
			             {Type:"Text",    	Hidden:0,  Width:40,     Align:"Center",  ColMerge:0,   SaveName:prefix+"locl_curr_cd",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//currency 
			             {Type:"Float",   	Hidden:0,  Width:100,    Align:"Right",   ColMerge:0,   SaveName:prefix+"inv_rev_act_amt",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//revenue
			             {Type:"Float",   	Hidden:0,  Width:100,    Align:"Right",   ColMerge:0,   SaveName:prefix+"inv_exp_act_amt",   KeyField:0,   CalcLogic:"",   Format:"",         	 PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//expense	
			             {Type:"Text",      Hidden:0,  Width:100,    Align:"Center",  ColMerge:0,   SaveName:prefix+"prnr_ref_no",  	 KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },//code
			             {Type:"Text",     	Hidden:0,  Width:40,     Align:"Center",  ColMerge:0,   SaveName:prefix+"cust_vndr_eng_nm",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }//name	
			             ];
            
			InitColumns(cols);     	
        	SetEditable(0);
        	SetCountPosition();
            SetSheetHeight(240);	                   	
            sheetObj.ShowSubSum([{StdCol:3, SumCols:"9|10", ShowCumulate:0, CaptionText:"", CaptionCol:0}]);
            resizeSheet();	
			break;
			}
		}
	}
	
	
	
	 
	
	function resizeSheet() {
		if(beforetab == 0){
	        ComResizeSheet(sheetObjects[0]);
	    }else{
	        ComResizeSheet(sheetObjects[1]);
	    }
	}
		
	function t1sheet1_OnSearchEnd(sheetObj, Condition) { 		 		
			   sumAll(sheetObj,0);
			   var sRow = sheetObj.FindSubSumRow(3);
				    sRow = sRow.split("|");
				const cell = []
				
				for(x=1;x<sRow.length+1;x++){
					cell[x-1] = sheetObj.GetCellValue(sRow[x-1]-1,6);			
				}		
			
				for(x=0;x<sRow.length;x++){
					sheetObj.SetCellValue(sRow[x], 6,cell[x] );				
				}	
		 
			ComOpenWait(false);	
	}
	
	function t2sheet1_OnSearchEnd(sheetObj, Condition) { 		 		
		   sumAll(sheetObj,2);
		   var sRow = sheetObj.FindSubSumRow(3);
			    sRow = sRow.split("|");
			const cell = []
			
			for(x=1;x<sRow.length+1;x++){
				cell[x-1] = sheetObj.GetCellValue(sRow[x-1]-1,8);			
			}		
		
			for(x=0;x<sRow.length;x++){
				sheetObj.SetCellValue(sRow[x], 8,cell[x] );				
			}	
			sheetObjects[1].SetSelectRow(rows);
		ComOpenWait(false);	
	}
	
	function t1sheet1_OnDblClick(sheetObj, formObject, Row, Col) { 
		var rows = Row;
//		tab1_OnChange(tabObjects[1] , 1);
		tab1.SetSelectedIndex(1);
		sheetObjects[1].SetSelectRow(rows);
//		doActionIBSheet(sheetObjects[1],formObject,IBSEARCH,1);
//		sheetObjects[1].SetSelectRow(row);
	}
	
	
	function sumAll(sheetObj, offset) {
		var totalRows = sheetObj.RowCount();//search total row in sheet	
		if(totalRows>0) {
			var sRow = sheetObj.FindSubSumRow(3);
			    sRow = sRow.split("|");
			
			for(i=0;i<sRow.length;i++) {
				sRow[i] -=1;
			}
			
			const t0 = performance.now();
			console.log(t0);
			
			const cur = [];
			const tmpRow = [];
			
			var vndRev = 0;
			var vndExp = 0;
	
			var usdRev = 0;
			var usdExp = 0;
			
			var eurRev = 0;		
			var eurExp = 0;
	
			var rowVND =sheetObj.DataInsert(-1);
			var rowUSD =sheetObj.DataInsert(-1);
			var rowEUR =sheetObj.DataInsert(-1);
			
			for(i=0;i<totalRows;i++) {
				for(j=0;j<sRow.length;j++) {
					cur[i] = sheetObj.GetCellValue(i+2,6+offset);					
				}
			}
			
			for(i=0;i<totalRows;i++) {
				if(cur[i]=="VND") {
					vndRev += sheetObj.GetCellValue(i+2,7+offset);
					vndExp += sheetObj.GetCellValue(i+2,8+offset);
					if(vndRev != 0 || vndExp !=0 ) {
						sheetObj.SetCellValue(rowVND, 6+offset, "VND" );
						sheetObj.SetCellValue(rowVND, 7+offset,vndRev );	
						sheetObj.SetCellValue(rowVND, 8+offset,vndExp );
						sheetObj.SetRowBackColor(rowVND,"#fdbb8f");
					}
				} else if(cur[i] == "USD") {
					usdRev += sheetObj.GetCellValue(i+2,7+offset);
					usdExp += sheetObj.GetCellValue(i+2,8+offset);
					if(usdRev != 0 || usdExp !=0) {		
						sheetObj.SetCellValue(rowUSD, 6+offset, "USD" );
						sheetObj.SetCellValue(rowUSD, 7+offset,usdRev );
						sheetObj.SetCellValue(rowUSD, 8+offset,usdExp );
						sheetObj.SetRowBackColor(rowUSD,"#fdbb8f");
					}
				} else if(cur[i] == "EUR") {
					eurRev += sheetObj.GetCellValue(i+2,7+offset);
					eurExp += sheetObj.GetCellValue(i+2,8+offset);
					if(eurRev != 0 || eurExp !=0) {		
						sheetObj.SetCellValue(rowEUR, 6+offset, "EUR" );	
						sheetObj.SetCellValue(rowEUR, 7+offset,eurRev );			
						sheetObj.SetCellValue(rowEUR, 8+offset,eurExp );
						sheetObj.SetRowBackColor(rowEUR,"#fdbb8f");
					}
				}
			}
		}
	}
	
		
	function doActionIBSheet(sheetObj, formObj, sAction) {
		var sheetId = sheetObj.id;
		switch (sAction) {
			case IBSEARCH:      // retrieve	for summary
							
					if(sheetId == "t1sheet1") {
						formObj.f_cmd.value=SEARCH;
						var arr1=new Array("t1sheet1_", "");
		  				var sParam1=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr1);
	//	  				ComOpenWait(true);	
		  				sheetObj.DoSearch("DOU_TRN_0003GS.do", sParam1);
	//	  				var sXml = sheetObj.GetSearchData("DOU_TRN_0003GS.do", sParam1);
	//	  				sheetObj.LoadSearchData(sXml, {Sync:1});
	//	  				ComOpenWait(false);	
	
					} else if(sheetId == "t2sheet1") {		
						formObj.f_cmd.value=SEARCH03;
		  				var arr1=new Array("t2sheet1_", "");
		  				var sParam1=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr1);
		  				ComOpenWait(true);	
	//	  				sheetObj.DoSearch("DOU_TRN_0003GS.do", sParam1);
		  				var sXml = sheetObj.GetSearchData("DOU_TRN_0003GS.do", sParam1);
		  				sheetObj.LoadSearchData(sXml, {Sync:1});
		  				ComOpenWait(false);	
		
					}
				
  						 
	            break;				
			case IBRESET: 
				resetForm(formObj);
				break;
			case IBDOWNEXCEL:	//엑셀다운로드
				if(sheetObj.RowCount() < 1){
					ComShowCodeMessage("COM132501");
				}else{
					sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
//					let param = {
//							URL:"/opuscntr/PRACTICE_0003_DETAILS.jsp"
//							,ExtendedParam: FormQueryString(formObj)
//							,FileName: "Details.xls"
//							,DownCols: makeHiddenSkipCol(sheetObj)
//							,Merge:1
//							,SheetDesign:1
//							,KeyFieldMark:0			
//					}
//					sheetObj.DirectDown2Excel(param);
				}
				break;
			case IBDELETE:
				with(formObj) {
					f_cmd.value = SEARCH04;
					target="_top";
					fr_acct_yrmon.disabled = false;
					to_acct_yrmon.disabled = false;			
					action="DOU_TRN_0003DL.do?" + FormQueryString(formObj);
					
					submit();
				
				
				}	
				ComOpenWait(false);
				break;			
		}
	}
		

	
	

	function UF_addMonth(obj, iMonth) {
		if (obj.value != "") {
			obj.value = ComGetDateAdd(obj.value + "-01", "M", iMonth).substring(0, 7);
		}
	}

	function GetCheckConditionPeriod() {
		var formObj = document.form;
		var frDt = formObj.fr_acct_yrmon.value.replaceStr("-", "") + "01";
		var toDt = formObj.to_acct_yrmon.value.replaceStr("-", "") + "01";
		if (ComGetDaysBetween(frDt, toDt) <= 0) {
			return false;
		}
		return true;
	}
