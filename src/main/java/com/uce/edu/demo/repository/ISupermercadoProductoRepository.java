package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.Producto;

public interface ISupermercadoProductoRepository {

	public void ingresar(Producto producto);

	public Producto buscarStock(Integer stock);
	
	public Producto codigoBarras(String codigoDeBarras);
	
	public Producto nativeQueryCodigoBarras(String codigoBarras);
}
