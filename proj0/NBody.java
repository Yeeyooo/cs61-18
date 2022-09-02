import java.lang.reflect.ParameterizedType;

public class NBody {
    public static double readRadius(String filename){
        In in=new In(filename);
        int totalnumber=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename){
        In in=new In(filename);
        int num=in.readInt();
        double radius=in.readDouble();
        Planet[] arr=new Planet[num];
        for(int i=0;i<num;i++){
            double xPos=in.readDouble();
            double yPos=in.readDouble();
            double xVel=in.readDouble();
            double yVel=in.readDouble();
            double m=in.readDouble();
            String imgName=in.readString();
            arr[i]=new Planet(xPos,yPos,xVel,yVel,m,imgName);
        }
        return arr;
    }
    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=readRadius(filename);    //the radius of the universe
        Planet[] planets=readPlanets(filename);   //planets in the universe

        //set the universe scale
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.enableDoubleBuffering();
        double t=0;
        int num=planets.length;

        while(t<T){
            double[] xForces=new double[num];
            double[] yForces=new double[num];
            for(int i=0;i<num;i++){   //calculate x and y net forces of each planet
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<num;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i=0;i<num;i++){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n",planets.length);
        StdOut.printf("%.2e\n",radius);
        for(int i=0;i<planets.length;i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",planets[i].xxPos,planets[i].yyPos,planets[i].xxVel,
                    planets[i].yyVel,planets[i].mass,planets[i].imgFileName);
        }
    }
}
