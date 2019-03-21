/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "books", catalog = "library", schema = "")
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b")
    , @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn")
    , @NamedQuery(name = "Books.findByBookName", query = "SELECT b FROM Books b WHERE b.bookName = :bookName")
    , @NamedQuery(name = "Books.findByAuthorName", query = "SELECT b FROM Books b WHERE b.authorName = :authorName")
    , @NamedQuery(name = "Books.findByOquantity", query = "SELECT b FROM Books b WHERE b.oquantity = :oquantity")
    , @NamedQuery(name = "Books.findByIquantity", query = "SELECT b FROM Books b WHERE b.iquantity = :iquantity")
    , @NamedQuery(name = "Books.findByIsReference", query = "SELECT b FROM Books b WHERE b.isReference = :isReference")})
public class Books implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISBN")
    private Long isbn;
    @Column(name = "Book_Name")
    private String bookName;
    @Column(name = "Author_Name")
    private String authorName;
    @Column(name = "oquantity")
    private Integer oquantity;
    @Column(name = "iquantity")
    private Integer iquantity;
    @Column(name = "isReference")
    private Integer isReference;

    public Books() {
    }

    public Books(Long isbn) {
        this.isbn = isbn;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        Long oldIsbn = this.isbn;
        this.isbn = isbn;
        changeSupport.firePropertyChange("isbn", oldIsbn, isbn);
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        String oldBookName = this.bookName;
        this.bookName = bookName;
        changeSupport.firePropertyChange("bookName", oldBookName, bookName);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        String oldAuthorName = this.authorName;
        this.authorName = authorName;
        changeSupport.firePropertyChange("authorName", oldAuthorName, authorName);
    }

    public Integer getOquantity() {
        return oquantity;
    }

    public void setOquantity(Integer oquantity) {
        Integer oldOquantity = this.oquantity;
        this.oquantity = oquantity;
        changeSupport.firePropertyChange("oquantity", oldOquantity, oquantity);
    }

    public Integer getIquantity() {
        return iquantity;
    }

    public void setIquantity(Integer iquantity) {
        Integer oldIquantity = this.iquantity;
        this.iquantity = iquantity;
        changeSupport.firePropertyChange("iquantity", oldIquantity, iquantity);
    }

    public Integer getIsReference() {
        return isReference;
    }

    public void setIsReference(Integer isReference) {
        Integer oldIsReference = this.isReference;
        this.isReference = isReference;
        changeSupport.firePropertyChange("isReference", oldIsReference, isReference);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "library.Books[ isbn=" + isbn + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
