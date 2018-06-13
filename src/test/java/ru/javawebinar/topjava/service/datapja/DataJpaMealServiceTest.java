package ru.javawebinar.topjava.service.datapja;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles(value = {Profiles.POSTGRES_DB, Profiles.DATAJPA})
public class DataJpaMealServiceTest extends MealServiceTest {

    @Test
    public void get() throws Exception {
        Meal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL1);
        Assert.assertEquals(actual.getUser(), ADMIN);
    }
}