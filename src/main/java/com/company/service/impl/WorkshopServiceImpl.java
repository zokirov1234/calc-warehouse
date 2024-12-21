package com.company.service.impl;

import com.company.model.entity.Workshop;
import com.company.model.form.WorkshopForm;
import com.company.repository.WorkshopRepository;
import com.company.service.WorkshopService;
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
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    @Override
    public ResponseEntity<?> add(WorkshopForm workshopForm) {
        try {
            workshopRepository.save(
                    Workshop.builder()
                            .name(workshopForm.getName())
                            .state(workshopForm.isState())
                            .build()
            );
            log.info("Workshop added successfully");
            return buildResponse(null, "Workshop added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding workshop {}", exception.getCause(), workshopForm);
            return buildResponse(null, "Workshop addition failed", false, 500);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(int id, WorkshopForm workshopForm) {
        try {
            workshopRepository.updateByName(workshopForm.getName(), id);
            log.info("Workshop updated successfully");
            return buildResponse(null, "Workshop updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating workshop {}", exception.getCause(), workshopForm);
            return buildResponse(null, "Workshop update failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int workshopId) {
        try {
            workshopRepository.deleteById(workshopId);
            log.info("Workshop deleted successfully");
            return buildResponse(null, "Workshop deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting workshop {}", exception.getCause(), workshopId);
            return buildResponse(null, "Workshop deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int workshopId) {
        Optional<Workshop> workshops = workshopRepository.findById(workshopId);
        if (workshops.isEmpty()) {
            log.info("Workshop id {} not found", workshopId);
            return buildResponse(null, "Workshop not found", false, 404);
        }
        log.info("Workshop id {} found", workshopId);
        return buildResponse(workshops.get(), "Workshop found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<Workshop> allWorkshop = workshopRepository.getList();
        return buildResponse(allWorkshop, "Workshop list found", true, 200);
    }
}
