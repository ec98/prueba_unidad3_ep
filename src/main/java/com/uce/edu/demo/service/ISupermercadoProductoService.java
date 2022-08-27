package com.uce.edu.demo.service;

import com.uce.edu.demo.repository.modelo.Producto;

public interface ISupermercadoProductoService {
	
	public void insertar(Producto producto);

	public void ingresarProductos(String nombre, Integer stock);

	public void consultarStock(String codigoBarras, String nombre, String categoria, Integer stock);
}
