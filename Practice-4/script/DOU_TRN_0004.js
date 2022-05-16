/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0004.js
*@FileTitle : Practice 4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
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
     * @class DOU_TRN_0004 : DOU_TRN_0004 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    //set sheet objects in an array
    var sheetObjects=new Array();   
    //counting for multiple sheets
	var sheetCnt=0;	
	//counting for multiple combo-boxes
	var comboCnt = 0;
	//receive the event from screen that is clicked
	document.onclick=processButtonClick;
	//set combo box objects in an array
	var comboObjects = new Array();
	
	
	
	//ComSheetObject from JSP call this function
	function setSheetObject(sheet_obj) {
		sheetObjects[sheetCnt++] = sheet_obj;
	}
	
	//ComComboObject from JSP call this function
	function setComboObject(combo_obj) {
		comboObjects[comboCnt++] = combo_obj;
	}
	
	//set config for sheet/ combobox
    function loadPage() {
    	var formObject = document.form;
    	//config sheet
    	for(i = 0; i<sheetObjects.length; i++) {
    		ComConfigSheet(sheetObjects[i]); //SetShowButtonImage, SetDown2ExcelConfig, SetDataRowHeight
    		initSheet(sheetObjects[i], i + 1);//initializing sheet
    		ComEndConfigSheet(sheetObjects[i]); //SetEditableColorDiff/SetEnterBehavior/SetEditTabBehavior
    	}
    	
    	//config combo box, generate dopdownlist data
    	for(var k=0; k<comboObjects.length; k++) {
    		initCombo(comboObjects[k], k+1);
    	}
    	document.getElementById('cre_dt_fm').disabled = true;
    	document.getElementById('cre_dt_to').disabled = true;
    	//searching when refreshing the page
    	doActionIBSheet(sheetObjects[0], formObject, IBSEARCH);
    }
	
	//handle the event that user click on page
	function processButtonClick(){
		var formObject = document.form;// assign current form to formObject for further processing
		
		try{
			//get event name which corresponding to button
			var srcName = ComGetEvent("name");
			switch(srcName) {
				//event fires when retrieve button is clicked
				case "btn_Retrieve":
					doActionIBSheet(sheetObjects[0], formObject, IBSEARCH);
					break;				
				case "btn_New": //event fires when New button is clicked, reset form
					doActionIBSheet(sheetObjects[0], formObject, IBRESET);
					break;
				case "btn_Save": //event fires when Save button is clicked, save new data
					doActionIBSheet(sheetObjects[0], formObject, IBSAVE);
					break;
				case "btn_DownExcel": //event fires when DownExcel button is clicked, down sheet to excel file
					doActionIBSheet(sheetObjects[0], formObject, IBDOWNEXCEL);
					break;
				case "btn_RowAdd": //event fires when Add button is clicked/ add new row
					doActionIBSheet(sheetObjects[0], formObject, IBINSERT);
					break;
				case "btn_RowDelete": //event fires when Delete button is clicked, delete current row
					doActionIBSheet(sheetObjects[0], formObject, IBDELETE);
					break;			
				case "btns_calendar1":
				case "btns_calendar2": 
					var cal=new ComCalendar();
	                if(srcName == "btns_calendar1"){
	                    cal.select(formObject.cre_dt_fm, 'yyyy-MM-dd');
	                }else{
	                    cal.select(formObject.cre_dt_to, 'yyyy-MM-dd');
	                }
	                break;
				
			}
		} catch(e) {
			if( e == "[object Error]") {
	   			ComShowMessage(ComGetMsg('COM12111'));
	   		} else {
	   			ComShowMessage(e.message);
	   		}
		}
	}

	function initCombo(comboObj, comboNo){
		var formObject = document.form;
		switch (comboNo) {
		case 1:
			with (comboObj) {
			SetMultiSelect(1); //could select muti box in combo box
	        SetDropHeight(200); // set the height for drop list
	        ValidChar(2,1); //input upper case, numbers
			}
			
			var comboItems = carrier.split("|"); // split all components to array
			addComboItem(comboObj, comboItems);  //then add content to combo
			comboObj.SetSelectIndex(0); //default selection at index 0
			break;
		}
	}
	
	//add data to combobox
	function addComboItem(comboObj, comboItems) {
		for (var i=0 ; i < comboItems.length ; i++) {
			var comboItem=comboItems[i].split(",");
			if(comboItem.length == 1){
				comboObj.InsertItem(i, comboItem[0], comboItem[0]);
			}else{
				comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
			}
			
		}   		
	}
	
	

	function initSheet(sheetObj, sheetNo){
		switch(sheetObj.id) {
		case "sheet1":
			with(sheetObj) {
				//set up head title for columns
				var HeadTitle = "Flag|Chk|Carrier|Rev.Lane|Vendor Code|Customer Code|Customer Code|Trade|DeleteFlag|Create Date|Create User ID|Update Date|Update User ID";
				//counting heade title
				var headCount = ComCountHeadTitle(HeadTitle);
				/*SearchMode:2 - lazy load
				Page: Number of rows to display in one page (default:20)
				DataRowMerge: Whether to allow horizontal merge of the entire row		
				mergeSHeet:5: merge header
				*/
				SetConfig({SearchMode : 2, MergeSheet : 5, Page : 10, DataRowMerge : 1});	
				
				//set infomation for sheet
				var info = {Sort : 1, ColMove : 1, HeaderCheck : 1, ColResize : 1};
				
				//setting for header
				var header = [{ Text:HeadTitle, Align:"Center"}];
				//initializing for header, define the header title and function using this method.
				sheetObj.InitHeaders(header, info);
				
				//set prefix to discriminate between sheets 
//				var prefix="sheet1_";
				
				//configure for each column
				/* Hidden: 1: hide, 0:show
				 * Width: the width of cell
				 * Align: align position of text
				 * ColMerge: 
				 * KeyField: an be used to configure whether to make a data cell a required field. If the value is 
				 * 			 set as 1 and the data cell is empty, a warning message appears when the saving method is 
				 * 			 called so as to encourage the user to fill the cell. The default value is 0. 
				 * ColMerge:  configures whether to allow vertical merge for data columns. The default value is 1.
				 * SaveName: for corresponding name of column when retrieving 
				 * Format: apply column type
				 * UpdateEdit: could edit to update
				 * InsertEdit: could insert data when add new row
				 * EditLen:maximum number of characters to allow for a piece of data.
				 * AcceptKeys: is where you configure key operations allowed for a column.
				 * 			   Set as "N" for numbers and "E" for English alphabet; 
				 * 			   if both numbers and English alphabets are allowed, connect the two values with "|"				
				 */
				var cols = [ 
				             { Type : "Status",   Hidden : 1, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
				             { Type : "CheckBox", Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
				             { Type : "Text", 	  Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "jo_crr_cd", 	 KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1, EditLen: 3 }, //carrier
				             { Type : "Combo", 	  Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "rlane_cd",    KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1, EditLen: 5  }, //rev lane
				             { Type : "Text",	  Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "vndr_seq",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 , EditLen: 6 }, //vendor code
				             { Type : "Text", 	  Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "cust_cnt_cd", KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 , EditLen: 2 }, //Customer code
				             { Type : "Text", 	  Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "cust_seq",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 , EditLen: 6 }, //customer code
				             { Type : "Text",     Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "trd_cd", 	 KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1	, EditLen: 3 }, //trade
				             { Type : "Combo",    Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "delt_flg", 	 KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1}, //delete flag
				             { Type : "Text",     Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "cre_dt", 	 KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, //create date
				             { Type : "Text", 	  Hidden : 0, Width : 180, Align : "Left",   ColMerge : 0, SaveName : "cre_usr_id",  KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, //create user ID
				             { Type : "Text", 	  Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "upd_dt", 	 KeyField : 0, Format : "", UpdateEdit : 0,InsertEdit : 0 }, //update date
				             { Type : "Text", 	  Hidden : 0, Width : 180, Align : "Left", 	 ColMerge : 0, SaveName : "upd_usr_id",  KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0} //update user id
				             ];
				//configure functionality of each column into JSON format.
				InitColumns(cols);
				//allow edit cells
				SetEditable(1);
				SetColProperty("jo_crr_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1 });
				SetColProperty("vndr_seq", { AcceptKeys : "N"});
				SetColProperty("cust_cnt_cd", { AcceptKeys : "E", InputCaseSensitive : 1});
				SetColProperty("cust_seq", { AcceptKeys : "N"});
				SetColProperty("trd_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1 });
				SetColProperty("rlane_cd", { ComboText : lane, ComboCode : lane });
				SetColProperty("delt_flg", { ComboText : "N|Y", ComboCode : "N|Y" });
				resizeSheet();
			}
			break;
		}
	}
	
	//resize the sheet
	function resizeSheet(){
	    ComResizeSheet(sheetObjects[0]);
	}
	
    
	function doActionIBSheet(sheetObj, formObj,sAction ) {
		switch(sAction) {
		
		case IBSEARCH: //for retrieve
			formObj.f_cmd.value=SEARCH; //assign form command to server
			ComOpenWait(true);
			if(validateForm(sheetObj, formObj, sAction)) {
//			var arr1=new Array("sheet1_", "");
//			var sParam1=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr1);
				sheetObj.DoSearch("DOU_TRN_0004GS.do", FormQueryString(formObj));
			} else {
				ComOpenWait(false);
			}
			
			break;
		case IBRESET: //for new button
			//remove header and table data
			sheetObj.RemoveAll();
			//set carrier to the first position.
			comboObjects[0].SetSelectIndex(0);
			//reset date from and date to to current month
			getCurrentDate();
			break;
		case IBSAVE: //for save button
			// save data based on data transaction status or column to database.
			if(sheetObj.GetSaveString() != ''){
				formObj.f_cmd.value = MULTI;
				sheetObj.DoSave("DOU_TRN_0004GS.do", FormQueryString(formObj));
			}
			else

			ComShowCodeMessage("COM132910");
			break;
		case IBINSERT: //for row add button
			sheetObj.DataInsert(-1); //new row will be added at the bottom
			break;
		case IBDELETE: //for delete button
			for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ){//check from to bottom to the top row 
				if(sheetObj.GetCellValue(i, "del_chk") == 1){ 
					sheetObj.SetRowHidden(i, 1);
					sheetObj.SetRowStatus(i,"D");
				}			
			}
			break;	
		case IBDOWNEXCEL:	//download excel
			if(sheetObj.RowCount() < 1){
				ComShowCodeMessage("COM132501");
			}else{
				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
			}
			break;
		}
	}
	
	//event fires when ending retrieve in sheet1
	function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
	 	ComOpenWait(false);
	 	
	}
	
	
	//get current date, month, year
	function getCurrentDate() {
		today=new Date();
	    var thisYear=today.getFullYear(); //get current year
	    var thisDate = today.getDate(); // get current date
	   	var thisMonth=today.getMonth()+1; // because it counts from 0 so add 1
	   	var firstDate = "01"; // first day of a month
	   	if(thisMonth<10 ) { // if date or month < 10, add "0" before value	   		
	   		thisMonth = "0" + thisMonth;
	   	} 
	   	if(thisDate<10) {
	   		thisDate = "0" + thisDate;
	   	}
	   	
   		var prev = thisYear + "-" + thisMonth + "-" + firstDate;
   		var now = thisYear + "-" + thisMonth+ "-" + thisDate;
   		
   		//set by id
   		var a = document.getElementById("cre_dt_fm").value
	    document.getElementById("cre_dt_fm").value = prev ;
		document.getElementById("cre_dt_to").value = now ;
	}
	
	function sheet1_OnChange(sheetObj, Row, Col, Value, OldValue, RaiseFlag) {
		var formObject = document.form; 
		var colName=sheetObj.ColSaveName(Col); //get current column name that changed
		
		if(colName == "jo_crr_cd" || colName == "rlane_cd"){//check duplicate data
			if(sheetObj.GetCellValue(Row,"jo_crr_cd") != "" && sheetObj.GetCellValue(Row,"rlane_cd") != ""){
				//check on UI Frist
				var headerRowNum = sheetObj.HeaderRows();
				for(var i = headerRowNum; i <= sheetObj.RowCount(); i++){
					if(i != Row && sheetObj.GetCellValue(Row,"jo_crr_cd") == sheetObj.GetCellValue(i,"jo_crr_cd")
							&& sheetObj.GetCellValue(Row,"rlane_cd") == sheetObj.GetCellValue(i,"rlane_cd")){
						ComShowCodeMessage("COM12115");
						sheetObj.SetCellValue(Row, Col,OldValue,0);
						sheetObj.SelectCell(Row, Col);
						return;
					}
				}
				//check on Service side
				formObject.f_cmd.value		= COMMAND01;
				var param = FormQueryString(formObject) + "&jo_crr_cd=" + sheetObj.GetCellValue(Row,"jo_crr_cd") + "&rlane_cd=" + sheetObj.GetCellValue(Row,"rlane_cd");
				var sXml = sheetObj.GetSearchData("DOU_TRN_0004GS.do", param,{sync:1});
				var flag				= ComGetEtcData(sXml, "ISEXIST");
				if(flag == 'Y'){
					ComShowCodeMessage("COM12115");
					sheetObj.SetCellValue(Row, Col,OldValue,0);
					sheetObj.SelectCell(Row, Col);
				}
			}
		}
	}

	////event fires when partner combobox is clicked 
	function s_jo_crr_cd_OnCheckClick(comboObj, index, code) {
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
	    }
	    ////When all Combo Items are Unchecked, All is automatically selected.
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
	
	function sheet1_OnSaveEnd(code, msg) { //this function will fire when saved transaction end
		if(msg >= 0) {			
			ComShowCodeMessage("COM130102"); // pop up save successfully 
		} else {
			ComShowCodeMessage("COM130103"); //pop up fail to save
		}
		doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
	} 
	
	function validateForm(sheetObj, formObj, sAction) {
		sheetObj.ShowDebugMsg(false);
		switch (sAction) {
		case IBSEARCH: // retrieve
			var regex = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/; //regex for yyyy/mm/dd
			var creDtFm = form.cre_dt_fm; 
	        var creDtTo = form.cre_dt_to;
	        creDtFm1 = document.getElementById("cre_dt_fm").value; //get value from date from 
	        creDtTo1 = document.getElementById("cre_dt_to").value; //get value from date to
	        var compare = document.getElementById("cre_dt_to").value.replaceAll('-','') - document.getElementById("cre_dt_fm").value.replaceAll('-',''); //take date to - date fr
	        if(creDtFm.value != "" && creDtTo.value != "" ) { // if values in box search not blank and date to - date fr < 0    
	            if(compare < 0 && creDtFm1.match(regex) && creDtFm1.match(regex)) {
	            	ComShowCodeMessage("COM132905");  // show msg date fr cannot > than date to
	            	return false;
	            }
	            
	            if(!creDtFm1.match(regex)) { // check date fr is invalid
	            	ComShowCodeMessage("COM132907");	
	            	return false;
	            }
	            
	            if(!creDtTo1.match(regex)) { // check date to is invalid
	            	ComShowCodeMessage("COM132907");	
	            	return false;
	            }           
	            return false;
	        } else if (creDtFm.value == "" && creDtTo.value != "") { //check date from if it was inserted
	        	ComShowCodeMessage("COM132908");
	        	return false;
	        } else if (creDtFm.value != "" && creDtTo.value == "") {//check date to if it was inserted
	        	ComShowCodeMessage("COM132909");
	        	return false;
	        }	        
			break;
		} 
		return true;
	}
	
	//only insert number on vendor
	function validateVendor(vendor) {
		// regex for vendor code
		var regex = /^(\d{6})$/;
		// check whether the message code matches with format.
		if(!vendor.match(regex)){
//			alert("Only number is allowed and atlest 6 digits");
			
			ComShowCodeMessage("COM140000");
			setTimeout(function(){
				document.getElementById("vndr_seq");
			},1);

		} 
	}
	

	
