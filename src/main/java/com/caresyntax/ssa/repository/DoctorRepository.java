package com.caresyntax.ssa.repository;


import com.caresyntax.ssa.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
