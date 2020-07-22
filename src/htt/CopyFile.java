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
		//����Ŀ�ĵ��ļ���
		File destfile = new File(dest);
		if(!destfile.exists()){
			destfile.mkdir();
		}
		//source���ļ��������ֽ���������������ļ�
		 if(source.isFile()){
			FileInputStream fis = new FileInputStream(source);
			//�����µ��ļ������渴�����ݣ��ļ�������Դ�ļ�����һ��
			File dfile = new File(dest+"\\"+source.getName());
			if(!dfile.exists()){
				dfile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(dfile);
				// ��д����
				// ��������
				byte[] b = new byte[1024];
				// ���峤��
				int len;
				// ѭ����ȡ
				while ((len = fis.read(b))!=-1) {
					// д������
					fos.write(b, 0 , len);
				}

				//�ر���Դ
				fos.close();
				fis.close();
			
		}
	}
}
