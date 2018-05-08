//UI控件
import javax.swing.*;
//画图控件
import java.awt.*;
import java.awt.event.*;
//控制台输出
import java.util.Scanner;

//类
public class MainGame extends JFrame implements ActionListener {

    //下棋相关属性
    private  static int N=19;
    private int a[][]=new int[N][N];
    private int chess;
    private int flag;
    private int computerX;
    private int computerY;
    private int playerX;
    private int playerY;

    //UI界面属性
    private JPanel contentPane;//内容面板
    private JButton button;
    
    public MainGame() {

        //设置这个JFrame的相关信息
        this.setTitle("五子棋游戏 - 玩得开心哈哈");// 设置窗体标题
        this.setSize(800, 600);// 设置窗体大小
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //设置中间面板
        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.LIGHT_GRAY);
        button = new JButton();
        //contentPane.setJButton(button);

        //设置左右上下面板
        this.add(contentPane, BorderLayout.CENTER);
        this.add(new JButton("先手          "), BorderLayout.WEST);
        this.add(new JButton("后手          "), BorderLayout.EAST);
        this.add(new JButton("五子棋"), BorderLayout.NORTH);
        this.add(new JButton("底部"), BorderLayout.SOUTH);
    
    }
    
    
    class MyPanel extends JPanel implements MouseListener {
        int mouseX0;
        int mouseY0;
        Graphics g;
        int mouseX;
        int mouseY;
        JButton button;
        Keyboard kb;

        MyPanel() {
            kb = new Keyboard();

            addMouseListener(this);
            mouseX = 100;
            mouseY = 300;
        }

        public void setJButton(JButton button) {
            this.button = button;
            this.button.setText("对弈开始");
            kb.setJButton(button);
        }

        public int getmouseX() {
            return mouseX;
        }

        public int getmouseY() {
            return mouseY;
        }

        public void paint(Graphics G) {
            super.paint(G);

            int i;
            G.setColor(Color.blue);

            for (i = 0; i < 19; i++) {
                G.drawLine(300, 60 + (30 * i), 840, 60 + (30 * i));
                G.drawLine(300 + (30 * i), 60, 300 + (30 * i), 600);
            }

            int[][] a;
            a = kb.geta();

            int j;

            for (i = 0; i < 19; i++)
                for (j = 0; j < 19; j++) {
                    if (a[i][j] == 1) {
                        G.setColor(Color.WHITE);
                        G.fillOval(((j * 30) + 300) - 8, ((i * 30) + 60) - 8, 16, 16);
                    }

                    if (a[i][j] == 2) {
                        G.setColor(Color.black);
                        G.fillOval(((j * 30) + 300) - 8, ((i * 30) + 60) - 8, 16, 16);
                    }
                }

            g = this.getGraphics();
        }

//        public void mousePressed(MouseEvent e) {
//            //Graphics g=getGraphics();
//            mouseX = e.getX();
//            mouseY = e.getY();
//
//            if ((mouseX < 300) || (((mouseX % 30) < 25) && ((mouseX % 30) > 5)) ||
//                    (mouseY < 60) || (((mouseY % 30) > 5) && ((mouseY % 30) < 25))) {
//                return;
//            }
//
//            if (((mouseX % 30) >= 0) && ((mouseX % 30) <= 5)) {
//                mouseX = mouseX - (mouseX % 30);
//            }
//            else {
//                mouseX = mouseX - (mouseX % 30) + 30;
//            }
//
//            if (((mouseY % 30) >= 0) && ((mouseY % 30) <= 5)) {
//                mouseY = mouseY - (mouseY % 30);
//            }
//            else {
//                mouseY = mouseY - (mouseY % 30) + 30;
//            }
//
//            int playerX;
//            int playerY;
//            playerY = (mouseX - 300) / 30;
//            playerX = (mouseY - 60) / 30;
//
//            if (kb.setPlayXY(playerX, playerY)) {
//                g.setColor(Color.WHITE);
//                g.fillOval(mouseX - 8, mouseY - 8, 16, 16);
//                kb.play();
//                mouseX = (kb.getcomputerY() * 30) + 300;
//                mouseY = (kb.getcomputerX() * 30) + 60;
//                g.setColor(Color.black);
//                g.fillOval(mouseX - 8, mouseY - 8, 16, 16);
//                kb.condition();
//            }
        }
    }

    //main方法 启动执行
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGame frame = new MainGame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btnBack) {
//            do_btnBack_actionPerformed(e);
//        }
//        if (e.getSource() == btnExit) {
//            actionPerformed(e);
//        }
//        if (e.getSource() == btnOk) {
//            do_btnOk_actionPerformed(e);
//        }
    }

//    protected void do_btnOk_actionPerformed(ActionEvent e) {
//        //write code here
//        String s = textField.getText();
//        int n = 0;
//        if (radioButton.isSelected()) {
//            //2
//            n = Integer.parseInt(s, 2);
//        } else if (radioButton_1.isSelected()) {
//            //8
//            n = Integer.parseInt(s, 8);
//        } else if (radioButton_2.isSelected()) {
//            //10
//            n = Integer.parseInt(s);
//        } else if (radioButton_3.isSelected()) {
//            //16
//            n = Integer.parseInt(s, 16);
//        } else {
//
//        }
//        System.out.println(n);
//        String s1 = "";
//        if (radioButton_4.isSelected()) {
//            //2
//            s1 = Integer.toBinaryString(n);
//        } else if (radioButton_5.isSelected()) {
//            //8
//            s1 = Integer.toOctalString(n);
//        } else if (radioButton_6.isSelected()) {
//            //10
//            s1 = Integer.toString(n);
//        } else if (radioButton_7.isSelected()) {
//            //16
//            s1 = Integer.toHexString(n);
//        } else {
//
//        }
//        textField_1.setText(s1);
//    }

//    protected void do_btnBack_actionPerformed(ActionEvent e) {
//        MainMenu mm = new MainMenu();
//        mm.setVisible(true);
//        this.setVisible(false);
//    }


   //打印棋盘
    public void printboard() {
        int i;int j;
        System.out.printf("    ");
        for (i = 0; i < N; i++)
            System.out.printf("%2d", i);
        System.out.println();
        System.out.println("    ———————————————————————————————————————");
        for (i = 0; i < N; i++) {
            System.out.printf("%2d | ", i);
            for (j = 0; j < N; j++)
                switch (a[i][j]) {
                case 0:
                    System.out.print("+ ");
                    break;
                case 1:
                    System.out.print("o ");
                    break;
                case 2:
                    System.out.print("● ");
                    break;
                }
            System.out.println("|");
        }
        System.out.println("    ———————————————————————————————————————");
        System.out.printf("    ");
        for (i = 0; i < N; i++)
            System.out.printf("%2d", i);
        System.out.printf("\n");
    }

    //判断输赢
//    public void judge() {
//        int[] b1 = { 1, 1, 1, 1, 1 };
//        int[] b2 = { 2, 2, 2, 2, 2 };
//        int[] r;
//        r = BF(b1);
//        if (r[0] != 0) {
//            flag = 1;
//        }
//        r = BF(b2);
//        if (r[0] != 0) {
//            flag = 2;
//        }
//    }



}