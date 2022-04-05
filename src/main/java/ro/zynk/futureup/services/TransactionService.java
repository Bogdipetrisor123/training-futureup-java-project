package ro.zynk.futureup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.zynk.futureup.controllers.responses.TransactionResponse;
import ro.zynk.futureup.domain.dtos.Transaction;
import ro.zynk.futureup.domain.repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<TransactionResponse> getAllTransactions() {
        var transactionList = this.transactionRepository.findAll();
        var transactionResponseList = new ArrayList<TransactionResponse>();
        for (Transaction transaction : transactionList) {
            transactionResponseList.add(new TransactionResponse(transaction));
        }

        return transactionResponseList;
    }

    public List<TransactionResponse> getAllTransactionsGreaterThan(Double value) {
        var transactionList = this.transactionRepository.findAllByTotalValueGreaterThan(value);
        var transactionResponseList = new ArrayList<TransactionResponse>();
        for (Transaction transaction : transactionList) {
            transactionResponseList.add(new TransactionResponse(transaction));
        }

        return transactionResponseList;
    }


}
