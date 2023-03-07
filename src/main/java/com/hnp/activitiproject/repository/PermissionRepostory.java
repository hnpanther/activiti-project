package com.hnp.activitiproject.repository;

import com.hnp.activitiproject.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PermissionRepostory extends JpaRepository<Permission, Long> {

    Optional<Permission> findByPermissionName(String permissionName);
}