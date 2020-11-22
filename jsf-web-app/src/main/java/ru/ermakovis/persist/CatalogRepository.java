package ru.ermakovis.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Transactional
    public void delete(int id) {
        Product item = em.find(Product.class, id);
        if (item != null) {
            em.remove(item);
        }
    }

    public Product find(int id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("FROM Product c", Product.class).getResultList();
    }
}
