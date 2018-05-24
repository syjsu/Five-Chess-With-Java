import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

//主类 Game
public class Game {
    
    //主函数
    public static void main(String[] args) {
        WindowChess win = new WindowChess();
    }

    //UI界面类
    public static class WindowChess extends JFrame {
        MyPanel mypanel;
        JButton button;

        //构造函数
        WindowChess() {
            this.init();//调用下面的init函数
            this.setVisible(true);//可见
            this.setTitle("五子棋游戏 - 玩得开心哈哈");// 设置窗体标题
            this.setSize(1250, 725);// 设置窗体大小
            this.setLocation(100, 100);//设置位置
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置按钮
        }

        //初始化UI界面
        public void init() {
            mypanel = new MyPanel();
            mypanel.setLayout(new FlowLayout());

            mypanel.setBackground(Color.LIGHT_GRAY);
            button = new JButton();
            mypanel.setJButton(button);//设置对弈开始按钮

            this.add(mypanel, BorderLayout.CENTER);
            this.add(new JButton("黑棋   "), BorderLayout.WEST);
            this.add(new JButton("白棋   "), BorderLayout.EAST);
            this.add(new JButton("对弈比赛"), BorderLayout.NORTH);
            this.add(button, BorderLayout.SOUTH);
        }
    }

    //中间棋盘划线的部分
    // MyPannel类继承自JPannel类
    // MyPannel类实现MouseListener接口
    
    //文档在这里 http://www.java2s.com/Code/JavaAPI/javax.swing/JPanel.htm
    
    public static class MyPanel extends JPanel implements MouseListener {
        int mouseX0;
        int mouseY0;
        Graphics g;
        int mouseX;
        int mouseY;
        JButton button;
        Keyboard kb;

        //构造函数
        MyPanel() {
            kb = new Keyboard();
            this.addMouseListener(this);
            mouseX = 100;
            mouseY = 300;
        }

        //设置按钮
        public void setJButton(JButton button) {
            this.button = button;
            this.button.setText("开始游戏");
            kb.setJButton(button);
        }

        //返回鼠标的x坐标
        public int getmouseX() {
            return mouseX;
        }
        
        //返回鼠标的y坐标
        public int getmouseY() {
            return mouseY;
        }

        //绘制棋盘面板函数
        public void paint(Graphics G) {
            
            //执行一次父类的paint
            super.paint(G);

            //划棋盘线
            G.setColor(Color.blue);
            for (int i = 0; i < 19; i++) {
                G.drawLine(300, 60 + (30 * i), 840, 60 + (30 * i));
                G.drawLine(300 + (30 * i), 60, 300 + (30 * i), 600);
            }

            //画棋子
            int[][] a;
            a = kb.geta();
            for (int i = 0; i < 19; i++){
                for (int j = 0; j < 19; j++) {
                    if (a[i][j] == 1) {
                        G.setColor(Color.WHITE);
                        G.fillOval(((j * 30) + 300) - 8, ((i * 30) + 60) - 8,
                            16, 16);
                    }
                    if (a[i][j] == 2) {
                        G.setColor(Color.black);
                        G.fillOval(((j * 30) + 300) - 8, ((i * 30) + 60) - 8,
                            16, 16);
                    }
                }
            }
            
            //记录下当前Graphics 返回给后续使用
            g = this.getGraphics();
        }
        
        //覆盖抽象方法 mousePressed
        public void mousePressed(MouseEvent e) {
            Graphics g = getGraphics();
            mouseX = e.getX();
            mouseY = e.getY();

            //没点中的情况
            if ((mouseX < 300) ||
                    (((mouseX % 30) < 25) && ((mouseX % 30) > 5)) ||
                    (mouseY < 60) ||
                    (((mouseY % 30) > 5) && ((mouseY % 30) < 25))) {
                return;
            }

            //记录点中的x坐标
            if (((mouseX % 30) >= 0) && ((mouseX % 30) <= 5)) {
                mouseX = mouseX - (mouseX % 30);
            } else {
                mouseX = mouseX - (mouseX % 30) + 30;
            }

            //记录点中的y坐标
            if (((mouseY % 30) >= 0) && ((mouseY % 30) <= 5)) {
                mouseY = mouseY - (mouseY % 30);
            } else {
                mouseY = mouseY - (mouseY % 30) + 30;
            }

            int playerX;
            int playerY;
            playerY = (mouseX - 300) / 30;
            playerX = (mouseY - 60) / 30;

            if (kb.setPlayXY(playerX, playerY)) {
                //绘制玩家下棋的位置
                g.setColor(Color.WHITE);
                g.fillOval(mouseX - 8, mouseY - 8, 16, 16);

                //计算电脑AI的XY位置
                kb.play();
                mouseX = (kb.getcomputerY() * 30) + 300;
                mouseY = (kb.getcomputerX() * 30) + 60;
                
                //绘制电脑下棋的位置
                g.setColor(Color.black);
                g.fillOval(mouseX - 8, mouseY - 8, 16, 16);

                //判断输赢
                kb.condition();
            }
        }
        //覆盖抽象方法 mouseReleased
        public void mouseReleased(MouseEvent e) {
        }
        //覆盖抽象方法 mouseEntered
        public void mouseEntered(MouseEvent e) {
        }
        //覆盖抽象方法 mouseExited
        public void mouseExited(MouseEvent e) {
        }
        //覆盖抽象方法 mouseClicked
        public void mouseClicked(MouseEvent e) {
        }
    }

