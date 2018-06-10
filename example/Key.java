///package five_chess;

import java.awt.*;
import java.awt.event.*;

import java.util.Scanner;

import javax.swing.*;
44455555

class Keyboard {
    private static int N = 19;
    private int[][] a = new int[N][N];
    private int chess;
    private int flag;
    private int computerX;
    private int computerY;
    private int playerX;
    private int playerY;
    private JButton button;

    public Keyboard() {
        chess = 1;
        flag = 0;

        int i;
        int j;

        for (i = 0; i < N; i++)
            for (j = 0; j < N; j++)
                a[i][j] = 0;
    }

    public int[][] geta() {
        return a;
    }

    public void setJButton(JButton button) {
        this.button = button;
    }

    public void play() {
        if (condition()) {
            return;
        }

        if (man_play()) {
            button.setText("电脑思考中......");
            computer_play();
        }

        printboard();
    }

    public boolean condition() {
        judge();

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

    public int getcomputerX() {
        return computerX;
    }

    public int getcomputerY() {
        return computerY;
    }

    public boolean man_play() {
        int i = playerX;
        int j = playerY;
        a[i][j] = 1;
        printboard();

        return true;
    }

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

        //************************************	
        int x = second();

        if (x == 1) {
            return;
        }

        //************************************	
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

    public void printboard() {
        int i;
        int j;

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

    public void judge() {
        int[] b1 = { 1, 1, 1, 1, 1 };
        int[] b2 = { 2, 2, 2, 2, 2 };
        int[] r;
        r = BF(b1);

        if (r[0] != 0) {
            flag = 1;
        }

        r = BF(b2);

        if (r[0] != 0) {
            flag = 2;
        }
    }

    public int getflag() {
        return flag;
    }

    public int[] BF(int[] b) {
        int[] r;
        r = new int[3];

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
                }
                else {
                    index++;
                    j = index;
                    k = 0;
                }
            }

            if (k == b.length) {
                r[0] = 1;
                r[1] = i;
                r[2] = index;

                return r;
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
                }
                else {
                    index++;
                    i = index;
                    k = 0;
                }
            }

            if (k == b.length) {
                r[0] = 2;
                r[1] = index;
                r[2] = j;

                return r;
            }
        }

        int dis;

        for (dis = 0; dis < (N - 5); dis++) {
            index = dis;
            i = dis;
            j = i = dis;
            k = 0;

            while ((i < N) && (k < b.length)) {
                if (a[i][j] == b[k]) {
                    i++;
                    j++;
                    k++;
                }
                else {
                    index++;
                    i = index;
                    j = i - dis;

                    k = 0;
                }
            }

            if (k == b.length) {
                r[0] = 3;
                r[1] = index;
                r[2] = index - dis;

                return r;
            }
        }

        index = 0;

        for (dis = 0; dis < (N - 5); dis++) {
            index = dis;
            j = dis;
            i = j - dis;
            k = 0;

            while ((j < N) && (k < b.length)) {
                if (a[i][j] == b[k]) {
                    j++;
                    i++;
                    k++;
                }
                else {
                    index++;
                    j = index;
                    i = j - dis;

                    k = 0;
                }
            }

            if (k == b.length) {
                r[0] = 3;
                r[1] = index - dis;
                r[2] = index;

                return r;
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
                }
                else {
                    index--;
                    i = index;
                    j = dis - i;

                    k = 0;
                }
            }

            if (k == b.length) {
                r[0] = 4;
                r[1] = index;
                r[2] = dis - index;

                return r;
            }
        }

        for (dis = N - 1; dis < (((N - 5) + N) - 1); dis++) {
            index = dis - (N - 1);

            j = index;
            i = dis - j;
            k = 0;

            while ((j < N) && (k < b.length)) {
                if (a[i][j] == b[k]) {
                    i--;
                    j++;
                    k++;
                }
                else {
                    index++;
                    j = index;
                    i = dis - j;

                    k = 0;
                }
            }

            if (k == b.length) {
                r[0] = 4;
                r[1] = dis - index;
                r[2] = index;

                return r;
            }
        }

        return r;
    }

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
                }
                else {
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
                }
                else {
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
                }
                else {
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
                }
                else {
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
                }
                else {
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
                }
                else {
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
}


class WindowChess extends JFrame {
    MyPanel mypanel;
    JButton button;

    WindowChess() {
        init();
        setVisible(true);

        setBounds(50, 50, 500, 400);
    }

    public void init() {
        mypanel = new MyPanel();
        mypanel.setLayout(new FlowLayout());

        mypanel.setBackground(Color.LIGHT_GRAY);
        button = new JButton();
        mypanel.setJButton(button);

        add(mypanel, BorderLayout.CENTER);
        add(new JButton("西方          "), BorderLayout.WEST);
        add(new JButton("东方          "), BorderLayout.EAST);

        JLabel label1 = new JLabel("\t\t\t\t\t\t\t\t五子棋");

        label1.setSize(50, 60);

        add(new JButton("五子棋"), BorderLayout.NORTH);

        add(button, BorderLayout.SOUTH);
    }
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

    public void mousePressed(MouseEvent e) {
        //Graphics g=getGraphics();
        mouseX = e.getX();
        mouseY = e.getY();

        if ((mouseX < 300) || (((mouseX % 30) < 25) && ((mouseX % 30) > 5)) ||
                (mouseY < 60) || (((mouseY % 30) > 5) && ((mouseY % 30) < 25))) {
            return;
        }

        if (((mouseX % 30) >= 0) && ((mouseX % 30) <= 5)) {
            mouseX = mouseX - (mouseX % 30);
        }
        else {
            mouseX = mouseX - (mouseX % 30) + 30;
        }

        if (((mouseY % 30) >= 0) && ((mouseY % 30) <= 5)) {
            mouseY = mouseY - (mouseY % 30);
        }
        else {
            mouseY = mouseY - (mouseY % 30) + 30;
        }

        int playerX;
        int playerY;
        playerY = (mouseX - 300) / 30;
        playerX = (mouseY - 60) / 30;

        if (kb.setPlayXY(playerX, playerY)) {
            g.setColor(Color.WHITE);
            g.fillOval(mouseX - 8, mouseY - 8, 16, 16);

            kb.play();

            mouseX = (kb.getcomputerY() * 30) + 300;
            mouseY = (kb.getcomputerX() * 30) + 60;

            g.setColor(Color.black);
            g.fillOval(mouseX - 8, mouseY - 8, 16, 16);

            kb.condition();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
}


public class Key {
    public static void main(String[] args) {
        WindowChess win = new WindowChess();
    }
}
