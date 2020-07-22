package htt;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * java实现爬虫
 */
public class SpiderDemo1 {
 
    //网站主网页链接
    private final static String theURL = "https://www.javatpoint.com/java-class-getfields-method";
    private final static String theURL1 = "https://www.javatpoint.com/java-";
    //今日日期，用于标记日志
    private final static String theTIME = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    //网页链接文件路径
    private final static String theFILE = "htm"+ "/URL.txt";
    //网页源码路径
    private final static String thePATH = "htm"+ "/code";
    private  static int f=1189;
    //正则表达式，用于判断是否是一个网址
    private final static String theREGEX= "<a(?:\\s+.)*?\\s+href=\"([^\"]*?)\"";//"java-class-[a-zA-Z-]+\">";//"(http|https)://[\\w+\\.?/?]+\\.[A-Za-z]+";
   // static  SortJava sort1=new SortJava();
    /**
     * 启动类
     * @param args
     */
    public static void main(String[] args) {
        found();
        System.out.println("网站爬取完成");
    }
 
 
    public static void found() {
        PrintWriter pw = null;
        try{
            //创建文件目录
            File fileDir = new File(thePATH);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
 
            //创建网站网页链接文件
            pw = new PrintWriter(new FileWriter(theFILE),true);
 
            //使用递归遍历网站的每个网页
            spiderURL(theURL, pw);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pw != null) {
                    pw.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
 
 
    /**
     * 爬取该网页源码和网页内连接
     * @param url 该网页网址
     * @param tpw 对网站网页网址文件连接的io流
     */
    public static void spiderURL(String url, PrintWriter tpw){
        URL realURL=null;
        URLConnection connection=null;
        BufferedReader br=null;
        PrintWriter pw=null;
        PrintWriter pw1=null;
 
        Pattern pattern=Pattern.compile(theREGEX);
        try{
            realURL=new URL(url);
            connection=realURL.openConnection();
            connection.setConnectTimeout(10000); //设置连接超时
            connection.setReadTimeout(10000); //设置响应超时
            //生成文件夹
           // String src = thePATH + url.substring(theURL.length());
          /*  File fileDir = new File(src);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
 */
            //生成源代码文件
            //pw = new PrintWriter(new FileWriter(src + "/Test.txt"),true);
            pw1 = tpw;
 
            //爬取网页文件
            br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line=null;
            int flag=0;
           
            PrintWriter re=null;
           // String []path=src.split("D:/java1/htm/");
            //System.out.println(path[1]);
            String filename="";
            int flags=0;
            while((line=br.readLine())!=null){
                //把爬取的源码写入文件
            	if(line.equals("<div class=\"codeblock\"><textarea name=\"code\" class=\"java\">"))
            	{flag=1;
            	flags=0;
            		f++;
            		re= new PrintWriter(new FileWriter(thePATH + "/text"+f+".java"),true);
            		
            	}
            	else {
            	if(line.equals("</textarea></div>")&&flag==1)
            		{flag=0;
            		re.close();
            		if(flags==0) {
            			f--;
           		 File oldf = new File(thePATH + "/text"+f+".java");
        	     File newFile = new File(oldf.getParent() + File.separator + filename+"www.java");
        		 oldf.renameTo(newFile);
            		}
        		 //sort1.insert(newFile.getAbsolutePath(),newFile.getName(),"D:\\java1\\src\\run\\textr1.txt");
            		}
                if(flag==1) {
                	if(line.startsWith("public class"))
                	{
                		flags=1;
                		String[] krr=line.split("[\\s+|\\{]");
                		if(krr[2].equals("class"))
                		filename=krr[3];
                		else
                		filename=krr[2];
                		//re.println(String.valueOf("public class text")+f+"{");
                	}
                	if(filename.length()>0) {
                		line=line.replaceAll(filename,"text"+f);
                	}
                	//else
                	String line1=line.replaceAll("&lt;",'<'+"");
                	String line2=line1.replaceAll("&gt;",'>'+"");
                	
                	re.println(line2);
                }
            	}
                Matcher matcher=pattern.matcher(line);
                //判断是否是一个网址
                //pw.println(line);
                while(matcher.find()){
                    //判断网址是否以网站主网址为前缀，防止爬到其他网站,并判断是否和原先网址重复
                	
                    String[] temp=matcher.group().split("\"");
                  //  System.out.println(temp[1]);
                    String us="https://www.javatpoint.com/";
                    if(temp[1].equals("https://www.javatpoint.com")||temp[1].equals("http://www.javatpoint.com"))
                    	continue;
                    if(temp[1].startsWith(us))
                    	us=temp[1];
                    else
                        us+=temp[1];
                	if(us.startsWith(theURL1) && examine(us)) {
                		System.out.println(us);
                        pw1.println(us);
                        spiderURL(us, pw1);
                    	
                    }
                    

                }
               
            }
            System.out.println("网页" + url + "内链接爬取完成");
    }catch(Exception e){
        e.printStackTrace();
    }finally {
            try {
                if(br != null) {
                    br.close();
                }
                if(pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
 
    /**
     * 判断是否和以储存网址相同
     * @param str 需判断的网址
     * @return 是否重复
     */
    public static boolean examine(String str) {
        BufferedReader br = null;
        String str1;
        try {
            br = new BufferedReader(new FileReader(theFILE));
 
//            //针对该网站无用网页的屏蔽
//            if(str.startsWith("http://www.jyyishu.cn/artnews/")) {
//                return false;
//            }
            
            //循环文件中每一行的网址，判断是否重复，重复则退出
            while((str1 = br.readLine()) != null) {
                if(str.equals(str1)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
