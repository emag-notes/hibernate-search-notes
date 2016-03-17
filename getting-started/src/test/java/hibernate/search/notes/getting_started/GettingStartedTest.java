package hibernate.search.notes.getting_started;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GettingStartedTest {

  @Test
  public void test() throws Exception {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("primary");
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    Book book1 = new Book();
    book1.setTitle("Java");
    em.persist(book1);
    em.getTransaction().commit();

    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

    QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
      .forEntity(Book.class)
      .get();

    Query luceneQuery = qb
      .keyword()
      .onFields("title", "subtitle", "authors.name")
      .matching("Java rocks!")
      .createQuery();

    javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

    List<Book> result = jpaQuery.getResultList();
    assertThat(result.size(), is(1));

    em.close();
    emf.close();
  }

}
