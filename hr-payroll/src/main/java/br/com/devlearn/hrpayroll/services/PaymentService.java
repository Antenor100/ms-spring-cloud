package br.com.devlearn.hrpayroll.services;

import br.com.devlearn.hrpayroll.entities.Payment;
import br.com.devlearn.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PaymentService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${hr-worker.host}")
    String workerHost;

    public List<Payment> getPaymentsByDays(Integer days) {
        List<Worker> workers = Arrays.stream(restTemplate.getForObject(workerHost + "/workers", Worker[].class)).toList();

        List<Payment> payments = new ArrayList<>();

        workers.stream().forEach(worker -> {
            payments.add(new Payment(worker.getName(), days, worker.getDailyIncome()));
        });

        return payments;
    }

    public Payment getPaymentByWorkerIdAndDays(Long workerId, Integer days) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", workerId.toString());

        Worker worker = restTemplate.getForObject(workerHost+"/workers/{id}", Worker.class, uriVariables);

        return new Payment(worker.getName(), days, worker.getDailyIncome());
    }
}
