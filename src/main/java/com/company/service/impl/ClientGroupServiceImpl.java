package com.company.service.impl;

import com.company.mapper.ClientGroupMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.ClientGroup;
import com.company.model.form.ClientGroupForm;
import com.company.repository.ClientGroupRepository;
import com.company.service.ClientGroupService;
import com.company.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ClientGroupServiceImpl implements ClientGroupService {

    private final BaseUtil baseUtil;
    private final ClientGroupRepository clientGroupRepository;
    private final ClientGroupMapper clientGroupMapper;


    @Override
    public ResponseEntity<?> add(ClientGroupForm clientGroupForm) {

        ResponseDto<?> responseDto;

        try {
            clientGroupRepository.save(clientGroupMapper.clientGroupFormToClientGroup(clientGroupForm));
            log.info("Client group added successfully");
            responseDto = baseUtil.convertResponseDto(null, "Client group added successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        }catch (Exception exception){
            log.error("Something went wrong : {} while adding client group {}", exception.getCause(), clientGroupForm);
            responseDto = baseUtil.convertResponseDto(null, "Client group added failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> update(ClientGroupForm clientGroupForm) {
        ResponseDto<?> responseDto;

        try{
            clientGroupRepository.updateByName(clientGroupForm.getName(), clientGroupForm.getId());
            log.info("ClientGroup updated successfully");
            responseDto = baseUtil.convertResponseDto(null, "Client group updated successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception){
            log.error("Something went wrong : {} while updating client group {}", exception.getCause(), clientGroupForm);
            responseDto = baseUtil.convertResponseDto(null, "Client group updated failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> delete(int clientGroupId) {
        ResponseDto<?> responseDto;

        try{
            clientGroupRepository.deleteById(clientGroupId);
            log.info("ClientGroup deleted successfully");
            responseDto = baseUtil.convertResponseDto(null, "Client group deleted successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception){
            log.error("Something went wrong : {} while deleting client group {}", exception.getCause(), clientGroupId);
            responseDto =baseUtil.convertResponseDto(null, "Client group deleted failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> get(int clientGroupId) {
        ResponseDto<?> responseDto;

        Optional<ClientGroup> clientGroup = clientGroupRepository.findById(clientGroupId);

        if (clientGroup.isEmpty()){
            log.info("Client group id {} not found", clientGroupId);
            responseDto = baseUtil.convertResponseDto(null, "Client group not found", true, 404);
            return ResponseEntity.status(404).body(responseDto);
        }

        log.info("Client group id {} found", clientGroupId);
        responseDto = baseUtil.convertResponseDto(clientGroup.get(), "Client group found", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Override
    public ResponseEntity<?> list() {

        ResponseDto<?> responseDto;

        List<ClientGroup> allClientGroup = clientGroupRepository.getList();

        responseDto = baseUtil.convertResponseDto(allClientGroup, "Client group found", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }


}
