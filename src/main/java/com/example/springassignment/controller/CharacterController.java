package com.example.springassignment.controller;

import com.example.springassignment.model.Character;
import com.example.springassignment.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/character")
@EnableScheduling
public class CharacterController {

    private RestTemplate restTemplate = new RestTemplate();

    private final String apiUrl = "https://thronesapi.com/api/v1/Characters";

    @Autowired
    CharacterService characterService;


    @Scheduled(fixedRate = 60 * 10 * 1000)
    private List<Character> getCharacters()
    {
        List<Character> characters = new ArrayList<Character>();
        characters = Arrays.stream(restTemplate.getForObject(apiUrl,Character[].class))
               .collect(Collectors.toList());

        characterService.displayCharacterList(characters);
        characterService.saveCharacters(characters);
        return characters;

    }

    @RequestMapping("/getAll")
    private List<Character> getAll()
    {
        return characterService.getAllCharacters();
    }


    @RequestMapping("/get")
    private Optional<Character> getById(@RequestParam(name="id",required=true) Long id)
    {
     return characterService.getOne(id);
    }


    @RequestMapping("/delete")
    private void deleteById(@RequestParam(name="id",required=true) Long id)
    {
       characterService.deleteOne(id);
    }


    @RequestMapping("/create")
    private String createCharacter(@RequestParam(name="id",required=true) int id,
                                 @RequestParam(name="name",required=true) String name,
                                 @RequestParam(name="title",required=true) String title) {

        return characterService.createOne(id,name,title);
    }

    @RequestMapping("/update")
    private String updateCharacter(@RequestParam(name="id",required=true) int id,
                                   @RequestParam(name="name",required=true) String name,
                                   @RequestParam(name="title",required=true) String title) {

        return characterService.editOne(id,name,title);
    }
}
