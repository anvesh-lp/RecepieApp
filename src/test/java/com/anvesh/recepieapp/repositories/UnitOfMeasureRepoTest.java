package com.anvesh.recepieapp.repositories;

import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepoTest {

    @Autowired
    UnitOfMeasureRepo repo;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUom() {
        Optional<UnitOfMeasurment> unm = repo.findByUom("Teaspoon");
        assertEquals("Teaspoon", unm.get().getUom());
    }
}