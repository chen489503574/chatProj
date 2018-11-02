package common;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流的方法
 * Created by Chenjf on 2018/11/2.
 */
public class CloseUtil {
    public static void closeAll(Closeable... io){
        for(Closeable temp:io){
            if(null != temp){
                try {
                    temp.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
