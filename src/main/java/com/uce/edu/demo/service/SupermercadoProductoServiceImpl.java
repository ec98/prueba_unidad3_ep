package com.uce.edu.demo.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.ISupermercadoProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;

@Service
public class SupermercadoProductoServiceImpl implements ISupermercadoProductoService {

	@Autowired
	private ISupermercadoProductoRepository iSupermercadoProductoRepository;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void ingresarProductos(String nombre, Integer stock) {
		// TODO Auto-generated method stub

		Producto producto = this.iSupermercadoProductoRepository.buscarStock(stock);
		producto.setNombre(nombre);

		Integer valor = producto.getStock();

		if (valor.compareTo(stock) == 0) {
			valor = valor + stock;
		} else {
			this.iSupermercadoProductoRepository.codigoBarras(producto.getCodigoDeBarras());
			producto.setStock(stock);
		}

		this.iSupermercadoProductoRepository.ingresar(producto);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void consultarStock(String codigoBarras, String nombre, String categoria, Integer stock) {
		// TODO Auto-generated method stub
		Producto producto = this.iSupermercadoProductoRepository.nativeQueryCodigoBarras(codigoBarras);
		producto.setNombre(nombre);
		producto.setCategoria(categoria);
		producto.setStock(stock);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.iSupermercadoProductoRepository.ingresar(producto);
	}

	

}
