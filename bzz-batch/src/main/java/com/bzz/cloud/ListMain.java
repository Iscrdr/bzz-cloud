package com.bzz.cloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: bzz-cloud
 * @description: list切割
 * @author: Yang qianli
 * @create: 2018-10-27 03:02
 * @version: 1.0.0
 */
public class ListMain {
	
	public static void main(String[] args) {
		/*List<String> lists = new ArrayList<String>();
		for(int i=0;i<48571;i++){
			lists.add(i+"_"+i);
		}
		List<List<String>> lists1 = splitList(lists, 10000);
		System.out.println(lists1.size());
		for(int j=0;j<lists1.size();j++){
			System.out.println("第"+j+"个集合长度："+lists1.get(j).size());
		}
		*/
	}
	
	/**
	 * 按指定大小，分隔集合，将集合按规定个数分为n个部分
	 * @param <T>
	 *
	 * @param list
	 * @param len
	 * @return
	 *//*
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
	}*/
}
