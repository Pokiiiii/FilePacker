package FilePacker;

import java.io.*;

public class FilePacker {
    public static final Long BLOCKSIZE=10 * 1024 * 1024L;//10MB
    public static final String BASEPATH="c:\\Users\\73426\\Desktop\\PPT\\test";
    private static void splitFile(File f, Long eachSize) {
        if(f.length()>BLOCKSIZE) {
            int count = (int)(Math.ceil(f.length() / eachSize))+1;//块数
            try {
                InputStream inf = new FileInputStream(f);
                OutputStream[] outf = new FileOutputStream[count];
                /**创建文件夹，存储各小块文件*/
                int no = f.getName().lastIndexOf(".");
                String str = f.getParent()+"\\"+f.getName().substring(0, no );/**目录路径*/
                File dirfile = new File(str);
                if(!dirfile.exists()) {
                    dirfile.mkdirs();
                }
                /**创建各小块文件并命名*/
                File[] dir_f = new File[count];
                /**获取文件类型*/
                String fName = f.getName();
                String fPattern = fName.substring(fName.lastIndexOf("."), fName.length());
                for(int j=0; j<count; j++) {
                    String newPath = str+"\\"+f.getName().substring(0, no)+"-"+j+fPattern;
                    dir_f[j] = new File(newPath);
                    outf[j] = new FileOutputStream(dir_f[j]);
                }
                /**写入各块内容*/
                int s,m=0,  buffsize=10*1024;
                byte[] buffer = new byte[buffsize];
                s = inf.read(buffer, 0, buffsize);
                while(s != -1&& m<count) {
                    if(dir_f[m].length() < buffsize) {
                        outf[m].write(buffer, 0, buffsize);
                        s = inf.read(buffer, 0, buffsize);
                    }
                    if(dir_f[m].length() == buffsize){
                        outf[m].close();
                        m = m+1;
                        int off = (int)(f.length()-m*buffsize);
                        if(off<buffsize) {
                            outf[m].write(buffer, 0, off);
                            outf[m].close();
                            break;
                        }
                    }
                }
                inf.close();
                f.delete();
            }
            catch(IOException ioe) {
                System.out.println("IO 异常");
            }
            catch(IndexOutOfBoundsException ine) {
                System.out.println("数组越界 异常");
            }
            catch(Exception e) {
                System.out.println("异常");
            }
        }
    }

    public static void divide( String fPath ) {
        File f = new File(fPath);
        if(f.exists()){
            if(f.isFile() && f.length() > BLOCKSIZE) {
                splitFile(f, BLOCKSIZE);
            }
            else if(f.isDirectory() && f.length() > BLOCKSIZE) {
                File[] dir = f.listFiles();

                for(int i=0; i<dir.length; i++) {
                    if(dir[i].exists() && dir[i].length() > BLOCKSIZE){
                        if(dir[i].isFile()) {
                            splitFile(dir[i], BLOCKSIZE);
                        }
                        else if(dir[i].isDirectory() && dir[i].length() > BLOCKSIZE) {
                            divide(dir[i].getAbsolutePath());
                        }
                    }
                }
            }
        }
        else {
            System.out.println(fPath + "  不存在文件！");
        }
    }

    private static void murgeFile(File dir, File dst) throws IOException {

        String[] children = dir.list();
        FilenameFilter filter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                return true;
            }
        };
        File filePath = new File(BASEPATH+"\\作业要求");//需要拼装的目录
        String fileIndex="0";
        File FileBig = new File(filePath + "/" + "Files"+"_"+fileIndex);
        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(FileBig));
        int buffsize=1024;
        children = dir.list(filter);
        for (int i = 0; i < children.length; i++) {
            String inhalt = children[i];
            FileInputStream in = new FileInputStream(dir + "/" + inhalt);

            byte[] buffer = new byte[buffsize];
            int len=0;
            if (FileBig.length() /buffsize / buffsize < 10) {
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } else {
                int fileIndexInt = Integer.parseInt(fileIndex) + 1;
                FileBig = new File(filePath + "/" + "Files" + "_"
                        + fileIndexInt);
                out = new BufferedOutputStream(
                        new FileOutputStream(FileBig));

                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.flush();
                fileIndex = String.valueOf(fileIndexInt);
            }
        }
        out.close();
    }

    public static void start()
    {
        divide(BASEPATH);//切割
        File dir = new File(BASEPATH+"\\作业要求");
        File out=new File(BASEPATH+"\\作业要求");
        try {
            murgeFile(dir,out);
            //由于是在网上找的方法，东拼西凑改的，合并这个地方还不够灵活，应该要再创建一个方法遍历原目录所有文件进行合并处理，遇到子文件夹再将子文件也拼接（待完成）
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws IOException {
        start();
    }
}