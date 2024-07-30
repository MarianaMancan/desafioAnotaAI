package com.project.desafioAnotaAI.Domain.Product;

import com.project.desafioAnotaAI.Domain.Category.CategoryDTO;

public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {
}