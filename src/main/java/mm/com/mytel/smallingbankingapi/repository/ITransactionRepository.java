package mm.com.mytel.smallingbankingapi.repository;

import mm.com.mytel.smallingbankingapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository  extends JpaRepository<Transaction,Long> {
}
