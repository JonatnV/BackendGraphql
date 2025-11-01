package com.trabajo.graphqlbackend.model;

import lombok.Data;

@Data
public class Breed {

    private String id;
    private String name;
    private String temperament;
    private String origin;
    private String description;

    public Breed() {}

    // getters y setters
    // puedes usar Lombok si quieres, pero aquí pongo clásicos por claridad
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTemperament() { return temperament; }
    public void setTemperament(String temperament) { this.temperament = temperament; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
