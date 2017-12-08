package com.xiaolugo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chinaD on 2017/11/28.
 */
@Entity
@Table(name = "deparment")
public class Deparment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Deparment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
