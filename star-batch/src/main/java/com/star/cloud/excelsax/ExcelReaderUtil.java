package com.star.cloud.excelsax;

import com.star.cloud.excelsax.xlsx.ExcelXlsxReader;
import com.star.cloud.excelsax.xls.ExcelXlsReader;
import com.star.cloud.thread.ClientThread;

import java.util.List;

public class ExcelReaderUtil {
	
	//excel2003扩展名
	public static final String EXCEL03_EXTENSION = ".xls";
	//excel2007扩展名
	public static final String EXCEL07_EXTENSION = ".xlsx";
	
	/**
	 * 读取Excel文件，可能是03也可能是07版本
	 * @param
	 * @param
	 * @param fileName
	 * @throws Exception
	 */
	public static List<List<String>> readExcel(String fileName) throws Exception{
		List<List<String>> dataList = null;
		// 处理excel2003文件
		if (fileName.endsWith(EXCEL03_EXTENSION)){
			ExcelXlsReader excel03 = new ExcelXlsReader();
			
			excel03.process(fileName);
			dataList = excel03.getDataList();
			// 处理excel2007文件
		} else if (fileName.endsWith(EXCEL07_EXTENSION)){
			ExcelXlsxReader excel07 = new ExcelXlsxReader();
			
			excel07.process(fileName);
			dataList = excel07.getDataList();
		} else {
			throw new  Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
		}
		return dataList;
	}
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		
		//ExcelReaderUtil.readExcel(reader, "F://te03.xls");
		List<List<String>> lists =ExcelReaderUtil.readExcel("D://app//销售日报.xlsx");
		//List<List<String>> lists = ExcelReaderUtil.readExcel("D://app//中粮Call201401-03销售明细.xlsx");
		long end = System.currentTimeMillis();
		System.out.println("解析文件销售日报.XLSX完成，耗时(毫秒)："+(end-start)/1000);
		
		
		Thread t = new Thread(new ClientThread(lists));
		t.start();
		Thread.sleep(100);
		long engTime = System.currentTimeMillis();
		System.out.println("耗时："+(engTime-start)/1000+"秒(毫秒："+(engTime-start)+")");
		
		
		
	}
}