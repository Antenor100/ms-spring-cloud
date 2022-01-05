package br.com.devlearn.hrpayroll.resources;

import br.com.devlearn.hrpayroll.entities.Payment;
import br.com.devlearn.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
    @Autowired
    private PaymentService paymentService = new PaymentService();

    @GetMapping(value = "/days/{days}")
    public ResponseEntity<List<Payment>> findAllByDays(@PathVariable Integer days) {
        List<Payment> payments = paymentService.getPaymentsByDays(days);

        return ResponseEntity.ok(payments);
    }

    @GetMapping(value = "/{id}/days/{days}")
    public ResponseEntity<Payment> findPaymentByWorkerId(@PathVariable Long id, @PathVariable Integer days) {
        Payment payment = paymentService.getPaymentByWorkerIdAndDays(id, days);

        return ResponseEntity.ok(payment);
    }
}
