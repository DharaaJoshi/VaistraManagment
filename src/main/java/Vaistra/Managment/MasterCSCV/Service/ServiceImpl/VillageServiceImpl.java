package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.*;
import Vaistra.Managment.MasterCSCV.Dto.SubDistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.VillageDto;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.repo.*;
import Vaistra.Managment.MasterCSCV.Service.VillageService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageServiceImpl implements VillageService {
    private final AppUtils appUtils;
    private final SubDistrictRepo subDistrictRepo;
    private final VillageRepo villageRepo;

    private final StateRepo stateRepo;
    private final CountryRepo countryRepo;

private  final DistrictRepo districtRepo;


    public VillageServiceImpl(AppUtils appUtils, SubDistrictRepo subDistrictRepo, VillageRepo villageRepo, StateRepo stateRepo, CountryRepo countryRepo, DistrictRepo districtRepo) {
        this.appUtils = appUtils;
        this.subDistrictRepo = subDistrictRepo;
        this.villageRepo = villageRepo;

        this.stateRepo = stateRepo;

        this.countryRepo = countryRepo;
        this.districtRepo = districtRepo;
    }

    @Override
    public VillageDto addVillage(VillageDto villageDto) {
        villageDto.setVillageName(villageDto.getVillageName().trim().toUpperCase());

        if (villageRepo.existsByvillageName(villageDto.getVillageName()))
            throw new DuplicateEntryException("Village with name '" + villageDto.getVillageName() + "' already exist!");


        SubDistrict subDis = subDistrictRepo.findById(villageDto.getSubDistrictId())
                .orElseThrow(() -> new ResourceNotFoundException("sub District with Id '" + villageDto.getSubDistrictId()+ " not found!"));



        Village village = new Village();
        village.setVillageName(villageDto.getVillageName());
        village.setStatus(true);
        village.setSubDistrict(subDis);
        village.setDistrict(subDis.getDistrict());
        village.setState(subDis.getState());
        village.setCountry(subDis.getCountry());


        return appUtils.villageDto(villageRepo.save(village));
    }
    public VillageDto updateVillage(VillageDto villageDto, int id) {


        villageDto.setVillageName(villageDto.getVillageName().trim().toUpperCase());

         Village village= villageRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("village  with id '"+id+"' not found!"));

        if(villageRepo.existsByVillageName(villageDto.getVillageName()))
            throw new DuplicateEntryException("Village with name '"+villageDto.getVillageName()+"' already exist!");

        State state = stateRepo.findById(villageDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("State not found with given id: " + villageDto.getStateId()));
        Country country = countryRepo.findById(villageDto.getId()).orElseThrow(()->new ResourceNotFoundException("Country not found with given id: " + villageDto.getId()));
        District district = districtRepo.findById(villageDto.getDistrictId()).orElseThrow(()->new ResourceNotFoundException("District not found with given id: " + villageDto.getDistrictId()));
        SubDistrict subDistrict = subDistrictRepo.findById(villageDto.getSubDistrictId()).orElseThrow(()->new ResourceNotFoundException("SubDistrict not found with given id: " + villageDto.getSubDistrictName()));

        village.setVillageName(villageDto.getVillageName().trim());
        village.setStatus(true);
        village.setSubDistrict(subDistrict);
        village.setDistrict(district);
        village.setState(state);
        village.setCountry(country);

        return appUtils.villageDto(villageRepo.save(village));
    }
    @Override
    public List<VillageDto> getAllVillage(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Village> pageDistrict = villageRepo.findAll(pageable);
        return appUtils.villageToDtos(pageDistrict.getContent());
    }

}