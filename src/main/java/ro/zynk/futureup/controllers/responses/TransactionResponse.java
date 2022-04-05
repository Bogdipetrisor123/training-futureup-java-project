package ro.zynk.futureup.controllers.responses;

import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.Transaction;

import java.time.LocalDateTime;

public class TransactionResponse extends BaseResponse {

    private LocalDateTime transcationDate;
    private Float amount;
    private Double totalValue;
    private Coin coin;
    private Long coinId;

    public TransactionResponse() {
    }

    public TransactionResponse(Transaction transaction) {
        this.transcationDate = transaction.getTranscationDate();
        this.coin = transaction.getCoin();
        this.amount = transaction.getAmount();
        this.totalValue = transaction.getTotalValue();
    }

    public LocalDateTime getTranscationDate() {
        return transcationDate;
    }

    public void setTranscationDate(LocalDateTime transcationDate) {
        this.transcationDate = transcationDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }
}
