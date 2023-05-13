package com.fesc.apipartidos.data.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fesc.apipartidos.data.entidades.PartidoEntity;

public interface IPartidoRespositoy extends PagingAndSortingRepository<PartidoEntity, Long> {
    //El repositorio siempre va enviar entidades y recibir entidades
    List<PartidoEntity> getByUsuarioEntityIdOrderByCreadoDesc(long usuarioEntityId);

    //para hacer una consulta nativa
    @Query(nativeQuery = true, value = "SELECT * FROM partido ORDER BY creado DESC LIMIT 10")
    //aqui declaramos donde lo va a traer
    List<PartidoEntity> partidosCreados();

    PartidoEntity findByIdPartido(String id);
}
