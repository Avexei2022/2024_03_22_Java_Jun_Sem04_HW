package ru.kolodin.view;

import ru.kolodin.presenter.Presenter;
import ru.kolodin.view.randomName.RandomName;

import java.util.concurrent.ThreadLocalRandom;

public class ConsoleUI implements View{
    private final Presenter presenter;


    public ConsoleUI() {
        presenter = new Presenter(this);
    }
    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    public void run() {
        createNewStudent(10);
        presenter.getStudentsCash();
        presenter.saveStudents();

        presenter.loadStudents();
        presenter.getStudentsCash();

        presenter.findById("9");
        presenter.getStudentsCash();

        presenter.editById("7", "Ivan", "Ivanov", "25");
        presenter.loadStudents();
        presenter.getStudentsCash();

        presenter.removeByIdFromTo("10", "2000");
        presenter.getStudentsCash();

        presenter.findByQuery("SELECT s FROM student s WHERE age > 20");
        presenter.getStudentsCash();
    }



    private void createNewStudent(int amount) {
        for (int i = 0; i < amount; i++) {
            String firstName = new RandomName(5).getNameRusByStringShort();
            String secondName = new RandomName(7).getNameRusByString() + "дзе";
            String age = String.valueOf(ThreadLocalRandom.current().nextInt(18, 25));
            presenter.createNewStudent(firstName, secondName, age);
        }
    }


}
