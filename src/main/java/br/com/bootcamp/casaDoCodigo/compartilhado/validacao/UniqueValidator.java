package br.com.bootcamp.casaDoCodigo.compartilhado.validacao;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private String attributeName;
    private Class<?>  clazz;

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(Unique params) {
        attributeName = params.fieldName();
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String sql = "SELECT 1 FROM $class WHERE $attributeName =:value";
        sql = sql.replace("$class",clazz.getName())
                .replace("$attributeName",attributeName);

        Query query = manager.createQuery(sql);
        query.setParameter("value",value);
        int size = query.getResultList().size();

        boolean valid = size == 0;

        if(!valid){
            ((ConstraintValidatorContextImpl) context).addMessageParameter("value",value);
        }

        return valid;

    }
}
