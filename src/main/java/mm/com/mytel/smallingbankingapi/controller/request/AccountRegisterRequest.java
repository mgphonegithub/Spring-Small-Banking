package mm.com.mytel.smallingbankingapi.controller.request;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class AccountRegisterRequest {

    private String name;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
