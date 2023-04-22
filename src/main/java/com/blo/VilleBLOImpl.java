package com.blo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;
@Service
public class VilleBLOImpl implements VilleBLO{

    @Autowired
    private VilleDAO villeDAO;

    @Override
    public String getInfoVille(String codePostal) {
        ArrayList<Ville> listVille = new ArrayList<>();
        listVille = villeDAO.recupererVilles();//villeDAO.findAllVilles();
        String info = "Aucune ville ne correspond à ce code postal!!";
        for(Ville ville: listVille){
            if(ville.getCodePostal().equals(codePostal)){
                info = ville.getInfo();
            }
        }
        return info;
    }
    
    @Override
    public List<Ville> getVilles(){
    	List<Ville> listVille = new ArrayList<>();
    	listVille = villeDAO.recupererVilles();
    	return listVille;
    }

	@Override
	public void addVille(Ville ville) {
		villeDAO.save(ville);
	}

	@Override
	public void deleteVille(String nomVille) {
		villeDAO.deleteVille(nomVille);
	}

	@Override
	public void updateVille(Ville ville) {
		villeDAO.updateVille(ville);
	}

    @Override
	public Ville getVilleByNom(String nom) {
		return villeDAO.getVilleByNom(nom);
	}


    
}
