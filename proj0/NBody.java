
/**
 * 157788000.0 25000.0 /data/3body.txt
 *  command line three args, try out fun things in data folder
 */
public class NBody{
    public static int N;
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R =  readRadius(filename);
        Planet[] planets = readPlanets(filename);


        StdDraw.setScale(-R, R);
        StdDraw.clear();

        double time = 0;
        while(time<T){
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for(int i = 0;i<N;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0;i<N;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.enableDoubleBuffering();
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }



    }
    public static double readRadius(String txtFile){
        In in = new In(txtFile);
        N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String txtFile){
        In in = new In(txtFile);

        N = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[N];
        for(int i = 0;i<N;i++){
            planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return planets;
    }
}