package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.MetReponse;
import com.example.demo.dto.MetRequest;
import com.example.demo.models.Met;

public interface MetService {
	
	List<MetReponse> getAllMet();
	MetReponse creatMet(MetRequest request);
	MetReponse deleteMet(String nom);
	MetReponse updateMet(String nom , MetRequest request);

}
