package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.VillageDto;

import java.util.List;

public interface VillageService {
    VillageDto addVillage(VillageDto villageDto);

    List<VillageDto> getAllVillage(int pageNumber, int pageSize, String sortBy, String sortDirection);
}
