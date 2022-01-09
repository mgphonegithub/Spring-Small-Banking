package mm.com.mytel.smallingbankingapi.repository;

import mm.com.mytel.smallingbankingapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {



     List<Account> findByAvailableBalanceGreaterThan(Double amount);
     //only return list
    // count pyn phoe ka out ka lo myo Query nae yayy mha ya ml nae tu dl;

    @Query(value = "SELECT COUNT(*) FROM account where available_balance >0",nativeQuery = true)
    Integer queryByCountBalance();

    @Query(value = "SELECT SUM(available_balance) FROM account",nativeQuery = true)
    Double queryByCalculateTotalMoney();
}
