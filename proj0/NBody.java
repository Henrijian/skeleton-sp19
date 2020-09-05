public class NBody {
    /**
     * body number line number in planet text file
     */
    private static final int BODY_NUM_LINE_IDX = 0;

    /**
     * radius line number in planet text file
     */
    private static final int RADIUS_LINE_IDX = 1;

    /**
     * body information line number in planet text file
     */
    private static final int BODY_INFO_LINE_IDX = 2;

    /**
     * return radius from supplied planet text file path
     * @param planetTxtPath supplied planet text file path
     */
    public static double readRadius(String planetTxtPath) {
        In f = new In(planetTxtPath);
        f.readInt(); // pass through number of bodies
        return Double.valueOf(f.readDouble());
    }

    /**
     * return array of bodies from supplied planet text file path
     * @param planetTxtPath supplied planet text file path
     */
    public static Planet[] readBodies(String planetTxtPath) {
        In f = new In(planetTxtPath);
        int bodyTotal = f.readInt();
        f.readDouble(); // pass through radius
        Planet [] bodies = new Planet[bodyTotal];
        int bodyNum = 0;
        while (bodyNum < bodyTotal) {
            double xPos = f.readDouble();
            double yPos = f.readDouble();
            double xVel = f.readDouble();
            double yVel = f.readDouble();
            double mass = f.readDouble();
            String imgFileName = f.readString();
            Planet body = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
            bodies[bodyNum] = body;
            bodyNum++;
        }
        return bodies;
    }

    /**
     * draw background image
     */
    private static void drawBackground() {
        StdDraw.picture(0, 0, "./images/starfield.jpg");
    }

    /**
     * Simulate universe by gravitational force
     */
    public static void main(String[] args) {
        if (args.length < 3) {
    			  System.out.println("Please supply a needed input as a command line argument.");
    			  System.exit(0);
    		}
        /*
         * Collecting all needed input
         */
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] bodies = readBodies(filename);
        /*
         * Initialize drawing environment
         */
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        /*
         * Drawing the background
         */

        /*
         * Drawing the array of bodies on background
         */
        double st = 0;
        while (st < T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                Planet b = bodies[i];
                xForces[i] = b.calcNetForceExertedByX(bodies);
                yForces[i] = b.calcNetForceExertedByY(bodies);
            }
            drawBackground();
            for (int i = 0; i < bodies.length; i++) {
                Planet b = bodies[i];
                b.update(dt, xForces[i], yForces[i]);
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            st += dt;
        }
        /*
         * print out the final state of all bodies
         */
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet b: bodies) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
        }
    }
}
