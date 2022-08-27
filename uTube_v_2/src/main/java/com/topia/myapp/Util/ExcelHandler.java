//package com.topia.myapp.Util;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.HashMap;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.ibatis.session.ResultContext;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.streaming.SXSSFCell;
//import org.apache.poi.xssf.streaming.SXSSFRow;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.topia.myapp.entity.Member;
//
//public abstract class ExcelHandler<T> implements ResultHandler<T> {
//	String[] header;
//	String fileName;
//	String sheetName;
// 	int ROW_ACCESS_WINDOW_SIZE;
//	XSSFWorkbook xssfWorkbook;
//   	SXSSFWorkbook sxssfWorkbook;
//   	
//	SXSSFSheet objSheet = null;
//	SXSSFRow objRow = null;
//	SXSSFCell objCell = null; 
//	
//	int rowNum = 1; //row 카운트
//	int dataIdx = 1; //data 순번
//	
//	public void ExcelHandler(String[] header, String fileName, String sheetName, int size){
//		this.header = header;
//		this.fileName = fileName;
//		this.sheetName = sheetName;
//		this.ROW_ACCESS_WINDOW_SIZE = size;
//		this.xssfWorkbook = new XSSFWorkbook();
//		this.sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, ROW_ACCESS_WINDOW_SIZE);
//		this.objSheet = (SXSSFSheet) sxssfWorkbook.createSheet(this.sheetName); // sheet 생성
//		
//	}
//	
//	public void createExcelHeader(){
//    	// 0번째 부터 시작
//    	for(int i=0; i<1; i++) {
//    		objRow = (SXSSFRow) objSheet.createRow(0);
//    		for(int j=0; j <header.length; j++) {
//    			objCell = (SXSSFCell) objRow.createCell(j);
//    			objCell.setCellValue(header[j]);
//    		}
//    	}
//	}
//	
//	public void setExcelCell(int num, Object value){
//	  	objCell = (SXSSFCell) objRow.createCell(num);//cell 생성
//    		objCell.setCellValue(String.valueOf(value));//생성된 cell에 값 매핑
//	}
//	
//	public void writeExcelFile(HttpServletResponse response) throws IOException{
//    	response.setContentType("application/xlsx");
//    	response.setHeader("Content-Disposition", "ATTachment; Filename=" + this.fileName + ".xlsx");
//
//    	OutputStream fileOut = response.getOutputStream();
//    	sxssfWorkbook.write(fileOut);
//    	fileOut.close();
//
//    	response.getOutputStream().flush();
//    	response.getOutputStream().close();
//
//    	sxssfWorkbook.dispose(); 
//	}
//	
//	public abstract void createExcelBody(Member member);
//
//	@Override
//	public void handleResult(ResultContext resultContext) {
//		
//		Member member = (Member) resultContext.getResultObject();
//
//		createExcelBody(member);
//		
//	}
//}