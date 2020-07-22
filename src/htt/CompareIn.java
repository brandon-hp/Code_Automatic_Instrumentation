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
	//��ȡpath�ļ����µ������ļ�
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
	//�ж������ļ������Ƿ���ͬ������fileName1��fileName2�����ļ��ĵ�ַ
	public static boolean isSameFile(String fileName1,String fileName2){
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
		try {
			fis1 = new FileInputStream(fileName1);
			fis2 = new FileInputStream(fileName2);
			
			int len1 = fis1.available();//�����ܵ��ֽ���
			int len2 = fis2.available();
			
			if (len1 == len2) {//������ͬ����ȽϾ�������
				//���������ֽڻ�����
				byte[] data1 = new byte[len1];
				byte[] data2 = new byte[len2];
				
				//�ֱ������ļ������ݶ��뻺����
				fis1.read(data1);
				fis2.read(data2);
				
				//���αȽ��ļ��е�ÿһ���ֽ�
				for (int i=0; i<len1; i++) {
					//ֻҪ��һ���ֽڲ�ͬ�������ļ��Ͳ�һ��
					if (data1[i] != data2[i]) {
						System.out.println("�ļ����ݲ�һ��:");
						System.out.println(fileName1);
						cf.DealAndCopyfile(fileName1,fileName2);
						return false;
					}
				}
				//System.out.println("�����ļ���ȫ��ͬ");
				return true;
			} else {
				System.out.println("�ļ����ݲ�һ��:");
				System.out.println(fileName1);
				cf.DealAndCopyfile(fileName1,fileName2);
				//���Ȳ�һ�����ļ��϶���ͬ
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//�ر��ļ�������ֹ�ڴ�й©
			if (fis1 != null) {
				try {
					fis1.close();
				} catch (IOException e) {
					//����
					e.printStackTrace();
				}
			}
			if (fis2 != null) {
				try {
					fis2.close();
				} catch (IOException e) {
					//����
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		//��ȡ�����汾��API�����ļ�
	    List<File> files1 = getFiles("ResultFiles/1.7.0_07/in");
	    List<File> files2 = getFiles("ResultFiles/1.8.0_231/in");

	    //������һ���汾API�����ļ�
	    for(File f : files1){
	    	String F1inName=f.getName();
	    	String []head1=F1inName.split("class java");
	        Iterator<File> iter=files2.iterator();
	        //�����ڶ����汾API�����ļ�
	        while(iter.hasNext())
	        {
	        	File f2=iter.next();
	        	String F2inName=f2.getName();
		    	String []head2=F2inName.split("class java");
		    	//�����������ļ�ͬ��
	        	if(head1[0].equals(head2[0]))
	        	{
	        		//�ж������ļ������Ƿ���ͬ
	        		if(isSameFile(f.getAbsolutePath(), f2.getAbsolutePath()))
	        		{
	        			//����ͬѰ�����ǵ�����ļ�
	        			String []head3=F1inName.split("Input_");
	        			String F1outName=String.valueOf("ResultFiles/1.7.0_07/out/")+head3[1];
	        			String []head4=F2inName.split("Input_");
	        			String F2outName=String.valueOf("ResultFiles/1.8.0_231/out/")+head4[1];
	        			//�ж�����ļ��Ƿ���ͬ
	        			isSameFile(F1outName, F2outName);
	        		}
	        		iter.remove();
	        		break;
	        	}
	        }

	    }
	    
	    
	    
	}
}
