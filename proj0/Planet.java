public  class  Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    String imgFileName;
    static final double G  = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet that){
        double dx = that.xxPos - this.xxPos;
        double dy = that.yyPos - this.yyPos;
        return Math.pow(dx *  dx + dy * dy, 0.5);
    }

    public double calcForceExertedBy(Planet that){

        return G * this.mass * that.mass / (this.calcDistance(that) *  this.calcDistance(that));
    }

    public double calcForceExertedByX(Planet that){
        double Fx = this.calcForceExertedBy(that);
        double x = that.xxPos - this.xxPos;
        return Fx * x / this.calcDistance(that);
    }
    public double calcForceExertedByY(Planet that){
        double Fy = this.calcForceExertedBy(that);
        double y = that.yyPos - this.yyPos;
        return Fy * y / this.calcDistance(that);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double Fnetx = 0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                Fnetx += this.calcForceExertedByX(p);
            }
        }
        return Fnetx;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double Fnety = 0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                Fnety += this.calcForceExertedByY(p);
            }
        }
        return Fnety;
    }
    public void update(double dt, double fX, double fY){
       double Anetx = fX / mass;
       double Anety = fY / mass;
       xxVel += Anetx * dt;
       yyVel += Anety * dt;
       xxPos += xxVel * dt;
       yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }

}