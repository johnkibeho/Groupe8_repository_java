package genie_lgiciel_tp.newpackage.com.groupe4.projet.abstractclasssusage;

import java.sql.Connection;
import java.sql.SQLException;
import genie_lgiciel_tp.newpackage.com.groupe4.projets.connectionsample.ConnectionFactory;

public class TestAbstract {

    public static void main(String[] args) {
        try {
            System.out.println("🔄 Initialisation de l'insertion des employés et étudiants...\n");

            // Création et insertion du premier employé
            Person e1 = new Employee(1, "Isaac", "Kibeho", "12IJdfogsrdGSD890");
            insertPerson(e1, "Employee 1");
            Person e2 = new Employee(2, "IYUMBA", "KWIRAVIWE", "13ahghdjeyqay789");
            insertPerson(e2, "Employee 2");

            Person e3 = new Employee(3, "Iragi", "daniel", "14IJdfogsrdbhf800");
            insertPerson(e3, "Employee 3");

            Person e4 = new Employee(4, "Kahindo", "MARIE-LUC", "15IJdfogsrdGSD880");
            insertPerson(e4, "Employee 4");

            Person e5 = new Employee(5, "KAFINDO", "MUSEKA", "72IJdfogsrdGSD220");
            insertPerson(e5, "Employee 5");

            Person e6 = new Employee(6, "MWENZE", "CLAUDETTE", "12IJdfogsrdGSD890");
            insertPerson(e6, "Employee 6");

            Person e7 = new Employee(7, "ITONGWA", "KINUMBA", "12IJdfogsrdGSD400");
            insertPerson(e7, "Employee 7");

            Person e8 = new Employee(8, "ISENGE", "BENEDICTION", "12IJdfogsrdGSD830");
            insertPerson(e8, "Employee 8");

            Person e9 = new Employee(9, "ISRAEL", "DAMIEN", "12IJdfogsrdGSD190");
            insertPerson(e9, "Employee 9");

            Person e10 = new Employee(10, "KABUO", "NATHALIE", "12IJdfogsrdGSD000");
            insertPerson(e10, "Employee 10");
            

            // Création et insertion du deuxième employé
            Person e2 = new Employee(2, "Bamwisho", "Feli", "5239NBK82302");
            insertPerson(e2, "Employee 2");

            // Création et insertion du premier étudiant
            Person s1 = new Student(1, "Jean", "Shabani", "sdfusd233");
            insertPerson(s1, "Student 1");

            // Création et insertion du deuxième étudiant
            Person s2 = new Student(2, "DFFJI", "Ndeze", "DFGSF843765TSREGF");
            insertPerson(s2, "Student 2");

            // Affichage des identités des employés et étudiants
            System.out.println("\n🔍 Affichage des employés insérés:");
            showPersonIdentity(e1);
            showPersonIdentity(e2);

            System.out.println("\n🔍 Affichage des étudiants insérés:");
            showPersonIdentity(s1);
            showPersonIdentity(s2);

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'insertion des données !");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Insère une personne dans la base de données et affiche un message de confirmation.
     */
    private static void insertPerson(Person p, String label) throws SQLException {
        int result = p.add(p);
        if (result > 0) {
            System.out.println("✅ " + label + " inséré avec succès !");
        } else {
            System.out.println("⚠️ Échec de l'insertion de " + label + ".");
        }
    }

    /**
     * Affiche l'identité dynamique d'une personne.
     */
    private static void showPersonIdentity(Person p) throws SQLException {
        System.out.println("-----------------------------------------");
        p.showDynamicIdentity(p.getId());
    }

    /**
     * Ferme la connexion à la base de données proprement.
     */
    private static void closeConnection() {
        try (Connection connection = ConnectionFactory.getConnection(ConnectionFactory.MYSQL_CONNECTION)) {
            if (connection != null) {
                System.out.println("🔌 Connexion fermée avec succès.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Problème lors de la fermeture de la connexion.");
            e.printStackTrace();
        }
    }
}
