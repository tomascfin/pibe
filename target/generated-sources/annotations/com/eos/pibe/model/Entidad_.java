package com.eos.pibe.model;

import com.eos.pibe.model.Comuna;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-04T10:11:52")
@StaticMetamodel(Entidad.class)
public class Entidad_ { 

    public static volatile SingularAttribute<Entidad, String> id;
    public static volatile SingularAttribute<Entidad, Comuna> comuna;
    public static volatile SingularAttribute<Entidad, String> nombreContacto;
    public static volatile SingularAttribute<Entidad, String> direccionEntidad;
    public static volatile SingularAttribute<Entidad, String> telefonoContacto;
    public static volatile SingularAttribute<Entidad, String> emailContacto;
    public static volatile SingularAttribute<Entidad, String> nombreEntidad;

}