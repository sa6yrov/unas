package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.services.PaymentChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentChequeService paymentChequeService;

    @PostMapping
    public PaymentCheque save(@RequestBody PaymentCheque paymentCheque){
        return paymentChequeService.save(paymentCheque);
    }

    @GetMapping
    public List<PaymentCheque> getAll(){
        return paymentChequeService.getAll();
    }

    @GetMapping("/{id}")
    public PaymentCheque getById (@PathVariable("id")Long id){
        return paymentChequeService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable("id") Long id) {
        paymentChequeService.deleteById(id);
    }
}
