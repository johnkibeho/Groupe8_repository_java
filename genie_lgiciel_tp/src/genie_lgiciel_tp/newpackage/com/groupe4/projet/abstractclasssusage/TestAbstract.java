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
