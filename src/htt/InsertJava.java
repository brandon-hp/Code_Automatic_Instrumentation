package htt;

import java.util.regex.*;
import java.io.*;

class InsertJava{
	
	/* public static boolean check(String name1)
	{
		switch(name1)
		{
		case " Class": 
		case "Class": 
		case " String": 
		case "String":
		case " Object":
		case "Object":return true;
		default:;
		}
		
		
		return false;
		
	}*/
	//src Դ�ļ���ַ��name1Դ�ļ�����tar��ʱ����޸ĺ��ļ����ı���ַ��
	//��Դ�ļ�ÿһ���޸ĺ�д����ʱ�ļ���������ʱ�ļ�����д��Դ�ļ�
	public  void insert(String src,String name1,String tar){
	   BufferedReader in=null;
	   try {
			//String src="D:\\java1\\src\\run\\text11.java";
			//String tar="D:\\java1\\src\\run\\textr.txt";
		   in = new BufferedReader(new FileReader(src));
		   //�ж���ʱ�ļ��Ƿ���ڣ����������򴴽�һ��
		   File file = new File(tar);
		   if (!file.exists()) {
			    file.createNewFile();
			   }
		   
		   FileWriter fw = new FileWriter(file.getAbsoluteFile());
		   BufferedWriter bw = new BufferedWriter(fw);
		   
		  
		   int line=0;
		   //���ļ����л�ȡ���ļ���ID����text10.java ��ID=10
		   String ID=name1.substring(4,name1.indexOf("."));
		   //����Ƿ����package���
		   int packageflag=0;
		   //��ǵ�ǰ���Ƿ���ע��
		   int noteflag=0;
		   //����Ƿ�Ҫ����static SortFun����
		   int headflag=0;
		   //ʹ��Whileѭ����Դ�ļ����ȡÿһ�д���lineString��
		   String lineString;
		   while ((lineString = in.readLine()) != null){
			   if(lineString.contains("Scanner"))
				   continue;
			   //����+1
			   line++;
			   //�ж��Ƿ���package��ͷ��䣬���ǽ����޸�Ϊpackage run;
			   if(lineString.startsWith("package")) {
				  bw.write("package run;\n"); 
				  //����Ǵ���package���
				  packageflag=1;
			   }
			  else//����д����ʱ�ļ�
		          bw.write(lineString+"\n");
			   //�жϵ�ǰ���Ƿ���ע��
			   if(lineString.startsWith("\\/\\/"))//û������
				   continue;
			   if(lineString.startsWith("\\/*"))
				   noteflag=1;
			   if(lineString.endsWith("*\\/"))
				   noteflag=0;
			   if(noteflag==1)
				   continue;
			   
			   //headflag=1��ʾӦ����Դ�ļ����в����Զ�����
			   if(headflag==1)
			   {
				   bw.write("static InsertFun My= new InsertFun();\n");
				   headflag=0;
			   }
			   //��������public class��䣬����{��β��ֱ�Ӳ���static SortFun
			   //���� headflag���Ϊ1����ʾ��Դ�ļ���һ�в���static SortFun
			   if(lineString.startsWith("public class"))
			   { 	
				   if(lineString.endsWith("{"))
					   bw.write("static InsertFun My= new InsertFun();\n");
				   else
					   headflag=1;
			   
			   }
			   
			   //������ʽƥ��pattern
			  String pattern = "[A-Za-z0-9\\[\\]]+\\s{0,1}[=|:]\\s{0,1}[A-Za-z0-9]+(\\[[a-z]+\\])?([.][A-Za-z0-9]+)+[\\(]([A-Za-z0-9.\"]+([.][\\(](\\w+)?[\\)])?)?(([,][A-Za-z0-9.\"]+([.][\\(](\\w+)?[\\)])?)+)?[\\)]";
		      Pattern comp = Pattern.compile(pattern);
		      //�Զ�ȡ�ľ��ӽ���ƥ�䣬��API������mat��
		      Matcher mat = comp.matcher(lineString);
		      //��mat��ȡÿһ��ƥ�䵽���ַ���
		      while(mat.find()){
				   //����Ƿ�Ҫ������ñ���
				   int classflag=0;
		    	  //��ȡҪ��װ��API���
		    	  String group = mat.group();
		    	  //��API�����зָ� ,����s=a.vv(bb,bb)�ָ��s=a.vv,bb,bb(APIͷ��������������������....)
		    	  String[] arr=group.split("[,\\(\\)]");
		    	  //��s=a.vvͷ�����зָ��s,a.vv������ֵ������.API��
		    	  String[] brr=(arr[0]).split("[=:]");
		    	  //������ֵΪs[]�������ŷָ��
		    	  String[] wrr=brr[0].split("\\[\\]");
		    	  String classname="";
		    	  //��a.vv����.API�ָ��ȡa,vv��������API��
		    	  String[] crr=brr[1].split("[.]");
		    	  //��API���ķ���ֵ���в�װ
		    	  if(brr.length>1)
		    	  {
		    		  //�������API�ı���
		    		  classname=crr[0];
		    		  //�ж�������õ�API�ı������������Ǳ���,����������.class
		    		  if(crr[1].equals(" Period")||crr[1].equals(" Duration")||crr[1].equals(" DayOfWeek")||crr[1].equals(" Instant")||crr[1].equals(" Calendar")||crr[1].equals(" TimeZone")||crr[1].equals(" Float")||crr[1].equals("Float")||crr[1].equals("Double")||crr[1].equals(" Executors")||crr[1].equals("Arrays")||crr[1].equals(" ObjectStreamClass")||crr[1].equals(" Optional")||crr[1].equals("java")||crr[1].equals("String")||crr[1].equals("JColorChooser")||crr[1].equals(" LocalDate")||crr[1].equals(" LocalTime")||crr[1].equals(" LocalDateTime")||crr[1].equals(" MonthDay")||crr[1].equals(" OffsetTime")||crr[1].equals(" OffsetDateTime")||crr[1].equals(" Duration")||crr[1].equals(" Clock")||crr[1].equals(" ZonedDateTime")||crr[1].equals("ZonedDateTime")||crr[1].equals(" ZoneId")||crr[1].equals(" LocalTime")||crr[1].equals(" ZoneOffset")||crr[1].equals(" Year")||crr[1].equals(" YearMonth")||crr[1].equals(" Period")||crr[1].equals(" Duration")||crr[1].equals(" DayOfWeek")||crr[1].equals(" Instant")||crr[1].equals("class")||crr[1].equals(" Calendar")||crr[1].equals(" TimeZone")||crr[1].equals(" Float")||crr[1].equals("Float")||crr[1].equals("Double")||crr[1].equals("Integer")||crr[1].equals(" Executors")||crr[1].equals(" AccessController")||crr[0].equals(" Math")||crr[0].equals(" Compiler")||crr[0].equals(" Mobile")||crr[0].equals(" Character")||crr[0].equals(" Thread")||crr[0].equals(" Integer")||crr[0].equals(" Runtime")||crr[0].equals(" Locale")||crr[0].equals(" StrictMath")||crr[0].equals(" Double")||crr[0].equals(" Thread")||crr[0].equals(" Class")||crr[0].equals(" Byte")||crr[0].equals(" System")||crr[0].equals(" Boolean")||crr[0].equals(" Package")||crr[0].equals(" Long")||crr[0].equals(" Modifier")||crr[0].equals(" String")||crr[0].equals(" string"))
		    			  classname+=".class";
		    		  else//���Ǳ��������    Period
		    			  classflag=1;
		    		  //�ļ���
		    		  String filename=name1+"_"+ID+"_line"+line+"_\")+"+"String.valueOf("+classname+".getClass())+"+"String.valueOf(\"_"+crr[1]+"_\"+System.getProperty(\"java.version\")+\".txt";  
			    	  //Ҫ��������
		    		  String insertstatement="";
		    		  //��һ������wrr[0]Ϊ����ֵ�����������ڶ�����String.valueOf(filename)Ϊ��ŵ�txt�ı���ַ������������classname.getClass().getClassLoader()==null�жϱ��������Ƿ���JAVA��jdk����
			    	  insertstatement+="My.WriteFile("+wrr[0]+",String.valueOf(\""+filename+"\"),"+classname+".getClass().getClassLoader()==null"+");\n";
			    	  bw.write(insertstatement);	
		    
		    	  }

		    	  	//��API������������в�װ
				      String filename="Input_"+name1+"_"+ID+"_line"+line+"_\")+"+"String.valueOf("+classname+".getClass())+"+"String.valueOf(\"_"+crr[1]+"_\"+System.getProperty(\"java.version\")+\".txt";  
				      String insertstatement="";//"file1 = new File(\""+head+"\");\nfile1.createNewFile();\nfw1 = new FileWriter(file1.getAbsoluteFile());\nbw1 = new BufferedWriter(fw1);\n";
				      //���ζ�API��ÿ���������в�װ
				      for(int i=1;i<arr.length;i++)
				      { 
				    	  insertstatement+="My.WriteFile("+arr[i]+",String.valueOf(\""+filename+"\"),"+classname+".getClass().getClassLoader()==null"+");\n";//"bw1.write(String.valueOf("+arr[i]+")+\"\\n\");\n";
				      
				      }
				      //��û�в���Ҳ����һ�������ļ�.����Ƚ��ı�
				      if(arr.length<=1&&classflag==0)
				      {
				    	  insertstatement+="My.WriteFile(\"��\",String.valueOf(\""+filename+"\"),true);\n";
				      }
				      else if(classflag==1)
				    	  insertstatement+="My.WriteFile("+classname+",String.valueOf(\""+filename+"\"),"+classname+".getClass().getClassLoader()==null"+");\n";
				      bw.write(insertstatement);  
		    }
		   }
		   bw.close();
		   in.close();
		   //����ʱ�ļ����ȡ��װ���Դ���룬д��Դ�ļ�
		   in= new BufferedReader(new FileReader(tar));
		   	file = new File(src);
		   if (!file.exists()) {
			    file.createNewFile();
			   }
		    fw = new FileWriter(file.getAbsoluteFile());
		    bw = new BufferedWriter(fw);
		    //��û��package run;��䣬�����ļ���ͷд��
		    if(packageflag==0)
		    	bw.write("package run;\n");
		    while ((lineString = in.readLine()) != null){
	            bw.write(lineString+"\n");	    	
		    }
		   bw.close();
		   in.close();
		   
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
   }
}