    //棋盘类
    public static class Keyboard {
        private static int N = 19;//棋盘大小
        private int[][] a = new int[N][N];//棋盘数组 0为空 1为玩家 2为电脑
        private int chess;//判断当前轮到谁下棋 1为玩家 2为电脑
        private int flag;//判断是否赢了 0为空 1为玩家 2为电脑 
        private int computerX;//电脑当前下棋的x
        private int computerY;//电脑当前下棋的y
        private int playerX;//玩家当前下棋的x
        private int playerY;//玩家当前下棋的y
        private JButton button;//底下按钮

        //初始化棋盘 全部0填充
        public Keyboard() {
            chess = 1;
            flag = 0;
            int i;
            int j;
            for (i = 0; i < N; i++)
                for (j = 0; j < N; j++)
                    a[i][j] = 0;
        }

        //返回棋盘数组a
        public int[][] geta() {
            return a;
        }

        //设置底下按钮的
        public void setJButton(JButton button) {
            this.button = button;
        }

        //电脑下棋
        public void play() {
            //判断输赢
            if (condition()) {
                return;
            }
            //计算电脑下棋
            if (man_play()) {
                button.setText("电脑思考中......");
                computer_play();
            }
            //绘制棋盘
            printboard();
        }

        //判断
        public boolean condition() {
            //进行判断
            judge();
            //显示下方按钮的结果
            if (flag == 1) {
                System.out.println("玩家胜出！");
                button.setText("玩家胜出！");
                return true;
            } else if (flag == 2) {
                System.out.println("电脑胜出！");
                button.setText("电脑胜出！");
                return true;
            } else {
                System.out.println("游戏对弈中...");
                button.setText("游戏对弈中...");
                return false;
            }
        }
        
        //设置下棋位置
        public boolean setPlayXY(int playerX, int playerY) {
            if (flag != 0) {
                button.setText("对弈已结束！");
                return false;
            }
            if (a[playerX][playerY] != 0) {
                System.out.println("该处已有棋子，请选择他处落子");
                button.setText("该处已有棋子，请选择他处落子");
                printboard();
                return false;
            }
            this.playerX = playerX;
            this.playerY = playerY;
            return true;
        }

        //返回电脑下棋的x坐标
        public int getcomputerX() {
            return computerX;
        }

        //返回电脑下棋的y坐标
        public int getcomputerY() {
            return computerY;
        }

        //用户下棋
        public boolean man_play() {
            int i = playerX;
            int j = playerY;
            a[i][j] = 1;
            printboard();
            return true;
        }
        
        //打印棋盘到console
        public void printboard() {
            int i;
            int j;
            System.out.printf(" ");
            for (i = 0; i < N; i++){
                System.out.printf("%2d", i);
            }
            System.out.println();
            System.out.println(" —————————————");
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
            System.out.println(" —————————————");
            System.out.printf(" ");
            for (i = 0; i < N; i++){
                System.out.printf("%2d", i);
            }
            System.out.printf("\n");
        }

        //判断输赢的函数
        public void judge() {
            int[] b1 = { 1, 1, 1, 1, 1 };
            int[] b2 = { 2, 2, 2, 2, 2 };
            int[] r;
            //判断是否玩家赢了
            r = BF(b1);
            if (r[0] != 0) {
                flag = 1;
            }
            //判断电脑是否赢了
            r = BF(b2);
            if (r[0] != 0) {
                flag = 2;
            }
        }
        
