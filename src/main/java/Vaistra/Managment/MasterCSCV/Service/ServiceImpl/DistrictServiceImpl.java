package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dao.District;
import Vaistra.Managment.MasterCSCV.Dao.State;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.Service.DistrictService;
import Vaistra.Managment.MasterCSCV.repo.CountryRepo;
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

    private final CountryRepo countryRepo;
    public DistrictServiceImpl(AppUtils appUtils, StateRepo stateRepo, DistrictRepo DistrictRepo, DistrictRepo districtRepo,CountryRepo countryRepo) {
        this.appUtils = appUtils;
        this.stateRepo = stateRepo;
        this.countryRepo= countryRepo;
        this.districtRepo = districtRepo;
    }

    @Override
    public DistrictDto addDistrict(DistrictDto districtDto) {
        districtDto.setDistrictName(districtDto.getDistrictName().trim().toUpperCase());

        if(districtRepo.existsByDistrictName(districtDto.getDistrictName()))
            throw new DuplicateEntryException("District with name '"+districtDto.getDistrictName()+"' already exist!");



        State state = stateRepo.findById(districtDto.getStateId())
                .orElseThrow(()->new ResourceNotFoundException("State with Id '"+districtDto.getStateId()+" not found!"));



        District district = new District();
        district.setDistrictName(districtDto.getDistrictName());
        district.setState(state);
        district.setCountry(state.getCountry());

          district.setStatus(true);

        return appUtils.districtToDto(districtRepo.save(district));
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

        State state = stateRepo.findById(districtDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("State not found with given id: " + districtDto.getStateId()));
        Country country = countryRepo.findById(districtDto.getId()).orElseThrow(()->new ResourceNotFoundException("Country not found with given id: " + districtDto.getId()));
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

@Override
public HttpResponse getDistrict(int pageNo, int pageSize, String sortBy, String sortDirection) {
    Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
            Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

    Page<District> districtPage = districtRepo.findAllByState_Status(true,pageable);

    List<DistrictDto> districts = appUtils.districtsToDtos(districtPage.getContent());

    return HttpResponse.builder()
            .pageNumber(districtPage.getNumber())
            .pageSize(districtPage.getSize())
            .totalElements(districtPage.getTotalElements())
            .totalPages(districtPage.getTotalPages())
            .isLastPage(districtPage.isLast())
            .data(districts)
            .build();
}


    @Override
    public HttpResponse getDistrictByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, Integer.MAX_VALUE, sort);

        Integer keyword4 = null;
        Boolean keyword5 = null;


        if(keyword.equalsIgnoreCase("true"))
            keyword5 = Boolean.TRUE;
        else if (keyword.equalsIgnoreCase("false")) {
            keyword5 = Boolean.FALSE;
        }

        try {
            keyword4 = Integer.parseInt(keyword);
        } catch (NumberFormatException e) {
            keyword4 = null;
        }


        Page<District> districtPage = districtRepo.findByDistrictNameOrState_StateNameOrCountry_CountryOrDistrictId(pageable,keyword,keyword,keyword,keyword4,keyword5);

        List<DistrictDto> districts = appUtils.districtsToDtos(districtPage.getContent());

        return HttpResponse.builder()
                .pageNumber(districtPage.getNumber())
                .pageSize(districtPage.getSize())
                .totalElements(districtPage.getTotalElements())
                .totalPages(districtPage.getTotalPages())
                .isLastPage(districtPage.isLast())
                .data(districts)
                .build();
    }


}
