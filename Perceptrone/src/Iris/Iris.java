package Iris;

public class Iris {
    public double[] doubles;
    String string;
    double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Iris(double[] doubles, String s){
        this.doubles = doubles;
        this.string = s;
    }


    public double[] getDoubles() {
        return doubles;
    }

    public String getString() {
        return string;
    }
}
