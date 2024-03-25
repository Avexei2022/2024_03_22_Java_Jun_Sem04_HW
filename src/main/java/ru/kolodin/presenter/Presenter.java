package ru.kolodin.presenter;

import ru.kolodin.model.Service;
import ru.kolodin.view.View;

public class Presenter {
    private final View view;
    private final Service service;

    public Presenter(View view) {
        this.view = view;
        service = new Service();
    }

    public void saveStudents() {
        view.printAnswer(
                service.saveStudents());
    }

    public void loadStudents() {
        view.printAnswer(
                service.loadStudents());
    }

    public void createNewStudent(String firstName, String secondName, String age) {
        view.printAnswer(
                service.createNewStudent(firstName, secondName, age));
    }

    public void getStudentsCash() {
        view.printAnswer(
                service.getStudentsCashString());
    }

    public void findById(String id) {
        view.printAnswer(
                service.findById(id));
    }

    public void editById(String id, String firstName, String secondName, String age) {
        view.printAnswer(
                service.editById(id, firstName, secondName, age));
    }

    public void removeByIdFromTo(String idFrom, String idTo) {
        view.printAnswer(
                service.removeByIdFromTo(idFrom, idTo));
    }

    public void findByQuery(String query) {
        view.printAnswer(
                service.findByQuery(query));
    }
}
