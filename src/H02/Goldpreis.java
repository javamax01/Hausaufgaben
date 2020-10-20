package H02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Goldpreis {
    private ArrayList<Goldtagespreis> list;

    /**
     * Konstruktur ruft Methode aufbauListe auf.
     * @param dateiname aus dieser Datei werden die Daten entnommen
     */
    public Goldpreis(String dateiname) {
        aufbauListe(dateiname);
    }

    /**
     * Generiert eine ArrayList, die mit dem Inhalt der angegebenen Datei gefüllt wird.
     * Der Inhalt besteht aus Objekten von Goldtagespreis.
     * @param dateiname aus dieser Datei werden die Daten entnommen
     */
    private void aufbauListe(String dateiname) {
        list = new ArrayList<>();
        File gold = new File(dateiname);
        try {
            double preis;
            Scanner sc = new Scanner(gold);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] temp = s.split("\t");
                try {
                    preis = Double.parseDouble(temp[1].substring(0, 1));
                    char[] c = temp[1].toCharArray();
                    int punktIndex = temp[1].indexOf(".");
                    int kommaIndex = temp[1].indexOf(",");
                    c[punktIndex] = 0;
                    c[kommaIndex] = '.';
                    temp[1] = "";
                    for (char value : c) {
                        if (value != 0) {
                            temp[1] += value;
                        }
                    }
                    preis = Double.parseDouble(temp[1]);
                } catch (NumberFormatException e2) {
                    preis = -1;
                }
                Goldtagespreis goldtagespreis = new Goldtagespreis(temp[0], preis);
                list.add(goldtagespreis);
                // System.out.println(preis);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Zu einem angegebenen Datum (JJJJ-MM-TT) wird der zugehörige Preis zurueckgegeben.
     * @param datum vom Preis
     * @return Preis vom angegebenen Datum
     */
    public double getPreis(String datum) {
        try {
            int test = Integer.parseInt(datum.substring(0, 4));
            test = Integer.parseInt(datum.substring(5, 7));
            test = Integer.parseInt(datum.substring(8, 10));
            if (!(datum.charAt(4) == '-' && datum.charAt(7) == '-')) {
                throw new NumberFormatException("");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new NumberFormatException("Das Datum hat nicht das richtige Format!");
        }
        for (Goldtagespreis goldtagespreis : list) {
            if (datum.equals(goldtagespreis.datum)) {
                return goldtagespreis.preis;
            }
        }
        throw new NumberFormatException("Dieses Datum exisiert nicht!");
    }

    /**
     * Gibt den Mindestpreis mit Datum und Höchstpreis mit Datum in der Konsole aus.
     */
    public void printMinMax() {
        double preismax = list.get(0).preis;
        double preismin = preismax;
        String datummax = list.get(0).datum;
        String datummin = datummax;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).preis > preismax) {
                preismax = list.get(i).preis;
                datummax = list.get(i).datum;
            }
            if ((list.get(i).preis < preismin) && (list.get(i).preis > 0)) {
                preismin = list.get(i).preis;
                datummin = list.get(i).datum;
            }
        }
        System.out.println("Am " + datummin + " war der Mindestpreis von: " + preismin);
        System.out.println("Am " + datummax + " war der Höchstpreis von: " + preismax);
    }

    public static void main(String[] args) {
        Goldpreis test = new Goldpreis("C:\\Users\\mkalachnikov\\IdeaProjects\\Hausaufgaben\\src\\H02\\gold.txt");
        System.out.println(test.getPreis("2009-10-20")); // 22870.0
        System.out.println(test.getPreis("2009-02-07")); // -1
        test.printMinMax();

    }
}
