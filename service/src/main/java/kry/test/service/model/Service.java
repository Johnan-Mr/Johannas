package kry.test.service.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "entityService")
@Table(name = "services")
public class Service {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private int id;
    @NotNull
    @Column(name = "url", nullable = false)
    private String url;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;
    @Column(name = "status")
    private String status;

    private Service () {
    }

    public Service (String url, String name) {
        setUrl(url);
        setName(name);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getStatus(){ return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    @PrePersist
    public void creationDate() {
        Timestamp creation = new Timestamp(new Date().getTime());
        setCreationDate(creation);
        setLastUpdated(creation);
    }

    public void setCreationDate(Timestamp creationDate){
        this.creationDate = creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated){
        this.lastUpdated = lastUpdated;
    }
}
