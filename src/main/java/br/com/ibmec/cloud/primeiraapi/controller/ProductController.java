package br.com.ibmec.cloud.primeiraapi.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.ibmec.cloud.primeiraapi.model.Product;
import br.com.ibmec.cloud.primeiraapi.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    public ProductController() {
        super();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<List<Product>>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        
        Optional<Product> product = this.repository.findById(id);
        
        if (product.isPresent() == false)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        this.repository.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        
        Optional<Product> product = this.repository.findById(id);

        if (product.isPresent() == false) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        this.repository.delete(product.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Integer id, @RequestBody Product newData) {
        
        Optional<Product> product = this.repository.findById(id);

        if (product.isPresent() == false) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Product oldData = product.get();

        oldData.setNome(newData.getNome());
        oldData.setDescricao(newData.getDescricao());
        oldData.setPreco(newData.getPreco());

        this.repository.save(oldData);

        return new ResponseEntity<>(oldData,HttpStatus.OK);    


    }

}
