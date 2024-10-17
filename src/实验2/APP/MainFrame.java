package 实验2.APP;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainFrame extends JFrame {
    File selectedFile;  // 当前选择的文件
    private JLabel fileName;  // 文件路径

    String currentArchitectureStyleStr = "主程序-子程序软件体系结构";  // 风格名

    JTextArea inputText;  // 显示输入文本
    JTextArea outputText;  // 显示输出文本

    JLabel imageLabel;  // 原理图
    JTextArea codeStruct;  // 代码结构
    JTextArea keyFunc;  // 关键函数

    JLabel architectureStyle1;  // 风格名
    JLabel savePath;  // 保存的位置的提示文本

    public MainFrame(String title) {
        super(title);
        InitFrame();

        InitButton();

        InitText();

        InitMes();

        this.setVisible(true);
    }


    // 初始化原理图等信息，默认显示主程序子程序软件体系结构
    private void InitMes() {
        architectureStyle1 = new JLabel(currentArchitectureStyleStr + "的");
        architectureStyle1.setHorizontalAlignment(JLabel.CENTER);
        architectureStyle1.setFont(new Font("Dialog",Font.BOLD,20));
        architectureStyle1.setBounds(300, 40, 400,40);

        JLabel architectureStyle2 = new JLabel("原理图、源程序代码结构和关键函数");
        architectureStyle2.setHorizontalAlignment(JLabel.CENTER);
        architectureStyle2.setFont(new Font("Dialog",Font.BOLD,20));
        architectureStyle2.setBounds(300, 80, 400,40);



        JLabel imageText = new JLabel("原理图");
        imageText.setBounds(900, 0, 100, 40);

        ImageIcon imageIcon1 = new ImageIcon("src/mes/主程序子程序软件体系结构/原理图.png");
        int width = 450;
        Image image = imageIcon1.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH);

        ImageIcon imageIcon = new ImageIcon(image);


        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(700, 40, imageIcon.getIconWidth(), imageIcon.getIconHeight());


        // 代码结构
        JLabel codeStructText = new JLabel("源程序代码结构");  // 提示文本
        codeStructText.setBounds(450,410,100,40);

        codeStruct = new JTextArea();
        codeStruct.setEditable(false);
        JScrollPane codeScrollPane = new JScrollPane(codeStruct);
        codeScrollPane.setBounds(300, 450, 400,300);
        // 读取文本文件并显示在 JTextArea 中
        this.writeToTextArea("src/mes/主程序子程序软件体系结构/源程序代码结构.txt", codeStruct);


        // 关键函数
        JLabel keyFuncText = new JLabel("关键函数");  // 提示文本
        keyFuncText.setBounds(900,410,100,40);

        keyFunc = new JTextArea();
        keyFunc.setEditable(false);
        JScrollPane keyFuncScrollPane = new JScrollPane(keyFunc);
        keyFuncScrollPane.setBounds(700, 450, 480,300);
        if(keyFunc.getText() != null || keyFunc.getText() != "") keyFunc.setText("");
        // 读取文本文件并显示在 JTextArea 中
        this.writeToTextArea("src/mes/主程序子程序软件体系结构/关键函数.txt", keyFunc);


        // 添加至窗口
        this.getContentPane().setLayout(null);
        this.getContentPane().add(architectureStyle1);
        this.getContentPane().add(imageText);
        this.getContentPane().add(imageLabel);
        this.getContentPane().add(codeStructText);
        this.getContentPane().add(codeScrollPane);
        this.getContentPane().add(keyFuncText);
        this.getContentPane().add(keyFuncScrollPane);
        this.getContentPane().add(architectureStyle2);
    }

    // 当用户切换风格时，对原理图等更新
    private void UpdateMes(String s) {
        String style;
        // 由于包的命名中不能包含"-"，因此进行一次转换
        if("主程序-子程序软件体系结构".equals(s)) style = "主程序子程序软件体系结构";
        else if("管道-过滤软件体系结构".equals(s)) style = "管道过滤软件体系结构";
        else style = s;

        architectureStyle1.setText(style + "的");

        int width = 450;
        Image image = new ImageIcon("src/mes/"+ style +"/原理图.png")
                        .getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);

        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(700, 40, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        this.writeToTextArea("src/mes/"+ style +"/源程序代码结构.txt", codeStruct);

        this.writeToTextArea("src/mes/"+ style +"/关键函数.txt", keyFunc);
    }

    // 将path中的内容写进textArea中
    private void writeToTextArea(String path, JTextArea textArea) {
        try {
            // 使用 Files 类一次性读取整个文件内容
            String content = new String(Files.readAllBytes(Paths.get(path)));
            textArea.setText(content); // 设置到 JTextArea
        } catch (IOException e) {
            e.printStackTrace();
            textArea.append("Error reading file.");
        }
    }

    // 几乎都是提示文本
    private void InitText() {
        fileName = new JLabel();  // 创建文本框用于显示文件路径
        fileName.setBounds(100,0,100,40);

        JLabel currentFile = new JLabel("需要处理的文件：");
        currentFile.setBounds(0,0,100,40);

        // 选择风格的提示文本
        JLabel currentArchitectureStyle = new JLabel("选择处理的风格：");
        currentArchitectureStyle.setBounds(0,40,100,40);

        inputText = new JTextArea();  // 输入文本
        inputText.setEditable(false);
        JScrollPane inputScrollPane = new JScrollPane(inputText);
        inputScrollPane.setBounds(100, 120, 200, 300);

        outputText = new JTextArea();  // 输出文本
        outputText.setEditable(false);
        JScrollPane outPutScrollPane = new JScrollPane(outputText);
        outPutScrollPane.setBounds(100, 420, 200, 300);

        savePath = new JLabel();
        savePath.setBounds(100,710,200,40);


        JLabel input = new JLabel("输入文本");
        input.setHorizontalAlignment(JLabel.CENTER);
        input.setBounds(0,160,100,40);

        JLabel output = new JLabel("输出文本");
        output.setHorizontalAlignment(JLabel.CENTER);
        output.setBounds(0,460,100,40);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(fileName);
        this.getContentPane().add(currentFile);
        this.getContentPane().add(currentArchitectureStyle);
        this.getContentPane().add(inputScrollPane);
        this.getContentPane().add(outPutScrollPane);
        this.getContentPane().add(input);
        this.getContentPane().add(output);
        this.getContentPane().add(savePath);

    }

    private void InitButton() {
        // 选择文件的按钮
        JButton chooseFileButton = new JButton("选择文件");
        chooseFileButton.addActionListener(e -> chooseFile());
        chooseFileButton.setBounds(200,0,100,40);

        JButton run = new JButton("运行");  // 点击执行
        run.addActionListener(e -> {
            try {
                // 如果文件不为空，由于时间原因，这里不对文件的类型进行判断了
                if(selectedFile != null) {
                    outputText.setText("");  // 重置内容
                    Main.inputPath = selectedFile.getAbsolutePath();
                    new Main().run(currentArchitectureStyleStr);
                    savePath.setText("运行结果存放在：" + Main.outputPath + "!");
                    this.writeToTextArea("E:\\Input.txt", inputText);
                    this.writeToTextArea(Main.outputPath, outputText);
                }
                else JOptionPane.showMessageDialog(this,"请选择文件！","提示",JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        run.setBounds(100,80,100,40);

        // 下拉列表
        JComboBox<String> architectureStyle = new JComboBox<>(new String[]{
                "主程序-子程序软件体系结构",
                "面向对象软件体系结构",
                "事件系统软件体系结构",
                "管道-过滤软件体系结构"
        });
        architectureStyle.addActionListener(e -> {
            currentArchitectureStyleStr = (String) architectureStyle.getSelectedItem();  // 读取当前风格
            UpdateMes(currentArchitectureStyleStr);
        });
        architectureStyle.setBounds(100, 40,200,40);


        this.getContentPane().setLayout(null);
        this.getContentPane().add(chooseFileButton);
        this.getContentPane().add(architectureStyle);
        this.getContentPane().add(run);
    }

    private void chooseFile() {
        // 创建文件选择器
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // 设置初始目录为用户家目录
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            fileName.setText(selectedFile.getAbsolutePath());
        }
    }

    // 初始化窗口
    private void InitFrame() {
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
