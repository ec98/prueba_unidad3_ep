package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Venta;

@Repository
@Transactional
public class SupermercadoVentaRepositoryImpl implements ISupermercadoVentaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarVenta(Venta venta) {
		// TODO Auto-generated method stub
		this.entityManager.persist(venta);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Venta> reporteVentas(String venta) {
		// TODO Auto-generated method stub
		TypedQuery<Venta> miQuery = this.entityManager
				.createQuery("SELECT v FROM Venta v JOIN v.productos vp WHERE vp.venta =: venta", Venta.class);
		miQuery.setParameter("venta", venta);
		return miQuery.getResultList();
	}

}
