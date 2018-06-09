package com.star.cloud;


import com.star.cloud.excelsax.ExampleEventUserModel;
import com.star.cloud.excelsax.FileUtils;
import com.star.cloud.thread.ClientThread;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ExcelToDB  {
	
	private void insertBatch(String path){
		try {
			File[] fileArr = FileUtils.getFileArr(path, ".xlsx");
			long startTime = System.currentTimeMillis();
			for(int i=0;i<fileArr.length;i++){
				ExampleEventUserModel example = new ExampleEventUserModel();
				List<List<String>> list = example.processOneSheet(fileArr[i].getPath());
				Thread t = new Thread(new ClientThread(list,FileUtils.getFileName(fileArr[i]),path+FileUtils.getFileName(fileArr[i])+".csv"));
				t.start();
			}
			
			Thread.sleep(100);
			long engTime = System.currentTimeMillis();
			System.out.println("共执行了"+fileArr.length+"个Excel文件"+"，耗时："+(engTime-startTime)/1000+"秒(毫秒："+(engTime-startTime)+")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertBatch1(){
		String path = "D:\\app1\\";
		insertBatch(path);
	}
	
	@Test
	public void testInsertBatch2(){
		String path = "D:\\app2\\";
		insertBatch(path);
	}
	
	@Test
	public void testInsertBatch3(){
		String path = "D:\\app3\\";
		insertBatch(path);
	}
	
	@Test
	public void testInsertBatch4(){
		String path = "D:\\app4\\";
		insertBatch(path);
	}
}
