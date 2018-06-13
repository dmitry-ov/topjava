package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository mealRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew()) {
            Meal m = get(meal.getId(), userId);
            if (Objects.isNull(m) || (m.getUser().getId() != userId)) {
                return null;
            }
        }
        meal.setUser(userRepository.getOne(userId));
        return mealRepository.save(meal);
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return mealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return mealRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return mealRepository.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return mealRepository.findByDateTimeIsBetweenAndUserIdOrderByDateTimeDesc(startDate, endDate, userId);
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return mealRepository.getWithUser(id, userId);
    }
}
