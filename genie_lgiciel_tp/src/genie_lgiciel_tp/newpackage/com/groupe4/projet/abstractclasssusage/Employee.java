package genie_lgiciel_tp.newpackage.com.groupe4.projet.abstractclasssusage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import genie_lgiciel_tp.newpackage.com.groupe4.projets.connectionsample.ConnectionFactory;

public class Employee extends Person {
    private String cnss;

    // Constructeur par défaut
    public Employee() {
    }

    // Constructeur avec paramètres
    public Employee(int id, String firstName, String lastName, String cnss) {
        super(id, firstName, lastName);
        this.cnss = cnss;
    }

    // Getters et Setters
    public String getCnss() {
        return cnss;
    }

    public void setCnss(String cnss) {
        this.cnss = cnss;
    }

    /**
     * Afficher l'identité d'un employé sans connexion à la base de données.
     */
    @Override
    public void showIdentity() {
        System.out.println(String.format("Employé avec ID [%d], Prénom [%s], Nom [%s], CNSS [%s]",
                id, firstName, lastName, cnss));
    }

    /**
     * Ajouter un employé dans la base de données.
     * 
     * @param p Objet Person (de type Employee)
     * @return Nombre de lignes affectées
     * @throws SQLException En cas d'erreur avec la base de données.
     */
    @Override
    public int add(Person p) throws SQLException {
        String sqlQuery = "INSERT INTO employee(id, firstName, lastName, cnss) VALUES(?,?,?,?)";
        
        try (PreparedStatement ps = ConnectionFactory.getConnection(ConnectionFactory.MYSQL_CONNECTION)
                .prepareStatement(sqlQuery)) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getFirstName());
            ps.setString(3, p.getLastName());
            ps.setString(4, ((Employee) p).getCnss());

            return ps.executeUpdate();
        }
    }

    /**
     * Récupérer et afficher dynamiquement l'identité d'un employé depuis la base de données.
     * 
     * @param id L'ID de l'employé à rechercher.
     * @throws SQLException En cas d'erreur avec la base de données.
     */
    @Override
    public void showDynamicIdentity(int id) throws SQLException {
        String sqlQuery = "SELECT id, firstName, lastName, cnss FROM employee WHERE id=?";
        
        try (PreparedStatement ps = ConnectionFactory.getConnection(ConnectionFactory.MYSQL_CONNECTION)
                .prepareStatement(sqlQuery)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(String.format("Employé avec ID [%d], Prénom [%s], Nom [%s], CNSS [%s]",
                            rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("cnss")));
                } else {
                    System.out.println("⚠️ Aucun employé trouvé avec l'ID " + id);
                }
            }
        }
    }
}
