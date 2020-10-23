package H03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EMailAdressBuch {
    private final HashMap<String, String> personEmailHashMap;

    public EMailAdressBuch() {
        this.personEmailHashMap = new HashMap<>();
    }

    /**
     * Hiermit kann ein neuer Eintrag in das E-Mail-Adressbuch eingefügt werden.
     * Falls versucht wird, einen schon vorhandenen Namen einzufügen,
     * wird nur die E-Mail-Adresse aktualisiert.
     *
     * @param name  der Personenname
     * @param email die Email-Adresse
     */
    public void einfuegen(String name, String email) {
        this.personEmailHashMap.put(name, email);
    }

    public String abfrage(String name) {
        if (this.personEmailHashMap.containsKey(name)) {
            return this.personEmailHashMap.get(name);
        }
        throw new UnknownNameException("Name existiert nicht!");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Set<Map.Entry<String, String>> kv = this.personEmailHashMap.entrySet();
        s.append("{");
        for (Map.Entry<String, String> entry : kv) {
            s.append(entry.getKey());
            s.append("=");
            s.append(entry.getValue());
            s.append(", ");
            s.append(System.lineSeparator());
        }
        s.delete(s.length() - 2, s.length());
        s.append("}");
        return s.toString();
    }

    public void einlesen(String dateiname) {
        File f = new File(dateiname);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] tmp = sc.nextLine().split(";");
                this.einfuegen(tmp[0], tmp[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Es konnte keine Datei gefunden werden!");
        }
    }
}
