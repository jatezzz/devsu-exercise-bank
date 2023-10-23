package com.devsu.account.service;

import com.devsu.account.exeption.ResourceNotFoundException;
import com.devsu.account.model.AbstractEntity;
import com.devsu.account.repository.AbstractEntityRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<DTO, ENTITY extends AbstractEntity> implements IService<DTO> {

    private final AbstractEntityRepo<ENTITY> repository;
    private final ICrudMapper<DTO, ENTITY> mapper;

    protected BaseService(AbstractEntityRepo<ENTITY> repository, ICrudMapper<DTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> findAll() {
        List<ENTITY> all = this.repository.findAll();
        return all.stream().map(mapper::toDTO).toList();
    }

    @Override
    public DTO getOne(Long id) {
        return this.repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public DTO createOne(DTO dto) {
        ENTITY entity = mapper.toEntity(dto);
        ENTITY result = this.repository.save(entity);
        return mapper.toDTO(result);
    }

    @Override
    public void deleteOne(Long id) {
        ENTITY entity = getOptionalEntity(id);
        this.repository.delete(entity);
    }

    @Override
    @Transactional
    public void updateOne(Long id, DTO dto) {
        ENTITY entity = getOptionalEntity(id);
        this.repository.save(mapper.updateEntity(entity, dto));
    }

    private ENTITY getOptionalEntity(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity with the id %s was not found...".formatted(id)));
    }
}
