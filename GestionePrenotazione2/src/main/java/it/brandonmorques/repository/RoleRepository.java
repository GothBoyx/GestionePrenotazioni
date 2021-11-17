package it.brandonmorques.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.brandonmorques.model.Role;
import it.brandonmorques.model.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleType(RoleType roletype);

}
