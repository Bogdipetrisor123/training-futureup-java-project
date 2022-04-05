package ro.zynk.futureup.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ro.zynk.futureup.domain.dtos.Transaction;

import java.util.List;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findAll();
    List<Transaction> findAllByTotalValueGreaterThan(Double value);
}
