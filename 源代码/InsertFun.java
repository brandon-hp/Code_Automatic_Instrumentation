package run;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InsertFun {
			//��һ������ΪҪ�����ı��Ĳ�װ������path1Ϊ�����txt�ı���flag�ж��Ƿ�ΪJAVA��CLASS
	 public  void WriteFile(Object[] args,String path1,Boolean flag) {
		 	//�ж��Ƿ���JAVA������õ�API,�����ǣ�ֱ�ӷ��ز�������.�������API����JAVA��API
		 	if(flag==false)return;
		 try {
			 //��ȡjava�İ汾
			 String path="ResultFiles\\"+System.getProperty("java.version");
			 //�ð汾���������ϲ��ļ��У�����in��out�ļ��У�
			 File file = new File(path);
			  if (!file.exists()) {
				    file.mkdir();
				   }
			  //���ļ�������input�����in�ļ����£���û�������out�ļ�����
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
			  //���޸��ļ��򴴽�һ��txt�ļ�
			   file = new File(path);
			   if (!file.exists()) {
			    file.createNewFile();
			   }
			   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   //���������ı�����д����ļ�
			   for(int i=0;i<args.length;i++)
			   bw.write(args[i].toString()+",");
			   bw.close();

			   System.out.println("Done");

			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		 
		 
		 
	 }
	 //�����ĸ�����ͬ����һ��
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

