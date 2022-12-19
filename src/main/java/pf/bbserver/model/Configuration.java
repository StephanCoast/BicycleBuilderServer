package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@javax.persistence.Entity
@Getter @Setter
public class Configuration extends EntityWithID {


    @NotEmpty @NotBlank @NotNull
    String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    @Column(nullable = false)
    Date dateCreated;

    @PrePersist
    protected void onCreate() {
        dateCreated = dateLastChanged = new Date();
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
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
    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            name = "CONFIGURATION_ARTICLES",
            joinColumns = @JoinColumn(name = "CONFIGURATION_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (CONFIGURATION_ID) references CONFIGURATION on delete CASCADE"), referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (ARTICLE_ID) references ARTICLE on delete CASCADE"), referencedColumnName = "id")
    )
    Set<Article> articles = new java.util.LinkedHashSet<>();

    public void setDateLastChanged(Date dateLastChanged) {
        this.dateLastChanged = dateLastChanged;
    }

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[datum='%s', id=%d, benutzer='%s']", dateLastChanged, id, user.name);
    }
}
