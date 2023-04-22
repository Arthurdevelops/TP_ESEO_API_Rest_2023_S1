package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	private Connection connexion;

	private void loadDatabase() {
		// Chargement du driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/twic", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Ville> findAllVilles() {
		System.out.println("findAllVilles");
		ArrayList<Ville> listVille = new ArrayList<>();

		Ville ville = new Ville();
		ville.setCodePostal("49000");
		ville.setNom("Angers");
		ville.setLigne("ligne");
		listVille.add(ville);
		return listVille;
	}

	public ArrayList<Ville> recupererVilles() {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();
			// Exécution de la requête
			resultat = statement.executeQuery("SELECT Nom_commune, Code_postal, Latitude, Longitude from ville_france;");
			// Récupération des données
			while (resultat.next()) {
				//System.out.println("2");
				String nom = resultat.getString("Nom_commune");
				String codePostal = resultat.getString("Code_postal");
                String latitude = resultat.getString("Latitude");
                String longitude = resultat.getString("Longitude");
				Ville ville = new Ville();
				ville.setNom(nom);
				ville.setCodePostal(codePostal);
                ville.setLatitude(latitude);
                ville.setLongitude(longitude);
				villes.add(ville);
			}
		} catch (SQLException e) {
		} finally {
			// Fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return villes;
	}
	

	@Override
	public void save(Ville ville) {
        PreparedStatement statement = null;
        loadDatabase();
        try {
            String sql = "INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connexion.prepareStatement(sql);
            statement.setString(1, ville.getCodeCommune());
            statement.setString(2, ville.getNom());
            statement.setString(3, ville.getCodePostal());
            statement.setString(4, ville.getLibelle());
            statement.setString(5, ville.getLigne());
            statement.setString(6, ville.getLatitude());
            statement.setString(7, ville.getLongitude());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Gérer l'exception
                }
            }
            if (connexion != null) {
                try {
                	connexion.close();
                } catch (SQLException e) {
                    // Gérer l'exception
                }
            }
        }
	}

	@Override
	public void deleteVille(String nomVille) {
		PreparedStatement statement = null;
        loadDatabase();
        try {
            String sql = "DELETE FROM `ville_france` WHERE Nom_commune = ?";
            statement = connexion.prepareStatement(sql);
            statement.setString(1, nomVille);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Gérer l'exception
                }
            }
            if (connexion != null) {
                try {
                	connexion.close();
                } catch (SQLException e) {
                    // Gérer l'exception
                }
            }
        }
	}

	@Override
	public void updateVille(Ville ville) {
		PreparedStatement statement = null;
        loadDatabase();
        try {
        	String sql = "UPDATE `ville_france` SET `Code_commune_INSEE`=?,`Nom_commune`=?,`Code_postal`=?,`Libelle_acheminement`=?,`Ligne_5`=?,`Latitude`=?,`Longitude`=? WHERE Nom_commune = ?";
            statement = connexion.prepareStatement(sql);
            statement.setString(1, ville.getCodeCommune());
            statement.setString(2, ville.getNom());
            statement.setString(3, ville.getCodePostal());
            statement.setString(4, ville.getLibelle());
            statement.setString(5, ville.getLigne());
            statement.setString(6, ville.getLatitude());
            statement.setString(7, ville.getLongitude());
            statement.setString(8, ville.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Gérer l'exception
                }
            }
            if (connexion != null) {
                try {
                	connexion.close();
                } catch (SQLException e) {
                    // Gérer l'exception
                }
            }
        }
		
	}

    @Override
    public Ville getVilleByNom(String ville) {
        loadDatabase();
        ResultSet resultat = null;
        try {
            String sql = "SELECT Nom_commune, Code_postal, Latitude, Longitude FROM ville_france WHERE Nom_commune =  '" + ville +"'";
            Statement statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);
            Ville villeSearched = new Ville();
            while (resultat.next()) {
                villeSearched.setNom(resultat.getString("Nom_commune"));
                villeSearched.setCodePostal(resultat.getString("Code_postal"));
                villeSearched.setLatitude(resultat.getString("Latitude"));
                villeSearched.setLongitude(resultat.getString("Longitude"));
                return villeSearched;
            }
        } catch (SQLException e) {
            // Gérer l'exception
        }
        return null;
    }
	
	
}
