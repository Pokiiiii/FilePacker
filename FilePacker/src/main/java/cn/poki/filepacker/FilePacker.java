package cn.poki.filepacker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FilePacker {
    public static final Long BLOCKSIZE=10 * 1024 * 1024L;//10MB
    public static final String BASEPATH="./data/";

    // 下面两个函数就是递归的时候分割文件和文件夹的（意义上）
    private void  analyzeFile(File filepath){
        // todo [task]-2:把伪代码变成代码
        // 当前文件的大小为 a，最后一个块的大小为b
        // 如果a+b小于BLOCKSIZE
        //    当前文件加到最后一个块
        // else
        //    当前文件的前半部分加到最后一个块
        //    new 一个新的块加到数组（变成最后一块了)
        //     当前文件的后半部分加到最后一个块
        throw new NotImplementedException();
    }

    private void analyzeFolder(File dirPath){
        // todo [task]-1:遍历目录，递归调用它本身和analyzeFile
        throw new NotImplementedException();
    }

    private List<PackInfo> packInfos=new LinkedList<PackInfo>();

    private List<PackInfo> analyzeDir(File path){
        analyzeFolder(path);
        return packInfos;
    }

    /** 打包文件夹
     * @param path
     * @throws Exception
     */
    private void packageDir(String path) throws Exception {
        //程序得获取到全部的信息才能正确的分割和打包文件，
        //边扫描文件夹边打包的话信息量不足，虽然也可以实现，不过写起来有坏味道
        List<PackInfo> packInfos = analyzeDir(new File(path));
        new Executer().executePackage(packInfos);
    }
    public void start() throws Exception {
        packageDir(BASEPATH);
    }

    public static void main(String[] args) throws Exception {
        new FilePacker().start();
    }
}