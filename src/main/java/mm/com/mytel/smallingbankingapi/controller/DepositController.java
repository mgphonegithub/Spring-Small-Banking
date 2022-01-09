package mm.com.mytel.smallingbankingapi.controller;

import mm.com.mytel.smallingbankingapi.controller.request.DepositRequest;
import mm.com.mytel.smallingbankingapi.service.DepositService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/transaction")

public class DepositController {
    final
    DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request) throws Exception {
        return depositService.onDeposit(request);
    }
}
