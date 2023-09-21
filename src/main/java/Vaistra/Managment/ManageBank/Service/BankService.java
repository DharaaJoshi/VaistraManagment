package Vaistra.Managment.ManageBank.Service;

import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Service.ServiceImpl.FileSizeExceedException;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BankService {
    public BankDto addBank(BankDto bankDto/*MultipartFile file*/) throws IOException, FileSizeExceedException;



    public BankDto updateBank(Integer bankId, BankDto bankDto, MultipartFile file) throws IOException;

    public String deleteBank(Integer bankId);

    public HttpResponse getBank(int pageNo, int pageSize, String sortBy, String sortDirection);

    public HttpResponse getBankByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword);

//    public byte[] getBankLogo(Integer bankId);

    byte[] getBankLogo(Integer bankId);

    public List<BankDto> getAllActiveBank();
}
