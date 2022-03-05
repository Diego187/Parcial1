package com.umg.parcial;

import dto.Producto;
import rx.Observable;
import rx.functions.Func2;
import rx.observables.MathObservable;

import java.util.ArrayList;
import java.util.List;

public class Problema2 {
    public static void main(String[] args){

        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("ps4", 300));
        productos.add(new Producto("gamecube", 300));
        productos.add(new Producto("external disk",200));
        productos.add(new Producto("laptop",800));
        productos.add(new Producto("vr", 230));

        Observable<Producto> productoObservable = Observable.from(productos);

        MathObservable
                .from(productoObservable)
                .averageDouble(Producto::getPrecio)
                .subscribe((promedio) -> {
                    System.out.println("El promedio de los precio es de: " + promedio);
                });

        Observable productoObservableSumatoria = Observable.from(productos.toArray())
                .map((result) -> {
                    Producto producto = (Producto) result;
                    return producto.getPrecio();
                })
                .reduce(
                        new Func2<Double, Double, Double>() {
                            @Override
                            public Double call(Double suma, Double actual) {
                                return suma + actual;
                            }
                        }
                );

        productoObservableSumatoria.subscribe((sumatoria) -> {
            System.out.println("La sumatoria de los precios es de : " + sumatoria);
        });

        Observable productoObservableMax = Observable.from(productos.toArray())
                        .map((result) -> {
                            Producto producto = (Producto)  result;
                            return producto.getPrecio();
                        })
                        .reduce(
                                new Func2<Double, Double, Double>() {
                                    @Override
                                    public Double call(Double anterior, Double actual) {
                                        if(anterior > actual){
                                            return anterior;
                                        }else{
                                            return actual;
                                        }
                                    }
                                }
                        );


        productoObservableMax.subscribe((precioMax) -> {
            System.out.print("El precio maxima es de: " + precioMax);
        });

    }
}
