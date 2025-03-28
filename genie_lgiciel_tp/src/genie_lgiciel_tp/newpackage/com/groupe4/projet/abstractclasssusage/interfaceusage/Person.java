
package genie_lgiciel_tp.newpackage.com.groupe4.projet.abstractclasssusage.interfaceusage;

import java.sql.SQLException;

public interface Person {
	void showIdentity();
	void showDynamicIdentity(int id) throws SQLException;
	int add() throws SQLException;
}
