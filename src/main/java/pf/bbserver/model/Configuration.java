package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@javax.persistence.Entity
@Getter @Setter
public class Configuration extends EntityWithID {

    @JsonManagedReference(value="order-config")
    @OneToOne (mappedBy = "configuration")
    OrderClass order;

    @NotEmpty @NotBlank @NotNull
    String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Europe/Berlin")
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp timestampCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Europe/Berlin")
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    Timestamp timestampLastTouched;

    String writeAccess;

    public String getWriteAccess() {
        return writeAccess;
    }

    public void setWriteAccess(String writeAccess) {
        this.writeAccess = writeAccess;
    }

    @ManyToOne @NotNull
    @JoinColumn
    User user;

    // Set is more efficient than list, Foreign Key On Delete Cascade not the default setting
    @ManyToMany
    @JoinTable(
            name = "CONFIGURATION_ARTICLES",
            joinColumns = @JoinColumn(name = "CONFIGURATION_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (CONFIGURATION_ID) references CONFIGURATION on delete CASCADE"), referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (ARTICLE_ID) references ARTICLE on delete CASCADE"), referencedColumnName = "id")
    )
    Set<Article> articles = new java.util.LinkedHashSet<>();


    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[created='%s', id=%d, benutzer='%s']", timestampCreated, id, user.name);
    }

}
