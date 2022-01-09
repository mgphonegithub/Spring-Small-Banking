package mm.com.mytel.smallingbankingapi.service;

import mm.com.mytel.smallingbankingapi.controller.request.AccountRegisterRequest;
import mm.com.mytel.smallingbankingapi.model.Account;
import mm.com.mytel.smallingbankingapi.repository.IAccountRepository;
import mm.com.mytel.smallingbankingapi.response.BadResponse;
import mm.com.mytel.smallingbankingapi.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountRegisterService {
    @Autowired
    IAccountRepository iAccountRepository;

    public ResponseEntity<?> onRegister( AccountRegisterRequest request){
        if (request.getName()==null || request.getPhone()==null){
            BadResponse badResponse=new BadResponse();
            System.out.println(badResponse.getCode());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badResponse);
        }
        else {
            Account newAcc=new Account();
            newAcc.setAvailableBalance(0);
            newAcc.setPhone(request.getPhone());
            newAcc.setName(request.getName());
            newAcc.setCreatedAt(LocalDateTime.now());
            newAcc.setUpdatedAt(LocalDateTime.now());
            iAccountRepository.save(newAcc);

            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setCode("200");
            baseResponse.setMessage("Account successfully created");
            baseResponse.setResult(request);
            baseResponse.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        }

    }
}
