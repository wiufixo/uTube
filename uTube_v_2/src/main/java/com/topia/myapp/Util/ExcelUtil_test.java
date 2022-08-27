//package com.topia.myapp.Util;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelUtil_test {
//	
//	public static <T> void createXlsx(List<T> pojoObjectList, String filePath) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
//		Workbook workbook = new SXSSFWorkbook();
//		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
//		for (int i = 0; i < pojoObjectList.size(); i++) {
//			Row row = sheet.createRow(i);
//			if (i == 0) {
//				RowFilledWithPojoHeader(pojoObjectList, row);
//			} else {
//				RowFilledWithPojoData(pojoObjectList.get(i), row);
//			}
//		}
//		FileOutputStream fos = new FileOutputStream(filePath + pojoObjectList.get(0).getClass().getSimpleName().toUpperCase() + "_" + System.currentTimeMillis() + ".xlsx");
//		workbook.write(fos);
//		fos.close();
//	}
//
//	public static <T> void generateXlsx(List<T> pojoObjectList, String templateFileName, String sheetName, String outputFilePath, String outputFileName) throws InvalidFormatException, IOException, IllegalAccessException,
//			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		FileUtils.copyFile(new File(templateFileName), new File(outputFilePath + "TEMP_" + outputFileName));
//		OPCPackage opcPackage = OPCPackage.open(new File(outputFilePath + "TEMP_" + outputFileName));
//		XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
//		XSSFSheet sheet = workbook.getSheet(sheetName);
//		for (int i = 0; i < pojoObjectList.size(); i++) {
//			Row row = sheet.createRow(i);
//			if (i == 0) {
//				RowFilledWithPojoHeader(pojoObjectList.get(i), row);
//			} else {
//				RowFilledWithPojoData(pojoObjectList.get(i), row);
//			}
//		}
//		FileOutputStream fos = new FileOutputStream(outputFilePath + outputFileName);
//		workbook.write(fos);
//		fos.close();
//		opcPackage.close();
//		FileUtils.deleteQuietly(new File(outputFilePath + "TEMP_" + outputFileName));
//		return;
//	}
//
//	private static Row RowFilledWithPojoHeader(Object pojoObject, Row row) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		Field[] fields = pojoObject.getClass().getDeclaredFields();
//		int fieldLength = fields.length;
//		for (int i = 0; i < fieldLength; i++) {
//			String cellValue = fields[i].getName().toUpperCase();
//			row.createCell(i).setCellValue(cellValue);
//		}
//		return row;
//	}
//
//	private static Row RowFilledWithPojoData(Object pojoObject, Row row) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Field[] fields = pojoObject.getClass().getDeclaredFields();
//		int fieldLength = fields.length;
//		for (int i = 0; i < fieldLength; i++) {
//			Method method = pojoObject.getClass().getMethod("get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1));
//			String cellValue;
//			String returnType = method.getReturnType().getName();
//			if (returnType.equals("java.util.Date")) {
//				Object dateTime = method.invoke(pojoObject);
//				if (dateTime == null) {
//					cellValue = "";
//				} else {
//					cellValue = df.format(dateTime);
//				}
//			} else {
//				cellValue = String.valueOf(method.invoke(pojoObject));
//			}
//			row.createCell(i).setCellValue(cellValue);
//		}
//		return row;
//	}
//
//	public static String getCellValue(Row row, int cellIndex) {
//		Cell cell = row.getCell(cellIndex);
//		if (cell == null) {
//			return null;
//		}
//		cell.setCellType(Cell.CELL_TYPE_STRING);
//		return cell.getStringCellValue();
//	}
//
//	public static String getCellValue(Object object) {
//		if (object == null) {
//			return "";
//		}
//		return object.toString();
//	}
//}
