package com.company.service.impl;

import com.company.model.entity.Department;
import com.company.model.form.DepartmentForm;
import com.company.repository.DepartmentRepository;
import com.company.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<?> add(DepartmentForm departmentForm) {
        try {
            departmentRepository.save(
                    Department.builder()
                            .name(departmentForm.getName())
                            .workshopId(departmentForm.getWorkshopId())
                            .build()
            );
            log.info("Department added successfully");
            return buildResponse(null, "Department added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding department {}", exception.getCause(), departmentForm);
            return buildResponse(null, "Department addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, DepartmentForm departmentForm) {
        try {
            departmentRepository.updateByName(departmentForm.getName(), id);
            log.info("Department updated successfully");
            return buildResponse(null, "Department updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating department {}", exception.getCause(), departmentForm);
            return buildResponse(null, "Department update failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int departmentId) {
        try {
            departmentRepository.deleteById(departmentId);
            log.info("Department deleted successfully");
            return buildResponse(null, "Department deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting department {}", exception.getCause(), departmentId);
            return buildResponse(null, "Department deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int departmentId) {
        Optional<Department> departments = departmentRepository.findById(departmentId);
        if (departments.isEmpty()) {
            log.info("Department id {} not found", departmentId);
            return buildResponse(null, "Department not found", false, 404);
        }
        log.info("Department id {} found", departmentId);
        return buildResponse(departments.get(), "Department found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<Department> allDepartment = departmentRepository.getList();
        return buildResponse(allDepartment, "Department list found", true, 200);
    }
}
