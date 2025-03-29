package genie_lgiciel_tp.newpackage.com.groupe4.projet.abstractclasssusage;

import java.sql.SQLException;

public abstract class Person {
    protected int id;
    protected String firstName;
    protected String lastName;

    // Constructeur par défaut
    public Person() {
    }

    // Constructeur avec paramètres
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Afficher l'identité d'une personne sans connexion à la base de données.
     */
    public abstract void showIdentity();

    /**
     * Afficher l'identité d'une personne en interrogeant la base de données par son ID.
     * 
     * @param id L'ID de la personne à rechercher.
     * @throws SQLException En cas d'erreur avec la base de données.
     */
    public abstract void showDynamicIdentity(int id) throws SQLException;

    /**
     * Insérer une nouvelle personne dans la base de données.
     * 
     * @param p Objet Person (employé ou étudiant)
     * @return Nombre de lignes affectées (1 si l'insertion réussit)
     * @throws SQLException En cas d'erreur avec la base de données.
     */
    public abstract int add(Person p) throws SQLException;
}
