package com.dao;

import com.dto.Ville;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VilleDAOImpl implements VilleDAO{
    private Connection connexion;

    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/twic_maven", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Ville> findAllVilles(){
        System.out.println("findAllVilles");
        ArrayList<Ville> listVille = new ArrayList<>();

        Ville ville = new Ville();
        ville.setCodePostal("49000");
        ville.setNom("Angers");
        ville.setLigne("ligne");
        listVille.add(ville);
        return listVille;
    }

    public ArrayList<Ville> recupererVillesTest() {
        ArrayList<Ville> villes = new ArrayList<Ville>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery(
                    "SELECT nom, codePostal from villes;");
            // Récupération des données
            while (resultat.next()) {
                System.out.println("2");
                String nom = resultat.getString("nom");
                String cp = resultat.getString("codePostal");
                Ville ville = new Ville();
                ville.setNom(nom);
                ville.setCodePostal(cp);
                System.out.println(ville.getInfo());
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

    public ArrayList<Ville> recupererVilles() {
        ArrayList<Ville> villes = new ArrayList<Ville>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery(
                    "SELECT Nom_commune, Code_postal from ville_france;");
            // Récupération des données
            while (resultat.next()) {
                System.out.println("2");
                String nom = resultat.getString("Nom_commune");
                String cp = resultat.getString("Code_postal");
                Ville ville = new Ville();
                ville.setNom(nom);
                ville.setCodePostal(cp);
                System.out.println(ville.getInfo());
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
}
