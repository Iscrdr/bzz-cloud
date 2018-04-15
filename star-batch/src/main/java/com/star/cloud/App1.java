package com.star.cloud;

import com.star.cloud.ExcelUtils.ExampleEventUserModel;
import com.star.cloud.ExcelUtils.FileUtils;
import com.star.cloud.thread.ClientThread;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class App1 {
	public static void main(String args []){
		ExampleEventUserModel example = new ExampleEventUserModel();
		String path = "D:\\app1\\";
		try {
			File[] fileArr = FileUtils.getFileArr(path, ".xlsx");
			long startTime = System.currentTimeMillis();
			List<List<String>> list = null;
			for(int i=0;i<fileArr.length;i++){
				list = example.processOneSheet(fileArr[i].getPath());
			}
			Thread t = new Thread(new ClientThread(list));
			t.start();
			Thread.sleep(100);
			long engTime = System.currentTimeMillis();
			System.out.println("共执行了"+fileArr.length+"个Excel文件"+"，耗时："+(engTime-startTime)/1000+"秒(毫秒："+(engTime-startTime)+")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
