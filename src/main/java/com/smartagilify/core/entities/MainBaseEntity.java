package com.smartagilify.core.entities;
import com.smartagilify.core.entities.base.HibernateStaticValues;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class MainBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = HibernateStaticValues.ID_SEQ_GENERATOR)
    protected Long id;
    protected String ip;
    @Version
    Long version;
}
