package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.PaymentProcessLog;
import kg.onlinestore.unas.services.PaymentProcessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentsLog")
public class PaymentProcessLogController {
    @Autowired
    private PaymentProcessLogService paymentProcessLogService;

    @GetMapping
    public List<PaymentProcessLog> getAll(){
        return paymentProcessLogService.getAll();
    }

    @GetMapping("/{id}")
    public PaymentProcessLog getById(@PathVariable("id") Long id){
        return paymentProcessLogService.getById(id);
    }

    @PostMapping
    public PaymentProcessLog save(@RequestBody PaymentProcessLog paymentProcessLog){
        return paymentProcessLogService.save(paymentProcessLog);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePaymentLog(@PathVariable("id") Long id) {
        paymentProcessLogService.deleteById(id);
    }
}
