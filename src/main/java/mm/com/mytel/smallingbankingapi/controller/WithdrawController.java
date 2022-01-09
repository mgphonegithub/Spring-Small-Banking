package mm.com.mytel.smallingbankingapi.controller;

import mm.com.mytel.smallingbankingapi.controller.request.WithdrawRequest;
import mm.com.mytel.smallingbankingapi.service.WithdrawService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/transaction")

public class WithdrawController {
    final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService){
        this.withdrawService=withdrawService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest request)throws Exception{
        return withdrawService.onWithdraw(request);
    }
}
