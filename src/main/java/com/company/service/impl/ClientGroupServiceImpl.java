package com.company.service.impl;

import com.company.mapper.ClientGroupMapper;
import com.company.model.entity.ClientGroup;
import com.company.model.form.ClientGroupForm;
import com.company.repository.ClientGroupRepository;
import com.company.service.ClientGroupService;
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
public class ClientGroupServiceImpl implements ClientGroupService {

    private final ClientGroupRepository clientGroupRepository;
    private final ClientGroupMapper clientGroupMapper;

    @Override
    public ResponseEntity<?> add(ClientGroupForm clientGroupForm) {
        try {
            clientGroupRepository.save(clientGroupMapper.clientGroupFormToClientGroup(clientGroupForm));
            log.info("Client group added successfully");
            return buildResponse(null, "Client group added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding client group {}", exception.getCause(), clientGroupForm);
            return buildResponse(null, "Client group added failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, ClientGroupForm clientGroupForm) {
        try {
            clientGroupRepository.updateByName(clientGroupForm.getName(), id);
            log.info("ClientGroup updated successfully");
            return buildResponse(null, "Client group updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating client group {}", exception.getCause(), clientGroupForm);
            return buildResponse(null, "Client group updated failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int clientGroupId) {
        try {
            clientGroupRepository.deleteById(clientGroupId);
            log.info("ClientGroup deleted successfully");
            return buildResponse(null, "Client group deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting client group {}", exception.getCause(), clientGroupId);
            return buildResponse(null, "Client group deleted failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int clientGroupId) {
        Optional<ClientGroup> clientGroup = clientGroupRepository.findById(clientGroupId);
        if (clientGroup.isEmpty()) {
            log.info("Client group id {} not found", clientGroupId);
            return buildResponse(null, "Client group not found", true, 404);
        }
        log.info("Client group id {} found", clientGroupId);
        return buildResponse(clientGroup.get(), "Client group found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<ClientGroup> allClientGroup = clientGroupRepository.getList();
        return buildResponse(allClientGroup, "Client group found", true, 200);
    }


}
