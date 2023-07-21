package com.example.springassignment.service;

import com.example.springassignment.model.Character;
import com.example.springassignment.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public void displayCharacterList(List<Character> characterList){
        characterList.stream().forEach(c->System.out.println(c));
    }

    public CharacterRepository getCharacterRepository() {
        return characterRepository;
    }

    public void saveCharacters(List<Character> characterList){
        characterList.stream().forEach(c->characterRepository.save(c));
    }

    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<Character>();
        characterRepository.findAll().forEach(c->characters.add(c));
        return characters;
    }


    public Optional<Character> getOne(Long id){
        return characterRepository.findById(id);
    }


    public void deleteOne(Long id){
        characterRepository.deleteById(id);
    }


    public String createOne(int id, String name, String title){
        Optional<Character> check = characterRepository.findById(Long.valueOf(id));
        if(check.isPresent()) {
            return "Exista deja un character cu id-ul dat";
        }
        else {
            Character c = new Character(id,name,title);
            characterRepository.save(c);
            return "Adaugarea s-a realizat cu succes";
        }
    }

    public String editOne(int id, String name, String title){
        Optional<Character> check = characterRepository.findById(Long.valueOf(id));
        if(check.isEmpty()) {
            Character c = new Character(id,name,title);
            characterRepository.save(c);
            return "Id-ul selectat nu apartine niciunui caracter , cream unul nou ";
        }
        else{
            Character c = new Character(id,name,title);
            characterRepository.save(c);
            return "Caracterul a fost editat cu succes";
        }
    }
}
