package com.uce.edu.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.service.ISupermercadoProductoService;
import com.uce.edu.demo.service.ISupermercadoVentaService;

@SpringBootApplication
public class PruebaUnidad3EcApplication implements CommandLineRunner {

	private static final Logger Logger = LoggerFactory.getLogger(PruebaUnidad3EcApplication.class);

	@Autowired
	private ISupermercadoProductoService iSupermercadoProductoService;

	@Autowired
	private ISupermercadoVentaService iSupermercadoVentaService;

	public static void main(String[] args) {
		SpringApplication.run(PruebaUnidad3EcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Producto producto = new Producto();
		producto.setNombre("Atun");
		producto.setCategoria("A");
		producto.setCodigoDeBarras("611");
		producto.setPrecio(new BigDecimal(11));
		producto.setStock(3);

		this.iSupermercadoProductoService.insertar(producto);

		Venta venta = new Venta();

		List<Producto> milist = new ArrayList<>();
		milist.add(producto);

		venta.setCedulaCliente("12211111");
		venta.setProductos(milist);
		venta.setNumero("12");
		venta.setFecha(LocalDateTime.now());
		
		this.iSupermercadoVentaService.realizarVenta(milist, venta.getCedulaCliente(), venta.getNumero());
		
		this.iSupermercadoProductoService.consultarStock("2211", "Arroz", "A", 3);

		this.iSupermercadoVentaService.reportVenta(LocalDateTime.of(2011, 12, 11, 1, 13, 41), producto.getCategoria(),	"1");

	}

}
