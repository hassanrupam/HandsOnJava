package com.hassan.main.core.repository.tsk;

import com.hassan.main.core.model.tsk.TaskInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository For Task Information
 * This Will Serve all the DB transaction Regarding TaskInformation Entity
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.25 PM
 */
@Repository
public interface ITaskInformationRepository extends JpaRepository<TaskInformation,Long> {
    Optional<TaskInformation> findByTskId(Long tskId);
    TaskInformation deleteByTskId(Long tskId);
    Page<TaskInformation> findAll(Pageable pageable);
}
