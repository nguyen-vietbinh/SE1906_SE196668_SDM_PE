package com.se196668.mvc.service.impl;

import com.se196668.mvc.entity.Category;
import com.se196668.mvc.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void cateList_categoriesExist_returnsIdNameMap() {
        // Arrange
        Category category1 = mock(Category.class);
        when(category1.getId()).thenReturn(1);
        when(category1.getCategoryName())
                .thenReturn("Computer");

        Category category2 = mock(Category.class);
        when(category2.getId()).thenReturn(2);
        when(category2.getCategoryName())
                .thenReturn("Printer");

        when(categoryRepository.findAll())
                .thenReturn(List.of(category1, category2));

        // Act
        HashMap<Integer, String> result =
                categoryService.cateList();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Computer", result.get(1));
        assertEquals("Printer", result.get(2));

        verify(categoryRepository).findAll();
    }

    @Test
    void cateList_noCategories_returnsEmptyMap() {
        // Arrange
        when(categoryRepository.findAll())
                .thenReturn(List.of());

        // Act
        HashMap<Integer, String> result =
                categoryService.cateList();

        // Assert
        assertTrue(result.isEmpty());
    }
}
