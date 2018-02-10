package com.zhenmin.superboot.util;

import com.zhenmin.superboot.Exception.SuperBootException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.lang.reflect.Constructor;
import java.util.Set;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public abstract class ValidatorUtil {

    public static <E> void checkParam(E obj, Class mafExceptionClass, Integer respCode, Class... validateGroup) throws Exception {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(obj, validateGroup);
        for (ConstraintViolation<Object> cv : set) {
            Constructor constructor = mafExceptionClass.getConstructor(int.class, String.class);
            SuperBootException superBootException = (SuperBootException) constructor.newInstance(respCode, cv.getMessage());
            throw superBootException;
        }
    }

    public static <E> void checkParam(E obj, Class mafExceptionClass, Integer respCode) throws Exception {
        checkParam(obj, mafExceptionClass, respCode, Default.class);
    }

}
