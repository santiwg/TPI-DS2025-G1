package org.example.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MuestraSismica {
    private LocalDateTime fechaHoraMuestra;
    private ArrayList<DetalleMuestraSismica> detalleMuestraSismica;

    public MuestraSismica(){}

    public MuestraSismica(LocalDateTime fechaHoraMuestra, ArrayList<DetalleMuestraSismica> detalleMuestraSismica) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestraSismica = detalleMuestraSismica;
    }
}
