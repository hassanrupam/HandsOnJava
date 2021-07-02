package com.hassan.main.core.repository.prj;

import com.hassan.main.core.model.prj.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository For Project
 * This Will Serve all the DB transaction Regarding Project Entity
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.23 PM
 */
@Repository
public interface IProjectRepository extends JpaRepository<Project, UUID> {
    Optional<Project> findByPrjId(UUID prjId);
    int deleteByPrjId(UUID prjId);
    Page<Project> findAll(Pageable pageable);
}
