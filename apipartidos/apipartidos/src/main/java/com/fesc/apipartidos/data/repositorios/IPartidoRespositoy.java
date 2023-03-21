package com.fesc.apipartidos.data.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fesc.apipartidos.data.entidades.PartidoEntity;

public interface IPartidoRespositoy extends PagingAndSortingRepository<PartidoEntity, Long> {
    
}
