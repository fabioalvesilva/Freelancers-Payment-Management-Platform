/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pt.ipp.isep.dei.esoft.autorizacao.AutorizationFacade;

/**
 *
 * @author
 */
public class TaskList implements Serializable {

    private final AutorizationFacade m_oAutorization;
    private List<Task> listTasks;

    public TaskList() {
        this.m_oAutorization = new AutorizationFacade();
        listTasks = new ArrayList<>();
    }

    public TaskList(List<Task> lstTasks) {
        this.m_oAutorization = new AutorizationFacade();
        listTasks = new ArrayList<>(lstTasks);
    }

    public List<Task> getListTasks() {
        return listTasks;
    }

    public Task newTask(String id, String description, Double assignDuration, Double coustPerHour, Category category) {
        return new Task(id, description, assignDuration, coustPerHour, category);
    }

    public boolean registTask(Task task) {
        if (this.validateTask(task)) {
            return addTask(task);
        }
        return false;
    }

    public boolean validateTask(Task task) {
        String id = task.getId();
        boolean compareNif = true;

        for (Task t : listTasks) {
            if (t.getId().equals(id)) {
                compareNif = false;
            }
        }
        return compareNif;
    }

    public boolean addTask(Task task) {
        return listTasks.contains(task) ? false
                : listTasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Task task : listTasks) {
            s.append(task);
            s.append("\n");
        }

        return s.toString().trim();
    }

}
