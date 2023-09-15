package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dao.District;
import Vaistra.Managment.MasterCSCV.Dao.State;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.InactiveStatusException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.Service.DistrictService;
import Vaistra.Managment.MasterCSCV.repo.DistrictRepo;
import Vaistra.Managment.MasterCSCV.repo.StateRepo;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {



    private final AppUtils appUtils;
    private final DistrictRepo districtRepo;
    private final StateRepo stateRepo;
    public DistrictServiceImpl(AppUtils appUtils, StateRepo stateRepo, DistrictRepo DistrictRepo, DistrictRepo districtRepo) {
        this.appUtils = appUtils;
        this.stateRepo = stateRepo;

        this.districtRepo = districtRepo;
    }

    @Override
    public DistrictDto addDistrict(DistrictDto districtDto) {
        districtDto.setDistrictName(districtDto.getDistrictName().trim().toUpperCase());

        if(districtRepo.existsByDistrictName(districtDto.getDistrictName()))
            throw new DuplicateEntryException("District with name '"+districtDto.getDistrictName()+"' already exist!");



        State state = stateRepo.findById(districtDto.getStateId())
                .orElseThrow(()->new ResourceNotFoundException("State with Id '"+districtDto.getStateId()+" not found!"));
        if(!state.isStatus())
            throw new InactiveStatusException("State with id '"+districtDto.getStateId()+"' is not active!");


        District district = new District();
        district.setDistrictName(districtDto.getDistrictName());
        district.setState(state);

          district.setStatus(true);

        return appUtils.districtToDto(districtRepo.save(district));
    }

    @Override
    public DistrictDto getDistrictById(int id) {
        return appUtils.districtToDto(districtRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("District with id '"+id+"' not found!")));
    }
    @Override
    public List<DistrictDto> getAllDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<District> pageDistrict = districtRepo.findAll(pageable);
        return appUtils.districtsToDtos(pageDistrict.getContent());
    }
    @Override
    public List<DistrictDto> getAllDistrictsByActiveState(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<District> pageDistrict = districtRepo.findAllByState_Status(true, pageable);
        return appUtils.districtsToDtos(pageDistrict.getContent());
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto, int id) {

        districtDto.setDistrictName(districtDto.getDistrictName().trim().toUpperCase());
        // HANDLE IF DISTRICT EXIST BY ID
        District district = districtRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("District with id '"+id+"' not found!"));

        if(districtRepo.existsByDistrictName(districtDto.getDistrictName()))
            throw new DuplicateEntryException("District with name '"+districtDto.getDistrictName()+"' already exist!");

        district.setDistrictName(districtDto.getDistrictName());

        return appUtils.districtToDto(districtRepo.save(district));
    }

    @Override
    public String deleteDistrictById(int id) {
        districtRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("District with id '"+id+"' not found!"));
        districtRepo.deleteById(id);
        return "District with id '"+id+"' deleted!";
    }


    @Override
    public List<DistrictDto> getDistrictsByStateId(int stateId) {

        stateRepo.findById(stateId).
                orElseThrow(()->new ResourceNotFoundException("State with id '"+stateId+"' not found!"));
        return appUtils.districtsToDtos(districtRepo.findByState_StateId(stateId));
    }

//    @Override
//    public List<DistrictDto> getDistrictsByCountryId(int countryId) {
//        countryRepo.findById(countryId)
//                .orElseThrow(()->new ResourceNotFoundException("Country with id '"+countryId+"' not found!"));
//        return appUtils.districtsToDtos(districtRepository.findByState_Country_CountryId(countryId));
//    }


}
