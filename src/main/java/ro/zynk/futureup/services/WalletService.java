package ro.zynk.futureup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.zynk.futureup.controllers.requests.CoinTransactionRequest;
import ro.zynk.futureup.controllers.responses.CoinResponse;
import ro.zynk.futureup.controllers.responses.CoinTransactionResponse;
import ro.zynk.futureup.controllers.responses.WalletResponse;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.CoinAmount;
import ro.zynk.futureup.domain.dtos.Wallet;
import ro.zynk.futureup.domain.repositories.CoinAmountRepository;
import ro.zynk.futureup.domain.repositories.CoinRepository;
import ro.zynk.futureup.domain.repositories.WalletRepository;
import ro.zynk.futureup.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final CoinRepository coinRepository;
    private CoinAmountRepository coinAmountRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, CoinRepository coinRepository, CoinAmountRepository coinAmountRepository) {
        this.walletRepository = walletRepository;
        this.coinRepository = coinRepository;
        this.coinAmountRepository = coinAmountRepository;
    }

    public WalletResponse saveNewWallet(WalletResponse walletResponse) {
        Wallet wallet = new Wallet(walletResponse);
        wallet = walletRepository.save(wallet);
        return new WalletResponse(wallet);
    }

    public WalletResponse getWallet(Long id) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(id);
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }
        Wallet wallet = walletOpt.get();
        return new WalletResponse(wallet);
    }

    public CoinTransactionResponse buyCoin(CoinTransactionRequest buyCoinRequest) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(buyCoinRequest.getWalletId());
        Optional<Coin> coinOpt = coinRepository.findById(buyCoinRequest.getCoinId());
        if (coinOpt.isEmpty()) {
            throw new NotFoundException("Coin not found!");
        }
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }
        Coin coin = coinOpt.get();
        Wallet wallet = walletOpt.get();
        CoinAmount coinAmount = new CoinAmount(wallet, coin, buyCoinRequest.getAmount());
        wallet.getCoinAmounts().add(coinAmount);
        walletRepository.save(wallet);
        return new CoinTransactionResponse(new CoinResponse(coin), new WalletResponse(wallet), coinAmount.getAmount());
    }

    public List<CoinTransactionResponse> getAllCoinsFromWallet(Long walletId) throws NotFoundException {
        Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        if (walletOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }
        List<CoinAmount> coinAmounts = coinAmountRepository.findAllByWallet(wallet);
        List<CoinTransactionResponse> coinTransactionResponses = new ArrayList<>();
        for (CoinAmount coinAmount :
                coinAmounts) {
            coinTransactionResponses.add(new CoinTransactionResponse(coinAmount));
        }
        return coinTransactionResponses;
    }
}