package com.topia.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topia.myapp.HomeController;
import com.topia.myapp.entity.Member;
import com.topia.myapp.service.MemberService;

@Controller
public class PoiController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/test/excel", method = RequestMethod.GET)
	public void excel() {
		logger.info("excel컨트롤러");
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/excel/member", method = RequestMethod.GET)
	public void memberDownload(@RequestParam(defaultValue = "5") int perPage, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType, @RequestParam(defaultValue = "mem_id") String criteria, @RequestParam(defaultValue = "5") int pageSize, HttpServletResponse response) {
		memberService.membersListExcel(perPage, page, keyword, searchType, criteria, pageSize, response);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/excel/download", method = RequestMethod.POST)
	public void download(@RequestParam(defaultValue = "5") int perPage, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType, @RequestParam(defaultValue = "mem_id") String criteria, @RequestParam(defaultValue = "5") int pageSize, HttpServletResponse response) {
		logger.info("****************** POI CONTROLLER ******************");
		
		HashMap<String, Object> resultMap = (HashMap<String, Object>)memberService.membersList(perPage, page, keyword, searchType, criteria, pageSize);
		List<Member> list = (ArrayList<Member>)resultMap.get("list");
		
		String pageStr = page+"";
		
		if(page==0) {
			pageStr="전체";
		}
		if(criteria.equals("mem_id")) {
			criteria = "최신등록순";
		}else if(criteria.equals("followers")) {
			criteria = "구독자순";
		}else if(criteria.equals("uploads")) {
			criteria = "업로드수순";
		}
		
		Workbook wb = new HSSFWorkbook(); // 엑셀파일 객체 생성
		
		Sheet sheet = wb.createSheet("회원목록"); //시트 생성 ( 첫번째 시트이며, 시트명은 테스트 시트 )
		sheet.setColumnWidth(0, (short)4*1000);
		sheet.setColumnWidth(1, (short)3*1000);
		sheet.setColumnWidth(2, (short)8*1000);
		sheet.setColumnWidth(3, (short)5*1000);
		sheet.setColumnWidth(6, (short)3*1000);
		sheet.setColumnWidth(7, (short)3*1000);
		
		// 자바의 배열처럼 첫번째 인덱스가 0 부터 시작한다.  첫번째는 0 , 두번째는 1 , 세번째는 2
		Row titleRow = sheet.createRow(0); // 타이틀행을 생성한다. 첫번째줄이기때문에 createRow(0)
		int titleColNum = 0; // 첫번째 열이기 때문에 0 
		Cell titleCell = titleRow.createCell(titleColNum); // 첫번째행의 첫번째열을 지정한다. 
		titleCell.setCellValue("회원목록"); // setCellValue 셀에 값넣기.
		titleRow.setHeight((short)850); // Row에서 setHeight를 하면 행 높이가 조정된다. 
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,7)); // 셀 병합  첫번째줄~아홉번째 열까지 병합 
		// new CellRangeAddress(시작 row, 끝 row, 시작 col, 끝 col) 
		
		Font font = wb.createFont(); // 폰트 스타일 생성
		//font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 글자 진하게
		font.setFontHeight((short)(16*18)); // 글자 크기
		font.setFontName("맑은 고딕"); // 글씨체
		
		CellStyle style = wb.createCellStyle(); // 셀 스타일 생성
		style.setWrapText(true); //문자열을 입력할때 \n 같은 개행을 인식해준다.
		//style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 수직 가운데 정렬
		//style.setAlignment(CellStyle.ALIGN_CENTER); // 수평 가운데 정렬
		style.setFont(font); // 스타일에 font 스타일 적용하기
		
		titleCell.setCellStyle(style); // 정리한 스타일들을 titleCell에 적용해주자 !
		
		//입력받은 날짜 출력하기
		Row pageRow = sheet.createRow(1);
		int pageCol = 0;
		Cell pageCell = pageRow.createCell(pageCol++); // 두번째줄의 첫번째열을 셀로 지정. 즉 두번째줄 첫째칸
		pageCell.setCellValue("페이지 : " + pageStr); // 두번째 행은 입력받은 날짜를 출력
		pageCell = pageRow.createCell(pageCol); // 두번째줄의 첫번째열을 셀로 지정. 즉 두번째줄 첫째칸
		
		Row orderRow = sheet.createRow(2);
		int orderCol = 0;
		Cell orderCell = orderRow.createCell(orderCol++); // 두번째줄의 첫번째열을 셀로 지정. 즉 두번째줄 첫째칸
		orderCell.setCellValue("정렬 : " + criteria); // 두번째 행은 입력받은 날짜를 출력
		
		//헤더 만들기
		Row headerRow = sheet.createRow(4); // 네번째줄 생성
		int headerCol = 0;
		Cell headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("회원번호");
		headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("아이디");
		headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("채널명");
		headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("이메일");
		headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("구독자수");
		headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("업로드수");
		headerCell = headerRow.createCell(headerCol++);
		headerCell.setCellValue("가입일");
		headerCell = headerRow.createCell(headerCol);
		headerCell.setCellValue("권한");
		
		CellStyle dataStyle = wb.createCellStyle(); // 데이터스타일은 테두리를 만들어보자
//		dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); //오른쪽 테두리
//		dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); //왼쪽 테두리
//		dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 상단 테두리
//		dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 하단 테두리
		
		//데이터 삽입하기
		int rowNum = 5; // 네번째줄이 헤더니까 그 밑에서부터 데이터 삽입
		int cellNum = 0;
		Row dataRow = null; // for문을 돌려주기위해.
		Cell dataCell = null;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 0; i<list.size(); i++) {
			cellNum = 0;
			dataRow = sheet.createRow(rowNum++); // for문 돌면서 행 1줄씩 추가
			
			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getMemId()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getMemName()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용

			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getChannelName()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getEmail()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getFollowers()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getUploads()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
			
			dataCell = dataRow.createCell(cellNum++); //열 한줄씩 추가
			dataCell.setCellValue(df.format(list.get(i).getRegistDate())); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
			dataCell = dataRow.createCell(cellNum); //열 한줄씩 추가
			dataCell.setCellValue(list.get(i).getAuth()); // 첫번째칸은 순번이기때문에 
			dataCell.setCellStyle(dataStyle); // 테두리 스타일 적용
			
		}
		
		dataRow = sheet.createRow(rowNum++); // 총 인원을 작성해보자
		dataCell = dataRow.createCell(0); // 첫번쨰칸
		dataCell.setCellValue("출력한 회원 수");
		dataCell = dataRow.createCell(1); // 두번쨰칸
		dataCell.setCellValue(list.size()); // 함수식을 입력할 수 있는 기능
		
//		try {
//			
//			/* 사진 삽입 */
//			String filePath = "resources/images/camera.png"; 
//			InputStream is = new FileInputStream(getClass().getClassLoader().getResource(filePath).getFile());
//			byte[] bytes = IOUtils.toByteArray(is);
//			int picIdx = wb.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
//			is.close();
//			
//			CreationHelper helper = wb.getCreationHelper();
//			Drawing drawing = sheet.createDrawingPatriarch();
//			ClientAnchor anchor = helper.createClientAnchor();
//			
//			// 이미지 출력할 엑셀 cell,row 위치
//			anchor.setRow1(12); //13번째줄
//			anchor.setCol1(4);  //3번째 셀
//			
//			//이미지 그리기
//			Picture pic = drawing.createPicture(anchor, picIdx);
//			pic.resize();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		/* 엑셀 파일 생성 */
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=member.xls");
		try {
			wb.write(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
