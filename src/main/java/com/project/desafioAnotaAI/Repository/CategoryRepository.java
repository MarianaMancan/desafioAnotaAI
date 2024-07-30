package com.project.desafioAnotaAI.Repository;

import com.project.desafioAnotaAI.Domain.Category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,String> {
}
