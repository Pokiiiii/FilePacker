package cn.poki.filepacker;

import java.io.File;

/**
 * 一个文件中的某段数据
 */
class FileBlock {
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
