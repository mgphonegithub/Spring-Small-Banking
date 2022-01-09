package mm.com.mytel.smallingbankingapi.controller;

import mm.com.mytel.smallingbankingapi.controller.request.AccountRegisterRequest;
import mm.com.mytel.smallingbankingapi.controller.request.AccountUpdateRequest;
import mm.com.mytel.smallingbankingapi.controller.request.FindAccountRequest;
import mm.com.mytel.smallingbankingapi.repository.IAccountRepository;
import mm.com.mytel.smallingbankingapi.service.AccountRegisterService;
import mm.com.mytel.smallingbankingapi.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("bank/accounts")
public class AccountController {
    final
    AccountRegisterService accountRegisterService;
    IAccountRepository iAccountRepository;
    AccountService accountService;

    public AccountController(AccountRegisterService accountRegisterService,IAccountRepository iAccountRepository, AccountService accountService) {
        this.accountRegisterService = accountRegisterService;
        this.iAccountRepository = iAccountRepository;
        this.accountService=accountService;
    }

    //create account
    @PostMapping("create")
    public ResponseEntity<?> registerAccount( @RequestBody AccountRegisterRequest request){
        return accountRegisterService.onRegister(request);
    }

    //update account
    @PutMapping("update")
    public ResponseEntity<?> updateAccount(@RequestBody AccountUpdateRequest updateRequest) throws Exception{
        return accountService.onUpdate(updateRequest);
    }

    //read(show) account
    @PostMapping("show")
    public ResponseEntity<?>findAccount(@RequestBody FindAccountRequest findAccountRequest) throws Exception{
        return accountService.onSearch(findAccountRequest);
    }


    //delete acc
    @DeleteMapping("delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable int accountId) throws Exception{
        return accountService.onDelete(accountId);
    }

    //test
    @GetMapping("totalactive")
    public ResponseEntity<?> findByAvailableBalanceGreaterThan(){
        return accountService.totalActive();
    }

    //accounts
    @GetMapping("")
    public ResponseEntity<?> getAllAccounts() {
        return accountService.getAll();
    }

    //total active accounts count
    @GetMapping("totalaccounts")
    public ResponseEntity<?> getTotalActiveAccounts() {
        return accountService.totalAccounts();
    }

    //total emoney
    @GetMapping("/totalmoney")
    public ResponseEntity<?> calculateTotalMoney(){
        return accountService.totalEmoney();
    }

}
