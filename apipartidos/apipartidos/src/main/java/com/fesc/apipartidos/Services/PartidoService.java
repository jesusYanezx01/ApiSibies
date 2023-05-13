package com.fesc.apipartidos.Services;

import java.util.ArrayList;
import java.util.List;
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
        partidoEntity.setGolesLocal("0");
        partidoEntity.setGolesVisitante("0");
        partidoEntity.setUsuarioEntity(usuarioEntity);
        partidoEntity.setEquipoEntityLocal(equipoEntityLocal);
        partidoEntity.setEquipoEntityVisitante(equipoEntityVisitante);

        PartidoEntity partidoEntityCreado = iPartidoRespositoy.save(partidoEntity);

        PartidoDto partidoDto = modelMapper.map(partidoEntityCreado, PartidoDto.class);





        return partidoDto;
    }

    @Override
    public List<PartidoDto> partidosCreados() {
        
        
        List<PartidoEntity> partidoEntityList= iPartidoRespositoy.partidosCreados(); 

        //instanciamos una arrayList
        List<PartidoDto> partidoDtoList = new ArrayList<>(); 

        for (PartidoEntity partidoEntity : partidoEntityList) {
            PartidoDto partidoDto = modelMapper.map(partidoEntity, PartidoDto.class);
            partidoDtoList.add(partidoDto);
            
        }

        return partidoDtoList; 
    }

    @Override
    public PartidoDto detallePartido(String id) {

        PartidoEntity partidoEntity = iPartidoRespositoy.findByIdPartido(id); 

        PartidoDto partidoDto = modelMapper.map(partidoEntity, PartidoDto.class);


        return partidoDto;
    }

    @Override
    public PartidoDto actualizarPartido(String idpartido, PartidoDto partidoActualizarDto) {

        PartidoEntity partidoEntity= iPartidoRespositoy.findByIdPartido(idpartido);

        UsuarioEntity usuarioEntity= iusuarioRepository.findByUsername(partidoActualizarDto.getUsername());

        if(partidoEntity.getUsuarioEntity().getId() != usuarioEntity.getId()){
            throw new RuntimeException( "No tienes permisos para actualizar este partido");
        }

        //solo actualizara los goles
        //en caso de querer actualizar otra cosa, se puede setear llamando el atributo a actualizar
        partidoEntity.setGolesLocal(partidoActualizarDto.getGolesLocal());
        partidoEntity.setGolesVisitante(partidoActualizarDto.getGolesVisitante());

        PartidoEntity partidoEntityActualizado= iPartidoRespositoy.save(partidoEntity);

        PartidoDto partidoDto= modelMapper.map(partidoEntityActualizado,PartidoDto.class);

        return partidoDto; 
    }

    @Override
    public void eliminarPartido(String idPartido, long idUsuario) {

        PartidoEntity partidoEntity= iPartidoRespositoy.findByIdPartido(idPartido);

        if(partidoEntity.getUsuarioEntity().getId() != idUsuario){
            throw new RuntimeException( "No tienes permisos para eliminar este partido");
        }

        iPartidoRespositoy.delete(partidoEntity);

        
    }
    
}
