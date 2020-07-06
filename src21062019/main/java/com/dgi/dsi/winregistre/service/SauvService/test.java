package com.dgi.dsi.winregistre.service.SauvService;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

@Repository
public class test {

//	@PersistenceContext
//	private EntityManager entityManager;
//
//
//	public Banque getArticleById(int articleId) {
//		return entityManager.find(Banque.class, articleId);
//	}
//
//
//	public List<Banque> getAllBanques() {
//		String hql = "FROM Banque as atcl ORDER BY atcl.articleId";
//		return (List<Banque>) entityManager.createQuery(hql).getResultList();
//	}
//
//
//	public void addArticle(Banque article) {
//		entityManager.persist(article);
//	}


//	public void updateArticle(Banque article) {
//		Banque artcl = getArticleById(article.getArticleId());
//		artcl.setTitle(article.getTitle());
//		artcl.setCategory(article.getCategory());
//		entityManager.flush();
//	}


//	public void deleteArticle(int articleId) {
//		entityManager.remove(getArticleById(articleId));
//	}
//
//
//	public boolean articleExists(String title, String category) {
//		String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
//		int count = entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category).getResultList()
//				.size();
//		return count > 0 ? true : false;
//	}

}
