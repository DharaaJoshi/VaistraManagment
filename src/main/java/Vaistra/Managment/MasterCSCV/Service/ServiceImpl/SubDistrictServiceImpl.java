package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dao.State;
import Vaistra.Managment.MasterCSCV.Dao.SubDistrict;
import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.SubDistrictDto;
import Vaistra.Managment.MasterCSCV.Dao.District;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.repo.CountryRepo;
import Vaistra.Managment.MasterCSCV.repo.DistrictRepo;
import Vaistra.Managment.MasterCSCV.repo.StateRepo;
import Vaistra.Managment.MasterCSCV.repo.SubDistrictRepo;
import Vaistra.Managment.MasterCSCV.Service.SubDistrictService;
import Vaistra.Managment.Utils.AppUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

@Service
public class SubDistrictServiceImpl implements SubDistrictService {


    private final AppUtils appUtils;
    private final SubDistrictRepo subDistrictRepo;
    private final DistrictRepo districtRepo;
    private final CountryRepo countryRepo;
    private final StateRepo stateRepo;
    public SubDistrictServiceImpl(AppUtils appUtils, StateRepo stateRepo, DistrictRepo DistrictRepo, SubDistrictRepo subDistrictRepo, DistrictRepo districtRepo, CountryRepo countryRepo) {
        this.appUtils = appUtils;
        this.stateRepo = stateRepo;
        this.subDistrictRepo = subDistrictRepo;
        this.countryRepo = countryRepo;


        this.districtRepo = districtRepo;
    }
    @Override
    public SubDistrictDto addSubDistrict(SubDistrictDto subDistrictDto) {
        subDistrictDto.setSubDistrictName(subDistrictDto.getSubDistrictName().trim().toUpperCase());

        if(subDistrictRepo.existsBySubDistrictName(subDistrictDto.getSubDistrictName()))
            throw new DuplicateEntryException("District with name '"+subDistrictDto.getSubDistrictName()+"' already exist!");



        District dis= districtRepo.findById(subDistrictDto.getDistrictId())
                .orElseThrow(()->new ResourceNotFoundException("District with Id '"+subDistrictDto.getDistrictId()+" not found!"));



        SubDistrict subdis = new SubDistrict();
        subdis.setSubDistrictName(subDistrictDto.getSubDistrictName());
        subdis.setDistrict(dis);
        subdis.setState(dis.getState());
        subdis.setCountry(dis.getCountry());
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
    public SubDistrictDto updateSubDistrict(SubDistrictDto subDistrictDto, int id) {


        subDistrictDto.setDistrictName(subDistrictDto.getSubDistrictName().trim().toUpperCase());
        // HANDLE IF DISTRICT EXIST BY ID
        SubDistrict subDistrict = subDistrictRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("SubDistrict with id '"+id+"' not found!"));

        if(subDistrictRepo.existsBySubDistrictName(subDistrictDto.getSubDistrictName()))
            throw new DuplicateEntryException("SubDistrict with name '"+subDistrictDto.getDistrictName()+"' already exist!");

        State state = stateRepo.findById(subDistrictDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("State not found with given id: " + subDistrictDto.getStateId()));
        Country country = countryRepo.findById(subDistrictDto.getId()).orElseThrow(()->new ResourceNotFoundException("Country not found with given id: " + subDistrictDto.getId()));
        District district = districtRepo.findById(subDistrictDto.getDistrictId()).orElseThrow(()->new ResourceNotFoundException("District not found with given id: " + subDistrictDto.getDistrictId()));
        subDistrict.setSubDistrictName(subDistrictDto.getSubDistrictName());


        subDistrict.setSubDistrictName(subDistrictDto.getSubDistrictName().trim());
        subDistrict.setStatus(true);
        subDistrict.setDistrict(district);
        subDistrict.setState(state);
        subDistrict.setCountry(country);
        return appUtils.subdistrictToDto(subDistrictRepo.save(subDistrict));
    }

    @Override
    public String deleteSubDistrictById(int id) {
        subDistrictRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("SubDistrict with id '"+id+"' not found!"));
        subDistrictRepo.deleteById(id);
        return "SubDistrict with id '"+id+"' deleted!";
    }

    @Override
    public HttpResponse searchSubDistrict(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<SubDistrict> pageDistrict = subDistrictRepo.findBySubDistrictNameContainingIgnoreCase(keyword, pageable);
        List<SubDistrictDto> dtos = appUtils.subdistrictsToDtos(pageDistrict.getContent());
        return HttpResponse.builder()
                .pageNumber(pageDistrict.getNumber())
                .pageSize(pageDistrict.getSize())
                .totalElements(pageDistrict.getTotalElements())
                .totalPages(pageDistrict.getTotalPages())
                .isLastPage(pageDistrict.isLast())
                .data(dtos)
                .build();
    }


}
