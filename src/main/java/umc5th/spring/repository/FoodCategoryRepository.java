package umc5th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc5th.spring.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}