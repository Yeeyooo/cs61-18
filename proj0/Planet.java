import java.text.DecimalFormat;
import java.text.NumberFormat;
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G=6.67e-11;
    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet another){
        double xDistance,yDistance;
        xDistance=this.xxPos-another.xxPos;
        yDistance=this.yyPos-another.yyPos;
        return Math.sqrt(xDistance*xDistance+yDistance*yDistance);
    }
    public double calcForceExertedBy(Planet another){
        double r=calcDistance(another);
        return G*mass*another.mass/(r*r);
    }
    public double calcForceExertedByX(Planet another){
        return calcForceExertedBy(another)*(another.xxPos-xxPos)/calcDistance(another);
    }
    public double calcForceExertedByY(Planet another){
        return calcForceExertedBy(another)*(another.yyPos-yyPos)/calcDistance(another);
    }
    public double calcNetForceExertedByX(Planet[] arr){
        double NetForceX=0;
        for(Planet p:arr){
            if(this.equals(p)){
                continue;
            }
            NetForceX+=calcForceExertedByX(p);
        }
        return NetForceX;
    }
    public double calcNetForceExertedByY(Planet[] arr){
        double NetForceY=0;
        for(Planet p:arr){
            if(this.equals(p)){
                continue;
            }
            NetForceY+=calcForceExertedByY(p);
        }
        return NetForceY;
    }
    public void update(double dt,double xforce,double yforce){
        double ax=xforce/mass;
        double ay=yforce/mass;
        xxVel+=dt*ax;
        yyVel+=dt*ay;
        xxPos+=dt*xxVel;
        yyPos+=dt*yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }

}
