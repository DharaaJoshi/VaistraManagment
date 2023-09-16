package Vaistra.Managment.MasterMines.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterMines.Dao.Designation;
import Vaistra.Managment.MasterMines.Dao.entity;
import Vaistra.Managment.MasterMines.Dto.DesignationDto;
import Vaistra.Managment.MasterMines.Repository.DesignationRepo;
import Vaistra.Managment.MasterMines.Service.DesignationService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DesignationServiceImpl implements DesignationService {

private final AppUtils appUtils;
private final DesignationRepo designationRepo;


    public DesignationServiceImpl(AppUtils appUtils, DesignationRepo designationRepo) {
        this.appUtils = appUtils;
        this.designationRepo = designationRepo;
    }

    @Override
    public DesignationDto addDesignation(DesignationDto designationDto) {
        if(designationRepo.existsByTypeOfDesignation(designationDto.getTypeOfDesignation()))
            throw new DuplicateEntryException("Designation with type"+designationDto.getTypeOfDesignation()+"already exist.");
        Designation dg=new Designation();
        dg.setTypeOfDesignation(designationDto.getTypeOfDesignation());

        return appUtils.designationToDto(designationRepo.save(dg));

    }

    @Override
    public DesignationDto getDesignationById(int id) {
        return appUtils.designationToDto(designationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Designation with id '" + id + "' Not Found!")));
    }

    @Override
    public DesignationDto updateDesignation(DesignationDto designationDto, int id) {

        Designation dg= designationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Designation with Id '" + id + "' not found!"));


        // HANDLE DUPLICATE ENTRY EXCEPTION
        Designation existedTypeOfDesignation = designationRepo.findByTypeOfDesignation(designationDto.getTypeOfDesignation());
        if(existedTypeOfDesignation != null)
            throw new DuplicateEntryException("Designation with type '"+designationDto.getTypeOfDesignation()+"' already exist!");

        dg.setTypeOfDesignation(designationDto.getTypeOfDesignation().toUpperCase());



        return appUtils.designationToDto(designationRepo.save(dg));
    }

    @Override
    public String deleteDesignationById(int id) {
        designationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Designation with Id '" + id + "' not found!"));

        designationRepo.deleteById(id);
        return "Designation with Id '" + id + "' deleted";
    }

    @Override
    public List<DesignationDto> getAllDesignation(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Designation> pageDesignation = designationRepo.findAll(pageable);
        return appUtils.designationToDtos(pageDesignation.getContent());
    }
}


