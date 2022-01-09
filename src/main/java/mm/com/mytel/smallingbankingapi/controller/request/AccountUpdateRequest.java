package mm.com.mytel.smallingbankingapi.controller.request;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class AccountUpdateRequest {
    private Long accountId;
    private String name;
    private String phone;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    @NonNull
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    @NonNull
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
