package hibernate.search.notes.getting_started;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Indexed
public class Book {

  @Id @GeneratedValue
  private Integer id;

  @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
  private String title;

  @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
  private String subtitle;

  @IndexedEmbedded
  @ManyToMany
  private Set<Author> authors = new HashSet<>();

  @Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  private Date publicationDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (id != null ? !id.equals(book.id) : book.id != null) return false;
    if (title != null ? !title.equals(book.title) : book.title != null) return false;
    if (subtitle != null ? !subtitle.equals(book.subtitle) : book.subtitle != null) return false;
    if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
    return publicationDate != null ? publicationDate.equals(book.publicationDate) : book.publicationDate == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
    result = 31 * result + (authors != null ? authors.hashCode() : 0);
    result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Book{" +
      "id='" + id + '\'' +
      ", title='" + title + '\'' +
      ", subtitle='" + subtitle + '\'' +
      ", authors=" + authors +
      ", publicationDate=" + publicationDate +
      '}';
  }

}