        //返回标记输赢的标志位
        public int getflag() {
            return flag;
        }

        //玩家下棋后判断的函数
        public int[] BF(int[] b) {
            
            //定义返回值
            int[] r;
            r = new int[3];

            //初始化遍历需要的变量
            int i = 0;//当前判断位置的横坐标
            int j = 0;//当前判断位置的纵坐标
            int k = 0;//当前b数组的索引
            int index = 0;//当前标记判断位置的索引

            //(1) 遍历第一种情况 横向是否满足
            for (i = 0; i < N; i++) {
                index = 0;
                k = 0;
                j = 0;
                while ((j < N) && (k < b.length)) {
                    //如果找到相同的
                    if (a[i][j] == b[k]) {
                        j++;//横加一
                        k++;//匹配结果数加一
                    } else {
                        index++;//下一个横向位置开始比较
                        j = index;//第一位设置成index
                        k = 0;//匹配结果数归零
                    }
                }
                //吻合个数等于需要判断的数组
                if (k == b.length) {
                    r[0] = 1;//是否吻合的返回的情况
                    r[1] = i;//最后匹配的横
                    r[2] = index;//最后匹配的纵
                    //返回结果
                    return r;
                }
            }

            //(2) 遍历第二种情况 纵向是否满足 同上情况
            for (j = 0; j < N; j++) {
                index = 0;
                k = 0;
                i = 0;
                //开始判断
                while ((i < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i++;
                        k++;
                    } else {
                        index++;
                        i = index;
                        k = 0;
                    }
                }
                //返回结果
                if (k == b.length) {
                    r[0] = 2;
                    r[1] = index;
                    r[2] = j;
                    return r;
                }
            }

            //(3) 遍历第三种情况 左上斜向右下
            int dis;
            for (dis = 0; dis < (N - 5); dis++) {
                index = dis;
                i = dis;
                j = i = dis;
                k = 0;
                //开始判断
                while ((i < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i++;
                        j++;
                        k++;
                    } else {
                        index++;
                        i = index;
                        j = i - dis;
                        k = 0;
                    }
                }
                //返回结果
                if (k == b.length) {
                    r[0] = 3;
                    r[1] = index;
                    r[2] = index - dis;
                    return r;
                }
            }

            //(3) 遍历第三种情况 左上斜向右下
            index = 0;
            for (dis = 0; dis < (N - 5); dis++) {
                index = dis;
                j = dis;
                i = j - dis;
                k = 0;
                //开始判断
                while ((j < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        j++;
                        i++;
                        k++;
                    } else {
                        index++;
                        j = index;
                        i = j - dis;
                        k = 0;
                    }
                }
                //返回结果
                if (k == b.length) {
                    r[0] = 3;
                    r[1] = index - dis;
                    r[2] = index;
                    return r;
                }
            }

            //(4) 遍历第四种情况 右下斜向左上
            k = 0;
            for (dis = N - 1; dis >= 4; dis--) {
                index = dis;
                i = dis;
                j = dis - i;
                k = 0;
                //开始判断
                while ((i >= 0) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i--;
                        j++;
                        k++;
                    } else {
                        index--;
                        i = index;
                        j = dis - i;
                        k = 0;
                    }
                }
                //返回结果
                if (k == b.length) {
                    r[0] = 4;
                    r[1] = index;
                    r[2] = dis - index;
                    return r;
                }
            }

            //(4) 遍历第四种情况 右下斜向左上
            for (dis = N - 1; dis < (((N - 5) + N) - 1); dis++) {
                index = dis - (N - 1);
                j = index;
                i = dis - j;
                k = 0;
                //开始判断
                while ((j < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i--;
                        j++;
                        k++;
                    } else {
                        index++;
                        j = index;
                        i = dis - j;
                        k = 0;
                    }
                }
                //返回结果
                if (k == b.length) {
                    r[0] = 4;
                    r[1] = dis - index;
                    r[2] = index;
                    return r;
                }
            }
            return r;
        }


        


