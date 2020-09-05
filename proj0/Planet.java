public class Planet {
    /**
     * Gravitational constant in unit: N * m^2 / kg^2.
     */
    private static final double G = 6.67e-11;

    /**
     * Current x position
     */
    public double xxPos;

    /**
     * Current y position
     */
    public double yyPos;

    /**
     * Current velocity in the x direction
     */
    public double xxVel;

    /**
     * Current velocity in the y direction
     */
    public double yyVel;

    /**
     * Mass
     */
    public double mass;

    /**
     * The name of the file that corresponds to the image that depicts the body
     */
    public String imgFileName;

    /**
     * Calculate distance between this body and supplied body
     * @param b supplied body
     */
    public double calcDistance(Planet b) {
        double xDelta = b.xxPos - xxPos;
        double yDelta = b.yyPos - yyPos;
        double distanceSqr = Math.pow(xDelta, 2) + Math.pow(yDelta, 2);
        double distance = Math.sqrt(distanceSqr);
        return distance;
    }

    /**
     * Calculate force exerted by supplied body to this body
     * @param b supplied body
     */
    public double calcForceExertedBy(Planet b) {
        double distance = calcDistance(b);
        double force = mass * b.mass / Math.pow(distance, 2) * G;
        return force;
    }

    /**
     * Calculate x-component of the exerted force by supplied body
     * @param b supplied body
     */
    public double calcForceExertedByX(Planet b) {
        double force = calcForceExertedBy(b);
        double cosine = (b.xxPos - xxPos) / calcDistance(b);
        double xForce = force * cosine;
        return xForce;
    }

    /**
     * Calculate y-component of the exerted force by supplied body
     * @param b supplied body
     */
    public double calcForceExertedByY(Planet b) {
      double force = calcForceExertedBy(b);
      double sine = (b.yyPos - yyPos) / calcDistance(b);
      double yForce = force * sine;
      return yForce;
    }

    /**
     * Calculate net x-component of the exerted forces by supplied group of bodies
     * @param bodies supplied group of bodies
     */
    public double calcNetForceExertedByX(Planet[] bodies) {
        double xForce = 0;
        for (Planet b : bodies) {
            if (this.equals(b)) {
                continue;
            }
            xForce += calcForceExertedByX(b);
        }
        return xForce;
    }

    /**
     * Calculate net y-component of the exerted forces by supplied group of bodies
     * @param bodies supplied group of bodies
     */
    public double calcNetForceExertedByY(Planet[] bodies) {
      double yForce = 0;
      for (Planet b : bodies) {
          if (this.equals(b)) {
              continue;
          }
          yForce += calcForceExertedByY(b);
      }
      return yForce;
    }

    /**
     * Update velocity and position of body by x-component and y-component force in elapsed time in seconds
     * @param delataTime elapsed time in seconds
     * @param xForce x-component force exerted on body
     * @param yForce y-component force exerted on body
     */
    public void update(double deltaTime, double xForce, double yForce) {
        double xAccel = xForce / mass;
        xxVel += xAccel * deltaTime;
        xxPos += xxVel * deltaTime;
        double yAccel = yForce / mass;
        yyVel += yAccel * deltaTime;
        yyPos += yyVel * deltaTime;
    }

    /**
     * Draw a body itself at its current position
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, String.format("./images/%s", imgFileName));
    }

    public Planet(double xP, double yP, double xV,
                   double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
}
