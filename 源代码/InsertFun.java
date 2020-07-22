package run;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InsertFun {
			//第一个参数为要记入文本的插装变量，path1为保存的txt文本，flag判断是否为JAVA的CLASS
	 public  void WriteFile(Object[] args,String path1,Boolean flag) {
		 	//判断是否是JAVA的类调用的API,若不是，直接返回不做处理.若是则该API就是JAVA的API
		 	if(flag==false)return;
		 try {
			 //获取java的版本
			 String path="ResultFiles\\"+System.getProperty("java.version");
			 //用版本号生成最上层文件夹（包含in和out文件夹）
			 File file = new File(path);
			  if (!file.exists()) {
				    file.mkdir();
				   }
			  //若文件名带有input则放入in文件夹下，若没有则放入out文件夹下
			  if(path1.startsWith("Input"))
			  {
				  file = new File(path+"\\in");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\in\\"+path1;
			  }
			  else
			  {
				  file = new File(path+"\\out");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\out\\"+path1;
			  }
			  //若无该文件则创建一个txt文件
			   file = new File(path);
			   if (!file.exists()) {
			    file.createNewFile();
			   }
			   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   //将变量的文本内容写入该文件
			   for(int i=0;i<args.length;i++)
			   bw.write(args[i].toString()+",");
			   bw.close();

			   System.out.println("Done");

			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		 
		 
		 
	 }
	 //下面四个函数同上面一样
	 public  void WriteFile(int[] args,String path1,Boolean flag) {
		 if(flag==false)return;
		 try {
			 
			 String path="ResultFiles\\"+System.getProperty("java.version");
			 File file = new File(path);
			  if (!file.exists()) {
				    file.mkdir();
				   }
			  if(path1.startsWith("Input"))
			  {
				  file = new File(path+"\\in");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\in\\"+path1;
			  }
			  else
			  {
				  file = new File(path+"\\out");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\out\\"+path1;
			  }
			 
			 
			 file = new File(path);
			   if (!file.exists()) {
			    file.createNewFile();
			   }

			   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   for(int i=0;i<args.length;i++)
			   bw.write(String.valueOf(args[i])+",");
			   bw.close();

			   System.out.println("Done");

			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		 
		 
		 
	 }
	 public  void WriteFile(char[] args,String path1,Boolean flag) {
		 if(flag==false)return;
		 try {
			 String path="ResultFiles\\"+System.getProperty("java.version");
			 File file = new File(path);
			  if (!file.exists()) {
				    file.mkdir();
				   }
			  if(path1.startsWith("Input"))
			  {
				  file = new File(path+"\\in");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\in\\"+path1;
			  }
			  else
			  {
				  file = new File(path+"\\out");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\out\\"+path1;
			  }
			  
			  file = new File(path);
			   if (!file.exists()) {
			    file.createNewFile();
			   }

			   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   for(int i=0;i<args.length;i++)
			   bw.write(String.valueOf(args[i])+",");
			   bw.close();

			   System.out.println("Done");

			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		 
		 
		 
	 }

		 

	 public  void WriteFile(Object args,String path1,Boolean flag) {
		 if(flag==false)return;
		 try {
			 String path="ResultFiles\\"+System.getProperty("java.version");
			 File file = new File(path);
			  if (!file.exists()) {
				    file.mkdir();
				   }
			  if(path1.startsWith("Input"))
			  {
				  file = new File(path+"\\in");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\in\\"+path1;
			  }
			  else
			  {
				  file = new File(path+"\\out");
				  if (!file.exists()) {
					    file.mkdir();
					   } 
				  path=path+"\\out\\"+path1;
			  }
			 
			    file = new File(path);
			   if (!file.exists()) {
			    file.createNewFile();
			   }
	
			   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(args.toString()+",");
			   bw.close();
	
			   System.out.println("Done");
	
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
	 
	 
	 
	 }

	
	}

