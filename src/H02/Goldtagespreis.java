package H02;

public class Goldtagespreis {
    public String datum;
    public double preis;

    public Goldtagespreis(String datum, double preis) {
        this.datum = datum;
        this.preis = preis;
    }

    public String toString() {
        return datum+":"+ preis;
    }

}
