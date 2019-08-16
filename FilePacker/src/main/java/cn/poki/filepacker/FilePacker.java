package cn.poki.filepacker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.List;

public class FilePacker {
    public static final Long BLOCKSIZE=10 * 1024 * 1024L;//10MB
    public static final String BASEPATH="./data/";

    class PackInfo{
        //todo answer10： 每个数据包里面应该包括什么信息？为什么
    }
    private void  analyzeFile(File filepath){
        throw new NotImplementedException();
    }
    private List<PackInfo> analyzeDir(File path){
        throw new NotImplementedException();
    }


    private void executePackage(List<PackInfo> packInfos){
        throw new NotImplementedException();
    }

    /** 打包文件夹
     * @param path
     * @throws Exception
     */
    private void packageDir(String path) throws Exception {
        //todo answer9： 分为两步，分析目录的所有信息，然后再执行打包，为什么？这样可以解决文件小于10m时的尴尬问题吗
        List<PackInfo> packInfos = analyzeDir(new File(path));
        executePackage(packInfos);
    }

    public void start() throws Exception {
        packageDir(BASEPATH);
    }

    public static void main(String[] args) throws Exception {
        new FilePacker().start();
    }
}