package genie_lgiciel_tp.newpackage.com.groupe4.projet.abstractclasssusage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import genie_lgiciel_tp.newpackage.com.groupe4.projets.connectionsample.ConnectionFactory;

public class Student extends Person {
    private String rollNumber;

    // Constructeur par défaut
    public Student() {
    }

    // Constructeur avec paramètres
    public Student(int id, String firstName, String lastName, String rollNumber) {
        super(id, firstName, lastName);
        this.rollNumber = rollNumber;
    }

    // Getters et Setters
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    /**
     * Afficher l'identité d'un étudiant sans connexion à la base de données.
     */
    @Override
    public void showIdentity() {
        System.out.println(String.format("Étudiant avec ID [%d], Prénom [%s], Nom [%s], Numéro de matricule [%s]", 
                id, firstName, lastName, rollNumber));
    }

    /**
     * Ajouter un étudiant dans la base de données.
     * 
     * @param p Objet Person (de type Student)
     * @return Nombre de lignes affectées
     * @throws SQLException En cas d'erreur avec la base de données.
     */
    @Override
    public int add(Person p) throws SQLException {
        String sqlQuery = "INSERT INTO student(id, firstName, lastName, rollNumber) VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection(ConnectionFactory.MYSQL_CONNECTION)
                .prepareStatement(sqlQuery)) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getFirstName());
            ps.setString(3, p.getLastName());
            ps.setString(4, ((Student) p).getRollNumber());

            return ps.executeUpdate();
        }
    }

    /**
     * Récupérer et afficher dynamiquement l'identité d'un étudiant depuis la base de données.
     * 
     * @param id L'ID de l'étudiant à rechercher.
     * @throws SQLException En cas d'erreur avec la base de données.
     */
    @Override
    public void showDynamicIdentity(int id) throws SQLException {
        String sqlQuery = "SELECT id, firstName, lastName, rollNumber FROM student WHERE id=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection(ConnectionFactory.MYSQL_CONNECTION)
                .prepareStatement(sqlQuery)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(String.format("Étudiant avec ID [%d], Prénom [%s], Nom [%s], Numéro de matricule [%s]", 
                            rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("rollNumber")));
                } else {
                    System.out.println("⚠️ Aucun étudiant trouvé avec l'ID " + id);
                }
            }
        }
    }
}
