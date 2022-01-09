package mm.com.mytel.smallingbankingapi.controller;

import mm.com.mytel.smallingbankingapi.controller.request.TransferRequest;
import mm.com.mytel.smallingbankingapi.service.TransferService;
import mm.com.mytel.smallingbankingapi.service.TransferServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/transaction")
public class TransferController {
    final TransferServiceV2 transferService;

    public TransferController(TransferServiceV2 transferService){
        this.transferService=transferService;
    }

    @PostMapping("transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) throws Exception {
        return transferService.onTransfer(request);
    }

}
