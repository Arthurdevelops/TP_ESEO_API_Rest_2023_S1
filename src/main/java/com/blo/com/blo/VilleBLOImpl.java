package com.blo.com.blo;

import com.dao.VilleDAO;
import com.dto.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
