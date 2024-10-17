package 实验2.APP;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String inputPath;
    public static String outputPath = "E:\\output.txt";  // 默认存放的位置

    public static void main(String[] args) {
        new MainFrame("经典软件体系结构教学软件");


    }

    public void run(String style) throws IOException {
        switch (style) {
            case "主程序-子程序软件体系结构":
                实验2.主程序子程序软件体系结构.demo1.main(null);
                break;
            case "面向对象软件体系结构":
                实验2.面向对象软件体系结构.Main.main(null);
                break;

            case "事件系统软件体系结构":
                实验2.事件系统软件体系结构.Main.main(null);
                break;

            case "管道-过滤软件体系结构":
                实验2.管道过滤软件体系结构.Main.main(null);
        }
    }
}
