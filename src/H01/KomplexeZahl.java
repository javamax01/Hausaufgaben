package Uebung01;

import java.util.Arrays;

public class KomplexeZahl {

    private double x;
    private double y;

    public KomplexeZahl(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public double getIm(){
        return this.y;
    }

    public void setIm(double y){
        this.y = y;
    }

    public double getRe(){
        return this.x;
    }

    public void setRe(double x){
        this.x = x;
    }

    public void addiere(KomplexeZahl z) {
        this.x+=z.x;
        this.y+=z.y;
    }

    public void multipliziere(KomplexeZahl z) {
        double a=this.x; // Zwischenspeicher für x
        this.x=this.x*z.x-this.y*z.y;
        this.y=a*z.y+this.y*z.x;
    }

    public double getBetrag() {
        return Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
    }

    public String toString() {
        return "" +this.x+ " + i*" +this.y+ "";

    }

    public KomplexeZahl[] getWurzel() {
        double phi = Math.atan(this.y / this.x);
        double faktor = Math.sqrt(this.getBetrag());
        /*if (this.x == 0) {
            throw new RuntimeException("Divide by zero!");
        }*/

        // Für den Fall 1. Quadrant ist phi = Math.atan(this.y / this.x)
        // Fall 2. oder 3. Quadrant
        if (this.x < 0 && (this.y > 0 || this.y < 0))
        {
            phi += Math.PI;
        }
        else if (this.x > 0 && this.y < 0) // Fall 4. Quadrant
        {
            phi += 2 * Math.PI;
        }
        else if (this.x > 0 && this.y == 0) //z liegt auf der x-Achse
        {
            phi = 0;
        }
        else if (this.x < 0 && this.y == 0)
        {
            phi = Math.PI;
        }
        else if (this.x == 0 && this.y > 0) //z liegt auf der y-Achse
        {
            phi = 0.5*Math.PI;
        }
        else if (this.x == 0 && this.y < 0)
        {
            phi = 1.5*Math.PI;
        }

        KomplexeZahl[] w = new KomplexeZahl[2];
        KomplexeZahl w0 = new KomplexeZahl (faktor * Math.cos(phi / 2.),
                faktor * Math.sin(phi / 2.));
        KomplexeZahl w1 = new KomplexeZahl (faktor * (Math.cos((phi + 2. * Math.PI) / 2.)),
                faktor * (Math.sin((phi + 2 * Math.PI) / 2.)));

        w[0] = w0;
        w[1] = w1;

        return w;
    }

    public KomplexeZahl getSumme(KomplexeZahl z){
        z.addiere(this);
        return z;
    }

    public KomplexeZahl getProdukt(KomplexeZahl z) {
        z.multipliziere(this);
        return z;
    }

    public static void main(String[] args) {
        KomplexeZahl z = new KomplexeZahl(0,0); // z := 0
        System.out.println("z = " + z);
        z = new KomplexeZahl(1,0); // z := 1
        System.out.println("z = " + z);
        z = new KomplexeZahl(0,1); // z := i
        System.out.println("z = " + z);
        z = new KomplexeZahl(-4,0); // z := -4
        System.out.println("z = " + z);
        KomplexeZahl[] wurzeln = z.getWurzel(); // => 2i und -2i
        System.out.println("sqrt(z) = " + Arrays.toString(wurzeln));
        z = new KomplexeZahl(1,1); // z := 1+i
        System.out.println("z = " + z);
        double betrag = z.getBetrag();
        System.out.println("|z| = " + betrag); // => sqrt(2) = 1.41...
        KomplexeZahl z2 = new KomplexeZahl(2,1); // z2 := 2+i
        System.out.println("z2 = " + z2);
        z.addiere(z2);
        System.out.println("z nach Addition von z2 = " + z);
        z.multipliziere(z2);
        System.out.println("z nach Multiplikation von z2 = " + z);
        z2 = z.getProdukt(new KomplexeZahl(-1,0)); // z2 := -z
        System.out.println("z2 = " + z2);
        KomplexeZahl summe = z.getSumme(z2); // z := z - z2 = 0
        System.out.println("summe = " + summe);
    }
}
