package com.system.pauta.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.system.pauta.domain.model.ContagemVotos;
import com.system.pauta.domain.model.Votacao;

public class VotacaoRepositoryImpl implements VotacaoRepositpryQuery {
	

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ContagemVotos> contagemVotosPorPauta(Long idPauta) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ContagemVotos> criteria = builder.createQuery(ContagemVotos.class);
		Root<Votacao> root = criteria.from(Votacao.class);
		
		criteria.select(builder.construct(ContagemVotos.class, 
				root.get("voto"),
				builder.count(root.get("cpf"))));
		criteria.where(builder.equal(root.get("pauta").get("id"), idPauta));
		criteria.groupBy(root.get("voto"));
		TypedQuery<ContagemVotos> query = manager.createQuery(criteria);
		return query.getResultList();
		
	}

}
