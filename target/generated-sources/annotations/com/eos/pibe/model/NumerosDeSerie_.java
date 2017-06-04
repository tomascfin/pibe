package com.eos.pibe.model;

import com.eos.pibe.model.Entidad;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T06:06:27")
@StaticMetamodel(NumerosDeSerie.class)
public class NumerosDeSerie_ { 

    public static volatile SingularAttribute<NumerosDeSerie, String> id;
    public static volatile SingularAttribute<NumerosDeSerie, Boolean> anuladoPorReinstalacion;
    public static volatile SingularAttribute<NumerosDeSerie, Integer> usos;
    public static volatile SingularAttribute<NumerosDeSerie, Entidad> entidad;
    public static volatile SingularAttribute<NumerosDeSerie, Date> fechaIngreso;
    public static volatile SingularAttribute<NumerosDeSerie, Boolean> activado;

}