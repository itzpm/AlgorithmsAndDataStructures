package com.zhu.algorithms.horse;

/**
 * @author zpm
 * @version 1.0
 */
public class HorseChess {

    /**
     * 定义一个步数表示当前的步数,每走过一个点就标记为step+1
     */
    static int step = 1;
    /**
     * 棋盘的长度  x=y
     */
    static int len = 8;

    /**
     * 棋盘
     */
    int[][] chess = new int[len][len];

    /**
     * 定义马可以走的下一步，有8种走法
     */
    Point[] next = {
            new Point(-1, -2),
            new Point(-1, 2),
            new Point(-2, -1),
            new Point(-2, 1),
            new Point(1, -2),
            new Point(1, 2),
            new Point(2, -1),
            new Point(2, 1)};


    public static void main(String[] args) {
        HorseChess h = new HorseChess();
        Point start = new Point(0, 0);
        long time = System.currentTimeMillis();
        h.move(start);
        System.out.println(System.currentTimeMillis()-time);
    }

    public void move(Point p) {
        if (isArrive(p)) {
            return;
        }
        //如果走到的位置已经有了坐标值，则退出重新选择下一步
        if (chess[p.x][p.y] != 0) {
            return;
        }
        //如果没有走过就将该点置位走过,标记为step
        chess[p.x][p.y] = step;
        //下一步
        step++;
        if (step > len * len) {
            print();
        }
        // 然后向8个方向一个一个试着走，能走下去就调用自身的move()方法
        else {
            //初始化下一个点
            Point nextPoint = new Point(0, 0);
            for (Point point : next) {
                nextPoint.x = p.x + point.x;
                nextPoint.y = p.y + point.y;
                //如果可以走通,就继续走
                if (!isArrive(nextPoint)) {
                    move(nextPoint);
                }
            }
        }
        //8个方向都不能走,则退到上一步位置，换一个方向走，此位置的坐标值清0
        if (step < len * len) {
            chess[p.x][p.y] = 0;
            step--;
        }
        // 继续执行move()方法
    }

    public void print() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.printf("%2d  ", chess[i][j]);
            }
            System.out.println();
        }
        System.out.println("======================");
        //路径太多 走一个出来就可以了
//        System.exit(0);
    }

    /**
     * 判断是否可以到达该点
     * true表示不可以到达
     * false表示可以到达
     *
     * @param point p
     * @return true false
     */
    public boolean isArrive(Point point) {
        return point.x < 0 || point.x > len - 1 || point.y < 0 || point.y > len - 1;
    }

    static class Point {
        int x;
        int y;

        public Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}


