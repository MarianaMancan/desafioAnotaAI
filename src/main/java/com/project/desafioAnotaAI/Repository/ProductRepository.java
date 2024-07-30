package com.project.desafioAnotaAI.Repository;

import com.project.desafioAnotaAI.Domain.Product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
