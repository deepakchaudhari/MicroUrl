package com.intuit.martech.microurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.martech.microurl.domain.URL;




/**
 * Spring Data JPA repository for the URL entity.
 */
@Repository
public interface URLRepository extends JpaRepository<URL, String> {

}
