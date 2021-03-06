package com.anvesh.recepieapp.services;


import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipies();

    Recipe findById(Long l);

    RecipeCommand commandFindyById(Long l);

    RecipeCommand saveReciepeCommand(RecipeCommand command);

    void deletById(Long id);
}
