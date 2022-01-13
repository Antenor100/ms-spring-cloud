package br.com.devlearn.hrpayroll.services;

import br.com.devlearn.hrpayroll.entities.Payment;
import br.com.devlearn.hrpayroll.entities.Worker;
import br.com.devlearn.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private WorkerFeignClient workerFeignClient;

    public List<Payment> getPaymentsByDays(Integer days) {
        List<Worker> workers = workerFeignClient.findAll().getBody();

        List<Payment> payments = new ArrayList<>();

        workers.stream().forEach(worker -> {
            payments.add(new Payment(worker.getName(), days, worker.getDailyIncome()));
        });

        return payments;
    }

    public Payment getPaymentByWorkerIdAndDays(Long workerId, Integer days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.getName(), days, worker.getDailyIncome());
    }
}
