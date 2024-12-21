package com.company.service.impl;

import com.company.model.entity.Roles;
import com.company.model.form.RolesForm;
import com.company.repository.RoleRepository;
import com.company.service.RoleService;
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
public class RolesServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> add(RolesForm rolesForm) {
        try {
            roleRepository.save(
                    Roles.builder()
                            .name(rolesForm.getName())
                            .build()
            );
            log.info("Role added successfully");
            return buildResponse(null, "Role added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding role {}", exception.getCause(), rolesForm);
            return buildResponse(null, "Role addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, RolesForm rolesForm) {
        try {
            roleRepository.updateByName(rolesForm.getName(), id);
            log.info("Role updated successfully");
            return buildResponse(null, "Role updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating role {}", exception.getCause(), rolesForm);
            return buildResponse(null, "Role update failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int roleId) {
        try {
            roleRepository.deleteById(roleId);
            log.info("Role deleted successfully");
            return buildResponse(null, "Role deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting role {}", exception.getCause(), roleId);
            return buildResponse(null, "Role deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int roleId) {
        Optional<Roles> roles = roleRepository.findById(roleId);
        if (roles.isEmpty()) {
            log.info("Role id {} not found", roleId);
            return buildResponse(null, "Role not found", false, 404);
        }
        log.info("Role id {} found", roleId);
        return buildResponse(roles.get(), "Role found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<Roles> allRoles = roleRepository.getList();
        return buildResponse(allRoles, "Roles list found", true, 200);
    }
}
