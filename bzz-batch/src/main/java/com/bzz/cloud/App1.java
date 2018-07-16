package com.bzz.cloud;

import com.bzz.cloud.excelsax.ExampleEventUserModel;
import com.bzz.cloud.excelsax.FileUtils;
import com.bzz.cloud.thread.ClientThread;

import java.io.File;
import java.util.List;

public class App1 {
	public static void main(String args []){
		ExampleEventUserModel example = new ExampleEventUserModel();
		String path = "D:\\app\\";
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
