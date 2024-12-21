package com.company.service.impl;

import com.company.model.entity.Permissions;
import com.company.model.form.PermissionForm;
import com.company.repository.PermissionRepository;
import com.company.service.PermissionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@Slf4j
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public ResponseEntity<?> add(PermissionForm permissionForm) {
        try {
            permissionRepository.save(
                    Permissions.builder()
                            .name(permissionForm.getName())
                            .roleId(permissionForm.getRoleId())
                            .build()
            );
            log.info("Permission added successfully");
            return buildResponse(null, "Permission added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding permission {}", exception.getCause(), permissionForm);
            return buildResponse(null, "Permission addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, PermissionForm permissionForm) {
        try {
            permissionRepository.updateByName(permissionForm.getName(), id);
            log.info("Permission updated successfully");
            return buildResponse(null, "Permission updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating permission {}", exception.getCause(), permissionForm);
            return buildResponse(null, "Permission update failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int permissionId) {
        try {
            permissionRepository.deleteById(permissionId);
            log.info("Permission deleted successfully");
            return buildResponse(null, "Permission deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting permission {}", exception.getCause(), permissionId);
            return buildResponse(null, "Permission deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int permissionId) {
        Optional<Permissions> permissions = permissionRepository.findById(permissionId);
        if (permissions.isEmpty()) {
            log.info("Permission id {} not found", permissionId);
            return buildResponse(null, "Permission not found", false, 404);
        }
        log.info("Permission id {} found", permissionId);
        return buildResponse(permissions.get(), "Permission found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<Permissions> allPermissions = permissionRepository.getList();
        return buildResponse(allPermissions, "Permissions list found", true, 200);
    }
}
