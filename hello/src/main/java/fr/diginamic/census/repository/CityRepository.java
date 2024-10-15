package fr.diginamic.census.repository;

import fr.diginamic.census.model.City;
import fr.diginamic.census.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer>
{
    // Fetches Cities based on name
    List<City> findCityByName(String prefix);
    
    // Fetches Cities over minimum population
    List<City> findCitiesWithPopOver(int min);
    
    // Fetches Cities within pop range
    List<City> findCitiesWithPopWithinRange(int min, int max);
    
    // Fetches Cities over minimum population in said Department
    @Query("SELECT c FROM City c WHERE c.department = :department AND c.popCount > :min")
    List<City> findCitiesWithPopOverInDepartment(@Param("department") Department department, @Param("min") int min);

    // Fetches Cities within pop Range in said Department
    @Query("SELECT c FROM City c WHERE c.department = :department AND c.popCount > :min AND c.popCount < :max")
    List<City> findCitiesWithPopWithinRangeInDepartment(@Param("department") Department department, @Param("min") int min, @Param("max") int max);
    
    // Fetches N Cities with the highest population count in said Department
    @Query("SELECT c FROM City c WHERE c.department = :department ORDER BY c.popCount DESC")
    Page<City> findMostPopulatedCitiesInDepartment(@Param("department") Department department, Pageable pageable);
}
