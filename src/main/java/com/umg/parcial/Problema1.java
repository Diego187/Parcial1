package com.umg.parcial;

import dto.Persona;
import rx.Observable;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

public class Problema1 {

    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Juan", 20));
        personas.add(new Persona("Pedro", 31));
        personas.add(new Persona("Fernando", 25));
        personas.add(new Persona("Josué", 19));
        personas.add(new Persona("Emanuel", 41));

        Observable personaObservable = Observable.from(personas.toArray())
                .map((result) -> {
                    Persona persona = (Persona) result;
                    return persona.getEdad();
                })
                .reduce(
                        new Func2<Integer, Integer, Integer>() {
                            @Override
                            public Integer call(Integer anterior, Integer actual) {
                                if (anterior > actual) {
                                    return anterior;
                                } else {
                                    return actual;
                                }
                            }
                        }
                );

        personaObservable.subscribe((edad) -> {
            System.out.print("La edad maximo es de: " + edad);
        });
    }
}