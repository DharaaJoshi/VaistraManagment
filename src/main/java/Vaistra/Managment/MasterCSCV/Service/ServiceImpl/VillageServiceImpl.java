package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.SubDistrict;
import Vaistra.Managment.MasterCSCV.Dao.Village;
import Vaistra.Managment.MasterCSCV.Dto.VillageDto;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.InactiveStatusException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.repo.SubDistrictRepo;
import Vaistra.Managment.MasterCSCV.repo.VillageRepo;
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

    public VillageServiceImpl(AppUtils appUtils, SubDistrictRepo subDistrictRepo, VillageRepo villageRepo) {
        this.appUtils = appUtils;
        this.subDistrictRepo = subDistrictRepo;
        this.villageRepo = villageRepo;
    }

    @Override
    public VillageDto addVillage(VillageDto villageDto) {
        villageDto.setVillageName(villageDto.getVillageName().trim().toUpperCase());

        if (villageRepo.existsByvillageName(villageDto.getVillageName()))
            throw new DuplicateEntryException("Village with name '" + villageDto.getVillageName() + "' already exist!");


        SubDistrict subdis = subDistrictRepo.findById(villageDto.getSubdistrictId())
                .orElseThrow(() -> new ResourceNotFoundException("District with Id '" + villageDto.getSubdistrictId() + " not found!"));
        if (!subdis.isStatus())
            throw new InactiveStatusException("District with id '" + villageDto.getVillageName() + "' is not active!");


        Village village = new Village();
        village.setVillageName(villageDto.getVillageName());
        village.setSdistrict(subdis);
        village.setStatus(true);

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