package htt;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeoutException;

public class Myjava {
	static readDict re=new readDict();
	//防止java子程序运行超时，其实没啥用可忽略Worker
    private static class Worker extends Thread {
        private final Process process;
        private Integer exit;
 
        private Worker(Process process) {
            this.process = process;
        }
 
        public void run() {
            try {
                exit = process.waitFor();
            } catch (InterruptedException ignore) {
                return;
            }
        }
    }

	 public static void main(String args[]){
		 Vector<String> ve=new Vector<String>();
		 //读取该目录下的所有文件
		    List<File> files = readDict.getFiles("src/run");
		    //将可执行文件.class的文件名存入ve中
		    for(File f : files){
		       if(f.getName().endsWith(".class"))
		       {
		    	   //将文件名的.class分割掉
		    	   String[] name=f.getName().split(".class");
		    	   ve.add(name[0]);
		       }
		       
		    }
		    //使用cmd的java命令批运行D:\\java1\\src\\run下的执行文件
		    //要删除不能执行的.class文件，否则会阻塞.
		    //每次运行重新运行前要把之前.class生成插装txt文本删除.
		    String JAVAC_PATH="java";
		    String SOURCE_PATH="src/";

		 	for(int i=0;i<ve.size();i++) {
		    try
		    {
		    	//Runtime.getRuntime().exec("cmd /c");
		    	//显示当前要执行的.class文件名，用于查找当前哪个文件阻塞，把它删去
		    	System.out.println(ve.elementAt(i));
		        String command=JAVAC_PATH + " -cp " + SOURCE_PATH+" run."+ve.elementAt(i);
		        //执行cmd命令
		        Process process=Runtime.getRuntime().exec(command);
		        //下面Worker没啥用忽略
		        Worker worker = new Worker(process);
		        worker.start();
		        try {
		            worker.join(4000);
		            if (worker.exit != null){
		                	;
		            } else{
		            	worker.interrupt();
		            	   Thread.currentThread().interrupt();
		                throw new TimeoutException();
		            }
		        } catch (InterruptedException ex) {
		            worker.interrupt();
		            Thread.currentThread().interrupt();
		            throw ex;
		        } finally {
		        	//从缓冲池中不断读取子程序的输出，防止溢出
			        InputStreamReader inputStr = new InputStreamReader(process.getInputStream());
			        BufferedReader br = new BufferedReader(inputStr);
			        String temp = "";
			        while((temp = br.readLine())!= null){
			        System.out.println(temp);
			        }
			        br.close();
			        inputStr.close();
		            process.destroy();
		        }

		    	}
		    
		    
		    catch(Exception ex1)
		    {
		    ex1.printStackTrace();
		    }
		 	}
		
	}
}
