package Vaistra.Managment.MasterMines.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterMines.Dao.Equipment;
import Vaistra.Managment.MasterMines.Dao.Equipment;
import Vaistra.Managment.MasterMines.Dto.EquipmentDto;
import Vaistra.Managment.MasterMines.Repository.EquipmentRepo;
import Vaistra.Managment.MasterMines.Service.EquipmentService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final AppUtils appUtils;
    private final EquipmentRepo equipmentRepo;
    public EquipmentServiceImpl(AppUtils appUtils, EquipmentRepo equipmentRepo) {
        this.appUtils = appUtils;
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    public EquipmentDto addEquipment(EquipmentDto equipmentDto) {
        if(equipmentRepo.existsByEquipment(equipmentDto.getEquipment()))
            throw new DuplicateEntryException("Equipment with name"+equipmentDto.getEquipment()+"is already exists! ");
        Equipment equ=new Equipment();
        equ.setEquipment(equipmentDto.getEquipment());
        return  appUtils.equipmentToDto(equipmentRepo.save(equ));
    }

    @Override
    public EquipmentDto getEquipmentById(int id) {
        return appUtils.equipmentToDto(equipmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment with id '" + id + "' Not Found!")));
    }

    @Override
    public EquipmentDto updateEquipment(EquipmentDto equipmentDto, int id) {
        Equipment equipment= equipmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment with Id '" + id + "' not found!"));


        // HANDLE DUPLICATE ENTRY EXCEPTION
        Equipment existedEquipment = equipmentRepo.findByEquipment(equipmentDto.getEquipment());
        if(existedEquipment != null)
            throw new DuplicateEntryException("Equipment with name '"+equipmentDto.getEquipment()+"' already exist!");

        equipment.setEquipment(equipmentDto.getEquipment().toUpperCase());



        return appUtils.equipmentToDto(equipmentRepo.save(equipment));
    }

    @Override
    public String deleteEquipmentById(int id) {
        equipmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipment with Id '" + id + "' not found!"));

        equipmentRepo.deleteById(id);
        return "Equipment with Id '" + id + "' deleted";
    }

    @Override
    public List<EquipmentDto> getAllEquipment(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Equipment> pageEquipment = equipmentRepo.findAll(pageable);
        return appUtils.equipmentToDtos(pageEquipment.getContent());
    }
}
