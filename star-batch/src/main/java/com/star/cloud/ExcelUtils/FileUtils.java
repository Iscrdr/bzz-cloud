package com.star.cloud.ExcelUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtils {
	
	/**
	 * 获取文件夹下的所有以.fileType为结尾的文件
	 * @param path 文件夹名称
	 * @param fileType 文件类型：例如 xlsx,txt等
	 * @return
	 */
	public static File[] getFileArr(String path, final String fileType){
		File file = new File(path);
		File[] files = null;
		if (file.isDirectory()) {
			files = file.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (name.endsWith(fileType)) {
						return true;
					}
					return false;
				}
			});
			
		}
		return files;
	}
	
	/**
	 * 获取文件名称去掉点，例如：部门信息.txt,返回部门信息，去掉.txt
	 * @param file 文件名称
	 * @return string filename
	 */
	public static String getFileName(File file){
		String fileName = "";
		if(null != file && file.isFile()){
			String name = file.getName();
			String[] split = StringUtils.split(name,".");
			fileName = split[0];
			
		}
		return fileName;
	}
	
}
