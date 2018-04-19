package com.example.demo.model.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Recommendation;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
	
@Repository
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {

}