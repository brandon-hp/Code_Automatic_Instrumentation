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
	//src 源文件地址，name1源文件名，tar临时存放修改后文件的文本地址。
	//将源文件每一行修改后写入临时文件，最后从临时文件读出写回源文件
	public  void insert(String src,String name1,String tar){
	   BufferedReader in=null;
	   try {
			//String src="D:\\java1\\src\\run\\text11.java";
			//String tar="D:\\java1\\src\\run\\textr.txt";
		   in = new BufferedReader(new FileReader(src));
		   //判断临时文件是否存在，若不存在则创建一个
		   File file = new File(tar);
		   if (!file.exists()) {
			    file.createNewFile();
			   }
		   
		   FileWriter fw = new FileWriter(file.getAbsoluteFile());
		   BufferedWriter bw = new BufferedWriter(fw);
		   
		  
		   int line=0;
		   //从文件名中获取该文件的ID号如text10.java 则ID=10
		   String ID=name1.substring(4,name1.indexOf("."));
		   //标记是否存在package语句
		   int packageflag=0;
		   //标记当前行是否是注释
		   int noteflag=0;
		   //标记是否要插入static SortFun变量
		   int headflag=0;
		   //使用While循环从源文件里读取每一行存入lineString中
		   String lineString;
		   while ((lineString = in.readLine()) != null){
			   if(lineString.contains("Scanner"))
				   continue;
			   //行数+1
			   line++;
			   //判断是否是package开头语句，若是将其修改为package run;
			   if(lineString.startsWith("package")) {
				  bw.write("package run;\n"); 
				  //并标记存在package语句
				  packageflag=1;
			   }
			  else//否则写入临时文件
		          bw.write(lineString+"\n");
			   //判断当前行是否是注释
			   if(lineString.startsWith("\\/\\/"))//没起作用
				   continue;
			   if(lineString.startsWith("\\/*"))
				   noteflag=1;
			   if(lineString.endsWith("*\\/"))
				   noteflag=0;
			   if(noteflag==1)
				   continue;
			   
			   //headflag=1表示应该在源文件类中插入自定义类
			   if(headflag==1)
			   {
				   bw.write("static InsertFun My= new InsertFun();\n");
				   headflag=0;
			   }
			   //若该行是public class语句，若以{结尾则直接插入static SortFun
			   //否则将 headflag标记为1，表示在源文件下一行插入static SortFun
			   if(lineString.startsWith("public class"))
			   { 	
				   if(lineString.endsWith("{"))
					   bw.write("static InsertFun My= new InsertFun();\n");
				   else
					   headflag=1;
			   
			   }
			   
			   //正则表达式匹配pattern
			  String pattern = "[A-Za-z0-9\\[\\]]+\\s{0,1}[=|:]\\s{0,1}[A-Za-z0-9]+(\\[[a-z]+\\])?([.][A-Za-z0-9]+)+[\\(]([A-Za-z0-9.\"]+([.][\\(](\\w+)?[\\)])?)?(([,][A-Za-z0-9.\"]+([.][\\(](\\w+)?[\\)])?)+)?[\\)]";
		      Pattern comp = Pattern.compile(pattern);
		      //对读取的句子进行匹配，将API语句存入mat中
		      Matcher mat = comp.matcher(lineString);
		      //从mat获取每一个匹配到的字符串
		      while(mat.find()){
				   //标记是否要插入调用变量
				   int classflag=0;
		    	  //获取要插装的API语句
		    	  String group = mat.group();
		    	  //对API语句进行分割 ,比如s=a.vv(bb,bb)分割成s=a.vv,bb,bb(API头部，输入参数，输入参数....)
		    	  String[] arr=group.split("[,\\(\\)]");
		    	  //对s=a.vv头部进行分割，成s,a.vv（返回值，变量.API）
		    	  String[] brr=(arr[0]).split("[=:]");
		    	  //若返回值为s[]，将括号分割掉
		    	  String[] wrr=brr[0].split("\\[\\]");
		    	  String classname="";
		    	  //对a.vv变量.API分割获取a,vv（变量，API）
		    	  String[] crr=brr[1].split("[.]");
		    	  //对API语句的返回值进行插装
		    	  if(brr.length>1)
		    	  {
		    		  //存入调用API的变量
		    		  classname=crr[0];
		    		  //判断这个调用的API的变量是类名还是变量,若是类名加.class
		    		  if(crr[1].equals(" Period")||crr[1].equals(" Duration")||crr[1].equals(" DayOfWeek")||crr[1].equals(" Instant")||crr[1].equals(" Calendar")||crr[1].equals(" TimeZone")||crr[1].equals(" Float")||crr[1].equals("Float")||crr[1].equals("Double")||crr[1].equals(" Executors")||crr[1].equals("Arrays")||crr[1].equals(" ObjectStreamClass")||crr[1].equals(" Optional")||crr[1].equals("java")||crr[1].equals("String")||crr[1].equals("JColorChooser")||crr[1].equals(" LocalDate")||crr[1].equals(" LocalTime")||crr[1].equals(" LocalDateTime")||crr[1].equals(" MonthDay")||crr[1].equals(" OffsetTime")||crr[1].equals(" OffsetDateTime")||crr[1].equals(" Duration")||crr[1].equals(" Clock")||crr[1].equals(" ZonedDateTime")||crr[1].equals("ZonedDateTime")||crr[1].equals(" ZoneId")||crr[1].equals(" LocalTime")||crr[1].equals(" ZoneOffset")||crr[1].equals(" Year")||crr[1].equals(" YearMonth")||crr[1].equals(" Period")||crr[1].equals(" Duration")||crr[1].equals(" DayOfWeek")||crr[1].equals(" Instant")||crr[1].equals("class")||crr[1].equals(" Calendar")||crr[1].equals(" TimeZone")||crr[1].equals(" Float")||crr[1].equals("Float")||crr[1].equals("Double")||crr[1].equals("Integer")||crr[1].equals(" Executors")||crr[1].equals(" AccessController")||crr[0].equals(" Math")||crr[0].equals(" Compiler")||crr[0].equals(" Mobile")||crr[0].equals(" Character")||crr[0].equals(" Thread")||crr[0].equals(" Integer")||crr[0].equals(" Runtime")||crr[0].equals(" Locale")||crr[0].equals(" StrictMath")||crr[0].equals(" Double")||crr[0].equals(" Thread")||crr[0].equals(" Class")||crr[0].equals(" Byte")||crr[0].equals(" System")||crr[0].equals(" Boolean")||crr[0].equals(" Package")||crr[0].equals(" Long")||crr[0].equals(" Modifier")||crr[0].equals(" String")||crr[0].equals(" string"))
		    			  classname+=".class";
		    		  else//若是变量则插入    Period
		    			  classflag=1;
		    		  //文件名
		    		  String filename=name1+"_"+ID+"_line"+line+"_\")+"+"String.valueOf("+classname+".getClass())+"+"String.valueOf(\"_"+crr[1]+"_\"+System.getProperty(\"java.version\")+\".txt";  
			    	  //要插入的语句
		    		  String insertstatement="";
		    		  //第一个参数wrr[0]为返回值变量的名，第二参数String.valueOf(filename)为存放的txt文本地址，第三个参数classname.getClass().getClassLoader()==null判断变量的类是否是JAVA的jdk的类
			    	  insertstatement+="My.WriteFile("+wrr[0]+",String.valueOf(\""+filename+"\"),"+classname+".getClass().getClassLoader()==null"+");\n";
			    	  bw.write(insertstatement);	
		    
		    	  }

		    	  	//对API的输入参数进行插装
				      String filename="Input_"+name1+"_"+ID+"_line"+line+"_\")+"+"String.valueOf("+classname+".getClass())+"+"String.valueOf(\"_"+crr[1]+"_\"+System.getProperty(\"java.version\")+\".txt";  
				      String insertstatement="";//"file1 = new File(\""+head+"\");\nfile1.createNewFile();\nfw1 = new FileWriter(file1.getAbsoluteFile());\nbw1 = new BufferedWriter(fw1);\n";
				      //依次对API的每个参数进行插装
				      for(int i=1;i<arr.length;i++)
				      { 
				    	  insertstatement+="My.WriteFile("+arr[i]+",String.valueOf(\""+filename+"\"),"+classname+".getClass().getClassLoader()==null"+");\n";//"bw1.write(String.valueOf("+arr[i]+")+\"\\n\");\n";
				      
				      }
				      //若没有参数也生成一个参数文件.方便比较文本
				      if(arr.length<=1&&classflag==0)
				      {
				    	  insertstatement+="My.WriteFile(\"无\",String.valueOf(\""+filename+"\"),true);\n";
				      }
				      else if(classflag==1)
				    	  insertstatement+="My.WriteFile("+classname+",String.valueOf(\""+filename+"\"),"+classname+".getClass().getClassLoader()==null"+");\n";
				      bw.write(insertstatement);  
		    }
		   }
		   bw.close();
		   in.close();
		   //从临时文件里读取插装完的源代码，写回源文件
		   in= new BufferedReader(new FileReader(tar));
		   	file = new File(src);
		   if (!file.exists()) {
			    file.createNewFile();
			   }
		    fw = new FileWriter(file.getAbsoluteFile());
		    bw = new BufferedWriter(fw);
		    //若没有package run;语句，则在文件开头写入
		    if(packageflag==0)
		    	bw.write("package run;\n");
		    while ((lineString = in.readLine()) != null){
	            bw.write(lineString+"\n");	    	
		    }
		   bw.close();
		   in.close();
		   
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
   }
}