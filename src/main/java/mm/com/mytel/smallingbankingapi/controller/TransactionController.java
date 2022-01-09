package mm.com.mytel.smallingbankingapi.controller;

import mm.com.mytel.smallingbankingapi.controller.request.DepositRequest;
import mm.com.mytel.smallingbankingapi.repository.ITransactionRepository;
import mm.com.mytel.smallingbankingapi.response.BaseResponse;
import mm.com.mytel.smallingbankingapi.response.NormalBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/transaction")
public class TransactionController {

    @Autowired
    ITransactionRepository iTransactionRepository;

    @GetMapping("all")
    public ResponseEntity<?> getAllTransactions(){
        NormalBaseResponse baseResponse=new NormalBaseResponse("Successfully Fetched",iTransactionRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);

    }

}
