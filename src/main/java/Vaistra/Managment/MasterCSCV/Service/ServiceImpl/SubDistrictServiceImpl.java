package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.SubDistrict;
import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.SubDistrictDto;
import Vaistra.Managment.MasterCSCV.Dao.District;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.InactiveStatusException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.repo.DistrictRepo;
import Vaistra.Managment.MasterCSCV.repo.StateRepo;
import Vaistra.Managment.MasterCSCV.repo.SubDistrictRepo;
import Vaistra.Managment.MasterCSCV.Service.SubDistrictService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubDistrictServiceImpl implements SubDistrictService {


    private final AppUtils appUtils;
    private final SubDistrictRepo subDistrictRepo;
    private final DistrictRepo districtRepo;
    private final StateRepo stateRepo;
    public SubDistrictServiceImpl(AppUtils appUtils, StateRepo stateRepo, DistrictRepo DistrictRepo, SubDistrictRepo subDistrictRepo, DistrictRepo districtRepo) {
        this.appUtils = appUtils;
        this.stateRepo = stateRepo;
        this.subDistrictRepo = subDistrictRepo;

        this.districtRepo = districtRepo;
    }
    @Override
    public SubDistrictDto addSubDistrict(SubDistrictDto subDistrictDto) {
        subDistrictDto.setSubDistrictName(subDistrictDto.getSubDistrictName().trim().toUpperCase());

        if(subDistrictRepo.existsBySubDistrictName(subDistrictDto.getSubDistrictName()))
            throw new DuplicateEntryException("District with name '"+subDistrictDto.getSubDistrictName()+"' already exist!");



        District dis= districtRepo.findById(subDistrictDto.getDistrictId())
                .orElseThrow(()->new ResourceNotFoundException("District with Id '"+subDistrictDto.getDistrictId()+" not found!"));
        if(!dis.isStatus())
            throw new InactiveStatusException("District with id '"+subDistrictDto.getDistrictId()+"' is not active!");


        SubDistrict subdis = new SubDistrict();
        subdis.setSubDistrictName(subDistrictDto.getSubDistrictName());
        subdis.setDistrict(dis);

        subdis.setStatus(true);

        return appUtils.subdistrictToDto(subDistrictRepo.save(subdis));
    }

    @Override
    public SubDistrictDto getSubDistrictById(int id) {
        return null;
    }

    @Override
    public List<SubDistrictDto> getAllSubDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<SubDistrict> pageDistrict = subDistrictRepo.findAll(pageable);
        return appUtils.subdistrictsToDtos(pageDistrict.getContent());
    }

    @Override
    public List<SubDistrictDto> getAllSubDistrictsByActiveState(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public SubDistrictDto updateSubDistrict(SubDistrictDto subDistrict, int id) {
        return null;
    }

    @Override
    public String deleteSubDistrictById(int id) {
        return null;
    }

    @Override
    public List<DistrictDto> getDistrictsByStateId(int stateId) {
        return null;
    }
}
