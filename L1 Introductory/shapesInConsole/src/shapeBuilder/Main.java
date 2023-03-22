package shapeBuilder;

public class Main {

    public static void main(String[] args) {

        int nPyramid, height, nFigure;
        //nPyramid = ConsoleReader.readIntFromConsole("Type n of pyramid: ", true);
        //height = ConsoleReader.readIntFromConsole("Type h of pyramid: ", false);
       // ConsolePainter.drawPyramid(nPyramid,height);

        //nFigure = ConsoleReader.readIntFromConsole("Type n of figure: ", false);
        //ConsolePainter.drawAFigure(nFigure);
        ConsolePainter.drawJoinedTriangles(1);
        System.out.println();
        ConsolePainter.drawJoinedTriangles(3);
        System.out.println();
        ConsolePainter.drawJoinedTriangles(4);
        System.out.println();
        ConsolePainter.drawJoinedTriangles(5);
        System.out.println();
        ConsolePainter.drawJoinedTriangles(7);


        //Tests tests = new Tests();

    }
}
