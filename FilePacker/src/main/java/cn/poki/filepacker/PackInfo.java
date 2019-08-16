package cn.poki.filepacker;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.List;

/**
 * 表示一个数据包
 * 一个数据文件里面包括来着不同文件的不同位置的数据对吧
 */
class PackInfo {

    /**
     * 总大小
     */
    private Long totalSize;

    /**
     * 文件块列表
     */
    private List<FileBlock> fileBlocks;

    /**将数据块写到数据文件
     * @param outputFile
     */
    public void saveToFile(File outputFile){
        //todo [task]-3:把伪代码变成代码
        //要写的内容有
        // 1.文件数量
        // 2.对于每个文件
        //  2.1 文件名长度
        //  2.2 文件名
        //  2.3 数据起始位置
        //  2.4 数据长度
        //  2.5 实际数据
        throw new NotImplementedException();
    }

    /**从数据文件读取数据块
     */
    public void loadFromFile(File inputFile){
        //todo [task]-4:把伪代码变成代码，顺序和save一样
        throw new NotImplementedException();
    }

    public void addBlock(FileBlock block){
        //todo [task]-5:加到数组
    }
}
