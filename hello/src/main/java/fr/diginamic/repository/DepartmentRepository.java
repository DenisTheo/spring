package fr.diginamic.repository;

import fr.diginamic.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<City, Integer> { }
