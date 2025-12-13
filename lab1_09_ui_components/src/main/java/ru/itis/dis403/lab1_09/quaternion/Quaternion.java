package ru.itis.dis403.lab1_09.quaternion;

public class Quaternion {
	public double w;
    public double x;
    public double y;
    public double z;

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion plus(Quaternion q){
        return new Quaternion(w+q.w, x+q.x, y+q.y, z+q.z);
    }

    public Quaternion multiply(Quaternion q){
        return new Quaternion(w*q.w - x*q.x-y*q.y-z*q.z,w*q.x+x*q.w+y*q.z-z*q.y,
                w*q.y-x*q.z+y*q.w+z*q.x,w*q.z+x*q.y-y*q.x+z*q.w);
    }

    public double smultiply(Quaternion q){
        return (w * q.w) + (x * q.x) + (y * q.y) + (z * q.z);
    }

    public Quaternion multiply(double k) {
        return new Quaternion(k*w, k*x, k*y, k*z);
    }

    public double norm(){
        return Math.sqrt(w*w + x*x + y*y +z*z);
    }

    public Quaternion inverse(){
        double norm = this.norm();
        return new Quaternion(w/norm*norm, -x/norm*norm, -y/norm*norm, -z/norm*norm);
    }

    public Quaternion unit(){
        double norm = this.norm();
        return new Quaternion(w/norm, x/norm, y/norm, z/norm);
    }

    @Override
    public String toString() {
        return "quaternion: ("+w+", "+x+", "+y+", "+z+")";
    }
}
