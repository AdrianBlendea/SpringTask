package com.example.springassignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springassignment.model.Character;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CharacterRepository extends CrudRepository<Character,Long> {
}
