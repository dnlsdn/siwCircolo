package com.dnlsdn.progettopersonale.Comparator;

import com.dnlsdn.progettopersonale.model.Libro;

import java.util.Comparator;

public class ComparatoreLibri implements Comparator<Libro> {
        @Override
        public int compare(Libro o1, Libro o2) {
            return (int) (o1.getId() - o2.getId());
        }
}
