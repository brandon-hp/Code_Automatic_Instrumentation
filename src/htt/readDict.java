package htt;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class readDict {
    static  InsertJava sort1=new InsertJava();
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
	     
	public static void main(String[] args) {
		//�����ļ����µ������ļ���List
	    List<File> files = getFiles("src/run");
	    for(File f : files){
	        System.out.println(f.getName());
	       //�������ļ����в�װ.
	        if(f.getName().equals("InsertFun.java"))
	        	continue;
	        if(f.getName().endsWith(".java"))
	       sort1.insert(f.getAbsolutePath(),f.getName(),"src/copyfile.txt");
	    }
	}

}