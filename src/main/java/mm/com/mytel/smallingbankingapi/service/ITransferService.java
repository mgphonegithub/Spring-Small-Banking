package mm.com.mytel.smallingbankingapi.service;

import mm.com.mytel.smallingbankingapi.controller.request.TransferRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface ITransferService {
    ResponseEntity<?> onTransfer(TransferRequest request) throws Exception;
}
