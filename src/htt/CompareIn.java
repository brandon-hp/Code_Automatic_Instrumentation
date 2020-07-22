package htt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompareIn {
	static CopyFile cf=new CopyFile();
	//获取path文件夹下的所有文件
	public static List<File> getFiles(String path){
	    File root = new File(path);
	    List<File> files = new ArrayList<File>();
	    if(!root.isDirectory()){
	        files.add(root);
	    }else{
	        File[] subFiles = root.listFiles();
	        for(File f : subFiles){
	            files.addAll(getFiles(f.getAbsolutePath()));
	        }    
	    }
	    return files;
	}
	//判断两个文件内容是否相同，参数fileName1，fileName2两个文件的地址
	public static boolean isSameFile(String fileName1,String fileName2){
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
		try {
			fis1 = new FileInputStream(fileName1);
			fis2 = new FileInputStream(fileName2);
			
			int len1 = fis1.available();//返回总的字节数
			int len2 = fis2.available();
			
			if (len1 == len2) {//长度相同，则比较具体内容
				//建立两个字节缓冲区
				byte[] data1 = new byte[len1];
				byte[] data2 = new byte[len2];
				
				//分别将两个文件的内容读入缓冲区
				fis1.read(data1);
				fis2.read(data2);
				
				//依次比较文件中的每一个字节
				for (int i=0; i<len1; i++) {
					//只要有一个字节不同，两个文件就不一样
					if (data1[i] != data2[i]) {
						System.out.println("文件内容不一样:");
						System.out.println(fileName1);
						cf.DealAndCopyfile(fileName1,fileName2);
						return false;
					}
				}
				//System.out.println("两个文件完全相同");
				return true;
			} else {
				System.out.println("文件内容不一样:");
				System.out.println(fileName1);
				cf.DealAndCopyfile(fileName1,fileName2);
				//长度不一样，文件肯定不同
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//关闭文件流，防止内存泄漏
			if (fis1 != null) {
				try {
					fis1.close();
				} catch (IOException e) {
					//忽略
					e.printStackTrace();
				}
			}
			if (fis2 != null) {
				try {
					fis2.close();
				} catch (IOException e) {
					//忽略
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		//获取两个版本的API输入文件
	    List<File> files1 = getFiles("ResultFiles/1.7.0_07/in");
	    List<File> files2 = getFiles("ResultFiles/1.8.0_231/in");

	    //遍历第一个版本API输入文件
	    for(File f : files1){
	    	String F1inName=f.getName();
	    	String []head1=F1inName.split("class java");
	        Iterator<File> iter=files2.iterator();
	        //遍历第二个版本API输入文件
	        while(iter.hasNext())
	        {
	        	File f2=iter.next();
	        	String F2inName=f2.getName();
		    	String []head2=F2inName.split("class java");
		    	//若两个输入文件同名
	        	if(head1[0].equals(head2[0]))
	        	{
	        		//判断输入文件内容是否相同
	        		if(isSameFile(f.getAbsolutePath(), f2.getAbsolutePath()))
	        		{
	        			//若相同寻找他们的输出文件
	        			String []head3=F1inName.split("Input_");
	        			String F1outName=String.valueOf("ResultFiles/1.7.0_07/out/")+head3[1];
	        			String []head4=F2inName.split("Input_");
	        			String F2outName=String.valueOf("ResultFiles/1.8.0_231/out/")+head4[1];
	        			//判断输出文件是否相同
	        			isSameFile(F1outName, F2outName);
	        		}
	        		iter.remove();
	        		break;
	        	}
	        }

	    }
	    
	    
	    
	}
}
