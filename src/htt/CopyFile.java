package htt;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.*;
public class CopyFile{
	public void DealAndCopyfile(String src,String src1) throws IOException {
		
		if(src.contains("\\"))
		{
			String[] f1=src.split("\\\\");
			String[] outfile=f1[5].split("Input_");
			String[] f2=src1.split("\\\\");
			String[] outfile2=f2[5].split("Input_");
			copyFile("ResultFiles/1.7.0_07/in/"+f1[5],"DifferentResultFiles/DifferentInput/in/");
			copyFile("ResultFiles/1.8.0_231/in/"+f2[5],"DifferentResultFiles/DifferentInput/in/");
			copyFile("ResultFiles/1.7.0_07/out/"+outfile[1],"DifferentResultFiles/DifferentInput/out/");
			copyFile("ResultFiles/1.8.0_231/out/"+outfile2[1],"DifferentResultFiles/DifferentInput/out/");
		}
		else
		{
			String[] f1=src.split("\\/");
			String[] f2=src1.split("\\/");
			copyFile("ResultFiles/1.7.0_07/in/Input_"+f1[3],"DifferentResultFiles/DifferentReturn/in/");
			copyFile("ResultFiles/1.8.0_231/in/Input_"+f2[3],"DifferentResultFiles/DifferentReturn/in/");
			copyFile("ResultFiles/1.7.0_07/out/"+f1[3],"DifferentResultFiles/DifferentReturn/out/");
			copyFile("ResultFiles/1.8.0_231/out/"+f2[3],"DifferentResultFiles/DifferentReturn/out/");
		}
		

    }
	public static void copyFile(String src,String dest )throws IOException{
		
		File source= new File(src);
		//创建目的地文件夹
		File destfile = new File(dest);
		if(!destfile.exists()){
			destfile.mkdir();
		}
		//source是文件，则用字节输入输出流复制文件
		 if(source.isFile()){
			FileInputStream fis = new FileInputStream(source);
			//创建新的文件，保存复制内容，文件名称与源文件名称一致
			File dfile = new File(dest+"\\"+source.getName());
			if(!dfile.exists()){
				dfile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(dfile);
				// 读写数据
				// 定义数组
				byte[] b = new byte[1024];
				// 定义长度
				int len;
				// 循环读取
				while ((len = fis.read(b))!=-1) {
					// 写出数据
					fos.write(b, 0 , len);
				}

				//关闭资源
				fos.close();
				fis.close();
			
		}
	}
}
