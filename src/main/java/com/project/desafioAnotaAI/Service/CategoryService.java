package com.project.desafioAnotaAI.Service;

import com.project.desafioAnotaAI.Domain.Category.Category;
import com.project.desafioAnotaAI.Domain.Category.CategoryDTO;
import com.project.desafioAnotaAI.Domain.Category.Exceptions.CategoryNotFoundException;
import com.project.desafioAnotaAI.Repository.CategoryRepository;
import com.project.desafioAnotaAI.Service.Aws.AwsSnsService;
import com.project.desafioAnotaAI.Service.Aws.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final AwsSnsService snsService;
    private final CategoryRepository repository;



    public Category insert(CategoryDTO category){
        Category newCategory =  new Category(category);
        this.repository.save(newCategory);
        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryData){

        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.snsService.publish(new MessageDTO(category.toString()));

        this.repository.save(category);

        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);
        this.snsService.publish(new MessageDTO(category.deleteToString()));
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }
}
