package mm.com.mytel.smallingbankingapi.service;

import mm.com.mytel.smallingbankingapi.controller.request.TransferRequest;
import mm.com.mytel.smallingbankingapi.model.Account;
import mm.com.mytel.smallingbankingapi.model.Transaction;
import mm.com.mytel.smallingbankingapi.repository.IAccountRepository;
import mm.com.mytel.smallingbankingapi.repository.ITransactionRepository;
import mm.com.mytel.smallingbankingapi.response.BadResponse;
import mm.com.mytel.smallingbankingapi.response.NormalBaseResponse;
import mm.com.mytel.smallingbankingapi.response.ResponseModelForTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferServiceV2 implements ITransferService{
    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    ITransactionRepository iTransactionRepository;

    @Override
    public ResponseEntity<?> onTransfer(TransferRequest transferRequest) throws Exception {
        if (transferRequest.getAmount()==null || transferRequest.getToAccountId()==null || transferRequest.getFromAccountId()==null || transferRequest.getFee()==null){
            BadResponse badResponse=new BadResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badResponse);
        }

        Transaction transaction=new Transaction();
        transaction.setTransType(Transaction.TransactionType.Transfer.name());
        transaction.setFromAccountId(transferRequest.getFromAccountId());
        transaction.setToAccountId(transferRequest.getToAccountId());
        transaction.setAmount(transferRequest.getAmount());
        transaction.setStatus(1);
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setLastUpdatedAt(LocalDateTime.now());
        iTransactionRepository.save(transaction);

        Account fromAccount = iAccountRepository.findById(transaction.getFromAccountId())
                .orElseThrow(Exception::new);
        Account toAccount = iAccountRepository.findById(transaction.getToAccountId())
                .orElseThrow(() -> new Exception());
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance()-transferRequest.getAmount()- transferRequest.getFee());
        toAccount.setAvailableBalance(toAccount.getAvailableBalance()+transferRequest.getAmount());
        iAccountRepository.save(fromAccount);
        iAccountRepository.save(toAccount);

        ResponseModelForTransfer responseModelForTransfer = new ResponseModelForTransfer();
        responseModelForTransfer.setTransferAmount(transferRequest.getAmount());
        responseModelForTransfer.setContent("Test Content");
        responseModelForTransfer.setTransactionType("Transfer");
        responseModelForTransfer.setFromAccountName(fromAccount.getName());
        responseModelForTransfer.setToAccountName(toAccount.getName());

        NormalBaseResponse normalBaseResponse=new NormalBaseResponse("Transfer Success",responseModelForTransfer);
        return ResponseEntity.status(HttpStatus.OK).body(normalBaseResponse);
    }
}
