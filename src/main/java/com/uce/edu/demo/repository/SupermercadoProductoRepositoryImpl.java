package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;

@Repository
@Transactional
public class SupermercadoProductoRepositoryImpl implements ISupermercadoProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingresar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarStock(Integer stock) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> miQuery = this.entityManager.createQuery("SELECT p FROM Producto p WHERE p.stock =:stock",
				Producto.class);
		miQuery.setParameter("stock", stock);
		return miQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto codigoBarras(String codigoDeBarras) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> miQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoDeBarras =:codigoDeBarras", Producto.class);
		miQuery.setParameter("codigoDeBarras", codigoDeBarras);
		return miQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto nativeQueryCodigoBarras(String codigoBarras) {
		// TODO Auto-generated method stub
		Query miQuery = this.entityManager
				.createNativeQuery("SELECT * FROM Producto WHERE prod_codigo_de_barras = :codigoBarras", Producto.class);
		miQuery.setParameter("codigoBarras", codigoBarras);
		return (Producto) miQuery.getSingleResult();
	}

}
