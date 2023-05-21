package com.mustycodified.Reservlyv1be.respositories;

import com.mustycodified.Reservlyv1be.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
