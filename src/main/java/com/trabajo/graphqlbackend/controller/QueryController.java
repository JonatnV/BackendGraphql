package com.trabajo.graphqlbackend.controller;


import com.trabajo.graphqlbackend.model.Breed;
import com.trabajo.graphqlbackend.model.Student;
import com.trabajo.graphqlbackend.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@CrossOrigin(origins = "https://front-graphql-six.vercel.app")
public class QueryController {
    private final StudentRepository studentRepo;
    private final RestTemplate restTemplate;
    // TheCatAPI key from env var
    private final String CAT_API_KEY;

    public QueryController(StudentRepository studentRepo){
        this.studentRepo = studentRepo;
        this.restTemplate = new RestTemplate();
        this.CAT_API_KEY = "live_wVW3QwTse99EDawE3P9I0ytmfREMLbinAHBLxkrUHtog8ENwqhJ9ZfpOa5Jn07bL"; // setear en entorno
    }

    @QueryMapping
    public List<Student> students() {
        return studentRepo.findAll();
    }

    @QueryMapping
    public Breed catBreed(@Argument String id) {
        // TheCatAPI endpoint para breed details no tiene {id} directo en /breeds/{id},
        // pero puedes filtrar con /breeds/search?q=... o usar /breeds y buscar por id.
        String url = "https://api.thecatapi.com/v1/breeds";
        HttpHeaders headers = new HttpHeaders();
        if(CAT_API_KEY != null && !CAT_API_KEY.isEmpty()){
            headers.set("x-api-key", CAT_API_KEY);
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Breed[]> resp = restTemplate.exchange(url, HttpMethod.GET, entity, Breed[].class);

        if(resp.getStatusCode() == HttpStatus.OK && resp.getBody() != null){
            for(Breed b : resp.getBody()){
                if(b.getId() != null && b.getId().equalsIgnoreCase(id)){
                    return b;
                }
            }
        }
        // si no lo encuentra intenta buscar en /breeds/search?q={id} como backup
        String searchUrl = "https://api.thecatapi.com/v1/breeds/search?q=" + id;
        ResponseEntity<Object[]> resp2 = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, Object[].class);
        if(resp2.getStatusCode() == HttpStatus.OK && resp2.getBody() != null && resp2.getBody().length>0){
            Map map = (Map) resp2.getBody()[0];
            Breed b = new Breed();
            b.setId((String)map.get("id"));
            b.setName((String)map.get("name"));
            b.setTemperament((String)map.get("temperament"));
            b.setOrigin((String)map.get("origin"));
            b.setDescription((String)map.get("description"));
            return b;
        }

        return null;
    }
}

