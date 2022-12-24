package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@javax.persistence.Entity
@Getter @Setter
public class Configuration extends EntityWithID {

    @JsonManagedReference(value="order-config")
    @OneToOne (mappedBy = "configuration")
    OrderClass order;

    @NotEmpty @NotBlank @NotNull
    String status;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "Europe/Berlin")
    @Column(nullable = false)
    Date dateCreated;

    @PrePersist
    protected void onCreate() {
        dateCreated = dateLastChanged = new Date();
    }

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "Europe/Berlin")
    @Column(nullable = false)
    Date dateLastChanged;

    @PreUpdate
    protected void onUpdate() {
        dateLastChanged = new Date();
    }


    @NotNull
    @Column
    boolean writeAccess = true;

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
        return String.format(this.getClass().getName() + "[datum='%s', id=%d, benutzer='%s']", dateLastChanged, id, user.name);
    }
}
