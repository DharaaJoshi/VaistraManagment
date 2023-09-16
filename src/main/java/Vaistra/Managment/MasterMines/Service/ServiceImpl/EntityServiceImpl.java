package Vaistra.Managment.MasterMines.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterMines.Dao.Mineral;
import Vaistra.Managment.MasterMines.Dao.entity;
import Vaistra.Managment.MasterMines.Dto.EntityDto;
import Vaistra.Managment.MasterMines.Dto.MineralDto;
import Vaistra.Managment.MasterMines.Repository.EntityRepo;
import Vaistra.Managment.MasterMines.Service.EntityService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    public EntityServiceImpl(AppUtils appUtils, EntityRepo entityRepo) {
        this.appUtils = appUtils;
        this.entityRepo = entityRepo;
    }

    private final AppUtils appUtils;
    private final EntityRepo entityRepo;


    @Override
    public EntityDto addEntity(EntityDto entityDto) {
        if(entityRepo.existsByEntityType(entityDto.getEntityType()))
            throw new DuplicateEntryException("Entity with name '"+entityDto.getEntityType()+"' already exist!");

        entity entity= new entity();
        entity.setEntityType(entityDto.getEntityType());
        entity.setShortName(entityDto.getShortName());

        return appUtils.EntityToDto(entityRepo.save(entity));
    }

    @Override
    public EntityDto getEntityById(int id) {
        return appUtils.EntityToDto(entityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity with id '" + id + "' Not Found!")));    }


    @Override
    public EntityDto updateEntity(EntityDto entityDto, int id) {
        entity ent = entityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity with Id '" + id + "' not found!"));


        // HANDLE DUPLICATE ENTRY EXCEPTION
        entity existedEntityType = entityRepo.findByEntityType(entityDto.getEntityType());
        if(existedEntityType != null)
            throw new DuplicateEntryException("Entity with name '"+entityDto.getEntityType()+"' already exist!");

        ent.setEntityType(entityDto.getEntityType().toUpperCase());

        ent.setShortName(entityDto.getShortName());

        return appUtils.EntityToDto(entityRepo.save(ent));
    }

    @Override
    public String deleteEntityById(int id) {
        entityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity with Id '" + id + "' not found!"));

        entityRepo.deleteById(id);
        return "Entity with Id '" + id + "' deleted";
    }

    @Override
    public List<EntityDto> getAllEntity(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<entity> pageEntity = entityRepo.findAll(pageable);
        return appUtils.entityToDtos(pageEntity.getContent());
    }
}
