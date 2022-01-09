package mm.com.mytel.smallingbankingapi.response;


public class BadResponse extends BaseResponse{
    public BadResponse() {
        this.Code="400";
        this.Message="Bad Request";
        this.Result=null;
        this.Success=false;
    }
}
