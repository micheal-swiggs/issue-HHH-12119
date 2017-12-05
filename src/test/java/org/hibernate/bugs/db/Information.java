package org.hibernate.bugs.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Modelclass for Entity Information.
 * Stores some general entity information.
 *
 * @author tlang
 */
@Embeddable
public class Information implements Serializable {

    @Column(nullable = false, length = 50)
    private String createdBy;

    @Column(nullable = false, length = 50)
    private String modifiedBy;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime modificationDate;

    /**
     * Constructs a new information instance.
     */
    public Information() {
        creationDate = LocalDateTime.now();
        modificationDate = LocalDateTime.now();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }
}
