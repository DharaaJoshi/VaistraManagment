package Vaistra.Managment.MasterMines.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterMines.Dao.Mineral;
import Vaistra.Managment.MasterMines.Dto.MineralDto;
import Vaistra.Managment.MasterMines.Repository.MineralRepo;
import Vaistra.Managment.MasterMines.Service.MineralService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MineralServiceImpl implements MineralService {
    public MineralServiceImpl(AppUtils appUtils, MineralRepo mineralRepo) {
        this.appUtils = appUtils;
        this.mineralRepo = mineralRepo;
    }

    private final AppUtils appUtils;
    private final MineralRepo mineralRepo;
    @Override
    public MineralDto addMineral(MineralDto mineralDto) {

        if(mineralRepo.existsByMineralName(mineralDto.getMineralName()))
            throw new DuplicateEntryException("Mineral with name '"+mineralDto.getMineralName()+"' already exist!");

        Mineral mineral = new Mineral();
        mineral.setMineralName(mineralDto.getMineralName());
        mineral.setCategory(mineralDto.getCategory());
        mineral.setGrade(mineralDto.getGrade());
        mineral.setHsnCode(mineralDto.getHsnCode());
        mineral.setAtrName(mineralDto.getAtrName());


        return appUtils.mineralToDto(mineralRepo.save(mineral));
    }

    public MineralDto getMineralById (int id){
        return appUtils.mineralToDto(mineralRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mineral with id '" + id + "' Not Found!")));
    }

    @Override
    public List<MineralDto> getMineralByName(int pageNumber, int pageSize, String sortBy, String SortDirection) {
        return null;
    }

    @Override
    public MineralDto updateMineral(MineralDto mineralDto, int id) {
        Mineral mineral = mineralRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mineral with Id '" + id + "' not found!"));


        // HANDLE DUPLICATE ENTRY EXCEPTION
        Mineral existedMineral = mineralRepo.findByMineralName(mineralDto.getMineralName());
        if(existedMineral != null)
            throw new DuplicateEntryException("Mineral with name '"+mineralDto.getMineralName()+"' already exist!");



        mineral.setMineralName(mineralDto.getMineralName().toUpperCase());

        mineral.setCategory(mineralDto.getCategory());
        mineral.setGrade(mineralDto.getGrade());
        mineral.setHsnCode(mineralDto.getHsnCode());
        mineral.setAtrName(mineralDto.getAtrName());


        return appUtils.mineralToDto(mineralRepo.save(mineral));

    }

    @Override
    public String deleteMineralById(int id) {
        mineralRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("mineral with Id '" + id + "' not found!"));

        mineralRepo.deleteById(id);
        return "Mineral with Id '" + id + "' deleted";
    }

    @Override
    public List<MineralDto> getAllMineral(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Mineral> pageMineral = mineralRepo.findAll(pageable);
        return appUtils.mineralToDtos(pageMineral.getContent());
    }
//    @Override
//    public HttpResponse searchMineral(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection) {
//        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
//                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//
//        Page<Country> pageMineral = mineralRepo.findByMineralNameContainingIgnoreCase(keyword, pageable);
//        List<MineralDto> mineral = appUtils.mineralToDtos(pageMineral.getContent());
//        return HttpResponse.builder()
//                .pageNumber(pageMineral.getNumber())
//                .pageSize(pageMineral.getSize())
//                .totalElements(pageMineral.getTotalElements())
//                .totalPages(pageMineral.getTotalPages())
//                .isLastPage(pageMineral.isLast())
//                .data(mineral)
//                .build();
//    }

}
