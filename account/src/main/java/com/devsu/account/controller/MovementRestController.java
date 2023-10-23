package com.devsu.account.controller;

import com.devsu.account.dto.movement.*;
import com.devsu.account.service.movement.IMovementsService;
import com.devsu.account.service.movement.MovementsMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
@RequestMapping("movimientos")
public class MovementRestController {

    private final IMovementsService service;
    private final MovementsMapper mapper;

    public MovementRestController(IMovementsService service, MovementsMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<MovementItemResponse> getMovements() {
        return service.findAll()
                .stream()
                .map(mapper::toItemResponse)
                .toList();
    }

    @GetMapping("{clientId}/report")
    public List<MovementReportItemResponse> reportClientMovements(
            @PathVariable final Long clientId,
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDateTime startDate,
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDateTime endDate
    ) {
        return this.service.findAll(clientId, startDate, endDate)
                .stream()
                .map(mapper::toReportItemResponse)
                .toList();
    }

    @GetMapping("{id}")
    public MovementDetailResponse getMovementDetail(
            @PathVariable Long id) {
        return mapper.toDetailResponse(this.service.getOne(id));
    }

    @PostMapping()
    public MovementDetailResponse createMovement(
            @RequestBody() MovementCreateRequest data
    ) {
        MovementDTO result = this.service.createOne(data);
        return mapper.toDetailResponse(result);
    }

    @DeleteMapping("{id}")
    public void deleteMovement(
            @PathVariable Long id) {
        this.service.deleteOne(id);
    }

    @PutMapping("{id}")
    public void updateMovement(
            @PathVariable Long id,
            @RequestBody MovementUpdateRequest data
    ) {
        this.service.updateOne(id, mapper.toDTO(data));
    }
}
