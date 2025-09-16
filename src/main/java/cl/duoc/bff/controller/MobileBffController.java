package cl.duoc.bff.controller;

import cl.duoc.bff.service.BankDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BFF Mobile - returns light-weight objects (id, owner, balance only)
 */
@RestController
public class MobileBffController {
    private final BankDataService svc;
    public MobileBffController(BankDataService svc){this.svc = svc;}

    public record MobileAccount(String accountNumber, String ownerName, BigDecimal balance){}
    @GetMapping("/mobile/accounts")
    public List<MobileAccount> accounts(){
        return svc.getAllAccounts().stream()
                .map(a->new MobileAccount(a.getAccountNumber(), a.getOwnerName(), a.getBalance()))
                .collect(Collectors.toList());
    }
    @GetMapping("/mobile/accounts/{accountNumber}")
    public MobileAccount account(@PathVariable String accountNumber){
        var a = svc.getAccountById(accountNumber);
        return a==null?null:new MobileAccount(a.getAccountNumber(), a.getOwnerName(), a.getBalance());
    }
}
