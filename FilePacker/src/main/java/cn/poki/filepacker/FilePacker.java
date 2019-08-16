package cn.poki.filepacker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

public class FilePacker {
    public static final Long BLOCKSIZE=10 * 1024 * 1024L;//10MB
    public static final String BASEPATH="c:\\Users\\73426\\Desktop\\PPT\\test";

    private static void splitFile(File f, Long eachSize) throws IOException {
        if(f.length()>BLOCKSIZE) {
            int count = (int)(Math.ceil(f.length() / eachSize))+1;//块数
            InputStream inf = new FileInputStream(f);
            String subpath = f.getName().substring(0, f.getName().lastIndexOf("."));
            String str = f.getParent()+"\\"+subpath;/**目录路径*/
            createOutputDir(str);
            packageBlockFiles(f, count, inf, subpath, str);
            inf.close();
        }else{
            throw new NotImplementedException();
        }
    }

    private static void packageBlockFiles(File f, int count, InputStream inf,String subpath, String str) throws IOException {
        OutputStream[] outf = new FileOutputStream[count];
        /**创建各小块文件并命名*/
        File[] dir_f = new File[count];
        /**获取文件类型*/
        String fName = f.getName();
        String fPattern = fName.substring(fName.lastIndexOf("."), fName.length());
        for(int j=0; j<count; j++) {
            String newPath = str+"\\"+subpath+"-"+j+fPattern;
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
    }

    /** 创建文件夹，存储各小块文件
     */
    private static void createOutputDir(String str) {
        File dirfile = new File(str);
        if(!dirfile.exists()) {
            dirfile.mkdirs();
        }
    }

    public static void divide( String fPath ) throws Exception {
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
                            // todo answer8:  很显然这里传进去的路径**存在**且是**目录** ，而divide函数里面有判断，这样的函数应该是同一个吗？
                            divide(dir[i].getAbsolutePath());
                        }
                    }
                }
            }else{
                // todo answer7: 小于10m的被喵喵吃了吗？
                throw new NotImplementedException();
            }
        }
        else {
            throw new Exception("文件不存在"+fPath);
        }
    }

    /** 打包文件夹
     * todo answer6:为什么要有这个函数，直接调用divide不好吗？
     * @param path
     * @throws Exception
     */
    private static void packageDir(String path) throws Exception {
        divide(path);
    }

    public static void start() throws Exception {
        packageDir(BASEPATH);
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}