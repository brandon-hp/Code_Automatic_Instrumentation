package htt;


import java.io.IOException;

public class Myjavac {
	 public static void main(String args[]){
	
		    String command="javac src/run/*.java";
		    
		    
		    try {
		    	//使用cmd，javac去批量编译，编译前要删去不能运行的文件
		    	//Runtime.getRuntime().exec("javac D:\\java1\\src\\run\\InsertFun.java");
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	 }
}
