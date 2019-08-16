package cn.poki.filepacker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.LinkedList;
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

        /**
         * 一个文件中的某段数据
         */
        class FileBlock{
            /**
             * 对应的文件
             */
            private File file;

            /**
             * 数据起始位置
             */
            private Long start;

            /**
             * 数据长度
             */
            private Long length;
        }

        /**将数据块写到数据文件
         * @param outputFile
         */
        public void saveToFile(File outputFile){
            //todo answer14: 按什么顺序写，写什么，
            throw new NotImplementedException();
        }

        /**从数据文件读取数据块
         */
        public void loadFromFile(File inputFile){
            //todo answer15: 按什么顺序读，读什么，
            throw new NotImplementedException();
        }
    }

    // 下面两个函数就是递归的时候分割文件和文件夹的（意义上）
    private void  analyzeFile(File filepath){
        //todo answer13: 这个函数就是给 packInfos 增加元素的，
        // 考虑一下什么情况下需要增加一个元素，什么情况下只需补充信息即可？
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