package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Noticia;
import com.gdados.projeto.util.filter.NoticiaFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NoticiaFacade extends DaoGeneric<Noticia> implements Serializable {

    public NoticiaFacade() {
        super(Noticia.class);
    }

    EntityManager em = new JpaUtil().createEntityManager();

    public long contaTotal() {
        Query q = em.createQuery("select count(c) from Noticia c");
        long contador = (long) q.getSingleResult();
        return contador;
    }

    public List<Noticia> listaNoticiaBySubCategoria(String nome) {
        try {
            Query q = em.createQuery("SELECT n FROM Noticia n JOIN N.subCategoria s WHERE s.nome = :nome");
            q.setParameter("nome", nome);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("erro: " + e.getLocalizedMessage());
        }
        return null;
    }

    public List<Noticia> listaNoticiaByDestaque() {
        try {
            Query q = em.createQuery("SELECT n FROM Noticia n WHERE n.destaque = :destaque ORDER BY n.dataatuAlizacao DESC");
            q.setParameter("destaque", Boolean.TRUE);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("erro: " + e.getLocalizedMessage());
        }
        return null;
    }

    public List<String> nomeQueContem(String titulo) {
        TypedQuery<String> query = em.createQuery("SELECT p.titulo FROM Noticia p WHERE upper(p.titulo) LIKE upper(:titulo)", String.class);
        query.setParameter("titulo", "%" + titulo + "%");
        return query.getResultList();
    }

    public List<Noticia> buscaNoticiaByFiltro1(NoticiaFilter filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Noticia> query = criteriaBuilder.createQuery(Noticia.class);
        Root<Noticia> n = query.from(Noticia.class);

        Path<String> tituloPath = n.<String>get("titulo");
        Path<String> categoriaPath = n.join("subCategoria").<String>get("nome");

        List<Predicate> predicates = new ArrayList<>();

        if (!filter.getTitulo().isEmpty()) {
            Predicate paramentro = criteriaBuilder.like(tituloPath, "%" + filter.getTitulo() + "%");
            predicates.add(paramentro);
        }

        if (!filter.getCategoria().isEmpty()) {
            Predicate paramentro = criteriaBuilder.like(categoriaPath, "%" + filter.getCategoria() + "%");
            predicates.add(paramentro);
        }

        query.where((Predicate[]) predicates.toArray(new Predicate[0]));
        query.orderBy(criteriaBuilder.desc(n.get("dataatuAlizacao")));
        TypedQuery<Noticia> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }
}
