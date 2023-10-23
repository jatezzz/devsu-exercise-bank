package com.devsu.account.service;

import java.util.List;

public interface IService<DTO> {

    List<DTO> findAll();

    DTO getOne(Long id);

    DTO createOne(DTO dto);

    void deleteOne(Long id);

    void updateOne(Long id, DTO dto);
}
