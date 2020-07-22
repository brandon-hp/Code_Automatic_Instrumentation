package htt;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * javaʵ������
 */
public class SpiderDemo1 {
 
    //��վ����ҳ����
    private final static String theURL = "https://www.javatpoint.com/java-class-getfields-method";
    private final static String theURL1 = "https://www.javatpoint.com/java-";
    //�������ڣ����ڱ����־
    private final static String theTIME = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    //��ҳ�����ļ�·��
    private final static String theFILE = "htm"+ "/URL.txt";
    //��ҳԴ��·��
    private final static String thePATH = "htm"+ "/code";
    private  static int f=1189;
    //������ʽ�������ж��Ƿ���һ����ַ
    private final static String theREGEX= "<a(?:\\s+.)*?\\s+href=\"([^\"]*?)\"";//"java-class-[a-zA-Z-]+\">";//"(http|https)://[\\w+\\.?/?]+\\.[A-Za-z]+";
   // static  SortJava sort1=new SortJava();
    /**
     * ������
     * @param args
     */
    public static void main(String[] args) {
        found();
        System.out.println("��վ��ȡ���");
    }
 
 
    public static void found() {
        PrintWriter pw = null;
        try{
            //�����ļ�Ŀ¼
            File fileDir = new File(thePATH);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
 
            //������վ��ҳ�����ļ�
            pw = new PrintWriter(new FileWriter(theFILE),true);
 
            //ʹ�õݹ������վ��ÿ����ҳ
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
     * ��ȡ����ҳԴ�����ҳ������
     * @param url ����ҳ��ַ
     * @param tpw ����վ��ҳ��ַ�ļ����ӵ�io��
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
            connection.setConnectTimeout(10000); //�������ӳ�ʱ
            connection.setReadTimeout(10000); //������Ӧ��ʱ
            //�����ļ���
           // String src = thePATH + url.substring(theURL.length());
          /*  File fileDir = new File(src);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
 */
            //����Դ�����ļ�
            //pw = new PrintWriter(new FileWriter(src + "/Test.txt"),true);
            pw1 = tpw;
 
            //��ȡ��ҳ�ļ�
            br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line=null;
            int flag=0;
           
            PrintWriter re=null;
           // String []path=src.split("D:/java1/htm/");
            //System.out.println(path[1]);
            String filename="";
            int flags=0;
            while((line=br.readLine())!=null){
                //����ȡ��Դ��д���ļ�
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
                //�ж��Ƿ���һ����ַ
                //pw.println(line);
                while(matcher.find()){
                    //�ж���ַ�Ƿ�����վ����ַΪǰ׺����ֹ����������վ,���ж��Ƿ��ԭ����ַ�ظ�
                	
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
            System.out.println("��ҳ" + url + "��������ȡ���");
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
     * �ж��Ƿ���Դ�����ַ��ͬ
     * @param str ���жϵ���ַ
     * @return �Ƿ��ظ�
     */
    public static boolean examine(String str) {
        BufferedReader br = null;
        String str1;
        try {
            br = new BufferedReader(new FileReader(theFILE));
 
//            //��Ը���վ������ҳ������
//            if(str.startsWith("http://www.jyyishu.cn/artnews/")) {
//                return false;
//            }
            
            //ѭ���ļ���ÿһ�е���ַ���ж��Ƿ��ظ����ظ����˳�
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
