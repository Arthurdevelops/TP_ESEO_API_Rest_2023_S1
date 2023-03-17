package com.dto;

public class Ville {
    private String nom;
    private String codePostal;
    private String ligne;

    public Ville(){
        this.setLigne(null);
        this.setNom(null);
        this.setCodePostal(null);
    }

    public String getInfo(){
        return "Nom : "+ this.getNom() + " Code Postal : " +this.getCodePostal();
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }
}
