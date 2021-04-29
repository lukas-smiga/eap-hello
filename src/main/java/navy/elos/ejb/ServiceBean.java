package navy.elos.ejb;

import navy.elos.model.SimpleProperty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;

@Stateless
public class  ServiceBean {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void put(String key, String value){
        SimpleProperty p = new SimpleProperty();
        p.setKey(key);
        p.setValue(value);
		em.persist(p);
	}

	public void delete(SimpleProperty p){

		Query query = em.createQuery("delete FROM SimpleProperty p where p.key='"+p.getKey()+"'");

		query.executeUpdate();

	}

	public void delete(String key)  {
		Query q = em.createQuery("delete FROM SimpleProperty p where p.key='" + key + "'");
		q.executeUpdate();
	}

	public List<SimpleProperty> findAll(){

		Query query = em.createQuery("FROM SimpleProperty");

		List <SimpleProperty> list = query.getResultList();
		return list;

	}

	public SimpleProperty findById(String id){

		SimpleProperty p = em.find(SimpleProperty.class, id);
		return p;

	}

}
