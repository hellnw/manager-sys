package com.sbs.common.validator;

import org.apache.commons.lang3.StringUtils;

import com.sbs.common.annotation.IsMobile;
import com.sbs.common.entity.Regexp;
import com.sbs.common.utils.SysUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = Regexp.MOBILE_REG;
                return SysUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
