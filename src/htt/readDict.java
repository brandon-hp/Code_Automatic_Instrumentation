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
		//遍历文件夹下的所有文件的List
	    List<File> files = getFiles("src/run");
	    for(File f : files){
	        System.out.println(f.getName());
	       //对所有文件进行插装.
	        if(f.getName().equals("InsertFun.java"))
	        	continue;
	        if(f.getName().endsWith(".java"))
	       sort1.insert(f.getAbsolutePath(),f.getName(),"src/copyfile.txt");
	    }
	}

}