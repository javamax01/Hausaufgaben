package H03;

public class EmailAdressBuchTest {
    public static void main(String[] args) {
        EMailAdressBuch matseAdressBuch = new EMailAdressBuch();
        matseAdressBuch.einlesen("C:\\Users\\mkalachnikov\\IdeaProjects\\Hausaufgaben\\src\\H03\\mitarbeiter_matse_intern.txt");
        matseAdressBuch.einlesen("C:\\Users\\mkalachnikov\\IdeaProjects\\Hausaufgaben\\src\\H03\\mitarbeiter_matse_extern.txt");
        System.out.println(matseAdressBuch.toString());
        System.out.println();
        EMailAdressBuch caeAdressBuch = new EMailAdressBuch();
        caeAdressBuch.einlesen("C:\\Users\\mkalachnikov\\IdeaProjects\\Hausaufgaben\\src\\H03\\emailadressbuch.txt");
        System.out.println(caeAdressBuch);
    }
}
