package com.dao;

import com.dto.Ville;

import java.util.ArrayList;
import java.util.List;

public interface VilleDAO {
    public ArrayList<Ville> findAllVilles();
    public ArrayList<Ville> recupererVilles() ;

}
