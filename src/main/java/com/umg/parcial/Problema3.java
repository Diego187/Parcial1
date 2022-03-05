package com.umg.parcial;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.MathObservable;

public class Problema3 {
    public static void main(String[] args){
        Integer [] numeros = {2, 5, 6, 8, 10, 35, 2, 10};

        Observable promedioObservable = Observable.from(numeros);

        MathObservable
                .from(promedioObservable)
                .averageInteger(promedioObservable)
                .subscribe((promedio) -> {
                    System.out.println("El promedio de los numeros es: " + promedio);
                });

        Observable
                .from(numeros)
                .filter(
                        new Func1<Integer, Boolean>() {
                            @Override
                            public Boolean call(Integer actual) {
                                return actual >= 10;
                            }
                        }
                )
                .subscribe((num) -> {
                    System.out.println("Número mayor a 10: " + num);
                });

        Observable sumaObservable = Observable.from(numeros)
                        .reduce(
                                new Func2<Integer, Integer, Integer>() {
                                    @Override
                                    public Integer call(Integer suma, Integer actual) {
                                        return suma + actual;
                                    }
                                }
                        );

        sumaObservable.subscribe((sumatoria) -> {
            System.out.println("La suma total: " + sumatoria);
        });
    }
}
