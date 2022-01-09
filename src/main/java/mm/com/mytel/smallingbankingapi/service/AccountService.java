package mm.com.mytel.smallingbankingapi.service;

import mm.com.mytel.smallingbankingapi.controller.request.AccountUpdateRequest;
import mm.com.mytel.smallingbankingapi.controller.request.FindAccountRequest;
import mm.com.mytel.smallingbankingapi.model.Account;
import mm.com.mytel.smallingbankingapi.repository.IAccountRepository;
import mm.com.mytel.smallingbankingapi.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    IAccountRepository iAccountRepository;

    public Integer countBalance() {
        return iAccountRepository.queryByCountBalance();
    }

    public Double calculateTotalMoney() {
        return iAccountRepository.queryByCalculateTotalMoney();
    }

    public ResponseEntity<?> onUpdate(AccountUpdateRequest updateRequest) throws  Exception{
        if ( updateRequest.getAccountId()==null || (updateRequest.getName()==null && updateRequest.getPhone()==null)){
            BadResponse badResponse=new BadResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badResponse);
        }
            Account account = iAccountRepository.findById(updateRequest.getAccountId())
                    .orElseThrow(Exception::new);

            if (updateRequest.getName()==null){
                account.setName(account.getName());
                account.setPhone(updateRequest.getPhone());
            }
            if (updateRequest.getPhone()==null){
                account.setPhone(account.getPhone());
                account.setName(updateRequest.getName());
            }
            else   {
                account.setName(updateRequest.getName());
                account.setPhone(updateRequest.getPhone());
            }
            RMForUpdateAccount rmForUpdateAccount = new RMForUpdateAccount();
            rmForUpdateAccount.setAvailableBalance(account.getAvailableBalance());
            rmForUpdateAccount.setUpdatedAt(account.getUpdatedAt());
            rmForUpdateAccount.setName(account.getName());
            rmForUpdateAccount.setPhone(account.getPhone());

            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setCode("200");
            baseResponse.setMessage("Successfully updated");
            baseResponse.setResult(rmForUpdateAccount);
            baseResponse.setSuccess(true);
            return  ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    public ResponseEntity<?> onSearch (FindAccountRequest findAccountRequest) throws Exception{
        if (findAccountRequest.getAccountId()==null){
            BadResponse baseResponse=new BadResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
        }
        Account account=iAccountRepository.findById(findAccountRequest.getAccountId()).orElseThrow(Exception::new);
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Success");
        baseResponse.setResult(account);
        baseResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }
    public ResponseEntity<?> onDelete (int accountId) throws Exception{
        Account account = iAccountRepository.findById(Long.valueOf(accountId))
                .orElseThrow(Exception::new);
        iAccountRepository.delete(account);

        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Successfully Deleted");
        baseResponse.setResult("Account "+account.getName() + " is successfully deleted");
        baseResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        //should i use reqBody instead of path variable?
    }

    public ResponseEntity<?> totalActive () {
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Successfully fetched");
        baseResponse.setResult(iAccountRepository.findByAvailableBalanceGreaterThan(0.0));
        baseResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    public ResponseEntity<?> getAll () {
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Successfully fetched");
        baseResponse.setResult(iAccountRepository.findAll());
        baseResponse.setSuccess(true);
        return  ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }
    public ResponseEntity<?> totalAccounts (){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Successfully fetched");
        ReponseModelForTotalAccounts reponseModelForTotalAccounts = new ReponseModelForTotalAccounts();
        reponseModelForTotalAccounts.setTotalAccountsWithMoreThanZeroBalance(this.countBalance().toString());
        baseResponse.setResult(reponseModelForTotalAccounts);
        baseResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    public ResponseEntity<?> totalEmoney (){
        ResponseModelForTotalEmoney responseModelForTotalEmoney =new ResponseModelForTotalEmoney();
        responseModelForTotalEmoney.setTotalEmoney(this.calculateTotalMoney().toString());
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Successfully fetched");
        baseResponse.setResult(responseModelForTotalEmoney);
        baseResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

}
