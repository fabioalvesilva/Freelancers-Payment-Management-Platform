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
public class Task implements Serializable {

    private String id;
    private String description;
    private double assignDuration;
    private double coustPerHour;
    private Category category;

    public Task(String id, String description, Double assignDuration, Double coustPerHour, Category category) {
        setId(id);
        setDescription(description);
        setAssignDuration(assignDuration);
        setCoustPerHour(coustPerHour);
        setCategory(category);
    }

    public Task(Task task) {
        setId(task.getId());
        setDescription(task.getDescription());
        setAssignDuration(task.getAssignDuration());
        setCoustPerHour(task.getCoustPerHour());
        setCategory(task.getCategory());
    }

    public Task() {

    }

    public Task newTask(String id, String description, Double assignDuration, Double coustPerHour, Category category) {
        return new Task(id, description, assignDuration, coustPerHour, category);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAssignDuration() {
        return assignDuration;
    }

    public double getCoustPerHour() {
        return coustPerHour;
    }

    public Category getCategory() {
        return category;
    }

    private void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid ID!");
        }
        this.id = id;
    }

    public final void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid description!");
        }
        this.description = description;
    }

    private void setAssignDuration(Double assignDuration) {
        if (assignDuration == null || assignDuration < 0) {
            throw new IllegalArgumentException("Invalid assign duration!");
        }
        this.assignDuration = assignDuration;
    }

    private void setCoustPerHour(Double coustPerHour) {
        if (coustPerHour == null || coustPerHour < 0) {
            throw new IllegalArgumentException("Invalid coust per hour!");
        }
        this.coustPerHour = coustPerHour;
    }

    private void setCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Invalid category!");
        }
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("TAREFA: %s \n-Description: %s \n-Assign Duration: %.2f \n-Cost/hour: %.2f \n-Category: %s \n", id, description,
                assignDuration, coustPerHour, category);
    }

    @Override
    public boolean equals(Object anotherObjet) {
        if (this == anotherObjet) {
            return true;
        }
        if (anotherObjet == null || this.getClass() != anotherObjet.getClass()) {
            return false;
        }
        Task anotherTask = (Task) anotherObjet;

        return id.equalsIgnoreCase(anotherTask.id)
                && description.equals(anotherTask.description)
                && (assignDuration == anotherTask.assignDuration)
                && (coustPerHour == anotherTask.coustPerHour)
                && category.equals(anotherTask.category);
    }

    public int compareTo(Task anotherTask) {
        return id.compareTo(anotherTask.id);
    }

}
