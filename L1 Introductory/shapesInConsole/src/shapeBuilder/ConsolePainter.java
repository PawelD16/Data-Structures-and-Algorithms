package shapeBuilder;

public abstract class ConsolePainter {

    public static void drawLine(int amountOfSpace, int amountOfX) {
        for (int i = 0; i < amountOfSpace; i++) {
            System.out.print(" ");
        }

        for (int i = 0; i < amountOfX; i++) {
            System.out.print("X");
        }

        System.out.println();
    }

    public static void drawPyramid(int n, int h) {
        if (h > 0) {
            if (n >= 0) {
                for (int i = 0; i < h; i++) {
                    ConsolePainter.drawLine(h - i - 1, (2 * n + 1) + 2 * i);
                }
            } else System.out.println("n value of a pyramid must be greater or equal to 0");
        } else System.out.println("h value of a pyramid must be greater than 0");
    }

    public static void drawAFigure(int n) {

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                ConsolePainter.drawPyramid(i, n - i);
            }
        } else System.out.println("n value of a figure must be greater than 0");
    }

    public static void drawJoinedTriangles(int n) {
        if (n > 0) {
            drawLine(0, 2 * n + 1);
            for (int i = 1; i < n; i++) {
                System.out.print(" ");
                for (int j = 1; j < 2*n; j++) {
                    if (j == i || j == n-i || j == n+i || j == 2*n-i) System.out.print("X");
                    else System.out.print(" ");
                }
                System.out.println();
            }
            drawLine(0, 2 * n + 1);
        } else System.out.println("n value of a figure must be greater than 0");
    }

}
