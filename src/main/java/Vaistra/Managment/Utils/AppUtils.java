package Vaistra.Managment.Utils;

import Vaistra.Managment.ManageBank.Dao.Bank;
import Vaistra.Managment.ManageBank.Dao.BankBranch;
import Vaistra.Managment.ManageBank.Dto.BankBranchDto;
import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.MasterCSCV.Dao.*;
import Vaistra.Managment.MasterCSCV.Dto.*;
import Vaistra.Managment.MasterCSCV.Dao.*;
import Vaistra.Managment.MasterCSCV.Dto.*;
import Vaistra.Managment.MasterMines.Dao.*;
import Vaistra.Managment.MasterMines.Dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AppUtils {



    private final ModelMapper modelMapper;

    @Autowired
    public AppUtils(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public CountryDto countryToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }

    public Country dtoToCountry(CountryDto dto) {
        return modelMapper.map(dto, Country.class);    }

    public List<CountryDto> countriesToDtos(List<Country> countries) {
        java.lang.reflect.Type targetListType = new TypeToken<List<CountryDto>>() {}.getType();
        return modelMapper.map(countries, targetListType);
    }


    public UserDto userToDto(User user)
    {
        return modelMapper.map(user, UserDto.class);
    }
    public User dtoToUser(UserDto userDto)
    {
        return modelMapper.map(userDto, User.class);
    }

    public List<UserDto> usersToDtos(List<User> users) {
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {}.getType();
        return modelMapper.map(users, targetListType);
    }



    public StateDto StateToDto(State state) {
        return modelMapper.map(state, StateDto.class);
    }

    public State dtoToState(StateDto dto) {
        return modelMapper.map(dto, State.class);    }

    public List<StateDto> StatesToDtos(List<State> states) {
        java.lang.reflect.Type targetListType = new TypeToken<List<StateDto>>() {}.getType();
        return modelMapper.map(states, targetListType);
    }

    public DistrictDto districtToDto(District district) {
        return modelMapper.map(district, DistrictDto.class);
    }

    public District dtoToDistrict(DistrictDto dto) {
        return modelMapper.map(dto, District.class);
    }

    public List<DistrictDto> districtsToDtos(List<District> districts) {
        java.lang.reflect.Type targetListType = new TypeToken<List<DistrictDto>>() {}.getType();
        return modelMapper.map(districts, targetListType);
    }
    public SubDistrictDto subdistrictToDto(SubDistrict subdistrict) {
        return modelMapper.map(subdistrict, SubDistrictDto.class);
    }

    public SubDistrict dtoToSubDistrict(SubDistrictDto dto) {
        return modelMapper.map(dto, SubDistrict.class);
    }

    public List<SubDistrictDto> subdistrictsToDtos(List<SubDistrict> subdistricts) {
        java.lang.reflect.Type targetListType = new TypeToken<List<SubDistrictDto>>() {}.getType();
        return modelMapper.map(subdistricts, targetListType);
    }
    public VillageDto villageDto(Village village) {
        return modelMapper.map(village, VillageDto.class);
    }
    public List<VillageDto> villageToDtos(List<Village> village) {
        java.lang.reflect.Type targetListType = new TypeToken<List<VillageDto>>() {}.getType();
        return modelMapper.map(village, targetListType);
    }
    public MineralDto mineralDto(Mineral mineral) {
        return modelMapper.map(mineral, MineralDto.class);
    }
    public List<MineralDto> mineralToDtos(List<Mineral> minerals) {
        java.lang.reflect.Type targetListType = new TypeToken<List<Mineral>>() {}.getType();
        return modelMapper.map(minerals, targetListType);
    }


    public MineralDto  mineralToDto(Mineral mineral) {
        return modelMapper.map(mineral, MineralDto.class);
    }


    public EntityDto EntityToDto(entity entity) {
        return modelMapper.map(entity,EntityDto.class );

    }

    public List<EntityDto> entityToDtos(List<entity> entity) {
        java.lang.reflect.Type targetListType = new TypeToken<List<entity>>() {}.getType();
        return modelMapper.map(entity, targetListType);
    }

    public DesignationDto designationToDto(Designation dg) {
        return modelMapper.map(dg,DesignationDto.class);
    }
    public List<DesignationDto> designationToDtos(List<Designation> dg) {
        java.lang.reflect.Type targetListType = new TypeToken<List<Designation>>() {}.getType();
        return modelMapper.map(dg, targetListType);
    }

    public VehicleDto vehicleToDto(Vehicle vehicle) {
        return modelMapper.map(vehicle,VehicleDto.class);
    }
    public List<VehicleDto> vehicleToDtos(List<Vehicle> vehicle) {
        java.lang.reflect.Type targetListType = new TypeToken<List<Vehicle>>() {}.getType();
        return modelMapper.map(vehicle, targetListType);
    }

    public EquipmentDto equipmentToDto(Equipment equ) {
        return modelMapper.map(equ, EquipmentDto.class);
    }
    public List<EquipmentDto>equipmentToDtos(List<Equipment>equipment){
        java.lang.reflect.Type targetListTYpe=new TypeToken<List<Equipment>>(){}.getType();
        return modelMapper.map(equipment,targetListTYpe);
    }
    public boolean isSupportedExtension(String ext){
        int i = ext.lastIndexOf(".");

        String extension = "";

        if(i != -1){
            extension = ext.substring(i + 1);
        }

        return extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("JPG") || extension.equals("JPEG")  || extension.equals("PNG");
    }

    public BankDto bankToDto(Bank save) {
        return modelMapper.map(save, BankDto.class);
    }

    public List<BankDto> banksToDtos(List<Bank> bank) {
        java.lang.reflect.Type targetListTYpe=new TypeToken<List<Bank>>(){}.getType();
        return modelMapper.map(bank,targetListTYpe);
    }

    public BankBranchDto bankBranchToDto(BankBranch save) {
        return modelMapper.map(save, BankBranchDto.class);
    }

    public List<BankBranchDto> bankBranchesToDtos(List<BankBranch> content) {
        java.lang.reflect.Type targetListTYpe=new TypeToken<List<BankBranch>>(){}.getType();
        return modelMapper.map(content,targetListTYpe);
    }
}
