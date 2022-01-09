package mm.com.mytel.smallingbankingapi.service;

import mm.com.mytel.smallingbankingapi.controller.request.DepositRequest;
import mm.com.mytel.smallingbankingapi.model.Account;
import mm.com.mytel.smallingbankingapi.model.Transaction;
import mm.com.mytel.smallingbankingapi.repository.IAccountRepository;
import mm.com.mytel.smallingbankingapi.repository.ITransactionRepository;
import mm.com.mytel.smallingbankingapi.response.BadResponse;
import mm.com.mytel.smallingbankingapi.response.BaseResponse;
import mm.com.mytel.smallingbankingapi.response.ResponseModelForDepositWithdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;


@Service
public class DepositService {
//    @Autowired
//    Transaction transaction;
    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    ITransactionRepository iTransactionRepository;

    public ResponseEntity<?> onDeposit(@RequestBody DepositRequest request) throws Exception {
        if (request.getAmount()==null || request.getAccountId()==null ){
            BadResponse badResponse=new BadResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badResponse);
        }

        Transaction transaction=new Transaction();
        transaction.setTransType(Transaction.TransactionType.Deposit.name());
        transaction.setFromAccountId(null);
        transaction.setToAccountId(request.getAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setStatus(1);
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setLastUpdatedAt(LocalDateTime.now());
        iTransactionRepository.save(transaction);

        Account account = iAccountRepository.findById(request.getAccountId())
                .orElseThrow(Exception::new);
        account.setAvailableBalance(account.getAvailableBalance()+ request.getAmount());
        iAccountRepository.save(account);

        ResponseModelForDepositWithdraw responseModelForDepositWithdraw = new ResponseModelForDepositWithdraw();
        responseModelForDepositWithdraw.setCurrentAmount(account.getAvailableBalance());
        responseModelForDepositWithdraw.setContent(request.getContent());
        responseModelForDepositWithdraw.setAccountName(account.getName());
        responseModelForDepositWithdraw.setTransactionType("Deposit");

        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMessage("Test Message");
        baseResponse.setResult(responseModelForDepositWithdraw);
        baseResponse.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }
}
