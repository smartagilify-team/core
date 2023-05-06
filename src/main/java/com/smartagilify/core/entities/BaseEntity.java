package com.smartagilify.core.entities;

import com.smartagilify.core.entities.base.HibernateStaticValues;
import com.smartagilify.core.enumerations.EN_STATE;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity extends MainBaseEntity {
    @Column(name = HibernateStaticValues.CREATE_DATE, nullable = false, updatable = false)
    protected LocalDateTime createDate;
    @Column(name = HibernateStaticValues.CREATE_BY_ID, nullable = false, updatable = false)
    protected Long createById;
    @Column(name = HibernateStaticValues.UPDATE_DATE)
    protected LocalDateTime updateDate;
    @Column(name = HibernateStaticValues.UPDATE_BY_ID)
    protected Long updateById;
    @Enumerated(EnumType.STRING)
    @Column(name = HibernateStaticValues.STATE)
    protected EN_STATE state;
    @Column(name = HibernateStaticValues.ROW_LEVEL_ID)
    protected Integer rowLevelId;
    @Column(name = HibernateStaticValues.PRIORITY)
    protected Integer priority;
}
