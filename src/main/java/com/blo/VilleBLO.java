package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBLO {
    public String getInfoVille(String codePostal);
    public void addVille(Ville ville);
	public List<Ville> getVilles();
	public void deleteVille(String nomVille);
	public void updateVille(Ville ville);
    public Ville getVilleByNom(String nom);
}
