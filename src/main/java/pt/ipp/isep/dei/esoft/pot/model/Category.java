/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;

/**
 *
 * @author
 */
public class Category implements Serializable {

    private String name;

    public Category(String name) {
        setName(name);
    }

    public Category(Category category) {
        setName(category.getName());
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name!");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Category newCategory(String name) {
        return new Category(name);
    }

    public Category newCategory(Category category) {
        return new Category(category);
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

}
