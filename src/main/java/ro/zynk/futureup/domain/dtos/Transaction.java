package ro.zynk.futureup.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import ro.zynk.futureup.controllers.responses.TransactionResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Bucharest")
    @Column
    private LocalDateTime transcationDate;

    @Column
    private Float amount;

    @Column
    private Double totalValue;

    @ManyToOne
    @JoinColumn(name = "coin")
    private Coin coin;

    public Transaction() {
    }

    public Transaction(LocalDateTime transcationDate, Float amount, Double totalValue) {
        this.transcationDate = transcationDate;
        this.amount = amount;
        this.totalValue = totalValue;
    }

    public Transaction(TransactionResponse transactionResponse) {
        this.transcationDate = transactionResponse.getTranscationDate();
        this.coin = transactionResponse.getCoin();
        this.amount = transactionResponse.getAmount();
        this.totalValue = transactionResponse.getTotalValue();
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
}
