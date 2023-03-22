package shapeBuilder;

public class Tests {

    public Tests(){
        System.out.println("\n\nFor pyramid: n = -1, h = -1. Dimensions can't be negative, this pyramid doesnt make sense");
        ConsolePainter.drawPyramid(-1,-1);

        System.out.println("\n\nFor pyramid: n = -1, h = 2. Dimensions can't be negative, this pyramid doesnt make sense");
        ConsolePainter.drawPyramid(-1,2);

        System.out.println("\n\nFor pyramid: n = 2, h = -1. Dimensions can't be negative, this pyramid doesnt make sense");
        ConsolePainter.drawPyramid(2,-1);

        System.out.println("\n\nFor pyramid: n = 0, h = -1. Dimensions can't be negative, this pyramid doesnt make sense");
        ConsolePainter.drawPyramid(0,-1);

        System.out.println("\n\nFor pyramid: n = -1, h = 0. Dimensions can't be negative, this pyramid doesnt make sense");
        ConsolePainter.drawPyramid(-1,0);

        System.out.println("\n\nFor pyramid: n = 0, h = 0. Such a pyramid doesnt have any dimensions, thus it cannot be drawn ");
        ConsolePainter.drawPyramid(0,0);

        System.out.println("\n\nFor pyramid: n = 5, h = 0. The pyramid has no height, it cannot be drawn");
        ConsolePainter.drawPyramid(5,0);

        System.out.println("\n\nFor pyramid: n = 0, h = 5. We should see a 5 tier pyramid with a 1 width top");
        ConsolePainter.drawPyramid(0,5);

        System.out.println("\n\nFor pyramid: n = 5, h = 5. We should see a 5 tier pyramid with a 11 width top");
        ConsolePainter.drawPyramid(5,5);

        System.out.println("\n\nFor a figure: n = -1. There can't be negative amount of tiers");
        ConsolePainter.drawAFigure(-1);

        System.out.println("\n\nFor a figure: n = 0. This figure doesnt have any usable dimension, thus it cannot be drawn");
        ConsolePainter.drawAFigure(0);

        System.out.println("\n\nFor a figure: n = 5. We should see a figure with 5 tiers, then 4...1 which are all a part of a similar pyramid but with a tier cut off in each iteration");
        ConsolePainter.drawAFigure(5);
    }
}
