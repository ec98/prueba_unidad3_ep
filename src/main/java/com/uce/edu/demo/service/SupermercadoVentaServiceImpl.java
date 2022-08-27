package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.ISupermercadoProductoRepository;
import com.uce.edu.demo.repository.ISupermercadoVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class SupermercadoVentaServiceImpl implements ISupermercadoVentaService {

	@Autowired
	private ISupermercadoProductoRepository iSupermercadoProductoRepository;

	@Autowired
	private ISupermercadoVentaRepository iSupermercadoVentaRepository;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void realizarVenta(List<Producto> productolist, String cedula, String numero) {
		// TODO Auto-generated method stub

		Venta venta = new Venta();
		venta.setCedulaCliente(cedula);
		venta.setNumero(numero);

		Producto producto = new Producto();
		Integer valor = 0;
		BigDecimal nuevo = new BigDecimal(0);

		for (Producto p : productolist) {
			DetalleVenta detalle = new DetalleVenta();
			producto.setCodigoDeBarras(p.getCodigoDeBarras());
			producto.setStock(1);
			detalle.setCantidad(detalle.getCantidad());
			detalle.setProducto(producto);
			p.setPrecio(nuevo);
			if (valor <= producto.getStock()) {
				detalle.setSubTotal(detalle.getProducto().getPrecio());
				nuevo = detalle.getSubTotal();
				detalle.setProducto(detalle.getProducto());
			} else if (producto.getStock() < valor) {
				detalle.setProducto(detalle.getProducto());
				venta.setTotalVenta(nuevo);
			} else {
				throw new RuntimeException();
			}

		}
		this.iSupermercadoVentaRepository.realizarVenta(venta);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void reportVenta(LocalDateTime fecha, String categoriaProducto, String cantidad) {
		// TODO Auto-generated method stub

		Producto producto = new Producto();
		DetalleVenta detalle = new DetalleVenta();

		Venta venta = new Venta();

		producto = this.iSupermercadoProductoRepository.codigoBarras(producto.getCodigoDeBarras());
		producto.setCategoria(categoriaProducto);
		detalle.setCantidad(cantidad);
		detalle.setPrecioUnitario(detalle.getPrecioUnitario());
		detalle.setSubTotal(producto.getPrecio());
		venta.setFecha(fecha);
		venta.setProductos(venta.getProductos());
		this.iSupermercadoVentaRepository.realizarVenta(venta);
	}

}
