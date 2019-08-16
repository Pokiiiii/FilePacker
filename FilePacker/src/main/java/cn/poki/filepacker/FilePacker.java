package cn.poki.filepacker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.List;

public class FilePacker {
    public static final Long BLOCKSIZE=10 * 1024 * 1024L;//10MB
    public static final String BASEPATH="./data/";

    /**
     * 表示一个数据包
     * 一个数据文件里面包括来着不同文件的不同位置的数据对吧
     */
    class PackInfo{

        /**
         * 总大小
         */
        private Long totalSize;

        /**
         * 文件块列表
         */
        private List<FileBlock> fileBlocks;

        class FileBlock{
            //todo answer11: 怎么表示“不同文件的不同位置的数据”？
        }
    }

    // 下面两个函数就是递归的时候分割文件和文件夹的（意义上）
    private void  analyzeFile(File filepath){
        throw new NotImplementedException();
    }

    private void analyzeFolder(File dirPath){
        throw new NotImplementedException();
    }

    private List<PackInfo> analyzeDir(File path){
        //这个函数就是生成一系列packinfo对象
        //todo answer12： 怎么生成？写伪代码
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
        //程序得获取到全部的信息才能正确的分割和打包文件，
        //边扫描文件夹边打包的话信息量不足，虽然也可以实现，不过写起来有坏味道
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