package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Venta;

public interface ISupermercadoVentaRepository {

	public void realizarVenta(Venta venta);
	
	public List<Venta> reporteVentas(String venta);
}
