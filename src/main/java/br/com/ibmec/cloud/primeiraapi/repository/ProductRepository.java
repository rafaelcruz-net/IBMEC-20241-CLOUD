package br.com.ibmec.cloud.primeiraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ibmec.cloud.primeiraapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
