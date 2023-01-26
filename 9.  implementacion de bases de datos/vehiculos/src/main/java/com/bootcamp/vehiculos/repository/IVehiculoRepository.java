package com.bootcamp.vehiculos.repository;

import com.bootcamp.vehiculos.model.Vehiculo;
import com.bootcamp.vehiculos.model.template.PatenteYMarca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long> {
    @Query("select v.patente from Vehiculo v")
    List<String> listPatentes();

    @Query("select new com.bootcamp.vehiculos.model.template.PatenteYMarca(v.patente, v.marca)" +
            "from Vehiculo v order by v.anioFabricacion desc")
    List<PatenteYMarca> listPatenteYMarcaOrderByAnioFabricacion();

    @Query("select v.patente from Vehiculo v where v.anioFabricacion = year(current_date()) and v.cantRuedas > 4")
    List<String> listPatentesOfFourWheeledFromCurrentYear();

}
