package main;

import domaine.Employe;

import java.util.function.Function;

public class EmployeNomFonction implements Function<Employe, String> {

    @Override
    public String apply(Employe employe) {
        return employe.getNom();
    }
}
