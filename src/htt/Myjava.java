package htt;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeoutException;

public class Myjava {
	static readDict re=new readDict();
	//��ֹjava�ӳ������г�ʱ����ʵûɶ�ÿɺ���Worker
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
		 //��ȡ��Ŀ¼�µ������ļ�
		    List<File> files = readDict.getFiles("src/run");
		    //����ִ���ļ�.class���ļ�������ve��
		    for(File f : files){
		       if(f.getName().endsWith(".class"))
		       {
		    	   //���ļ�����.class�ָ��
		    	   String[] name=f.getName().split(".class");
		    	   ve.add(name[0]);
		       }
		       
		    }
		    //ʹ��cmd��java����������D:\\java1\\src\\run�µ�ִ���ļ�
		    //Ҫɾ������ִ�е�.class�ļ������������.
		    //ÿ��������������ǰҪ��֮ǰ.class���ɲ�װtxt�ı�ɾ��.
		    String JAVAC_PATH="java";
		    String SOURCE_PATH="src/";

		 	for(int i=0;i<ve.size();i++) {
		    try
		    {
		    	//Runtime.getRuntime().exec("cmd /c");
		    	//��ʾ��ǰҪִ�е�.class�ļ��������ڲ��ҵ�ǰ�ĸ��ļ�����������ɾȥ
		    	System.out.println(ve.elementAt(i));
		        String command=JAVAC_PATH + " -cp " + SOURCE_PATH+" run."+ve.elementAt(i);
		        //ִ��cmd����
		        Process process=Runtime.getRuntime().exec(command);
		        //����Workerûɶ�ú���
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
		        	//�ӻ�����в��϶�ȡ�ӳ�����������ֹ���
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
