package com.aws.person.domain.model;

public class Person {

    private String name;
    private Long numberId;
    private String email;
     private Long id;

    public Person() {
    }

    public Person(String name, Long numberId, String email, Long id) {
        this.name = name;
        this.numberId = numberId;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
