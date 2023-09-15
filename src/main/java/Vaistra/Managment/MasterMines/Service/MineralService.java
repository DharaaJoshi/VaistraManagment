package Vaistra.Managment.MasterMines.Service;

import Vaistra.Managment.MasterMines.Dto.MineralDto;
import Vaistra.Managment.MasterMines.Repository.MineralRepo;

import java.util.List;

public interface MineralService {

    public MineralDto addMineral(MineralDto mineralDto);


    public MineralDto getMineralById(int id);

    public List<MineralDto> getMineralByName(int pageNumber, int pageSize, String sortBy, String SortDirection);

    public MineralDto updateMineral(MineralDto mineral, int id);

    public String deleteMineralById(int id);


    List<MineralDto> getAllMineral(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}










}
