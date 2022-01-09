package mm.com.mytel.smallingbankingapi.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class NormalBaseResponse extends BaseResponse{

    public NormalBaseResponse(String message, Object result){
        this.Code="200";
        this.Message=message;
        this.Result=result;
        this.Success=true;

    }
}
