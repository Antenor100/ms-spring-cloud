package br.com.devlearn.hrworker.repositories;

import br.com.devlearn.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
