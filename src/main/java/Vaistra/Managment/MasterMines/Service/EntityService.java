package Vaistra.Managment.MasterMines.Service;

import Vaistra.Managment.MasterMines.Dto.EntityDto;

import java.util.List;

public interface EntityService {
    EntityDto addEntity(EntityDto entityDto);


    EntityDto getEntityById(int id);


    public EntityDto updateEntity(EntityDto Entity, int id);

    public String deleteEntityById(int id);


    List<EntityDto> getAllEntity(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}
