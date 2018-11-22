package com.bzz.cloud;

import com.bzz.cloud.excelsax.ExampleEventUserModel;
import com.bzz.cloud.excelsax.FileUtils;
import com.bzz.cloud.thread.ClientThread;

import java.io.File;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class App1 {
	/**
	 * 按指定大小，分隔集合，将集合按规定个数分为n个部分
	 * @param <T>
	 *
	 * @param list
	 * @param len
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int len) {
		
		if (list == null || list.isEmpty() || len < 1) {
			return Collections.emptyList();
		}
		List<List<T>> result = new ArrayList<List<T>>();
		int size = list.size();
		int count = (size + len - 1) / len;
		for (int i = 0; i < count; i++) {
			List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subList);
		}
		return result;
	}

	public static void main(String args []){
		ExampleEventUserModel example = new ExampleEventUserModel();
		//SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
		SnowflakeIdFactory idWorker = new SnowflakeIdFactory(1, 2);
		String path = "D:\\test\\";
		CountDownLatch cdLatch = null;

		ExecutorService executorService = null;
		try {
			File[] fileArr = FileUtils.getFileArr(path, ".xlsx");
			long startTime = System.currentTimeMillis();
			List<List<String>> list = null;
			for(int i=0;i<fileArr.length;i++){
				list = example.processOneSheet(fileArr[i].getPath());
			}
			List<List<List<String>>> lists = splitList(list, 10000);
			executorService = Executors.newFixedThreadPool(lists.size());
			//cdLatch = new CountDownLatch(lists.size());
			Set<Long> setAll = new HashSet<>();
			for(int j=0;j<lists.size();j++){
				Thread t = new Thread(new ClientThread(lists.get(j),setAll,idWorker));
				executorService.submit(t);
			}

			long engTime = System.currentTimeMillis();
			System.out.println("共执行了"+fileArr.length+"个Excel文件"+"，耗时："+(engTime-startTime)/1000+"秒(毫秒："+(engTime-startTime)+")");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}
	}
	
	
}
