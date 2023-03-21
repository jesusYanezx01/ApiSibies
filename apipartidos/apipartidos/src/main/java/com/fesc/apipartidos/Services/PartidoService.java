package com.fesc.apipartidos.Services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fesc.apipartidos.data.entidades.EquipoEntity;
import com.fesc.apipartidos.data.entidades.PartidoEntity;
import com.fesc.apipartidos.data.entidades.UsuarioEntity;
import com.fesc.apipartidos.data.repositorios.IEquipoRespository;
import com.fesc.apipartidos.data.repositorios.IPartidoRespositoy;
import com.fesc.apipartidos.data.repositorios.IUsuarioRepository;
import com.fesc.apipartidos.shared.PartidoDto;

@Service //En los servicios llamamos a los respositorios
public class PartidoService implements IPartidoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioRepository iusuarioRepository;

    @Autowired
    IPartidoRespositoy iPartidoRespositoy;

    @Autowired
    IEquipoRespository iEquipoRespository;

    @Override
    public PartidoDto crearPartido(PartidoDto partidoCrearDto) {

        //en la base de datos quiero guardar informacion
        //Comenzamos guardando en la base de datos nuestra entidad
        UsuarioEntity usuarioEntity = iusuarioRepository.findByUsername(partidoCrearDto.getUsername());
        EquipoEntity equipoEntityLocal = iEquipoRespository.findById(partidoCrearDto.getEquipoLocal());
        EquipoEntity equipoEntityVisitante = iEquipoRespository.findById(partidoCrearDto.getEquipoVisitante());

        PartidoEntity partidoEntity = new PartidoEntity();
        partidoEntity.setIdPartido(UUID.randomUUID().toString());
        partidoEntity.setFecha(partidoCrearDto.getFecha());
        partidoEntity.setGolesLoscal("0");
        partidoEntity.setGolesVisitante("0");
        partidoEntity.setUsuarioEntity(usuarioEntity);
        partidoEntity.setEquipoEntityLocal(equipoEntityLocal);
        partidoEntity.setEquipoEntityVisitante(equipoEntityVisitante);

        PartidoEntity partidoEntityCreado = iPartidoRespositoy.save(partidoEntity);

        PartidoDto partidoDto = modelMapper.map(partidoEntityCreado, PartidoDto.class);





        return partidoDto;
    }
    
}