        // ===================//
        // 电脑下棋 AI 部分     //
        // ===================//
        // AI部分采用极大值极小值算法 https://blog.csdn.net/lihongxun945/article/details/50625267
        public void computer_play() {
            int k = 0;
            int[][] player = {
                    { 1, 1, 1, 1, 1 },
                    { 0, 1, 1, 1, 1, 0 },
                    { 2, 1, 1, 1, 1, 0 },
                    { 0, 1, 1, 1, 1, 2 },
                    { 1, 1, 0, 1, 1 },
                    { 1, 1, 1, 0, 1 },
                    { 1, 0, 1, 1, 1 },
                    { 0, 1, 1, 1, 0 },
                    { 0, 1, 1, 0, 1, 0 },
                    { 0, 1, 0, 1, 1 }
                };
            int i;
            int[] r1 = new int[3];
            for (i = 0; i < 10; i++) {
                r1 = BF(player[i]);
                if (r1[0] != 0) {
                    break;
                }
            }
            if (i == 0) {
                flag = 1;
                return;
            }
            int[][] computer = {
                    { 2, 2, 2, 2, 2 },
                    { 0, 2, 2, 2, 2, 0 },
                    { 1, 2, 2, 2, 2, 0 },
                    { 0, 2, 2, 2, 2, 1 },
                    { 1, 0, 2, 2, 2, 0 },
                    { 0, 2, 2, 2, 0, 1 },
                    { 0, 2, 2, 2, 0 },
                    { 0, 2, 2, 0, 2 },
                    { 0, 2, 0, 2, 2 },
                    { 2, 0, 2, 2, 0 },
                    { 2, 0, 2, 2, 0 },
                    { 1, 2, 2, 2, 0, 0 },
                    { 0, 0, 2, 2, 2, 1 },
                    { 0, 2, 0, 2, 0 },
                    { 0, 2, 2, 0, 0 },
                    { 0, 0, 2, 2, 0 },
                    { 0, 0, 2, 0, 0 },
                    { 0, 1, 0 }
                };
            int[] r2 = new int[3];
            int j = 0;
            while (true) {
                r2 = BF(computer[j]);
                if (r2[0] != 0) {
                    break;
                } else {
                    j++;
                }
            }
            if (j < 4) {
                switch (j) {
                case 0:
                    flag = 2;
                    break;
                case 1:
                    k = 0;
                    flag = 2;
                    break;
                case 2:
                    k = 5;
                    flag = 2;
                    break;
                case 3:
                    k = 0;
                    flag = 2;
                    break;
                }
                Play(r2, k);
                return;
            }
            if (i == 1) {
                flag = 1;
                k = 0;
                a[r1[1]][r2[2]] = 1;
                return;
            }
            if ((i >= 2) && (i <= 6)) {
                switch (i) {
                case 2:
                    k = 5;
                    break;
                case 3:
                    k = 0;
                    break;
                case 4:
                    k = 2;
                    break;
                case 5:
                    k = 3;
                    break;
                case 6:
                    k = 1;
                    break;
                }
                Play(r1, k);
                return;
            }
            if ((j >= 4) && (j < 13)) {
                switch (j) {
                case 4:
                    k = 5;
                    break;
                case 5:
                    k = 0;
                    break;
                case 6:
                    k = 0;
                    break;
                case 7:
                    k = 3;
                    break;
                case 8:
                    k = 2;
                    break;
                case 9:
                    k = 1;
                    break;
                case 10:
                    k = 2;
                    break;
                case 11:
                    k = 4;
                    break;
                case 12:
                    k = 1;
                    break;
                }
                Play(r2, k);
                return;
            }

            switch (i) {
            case 7:
                k = 0;
                Play(r1, k);
                return;
            case 8:
                k = 3;
                Play(r1, k);
                return;
            case 9:
                k = 2;
                Play(r1, k);
                return;
            default:
                break;
            } 
            int x = second();
            if (x == 1) {
                return;
            }
            switch (j) {
            case 13:
                k = 2;
                break;
            case 14:
                k = 3;
                break;
            case 15:
                k = 1;
                break;
            case 16:
                k = 1;
                break;
            case 17:
                k = 0;
                break;
            }
            Play(r2, k);
        }
        // AI
        public int bf(int[] b, int[][] r) {
            int n = 0;
            int i = 0;
            int j = 0;
            int k = 0;
            int index = 0;
            for (i = 0; i < N; i++) {
                index = 0;
                k = 0;
                j = 0;
                while ((j < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        j++;
                        k++;
                    } else {
                        index++;
                        j = index;
                        k = 0;
                    }
                }
                if (k == b.length) {
                    r[n][0] = 1;
                    r[n][1] = i;
                    r[n][2] = index;
                    n++;
                }
            }
            for (j = 0; j < N; j++) {
                index = 0;
                k = 0;
                i = 0;
                while ((i < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i++;
                        k++;
                    } else {
                        index++;
                        i = index;
                        k = 0;
                    }
                }
                if (k == b.length) {
                    r[n][0] = 2;
                    r[n][1] = index;
                    r[n][2] = j;
                    n++;
                }
            }
            int dis;
            for (dis = 0; dis < (N - 5); dis++) {
                index = dis;
                i = dis;
                j = i - dis;
                k = 0;
                while ((i < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i++;
                        j++;
                        k++;
                    } else {
                        index++;
                        i = index;
                        j = i - dis;
                        k = 0;
                    }
                }
                if (k == b.length) {
                    r[n][0] = 3;
                    r[n][1] = index;
                    r[n][2] = index - dis;
                    n++;
                }
            }
            index = 0;
            for (dis = 1; dis < (N - 5); dis++) {
                index = dis;
                j = dis;
                i = j - dis;
                k = 0;
                while ((j < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        j++;
                        i++;
                        k++;
                    } else {
                        index++;
                        j = index;
                        i = j - dis;
                        k = 0;
                    }
                }
                if (k == b.length) {
                    r[n][0] = 3;
                    r[n][1] = index - dis;
                    r[n][2] = index;
                    n++;
                }
            }
            k = 0;
            for (dis = N - 1; dis >= 4; dis--) {
                index = dis;
                i = dis;
                j = dis - i;
                k = 0;
                while ((i >= 0) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i--;
                        j++;
                        k++;
                    } else {
                        index--;
                        i = index;
                        j = dis - i;
                        k = 0;
                    }
                }
                if (k == b.length) {
                    r[n][0] = 4;
                    r[n][1] = index;
                    r[n][2] = dis - index;
                    n++;
                }
            }
            for (dis = N; dis < (((N - 5) + N) - 1); dis++) {
                index = dis - (N - 1);
                j = index;
                i = dis - j;
                k = 0;
                while ((j < N) && (k < b.length)) {
                    if (a[i][j] == b[k]) {
                        i--;
                        j++;
                        k++;
                    } else {
                        index++;
                        j = index;
                        i = dis - j;

                        k = 0;
                    }
                }
                if (k == b.length) {
                    r[n][0] = 4;
                    r[n][1] = dis - index;
                    r[n][2] = index;
                    n++;
                }
            }
            return n;
        }
        // AI
        public int second() {
            int[][] player = {
                    { 2, 1, 1, 1, 1, 0 },
                    { 0, 1, 1, 1, 1, 2 },
                    { 1, 1, 0, 1, 1 },
                    { 2, 1, 1, 1, 0, 1 },
                    { 1, 0, 1, 1, 1, 2 },
                    { 0, 1, 1, 1, 0 },
                    { 0, 1, 1, 0, 1, 0 },
                    { 0, 1, 0, 1, 1, 0 }
                };
            int i;
            int j;
            int m;
            int[][] b = new int[N][N];
            for (i = 0; i < N; i++)
                for (j = 0; j < N; j++)
                    b[i][j] = a[i][j];
            int[][][] r = new int[8][20][3];
            int[] n = new int[9];
            int nsum = 0;
            for (i = 0; i < N; i++)
                for (j = 0; j < N; j++) {
                    if (a[i][j] == 0) {
                        a[i][j] = 1;
                        for (m = 0; m < 8; m++)
                            n[m] = bf(player[m], r[m]);
                        for (m = 0; m < 8; m++)
                            nsum += n[m];
                        if (nsum >= 2) {
                            a[i][j] = 2;
                            computerX = i;
                            computerY = j;
                            return 1;
                        }
                        a[i][j] = 0;
                    }
                    nsum = 0;
                }
            return 0;
        }
        // AI
        public void Play(int[] r, int i) {
            if (i == 0) {
                a[r[1]][r[2]] = 2;
                computerX = r[1];
                computerY = r[2];
            } else {
                switch (r[0]) {
                case 1:
                    a[r[1]][r[2] + i] = 2;
                    computerX = r[1];
                    computerY = r[2] + i;
                    break;
                case 2:
                    a[r[1] + i][r[2]] = 2;
                    computerX = r[1] + i;
                    computerY = r[2];
                    break;
                case 3:
                    a[r[1] + i][r[2] + i] = 2;
                    computerX = r[1] + i;
                    computerY = r[2] + i;
                    break;
                case 4:
                    a[r[1] - i][r[2] + i] = 2;
                    computerX = r[1] - i;
                    computerY = r[2] + i;
                    break;
                }
            }
        }
        // ===================//
        // 电脑下棋 AI 部分     //
        // ===================//
        // AI算法部分结束


    }
}
