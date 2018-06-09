package com.star.cloud.excelsax;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 基于XSSF and SAX (Event API)
 * 读取excel的第一个Sheet的内容
 * @author yzl
 *
 */
public class ReadExcelUtils {
	private int headCount = 0;
	private List<List<String>> list = new ArrayList<List<String>>();
	private static final Log log = LogFactory.getLog(ReadExcelUtils.class);
	
	/**
	 * 通过文件流构建DOM进行解析
	 * @param ins
	 * @param headRowCount   跳过读取的表头的行数
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public  List<List<String>> processDOMReadSheet(InputStream ins,int headRowCount) throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(ins);
		return this.processDOMRead(workbook, headRowCount);
	}
	
	/**
	 * 采用DOM的形式进行解析
	 * @param filename
	 * @param headRowCount   跳过读取的表头的行数
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws Exception
	 */
	public  List<List<String>> processDOMReadSheet(String filename,int headRowCount) throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(new File(filename));
		return this.processDOMRead(workbook, headRowCount);
	}
	
	/**
	 * 采用SAX进行解析
	 * @param in
	 * @param headRowCount
	 * @return
	 * @throws OpenXML4JException
	 * @throws IOException
	 * @throws SAXException
	 * @throws Exception
	 */
	public List<List<String>> processSAXReadSheet(InputStream in,int headRowCount) throws IOException, OpenXML4JException, SAXException   {
		headCount = headRowCount;
		OPCPackage pkg = OPCPackage.open(in);
		XSSFReader r = new XSSFReader( pkg );
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		
		Iterator<InputStream> sheets = r.getSheetsData();
		InputStream sheet = sheets.next();
		InputSource sheetSource = new InputSource(sheet);
		parser.parse(sheetSource);
		sheet.close();
		
		log.debug("时间:"+DateUtils.getFragmentInSeconds(new Date(), Calendar.HOUR_OF_DAY)+",共读取了execl的记录数为 :"+list.size());
		
		return list;
	}
	
	private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser =
				XMLReaderFactory.createXMLReader(
						"org.apache.xerces.parsers.SAXParser"
				);
		ContentHandler handler = new SheetHandler(sst);
		parser.setContentHandler(handler);
		return parser;
	}
	
	/**
	 * SAX 解析excel
	 */
	private class SheetHandler extends DefaultHandler {
		private SharedStringsTable sst;
		private String lastContents;
		private boolean nextIsString;
		private boolean isNullCell;
		//读取行的索引
		private int rowIndex = 0;
		//是否重新开始了一行
		private boolean curRow = false;
		private List<String> rowContent;
		
		private SheetHandler(SharedStringsTable sst) {
			this.sst = sst;
		}
		
		public void startElement(String uri, String localName, String name,
		                         Attributes attributes) {
			//节点的类型
			//System.out.println("---------begin:" + name);
			if(name.equals("row")){
				rowIndex++;
			}
			//表头的行直接跳过
			if(rowIndex > headCount){
				curRow = true;
				// c => cell
				if(name.equals("c")) {
					String cellType = attributes.getValue("t");
					if(null == cellType){
						isNullCell = true;
					}else{
						nextIsString = cellType.equals("s");
						isNullCell = false;
					}
				}
				// Clear contents cache
				lastContents = "";
			}
		}
		
		public void endElement(String uri, String localName, String name) {
			//System.out.println("-------end："+name);
			if(rowIndex > headCount){
				if(nextIsString) {
					int idx = Integer.parseInt(lastContents);
					CTRst entryAt = sst.getEntryAt(idx);
					lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
					System.out.println(idx+":"+lastContents);
					nextIsString = false;
				}
				if(name.equals("v")) {
					//System.out.println(lastContents);
					if(curRow){
						//是新行则new一行的对象来保存一行的值
						if(null==rowContent){
							rowContent = new ArrayList<String>();
						}
						rowContent.add(lastContents);
					}
				}else if(name.equals("c") && isNullCell){
					if(curRow){
						//是新行则new一行的对象来保存一行的值
						if(null==rowContent){
							rowContent = new ArrayList<String>();
						}
						rowContent.add(null);
					}
				}
				
				isNullCell = false;
				
				if("row".equals(name)){
					list.add(rowContent);
					curRow = false;
					rowContent = null;
				}
			}
			
		}
		
		public void characters(char[] ch, int start, int length) {
			lastContents += new String(ch, start, length);
		}
	}
	/*//获取单元格各类型值，返回字符串类型
	private  String getCellValueByCell(Cell cell) {
		//判断是否为null或空串
		if (cell==null || cell.toString().trim().equals("")) {
			return "";
		}
		String cellValue = "";
		int cellType=cell.getCellType();
		if(cellType==Cell.CELL_TYPE_FORMULA){ //表达式类型
			cellType= Cell.CELL_TYPE_FORMULA;
		}
		
		switch (cellType) {
			case Cell.CELL_TYPE_STRING: //字符串类型
				cellValue= cell.getStringCellValue().trim();
				cellValue= StringUtils.isEmpty(cellValue) ? "" : cellValue;
				break;
			case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC: //数值类型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
					Date dateCellValue = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String format = sdf.format(dateCellValue);
					cellValue =   format;
				} else {  //否
					cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
				}
				break;
			default: //其它类型，取空串吧
				cellValue = "";
				break;
		}
		return cellValue;
	}*/
	/**
	 * DOM的形式解析execl
	 * @param workbook
	 * @param headRowCount
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private List<List<String>> processDOMRead(Workbook workbook,int headRowCount) {
		headCount = headRowCount;
		
		Sheet sheet = workbook.getSheetAt(0);
		//行数
		int endRowIndex = sheet.getLastRowNum();
		
		Row row = null;
		List<String> rowList = null;
		
		for(int i=headCount; i<=endRowIndex; i++){
			rowList = new ArrayList<String>();
			row = sheet.getRow(i);
			for(int j=0; j<row.getLastCellNum();j++){
				if(null == row.getCell(j)){
					rowList.add("");
				}
				
				int dataType = row.getCell(j).getCellType();
				if(dataType == Cell.CELL_TYPE_NUMERIC){
					DecimalFormat df = new DecimalFormat("0.####################");
					rowList.add(df.format(row.getCell(j).getNumericCellValue()));
				}else if(dataType == Cell.CELL_TYPE_BLANK){
					rowList.add(null);
				}else if(dataType == Cell.CELL_TYPE_ERROR){
					rowList.add(null);
				}else{
					//这里的去空格根据自己的情况判断
					String valString = row.getCell(j).getStringCellValue();
					Pattern p = Pattern.compile("\\s*|\t|\r|\n");
					Matcher m = p.matcher(valString);
					valString = m.replaceAll("");
					//去掉狗日的不知道是啥东西的空格
					if(valString.indexOf(" ")!=-1){
						valString = valString.substring(0, valString.indexOf(" "));
					}
					
					rowList.add(valString);
				}
				//getCellValueByCell( row.getCell(j));
				
				//String valString = row.getCell(j).getStringCellValue();
//				rowList.add(getCellValueByCell( row.getCell(j)));
			}
			
			list.add(rowList);
		}
		log.debug("时间:"+ DateUtils.getFragmentInSeconds(new Date(), Calendar.HOUR_OF_DAY)+",共读取了execl的记录数为 :"+list.size());
		
		return list;
	}
	
	/*public static void main(String[] args) throws Exception {
		ReadExcelUtils howto = new ReadExcelUtils();
		String fileName = "d://app//中粮Call201401-03销售明细.xlsx";
		Long start = System.currentTimeMillis();
		FileInputStream inputStream = new FileInputStream(fileName);
		List<List<String>> list = howto.processSAXReadSheet(inputStream,1);
		System.out.println(list.size());
		Long end = System.currentTimeMillis();
		System.out.println(end-start);
		*//*for(List<String> listStr : list){
			String row = "[";
			for(String s : listStr){
				row += (s +",");
				
			}
			System.out.println(row+"]");
		}
		
		Long end = System.currentTimeMillis();
		System.out.println(end-start);*//*
		*//*ReadExcelUtils h = new ReadExcelUtils();
		String fileName1 = "f:/test.xls";
		List<List<String>> result = h.processDOMReadSheet(fileName1,2);*//*
	}*/
}
