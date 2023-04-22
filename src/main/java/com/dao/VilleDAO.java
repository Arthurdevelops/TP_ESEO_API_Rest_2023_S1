package com.dao;

import com.dto.Ville;

import java.util.ArrayList;

public interface VilleDAO {
    public ArrayList<Ville> findAllVilles();
    public ArrayList<Ville> recupererVilles() ;
    public void save(Ville ville);
	public void deleteVille(String nomVille);
	public void updateVille(Ville ville);
    public Ville getVilleByNom(String ville);
}
