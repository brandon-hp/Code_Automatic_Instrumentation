package htt;


import java.io.IOException;

public class Myjavac {
	 public static void main(String args[]){
	
		    String command="javac src/run/*.java";
		    
		    
		    try {
		    	//ʹ��cmd��javacȥ�������룬����ǰҪɾȥ�������е��ļ�
		    	//Runtime.getRuntime().exec("javac D:\\java1\\src\\run\\InsertFun.java");
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
	 }
}
