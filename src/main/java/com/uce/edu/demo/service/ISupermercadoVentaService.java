package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;

public interface ISupermercadoVentaService {
	
	public void realizarVenta(List<Producto> milist, String cedula, String numero);
	
	public void reportVenta(LocalDateTime fecha, String categoriaProducto, String cantidad);
}
