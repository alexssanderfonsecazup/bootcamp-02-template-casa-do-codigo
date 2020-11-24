package br.com.bootcamp.casaDoCodigo.compartilhado.validacao;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator  implements ConstraintValidator<ExistsId,Object> {

    @PersistenceContext
    private EntityManager manager;

    private String attributeName;
    private Class<?> clazz;


    @Override
    public void initialize(ExistsId params) {
        attributeName = params.fieldName();
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }
        String sql = "SELECT 1 FROM $class WHERE $attributeName =:value";
        sql = sql.replace("$class",clazz.getName())
                .replace("$attributeName",attributeName);

        Query query = manager.createQuery(sql);
        query.setParameter("value",value);
        int size = query.getResultList().size();

        boolean valid = size == 1;

        if(!valid){
            ((ConstraintValidatorContextImpl) context).addMessageParameter("id",value);
        }

        return valid;
    }
}
