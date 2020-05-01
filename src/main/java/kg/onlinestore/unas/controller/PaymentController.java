package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Payment;
import kg.onlinestore.unas.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment save(@RequestBody Payment payment){
        return paymentService.save(payment);
    }

    @GetMapping
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    public Payment getById (@PathVariable("id")Long id){
        return paymentService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable("id") Long id) {
        paymentService.deleteById(id);
    }
}
